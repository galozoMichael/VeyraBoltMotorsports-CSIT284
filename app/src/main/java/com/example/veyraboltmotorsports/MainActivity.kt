package com.example.veyraboltmotorsports

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // Live Button Navigation
        val btnLive = findViewById<android.view.View>(R.id.btnLive)
        btnLive.setOnClickListener {
            val intent = android.content.Intent(this, LiveTabActivity::class.java)
            startActivity(intent)
        }

        // Bottom Navigation
        val bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> true // Already on Home
                R.id.nav_shop -> {
                    startActivity(android.content.Intent(this, ItemShopActivity::class.java))
                    true
                }
                R.id.nav_tickets -> {
                    startActivity(android.content.Intent(this, TicketsActivity::class.java))
                    true
                }
                R.id.nav_profile -> {
                    startActivity(android.content.Intent(this, EventCalendarActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}