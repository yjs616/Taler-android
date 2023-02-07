package com.example.taler.api

import com.example.taler.dao.ReadBookDto
import com.example.taler.dao.UserCharacterDto
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Path

interface CharacterCustonService {
    @FormUrlEncoded
    @POST("user/character/{userId}")
    fun postCharacterByName(
        @Path("userId") userId:Long,
        @Field("hairStyle") hairStyle:Int,
        @Field("faceStyle") faceStyle:Int,
        @Field("clothesStyle") clothesStyle:Int,
        @Field("pantsStyle") pantsStyle:Int,
        @Field("shoesStyle") shoesStyle:Int
    ): Call<UserCharacterDto>
}