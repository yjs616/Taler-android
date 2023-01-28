package com.example.taler.dao

import com.example.taler.model.Book
import com.google.gson.annotations.SerializedName

data class BestSellerDto (
    @SerializedName("title") val title: String,
    @SerializedName("item") val books: List<Book>,

    )
