package com.example.chromecastvideoplayer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class VideoDetails : AppCompatActivity() {
    private var thumbnailImageUrl: String? = null
    private var videoViewUrl: String? = null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_details)

        val tvDescription = findViewById<TextView>(R.id.tvDescription)
        val ivPlayDetails = findViewById<ImageView>(R.id.ivThumbnaiDetails)

        val bundle = intent.extras
        if (bundle != null) {

            tvDescription.text = "${bundle.getString("description")}"
            thumbnailImageUrl = "${bundle.getString("imageUrl")}"
            videoViewUrl = "${bundle.getString("videoUrl")}"

        }
        Glide.with(this)
            .load("https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/images/$thumbnailImageUrl")
            .into(ivPlayDetails)

        ivPlayDetails.setOnClickListener {
            val intent = Intent(this, VideoPlayer::class.java)
            intent.putExtras(bundle!!)
            startActivity(intent)
        }
    }
}