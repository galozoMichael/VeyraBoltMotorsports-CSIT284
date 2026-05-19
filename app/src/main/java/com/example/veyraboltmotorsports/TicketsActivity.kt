package com.example.veyraboltmotorsports

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class TicketsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)

        findViewById<Button>(R.id.btnViewQR).setOnClickListener { showQRDialog() }

        val bottomNav = findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNav)
        bottomNav.selectedItemId = R.id.nav_tickets
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
                R.id.nav_tickets -> true
                R.id.nav_profile -> {
                    startActivity(android.content.Intent(this, EventCalendarActivity::class.java))
                    finish()
                    true
                }
                else -> false
            }
        }
    }

    private fun showQRDialog() {
        val size = 21
        val scale = 16
        val bmp = Bitmap.createBitmap(size * scale, size * scale, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bmp)
        val paint = Paint()
        canvas.drawColor(Color.WHITE)
        paint.color = Color.BLACK
        for (x in 0 until size) {
            for (y in 0 until size) {
                if (Random.nextBoolean()) {
                    canvas.drawRect(
                        (x * scale).toFloat(), (y * scale).toFloat(),
                        ((x + 1) * scale).toFloat(), ((y + 1) * scale).toFloat(), paint
                    )
                }
            }
        }
        val imageView = ImageView(this).apply {
            setImageBitmap(bmp)
            setPadding(48, 48, 48, 48)
        }
        AlertDialog.Builder(this)
            .setTitle("Your QR Code")
            .setView(imageView)
            .setPositiveButton("Close", null)
            .show()
    }
}