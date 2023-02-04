package com.example.taler.dao.Picture

import com.google.gson.annotations.SerializedName

data class PictureInquireResponseDto(
    @SerializedName("result")
    var pictureId: Int,
    var bookrommId:Int,
    var pictureurl:String,

    @SerializedName("message")
    var message:String,

    @SerializedName("code")
    var code: Int



    )
