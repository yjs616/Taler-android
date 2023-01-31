package com.example.taler.dao

import com.google.gson.annotations.SerializedName

data class ReadBookDto (
    @SerializedName("userId") val userId:Long,
    @SerializedName("bookId1") val bookId1:Long,
    @SerializedName("bookId2") val bookId2:Long,
    @SerializedName("bookId3") val bookId3:Long,
    @SerializedName("bookId4") val bookId4:Long,
    @SerializedName("bookId5") val bookId5:Long
        )