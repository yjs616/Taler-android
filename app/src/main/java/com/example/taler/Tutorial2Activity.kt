package com.example.taler

import android.content.Intent
import android.media.SoundPool
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.animation.AnimationUtils
import com.example.taler.databinding.ActivityTutorial2Binding
import java.util.*

class Tutorial2Activity : AppCompatActivity() , TextToSpeech.OnInitListener {
    private lateinit var viewBinding: ActivityTutorial2Binding
    private lateinit var tts: TextToSpeech

    //soundPool
    private lateinit var mSoundPool: SoundPool
    private var mSoundId: Int?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityTutorial2Binding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        tts= TextToSpeech(this,this)

        //너는 어떤 사람이구나 - 반응 점프
        Handler(Looper.getMainLooper()).postDelayed({
            viewBinding.tutorial2CharaterM.animate().apply {
                duration=200
                translationYBy(-300f)
                //rotationYBy(360f)

            }.withEndAction {
                viewBinding.tutorial2CharaterM.animate().apply {
                    duration = 200
                    translationYBy(300f)
                    //rotationYBy(360f)
                }
            }.start()
        },3500)

        setSound()
    }
    private fun setSound() {
        mSoundPool = SoundPool.Builder().setMaxStreams(1).build()
        mSoundId=mSoundPool.load(this,R.raw.disapper,1)

    }
    private fun playSound() {
        mSoundPool.play(mSoundId!!,1f,1f,1,0,1f)
    }

    override fun onInit(status: Int) {

        //tts 실행
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.getDefault())

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("error","The Language not supported!")
            } else {
                val text = viewBinding.tutorial2Txt.text.toString()
                tts!!.speak(text, TextToSpeech.QUEUE_FLUSH,null,"")

            }
        }else{
            Log.e("error","Error!")
        }

        //text 부분 누르면 다시 음성 나옴(다시 읽어줌)
        viewBinding.tutorial2Txt.setOnClickListener {
            val text = viewBinding.tutorial2Txt.text.toString()
            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH,null,"")
        }

        //"다음" 버튼 누르면 tutorialTxt 프래그먼트 -> 더 내용 추가 될 경우
        viewBinding.tutorial2NextBtn.setOnClickListener {
            /*supportFragmentManager
                .beginTransaction()
                .replace(viewBinding.frameFragment2.id, tutorialTxt2())
                .commitNowAllowingStateLoss()*/
            val intent = Intent(this, CharacterCustomActivity::class.java)
            startActivity(intent)
        }


        //3초 후 캐릭터 회전, 사라짐
        Handler(Looper.getMainLooper()).postDelayed({
            viewBinding.tutorial2CharaterM.animate().apply {
                duration=300
                rotationYBy(360f)

            }.start()
        },6000)

        //캐릭터 fade out - 사라짐
        Handler(Looper.getMainLooper()).postDelayed({
            val fadeOut = AnimationUtils.loadAnimation(this,R.anim.fade_out)
            viewBinding.tutorial2CharaterM.startAnimation(fadeOut)
            playSound()
        },6000)

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
    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }

    override fun onBackPressed() {
        mSoundPool.release()
        super.onBackPressed()
    }

}