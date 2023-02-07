package com.example.taler.api

import com.example.taler.dao.EnrollBookDto
import com.example.taler.dao.ReadBookDto
import retrofit2.Call
import retrofit2.http.*

interface EnrollBookService {
    @FormUrlEncoded
    @POST("book/bookroom/{userId}")
    fun postEnrolledBook(
        @Path ("userId") userId:String,
        @Field("bookTitle") bookTitle:String,
        @Field("bookTitle") bookAuthor:String
    ): Call<EnrollBookDto>
}
