package com.example.veyraboltmotorsports

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etUsername = findViewById<TextInputEditText>(R.id.etUsername)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnSignUp = findViewById<MaterialButton>(R.id.btnSignUp)
        val tvLogin = findViewById<TextView>(R.id.tvLogin)
        val btnBack = findViewById<ImageView>(R.id.btnBack)

        btnBack.setOnClickListener { finish() }

        tvLogin.setOnClickListener {
            // Send the user to the login screen. finish() pops this activity
            // so they don't end up with Register on the back stack.
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        btnSignUp.setOnClickListener {
            val username = etUsername.text?.toString()?.trim().orEmpty()
            val email = etEmail.text?.toString()?.trim().orEmpty()
            val password = etPassword.text?.toString().orEmpty()

            when {
                username.isEmpty() -> {
                    etUsername.error = "Required"
                    toast("Please enter a username")
                }
                email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    etEmail.error = "Invalid email"
                    toast("Please enter a valid email")
                }
                password.length < 6 -> {
                    etPassword.error = "Min 6 characters"
                    toast("Password must be at least 6 characters")
                }
                UserStore.isUsernameTaken(this, username) -> {
                    etUsername.error = "Already taken"
                    toast("Username is already taken")
                }
                else -> {
                    UserStore.register(this, username, email, password)
                    toast("Account created. Please log in.")
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                }
            }
        }
    }

    private fun toast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
