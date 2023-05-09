package com.kiki.storyapp.Detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.kiki.storyapp.R
import com.kiki.storyapp.Response.ListStory
import com.kiki.storyapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = getString(R.string.list_story_page)

        val data = intent.getParcelableExtra<ListStory>(EXTRA_STORY)
        val empty = ListStory("", "", "", "", 0.0, "", 0.0)
        DetailStory(data ?: empty)
    }

    private fun DetailStory(listStory: ListStory){
        Glide.with(this@DetailActivity)
            .load(listStory.photoUrl)
            .into(binding.ivPhoto)

        binding.tvNameUser.text = listStory.name
        binding.tvDesc.text = listStory.description
    }

    companion object {
        const val EXTRA_STORY = "EXTRA_STORY"
    }
}