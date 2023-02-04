package com.example.taler.dao.Picture

import com.google.gson.annotations.SerializedName

data class PictureRegisterRequestDto(
    @SerializedName("pictureUrl")
    var pictureUrl: String,
)
