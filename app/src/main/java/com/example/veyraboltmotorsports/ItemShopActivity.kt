package com.example.veyraboltmotorsports

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class ItemShopActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_itemshop)

        val bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNav)

        // Bottom Navigation
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(android.content.Intent(this, MainActivity::class.java))
                    finish() // Closes shop so it doesn't stack over home
                    true
                }
                R.id.nav_shop -> true // Already on Shop
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