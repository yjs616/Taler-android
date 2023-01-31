package com.example.taler.api

import com.example.taler.dao.ReadBookDto
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface EnrollBookService {
    @FormUrlEncoded
    @POST("book/bookroom")
    fun postEnrolledBook(
        @Query("userId") userId:String,
        bookTitle:String,
        bookAuthor:String
    ): Call<ReadBookDto>
}
