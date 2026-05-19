package com.example.veyraboltmotorsports

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class CheckoutActivity : AppCompatActivity() {

    /** Flat shipping fee while the cart has at least one item. */
    private val shippingCents = 25000L // ₱250.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }

        findViewById<MaterialButton>(R.id.btnPay).setOnClickListener {
            showPaymentSuccessDialog()
        }
    }

    override fun onResume() {
        super.onResume()
        // Re-render on resume so changes made elsewhere (e.g. coming back
        // from ItemInfoActivity after tapping Add to Cart) are reflected.
        renderCart()
    }

    private fun renderCart() {
        val container = findViewById<LinearLayout>(R.id.cartItemsContainer)
        val empty = findViewById<TextView>(R.id.tvCartEmpty)

        val items = CartStore.items(this)
        container.removeAllViews()

        if (items.isEmpty()) {
            empty.visibility = View.VISIBLE
        } else {
            empty.visibility = View.GONE
            val inflater = LayoutInflater.from(this)
            for (item in items) {
                val row = inflater.inflate(R.layout.item_cart_row, container, false)
                row.findViewById<TextView>(R.id.tvCartItemName).text = item.name
                row.findViewById<TextView>(R.id.tvCartItemDetails).text = item.details
                row.findViewById<TextView>(R.id.tvCartItemPrice).text =
                    formatPeso(item.priceCents)

                val img = row.findViewById<ImageView>(R.id.ivCartItemImage)
                @Suppress("DiscouragedApi")
                val resId = resources.getIdentifier(
                    item.imageResName, "drawable", packageName
                )
                if (resId != 0) img.setImageResource(resId)

                row.findViewById<ImageButton>(R.id.btnRemoveCartItem).setOnClickListener {
                    CartStore.remove(this, item.id)
                    renderCart()
                }

                container.addView(row)
            }
        }

        val subtotal = items.sumOf { it.priceCents }
        val shipping = if (items.isEmpty()) 0L else shippingCents
        val total = subtotal + shipping

        findViewById<TextView>(R.id.tvSubtotalAmount).text = formatPeso(subtotal)
        findViewById<TextView>(R.id.tvShippingAmount).text = formatPeso(shipping)
        findViewById<TextView>(R.id.tvTotalAmount).text = formatPeso(total)

        val btnPay = findViewById<MaterialButton>(R.id.btnPay)
        btnPay.text = "Pay ${formatPeso(total)}"
        btnPay.isEnabled = items.isNotEmpty()
    }

    private fun formatPeso(cents: Long): String {
        val whole = cents / 100
        val fraction = cents % 100
        return "₱ %,d.%02d".format(whole, fraction)
    }

    private fun showPaymentSuccessDialog() {
        AlertDialog.Builder(this)
            .setTitle("Payment Successful")
            .setMessage("Your order has been placed.")
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                // Clear the cart so the next checkout starts fresh, then
                // bring the existing ItemShopActivity to the front (or open
                // a new one if it isn't on the back stack).
                CartStore.clear(this)
                val intent = Intent(this, ItemShopActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or
                        Intent.FLAG_ACTIVITY_SINGLE_TOP
                }
                startActivity(intent)
                finish()
            }
            .show()
    }
}
