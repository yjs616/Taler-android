package com.example.taler.api

import com.example.taler.dao.ReadBookDto
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface EnrollMindTabService {
    @FormUrlEncoded
    @POST("book/mind")
    fun postEnrollMindTab(
        @Query("bookroomId") bookroomId:String,
        wordPictureUrl:String,
        wordVoiceUrl:String,
        wordText:String,
        priority:String
    ): Call<ReadBookDto>
}