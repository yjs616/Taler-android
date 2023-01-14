package com.example.taler

import android.content.Intent
import android.media.MediaPlayer
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.Voice
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import com.example.taler.databinding.ActivityTutorial1Binding
import java.util.*


class Tutorial1Activity : AppCompatActivity(), TextToSpeech.OnInitListener {
    lateinit var viewBinding: ActivityTutorial1Binding

    //tts
    private lateinit var tts: TextToSpeech
    private lateinit var voice:Voice         //추후에..

    //soundPool
    private lateinit var mSoundPool: SoundPool
    private var mSoundId: Int?=null
    private var mStreamId: Int?=null

    //mediaPlayer
    private lateinit var mp:MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTutorial1Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        //tts
        tts= TextToSpeech(this,this)

        setSound()

        //mediaPlayer
        mp=MediaPlayer.create(this,R.raw.bgm)
        mp.setVolume(0.5f,0.5f)
        mp.start()

    }

    private fun setSound() {
        mSoundPool = SoundPool.Builder().setMaxStreams(1).build()
        //mSoundPool = SoundPool(4,AudioManager.STREAM_MUSIC,100)
        mSoundId=mSoundPool.load(this,R.raw.jump,0)

    }
    private fun playSound() {
        mStreamId=mSoundPool.play(mSoundId!!,2f,2f,0,0,1f)

    }

    /*private fun stopSound() {
        mSoundPool.stop(mStreamId!!)
        mSoundPool.stop(mSoundId!!)
        mSoundPool.autoPause()
        //mSoundPool.stop(mStreamId!!)
    }*/


    override fun onInit(status: Int) {

        //tts 실행
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.getDefault())

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("error","The Language not supported!")
            } else {
                val text = viewBinding.tutorial1Txt.text.toString()
                tts!!.speak(text, TextToSpeech.QUEUE_FLUSH,null,"")


            }
        }else{
            Log.e("error","Error!")
        }

        //text 부분 누르면 다시 음성 나옴(다시 읽어줌)
        viewBinding.tutorial1Txt.setOnClickListener {
            val text = viewBinding.tutorial1Txt.text.toString()
            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH,null,"")
        }
        //캐릭터 누르면 점프 함
        viewBinding.tutorial1CharaterM.setOnClickListener {
            playSound()
            viewBinding.tutorial1CharaterM.animate().apply {
                duration=200
                translationYBy(-300f)
                //rotationYBy(360f)

            }.withEndAction {
                viewBinding.tutorial1CharaterM.animate().apply {
                    duration = 200
                    translationYBy(300f)
                    //rotationYBy(360f)
                }
            }.start()

        }

        //"다음" 버튼 누르면 액티비티 이동
        viewBinding.tutorial1NextBtn.setOnClickListener {
            /*supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.frameFragment.id, tutorialTxt2())
                .commitNowAllowingStateLoss()*/

            val intent = Intent(this,Tutorial2Activity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left)
            mSoundPool.release()   //sound를 사용하지 않을 때 (soundPool에 있던 리소스 해제)

        }

        //캐릭터 fade in
        viewBinding.tutorial1CharaterM.visibility=View.VISIBLE
        val fadeIn = AnimationUtils.loadAnimation(this,R.anim.fade_in)
        viewBinding.tutorial1CharaterM.startAnimation(fadeIn)

        //3초 후 캐릭터 점프함
        Handler(Looper.getMainLooper()).postDelayed({
            playSound()
            viewBinding.tutorial1CharaterM.animate().apply {
                duration=200
                translationYBy(-300f)
                //rotationYBy(360f)

            }.withEndAction {
                viewBinding.tutorial1CharaterM.animate().apply {
                    duration = 200
                    translationYBy(300f)
                    //rotationYBy(360f)
                }
            }.start()
        },3000)


        /*viewBinding.tutorial1CharaterM.animate().apply {
            duration = 400
            //alpha(.5f)         //투명도
            //rotationYBy(360f)  //y축을 기준으로 회전(가로로 회전)
            //scaleX(.5f)        //좀 기울어짐
            //translationXBy(-200f)    //x축 방향으로 -200f 만큼 이동
            //translationYBy(500f)
            translationY(-500f)
            //translationYBy(-500f)  //차이가 뭘까
            //rotationYBy(360f)  //y축을 기준으로 회전(가로로 회전)
            //rotation(10f)          //뚜벅뚜벅 걸을 때 쓰자


        }.withStartAction{
            viewBinding.tutorial1CharaterM.animate().apply {
                translationYBy(0.0f)
                //rotationYBy(360f)
            }
        }.start()

        viewBinding.tutorial1CharaterM.animate().apply {
            duration = 1000
            rotationYBy(360f)
        }.start()*/

    }

    override fun onPause() {
        super.onPause()

    }


    override fun onStop() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onStop()

    }

    override fun onRestart() {
        super.onRestart()
    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()

    }


}

