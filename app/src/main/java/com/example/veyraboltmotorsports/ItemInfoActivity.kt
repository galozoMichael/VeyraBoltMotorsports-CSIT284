package com.example.veyraboltmotorsports

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ItemInfoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item_info)

        // Add-to-cart wiring. Item fields mirror the static layout values
        // for now since every shop tile launches this same screen. Once the
        // shop becomes data-driven, pass these values via Intent extras and
        // read them here instead.
        findViewById<Button>(R.id.btnAddToCart).setOnClickListener {
            CartStore.add(
                ctx = this,
                name = "Veyra Bolt Gloves",
                priceCents = 45000L, // ₱450.00
                imageResName = "veyraboltmotorsportgloves",
                details = "Size: M, Qty: 1"
            )
            Toast.makeText(this, "Added to cart", Toast.LENGTH_SHORT).show()
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_shop
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_shop -> {
                    finish() // Closes Item Info and returns to the main Shop screen
                    true
                }
                R.id.nav_tickets -> {
                    startActivity(Intent(this, TicketsActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_profile -> {
                    startActivity(Intent(this, EventCalendarActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}
