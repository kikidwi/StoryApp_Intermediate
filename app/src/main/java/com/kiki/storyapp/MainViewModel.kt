package com.kiki.storyapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kiki.storyapp.Api.ApiConfig
import com.kiki.storyapp.Model.userPreference
import com.kiki.storyapp.Response.ListStory
import com.kiki.storyapp.Response.StoryResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val pref: userPreference) : ViewModel(){
    private val _listStory = MutableLiveData<List<ListStory>>()
    val listStory: LiveData<List<ListStory>> = _listStory

    fun getStory(token: String) {
        ApiConfig.getApiService()
            .getStory("Bearer $token")
            .enqueue(object : Callback<StoryResponse> {
                override fun onResponse(
                    call: Call<StoryResponse>,
                    response: Response<StoryResponse>
                ) {
                    if (response.isSuccessful){
                        val responseBody = response.body()
                        if (responseBody != null) {
                            _listStory.value = responseBody.listStory
                        }
                    }
                }

                override fun onFailure(call: Call<StoryResponse>, t: Throwable) {
                    Log.d("Faillure" , t.message.toString())
                }

            })
    }

}