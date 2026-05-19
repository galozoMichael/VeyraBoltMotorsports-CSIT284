package com.example.veyraboltmotorsports

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

class LiveAuctionActivity : AppCompatActivity() {

    private lateinit var videoView: VideoView
    private lateinit var btnPlay: ImageView
    private var hasStartedOnce = false

    private lateinit var chatContainer: LinearLayout
    private lateinit var chatScroll: ScrollView
    private lateinit var etChatInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_auction)

        // ----- Video player -----
        videoView = findViewById(R.id.videoView)
        btnPlay = findViewById(R.id.btnPlay)

        videoView.setVideoURI(
            Uri.parse("android.resource://$packageName/${R.raw.carshowvideo}")
        )
        videoView.setOnPreparedListener { mp -> mp.isLooping = true }
        videoView.setOnErrorListener { _, _, _ ->
            btnPlay.visibility = View.VISIBLE
            true
        }
        btnPlay.setOnClickListener {
            btnPlay.visibility = View.GONE
            videoView.start()
            hasStartedOnce = true
        }

        // ----- Live chat -----
        chatContainer = findViewById(R.id.chatContainer)
        chatScroll = findViewById(R.id.chatScroll)
        etChatInput = findViewById(R.id.etChatInput)
        val btnSend = findViewById<ImageView>(R.id.btnSendChat)

        btnSend.setOnClickListener { sendChatMessage() }
        etChatInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendChatMessage()
                true
            } else false
        }
    }

    private fun sendChatMessage() {
        val messageText = etChatInput.text.toString().trim()
        if (messageText.isEmpty()) return

        val item = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply { bottomMargin = dp(6) }
        }
        val nameTv = TextView(this).apply {
            text = "You"
            setTextColor(0xFFE53935.toInt()) // red highlight so own messages stand out
            textSize = 10f
            setTypeface(typeface, Typeface.BOLD)
        }
        val msgTv = TextView(this).apply {
            text = messageText
            setTextColor(0xFFDDDDDD.toInt())
            textSize = 11f
        }
        item.addView(nameTv)
        item.addView(msgTv)
        chatContainer.addView(item)

        etChatInput.setText("")

        // Scroll to the new message after layout completes.
        chatScroll.post { chatScroll.fullScroll(View.FOCUS_DOWN) }
    }

    private fun dp(value: Int): Int =
        (value * resources.displayMetrics.density).toInt()

    override fun onResume() {
        super.onResume()
        if (hasStartedOnce && !videoView.isPlaying) {
            videoView.start()
        }
    }

    override fun onPause() {
        super.onPause()
        if (videoView.isPlaying) {
            videoView.pause()
        }
    }
}
