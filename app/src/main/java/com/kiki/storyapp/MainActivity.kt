package com.kiki.storyapp

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kiki.storyapp.Model.userPreference
import com.kiki.storyapp.Response.ListStory
import com.kiki.storyapp.databinding.ActivityMainBinding
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "Setting")

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var mainViewModel : MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(userPreference.getInstance(dataStore))
        )[MainViewModel::class.java]
        mainViewModel.getStory("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiJ1c2VyLUNqTDRwY1RTYXA2WlJpaVQiLCJpYXQiOjE2ODI3Mjk0NjB9.fUrDotNVIvzDfXJoaYcLFq8r-OrgNMy6YMk7RlMP0O8")

        mainViewModel.listStory.observe(this) {
            if (it.isEmpty()) {
                val alertDialogBuilder = AlertDialog.Builder(this)
                alertDialogBuilder.setTitle(getString(R.string.app_name))

                alertDialogBuilder
                    .setMessage(getString(R.string.empty_list))
                    .setCancelable(false)
                    .setPositiveButton(getString(R.string.yes)) { _, _ ->

                        finish()
                    }
                    .setNegativeButton(getString(R.string.No)) { dialog, _ -> dialog.cancel() }
                val alertDialog = alertDialogBuilder.create()
                alertDialog.show()
            }
            setStoryItem(it)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvStory.layoutManager = layoutManager

        binding.btnAddStory.setOnClickListener{
            startActivity(Intent(this@MainActivity, AddStoryActivity::class.java))
        }
    }

    private fun setStoryItem(story: List<ListStory>?) {
        val adapter = story?.let { StoryAdapter(it) }
        adapter?.setOnItemClickCallback(object : StoryAdapter.OnItemClickCallback {
            override fun onItemClicked(listStory: ListStory) {
                val i = Intent(this@MainActivity, DetailActivity::class.java)
                i.putExtra(DetailActivity.EXTRA_STORY, listStory)
                startActivity(i)
            }
        })
        binding.rvStory.adapter = adapter
    }
}