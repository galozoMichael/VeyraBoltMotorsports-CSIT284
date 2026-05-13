package com.example.veyraboltmotorsports

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ItemShopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itemshop)


        // image cart -> checkout
        val imgCart = findViewById<android.widget.ImageView>(R.id.imgCart)
        imgCart.setOnClickListener {
            val intent = android.content.Intent(this, CheckoutActivity::class.java)
            startActivity(intent)
        }
        
        // Find all the Apparel items
        val itemApparel1 = findViewById<android.widget.LinearLayout>(R.id.itemApparel1)
        val itemApparel2 = findViewById<android.widget.LinearLayout>(R.id.itemApparel2)
        val itemApparel3 = findViewById<android.widget.LinearLayout>(R.id.itemApparel3)

        // Find all the Exclusive items
        val itemExclusive1 = findViewById<android.widget.LinearLayout>(R.id.itemExclusive1)
        val itemExclusive2 = findViewById<android.widget.LinearLayout>(R.id.itemExclusive2)
        val itemExclusive3 = findViewById<android.widget.LinearLayout>(R.id.itemExclusive3)

        // Set up the click listeners to launch ItemInfoActivity
        val launchItemInfo = { _: android.view.View ->
            startActivity(android.content.Intent(this, ItemInfoActivity::class.java))
        }
        itemApparel1.setOnClickListener(launchItemInfo)
        itemApparel2.setOnClickListener(launchItemInfo)
        itemApparel3.setOnClickListener(launchItemInfo)
        itemExclusive1.setOnClickListener(launchItemInfo)
        itemExclusive2.setOnClickListener(launchItemInfo)
        itemExclusive3.setOnClickListener(launchItemInfo)

        // Bottom Navigation
        val bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_shop
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(android.content.Intent(this, MainActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_shop -> true
                R.id.nav_tickets -> {
                    startActivity(android.content.Intent(this, TicketsActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_profile -> {
                    startActivity(android.content.Intent(this, EventCalendarActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }
}