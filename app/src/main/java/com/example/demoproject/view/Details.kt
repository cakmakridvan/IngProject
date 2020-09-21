package com.example.demoproject.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.demoproject.databinding.ActivityDetailsBinding


class Details : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtOwner.text = getIntent().getStringExtra("Owner")
        binding.txtStars.text = getIntent().getIntExtra("Stars", 13).toString()
        binding.txtOpenIssue.text = getIntent().getIntExtra("OpenIssue", 13).toString()

        binding.txtName.text = getIntent().getStringExtra("Name")
        binding.btnBack.setOnClickListener {
            finish()
        }

        Glide.with(this)
            .asBitmap()
            .load(getIntent().getStringExtra("Url"))
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    binding.imageView.setImageBitmap(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }
}