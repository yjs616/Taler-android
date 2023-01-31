package com.example.taler.api

import com.example.taler.dao.ReadBookDto
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query


interface ReadBookService {
    @FormUrlEncoded
    @POST("book/readbook")
    fun postBookById(
        @Field("userId") userId: Long,
        @Field("bookId1") bookId1: Long,
        @Field("bookId2") bookId2: Long,
        @Field("bookId3") bookId3: Long,
        @Field("bookId4") bookId4: Long,
        @Field("bookId5") bookId5: Long
    ): Call<ReadBookDto>
}