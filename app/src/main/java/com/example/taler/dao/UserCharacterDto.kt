package com.example.taler.dao

import com.google.gson.annotations.SerializedName

data class UserCharacterDto(
    @SerializedName("userId") val userId:Long,
    @SerializedName("hairStyle") val hairStyle:Int,
    @SerializedName("faceStyle") val faceStyle:Int,
    @SerializedName("clothesStyle") val clothesStyle:Int,
    @SerializedName("pantsStyle") val pantsStyle:Int,
    @SerializedName("shoesStyle") val shoesStyle:Int
)