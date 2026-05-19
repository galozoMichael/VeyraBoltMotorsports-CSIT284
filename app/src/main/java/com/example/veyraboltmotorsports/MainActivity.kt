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

        // Profile Navigation
        val imgProfile = findViewById<android.widget.ImageView>(R.id.imgProfile)
        imgProfile.setOnClickListener {
            startActivity(android.content.Intent(this, LoginActivity::class.java))
        }

        // Live Button Navigation
        val btnLive = findViewById<android.view.View>(R.id.btnLive)
        btnLive.setOnClickListener {
            startActivity(android.content.Intent(this, LiveTabActivity::class.java))
        }

        // Ticket Button Navigation
        val btnTicket = findViewById<android.view.View>(R.id.btnTicket)
        btnTicket.setOnClickListener {
            startActivity(android.content.Intent(this, TicketsActivity::class.java))
        }

        // Auction Button Navigation
        val btnGear = findViewById<android.view.View>(R.id.btnGear)
        btnGear.setOnClickListener {
            startActivity(android.content.Intent(this, LiveAuctionActivity::class.java))
        }

        // Bottom Navigation
        val bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_home
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