package com.example.taler.api

import com.example.taler.dao.Picture.PictureInquireResponseDto
import com.example.taler.dao.Picture.PictureModifyRequestDto
import com.example.taler.dao.Picture.PictureRegisterResponseDto
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface BookRoomPictureApi {

    //1. Picture 탭 조회 - 응답 dto 존재해야 함
    @GET("/book/picture/{bookroomId}")
    fun pictureInquire(@Path("bookroomId") bookroomId: Int
    ): Call<PictureInquireResponseDto>

    //2. picture 등록 - 요청, 응답 dto 각각 존재해야 함
    @POST("book/picture/{bookroomId}")
    fun pictureRegister(@Query("bookroomId") bookroomId: Int
    ):Call<PictureRegisterResponseDto>    //여기는 응답 dto가 들어가는건가..?

    //3. pictrue 수정 - 요청 dto
    @PUT("/book/picture/{bookroomId}")
    fun pictureModify(@Query("bookroomId") bookroomId: Int
    ):Call<PictureModifyRequestDto>

    //4. picture 삭제
    @DELETE("/book/picture/{bookroomId}")
    fun pictureDelete(@Path("bookroomId") bookroomId: Int
    ):Call<Unit>   //아무것도 반환X


}