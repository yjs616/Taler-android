package com.example.taler.api

import com.example.taler.dao.ReadBookDto
import retrofit2.Call
import retrofit2.http.*

interface LookUpMindService {
    @GET("book/mind")
    fun postBookById(
        @Query("bookroomId") bookroomId:String
    ): Call<ReadBookDto>
}