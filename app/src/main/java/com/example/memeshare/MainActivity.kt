package com.example.memeshare

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

class MainActivity : AppCompatActivity() {

    var currentImageUrl :String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadMeme()
    }

    @SuppressLint("SetTextI18n")
    fun loadMeme(){

        val progressBar : ProgressBar = findViewById(R.id.ProgressCircle)
        progressBar.visibility = View.VISIBLE

        val memeImage : ImageView = findViewById(R.id.MemeImage)

        val textView = findViewById<TextView>(R.id.text)
// ...

// Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        currentImageUrl = "https://meme-api.herokuapp.com/gimme"

// Request a string response from the provided URL.
        val jsonReq = JsonObjectRequest(
                Request.Method.GET , currentImageUrl , null ,
                { response ->
                    val url = response.getString("url")

                    Glide.with(this).load(url).listener(object : RequestListener<Drawable>{

                        override fun onLoadFailed(
                            e: GlideException?,
                            model: Any?,
                            target: Target<Drawable>?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            return false
                        }

                        override fun onResourceReady(
                            resource: Drawable?,
                            model: Any?,
                            target: Target<Drawable>?,
                            dataSource: DataSource?,
                            isFirstResource: Boolean
                        ): Boolean {
                            progressBar.visibility = View.GONE
                            return false
                        }

                    }).into(memeImage)
                },
                {

                }
        )



// Add the request to the RequestQueue.
        queue.add(jsonReq)
    }

    fun nextMeme(view: View) {
        loadMeme()
    }




}