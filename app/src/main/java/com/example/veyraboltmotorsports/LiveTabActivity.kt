package com.example.veyraboltmotorsports

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LiveTabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livetab)
        val bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNav)

        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(android.content.Intent(this, MainActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_shop -> {
                    startActivity(android.content.Intent(this, ItemShopActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_tickets -> {
                    startActivity(android.content.Intent(this, TicketsActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_profile -> true
                else -> false
            }
        }
    }
}