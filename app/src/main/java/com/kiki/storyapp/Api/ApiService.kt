package com.kiki.storyapp.Api

import com.kiki.storyapp.Response.LoginResponse
import com.kiki.storyapp.Response.RegisterResponse
import com.kiki.storyapp.Response.StoryResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @FormUrlEncoded
    @POST("login")
    fun login(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<LoginResponse>

    @FormUrlEncoded
    @POST("register")
    fun register(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<RegisterResponse>

    @GET("stories")
    fun getStory(
        @Header("Authorization") authorization: String
    ): Call<StoryResponse>
}