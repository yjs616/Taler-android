package com.example.taler.dao

import com.google.gson.annotations.SerializedName

data class EnrollBookDto(
    @SerializedName("userId") val userId:String,
    @SerializedName("bookTitle") val bookTitle:String,
    @SerializedName("bookAuthor") val bookAuthor:String
)
