package com.example.taler.dao.Word

import com.google.gson.annotations.SerializedName

data class WordRegisterRequestDto(
    @SerializedName("wordPictureUrl")
    var wordId :Int,
    var bookrommId:Int,
    var mainId:Int,
    var wordPictureUrl:String,
    var wordMain:Int,
    var wordVoiceUrl:String,
    var wordText:String,


    )

