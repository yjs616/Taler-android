package com.example.taler.dao.Word

import com.google.gson.annotations.SerializedName

data class WordModifyrRequestDto(
    @SerializedName("result")
    var wordId :Int,
    var bookrommId:Int,
    var mainId:Int,
    var wordPictureUrl:String,
    var wordMain:Int,
    var wordVoiceUrl:String,
    var wordText:String,

    @SerializedName("message")
    var message:String,

    @SerializedName("code")
    var code: Int

    )
