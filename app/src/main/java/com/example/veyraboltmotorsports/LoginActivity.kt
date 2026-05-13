package com.example.veyraboltmotorsports

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val tvRegister = findViewById<android.widget.TextView>(R.id.tvRegister)
        tvRegister.setOnClickListener {
            startActivity(android.content.Intent(this, RegisterActivity::class.java))
        }

        val btnBack = findViewById<android.widget.ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish() // Closes this screen and goes back
        }
    }
}