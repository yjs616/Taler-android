package com.example.taler

import android.content.Context
import android.media.MediaPlayer

//bgm 조절
public class MusicManager {

    companion object{
        lateinit var mp: MediaPlayer

        public fun SoundPlayer(context:Context,rawId:Int) {
            mp=MediaPlayer.create(context,rawId)
            mp.isLooping = true  //set looping
            mp.setVolume(0.5f,0.5f)

            mp.start()
        }
    }



}