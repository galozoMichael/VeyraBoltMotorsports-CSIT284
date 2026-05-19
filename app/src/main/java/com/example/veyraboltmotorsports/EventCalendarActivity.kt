package com.example.veyraboltmotorsports

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class EventCalendarActivity : AppCompatActivity() {
    private var registeredCount = 142

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_calendar)

        val tvRegisteredCount = findViewById<TextView>(R.id.tvRegisteredCount)
        val btnRegisterNow = findViewById<Button>(R.id.btnRegisterNow)
        val btnJoinStream = findViewById<Button>(R.id.btnJoinStream)

        btnRegisterNow.setOnClickListener {
            registeredCount++
            tvRegisteredCount.text = "$registeredCount registered"
            AlertDialog.Builder(this)
                .setMessage("Successfully registered!")
                .setPositiveButton("OK", null)
                .show()
        }

        btnJoinStream.setOnClickListener {
            startActivity(Intent(this, LiveTabActivity::class.java))
        }

        val bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_profile

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