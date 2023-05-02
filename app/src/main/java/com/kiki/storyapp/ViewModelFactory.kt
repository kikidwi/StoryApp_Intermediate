package com.kiki.storyapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kiki.storyapp.Model.userPreference

class ViewModelFactory(private val pref: userPreference) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(pref) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}