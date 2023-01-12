package com.example.taler

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import com.example.taler.databinding.FragmentTutorialTxt2Binding
import java.util.*

class tutorialTxt2 : Fragment(), TextToSpeech.OnInitListener{
    private lateinit var viewBinding: FragmentTutorialTxt2Binding
    private lateinit var tts: TextToSpeech


    /*lateinit var tutorialActivity1: TutorialActivity1

    override fun onAttach(context: Context) {
        super.onAttach(context)
        tutorialActivity1 = context as TutorialActivity1
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentTutorialTxt2Binding.inflate(layoutInflater)
        return viewBinding.root


        /*val nextBtn:View = (tutorialTxt2() as Tutorial1Activity).findViewById(R.id.tutorial1_next_btn)
        nextBtn.setOnClickListener {
            val intent = Intent(activity,Tutorial3Activity::class.java)
            startActivity(intent)
        }*/

        /*var mainView:Button = (tutorialTxt2() as Tutorial1Activity).viewBinding.tutorial1NextBtn
        mainView.setOnClickListener {

        }*/


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tts = TextToSpeech(context, this)         //activity, context 둘 다 됨
    }


    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts!!.setLanguage(Locale.getDefault())

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("error", "The Language not supported!")
            } else {
                val text = viewBinding.tutorial2Txt.text.toString()
                tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")

            }
        } else {
            Log.e("error", "Error!")
        }

        //text 부분 누르면 다시 음성 나옴(다시 읽어줌)
        viewBinding.tutorial2Txt.setOnClickListener {
            val text = viewBinding.tutorial2Txt.text.toString()
            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
        }


    }

    override fun onDestroy() {
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}