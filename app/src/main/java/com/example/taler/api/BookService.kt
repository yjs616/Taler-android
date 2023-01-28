package com.example.taler.api

import com.example.taler.dao.BestSellerDto
import com.example.taler.dao.SearchBookDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {
    // get : 데이터 요청 시 반환 http
    // post : http body에 넣어 전달

    // 책 검색.
    @GET("/api/search.api?output=json")
    fun getBooksByName(
        @Query("key") apiKey: String,
        @Query("query") kweyWord: String,
        @Query("start") start: Int,
        @Query("maxResults")maxRusults:Int,
        @Query("sort") sort:String
    ): Call<SearchBookDto>

    // best seller 받아오기
    // 유아 : 109, 아동 : 110, 외국 어린이 : 201

    @GET("/api/bestSeller.api?output=json&categoryId=109")
    fun getBestSellerBooks(
        @Query("key") apiKey: String,
    ): Call<BestSellerDto>

}