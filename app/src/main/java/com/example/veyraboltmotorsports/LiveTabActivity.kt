package com.example.veyraboltmotorsports

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class LiveTabActivity : AppCompatActivity() {

    private lateinit var videoLive: VideoView
    private lateinit var imgPlaceholder: ImageView
    private lateinit var btnPlay: ImageView
    private var hasStartedOnce = false

    private lateinit var chatContainer: LinearLayout
    private lateinit var chatScroll: ScrollView
    private lateinit var etChatInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_livetab)

        // ----- Back button -----
        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }

        // ----- Video player -----
        videoLive = findViewById(R.id.videoLive)
        imgPlaceholder = findViewById(R.id.imgVideoPlaceholder)
        btnPlay = findViewById(R.id.btnPlayLive)

        videoLive.setVideoURI(
            Uri.parse("android.resource://$packageName/${R.raw.carshowvideo}")
        )
        videoLive.visibility = View.VISIBLE
        imgPlaceholder.visibility = View.GONE
        btnPlay.visibility = View.GONE
        videoLive.setOnPreparedListener { mp ->
            mp.isLooping = true
            videoLive.start()
            hasStartedOnce = true
        }
        videoLive.setOnErrorListener { _, _, _ ->
            videoLive.visibility = View.GONE
            imgPlaceholder.visibility = View.VISIBLE
            btnPlay.visibility = View.VISIBLE
            true
        }
        btnPlay.setOnClickListener { startPlayback() }

        // ----- Live chat -----
        chatContainer = findViewById(R.id.chatContainerLive)
        chatScroll = findViewById(R.id.chatScrollLive)
        etChatInput = findViewById(R.id.etChatInputLive)
        val btnSend = findViewById<ImageView>(R.id.btnSendChatLive)

        btnSend.setOnClickListener { sendChatMessage() }
        etChatInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendChatMessage()
                true
            } else false
        }

        // ----- Bottom nav -----
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_shop -> {
                    startActivity(Intent(this, ItemShopActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_tickets -> {
                    startActivity(Intent(this, TicketsActivity::class.java))
                    finish()
                    true
                }
                R.id.nav_profile -> true
                else -> false
            }
        }
    }

    private fun startPlayback() {
        videoLive.visibility = View.VISIBLE
        btnPlay.visibility = View.GONE
        imgPlaceholder.visibility = View.GONE
        videoLive.start()
        hasStartedOnce = true
    }

    private fun sendChatMessage() {
        val text = etChatInput.text.toString().trim()
        if (text.isEmpty()) return

        // New row matches the existing icon + name + message style of this screen.
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = dp(12) }
        }

        val icon = ImageView(this).apply {
            layoutParams = LinearLayout.LayoutParams(dp(20), dp(20)).apply {
                topMargin = dp(2)
            }
            setImageResource(android.R.drawable.ic_menu_send)
            imageTintList = android.content.res.ColorStateList.valueOf(0xFFE53935.toInt())
        }

        val column = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { marginStart = dp(8) }
        }
        val nameTv = TextView(this).apply {
            this.text = "You"
            setTextColor(0xFFE53935.toInt()) // red so own messages stand out
            textSize = 10f
            setTypeface(typeface, Typeface.BOLD)
        }
        val msgTv = TextView(this).apply {
            this.text = text
            setTextColor(0xFFFFFFFF.toInt())
            textSize = 12f
            val lp = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { topMargin = dp(2) }
            layoutParams = lp
        }
        column.addView(nameTv)
        column.addView(msgTv)

        row.addView(icon)
        row.addView(column)
        chatContainer.addView(row)

        etChatInput.setText("")
        chatScroll.post { chatScroll.fullScroll(View.FOCUS_DOWN) }
    }

    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density).toInt()

    override fun onResume() {
        super.onResume()
        if (hasStartedOnce && !videoLive.isPlaying) {
            videoLive.start()
        }
    }

    override fun onPause() {
        super.onPause()
        if (videoLive.isPlaying) {
            videoLive.pause()
        }
    }
}
