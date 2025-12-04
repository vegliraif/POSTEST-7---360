package com.vegli.post_7_360

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.vegli.post_7_360.databinding.ActivityBookDetailBinding

class BookDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarDetail)
        supportActionBar?.title = "Book Detail"

        val title = intent.getStringExtra("title")
        val date = intent.getStringExtra("date")
        val cover = intent.getStringExtra("cover")
        val description = intent.getStringExtra("description")

        binding.txtTitle.text = title
        binding.txtDate.text = date
        binding.txtDescription.text = description

        Glide.with(this)
            .load(cover)
            .into(binding.imgCover)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }
}
