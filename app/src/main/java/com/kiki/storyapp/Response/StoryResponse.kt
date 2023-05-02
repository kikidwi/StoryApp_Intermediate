package com.kiki.storyapp.Response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

data class StoryResponse(
    val listStory: List<ListStory>,
    val error: Boolean,
    val message: String
)

@Parcelize
data class ListStory(
    val photoUrl: String,
    val createdAt: String,
    val name: String,
    val description: String,
    val lon: Double,
    val id: String,
    val lat: Double
) : Parcelable