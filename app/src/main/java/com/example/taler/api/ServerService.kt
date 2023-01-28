package com.example.taler.api

import retrofit2.http.POST

interface ServerService {

    @POST("book/readbook/")
    fun postBook(){
        //@Query("user_id") userId : Int
    }
}