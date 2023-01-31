package com.example.taler.api

import com.example.taler.dao.ReadBookDto
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface RecommendBookService {
    @POST("book/recommend")
    fun getRecommendBookById(
    ): Call<ReadBookDto>
}