package com.example.taler

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.example.taler.databinding.ActivityCharacterCustomBinding

class CharacterCustomActivity : AppCompatActivity(){

    private lateinit var binding:ActivityCharacterCustomBinding
    var costumeVar = 1
    var clothes = 1
    var hair = 1
    var pants = 1
    var shoes = 1
    var face = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //MusicManager()
        MusicManager.mp.setVolume(0.3f,0.3f)

        binding.costumeRg.setOnCheckedChangeListener{ radioGroup,checkedId ->
            when(checkedId){
                R.id.hairBtn -> {
                    clickCostumeSeekBar(binding.costumeSb,binding.hairIv,"hair_")
                    clickColorSeekBar(binding.colorSb,binding.hairIv,"hairColor_")

                }
                R.id.clothesBtn -> {
                    clickCostumeSeekBar(binding.costumeSb,binding.clothesIv,"clothes_")
                    clickColorSeekBar(binding.colorSb,binding.clothesIv,"hairColor_")
                    //binding.costumeSb.setProgress(clothes)
                }
                R.id.pantsBtn -> {
                    clickCostumeSeekBar(binding.costumeSb,binding.pantsIv,"pants_")
                    clickColorSeekBar(binding.colorSb,binding.pantsIv,"hairColor_")
                    //binding.costumeSb.setProgress(pants)
                }
                R.id.shoesBtn -> {
                    clickCostumeSeekBar(binding.costumeSb,binding.shoesIv,"shoes_")
                    clickColorSeekBar(binding.colorSb,binding.shoesIv,"hairColor_")
                    //binding.costumeSb.setProgress(shoes)
                }
                R.id.faceBtn -> {
                    clickCostumeSeekBar(binding.costumeSb,binding.faceIv,"face_")
                    clickColorSeekBar(binding.colorSb,binding.faceIv,"hairColor_")
                    //binding.costumeSb.setProgress(face)
                }

            }
        }

        binding.btnNext.setOnClickListener {
            startActivity(Intent(this,MyTalerActivity::class.java))
        }

    }

    // type : clothes_
    // idx : 1 이면
    // R.drawable.clothes_1에 접근
    fun getDrawableResId(type:String,idx:Int):Int {
        var resId = resources.getIdentifier(type + idx, "drawable", packageName)
        return resId
    }
    fun getColorResId(color:String,idx:Int):Int {
        var resId = resources.getIdentifier(color+idx, "color", packageName)
        return resId
    }
    fun clickCostumeSeekBar(seekBar: SeekBar, imageView: ImageView, costumeType:String){
        seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                when(progress){
                    progress-> imageView.setImageResource(getDrawableResId(costumeType,progress))

                }
            }
            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
            }
        })
    }
    fun clickColorSeekBar(seekBar:SeekBar,imageView:ImageView,colorType:String){
        seekBar.setOnSeekBarChangeListener(object :
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seek: SeekBar, progress: Int, fromUser: Boolean) {
                when(progress){
                    progress-> imageView.setColorFilter(ContextCompat.getColor(applicationContext,getColorResId(colorType,progress)))
                }
            }
            override fun onStartTrackingTouch(seek: SeekBar) {
                // write custom code for progress is started
            }

            override fun onStopTrackingTouch(seek: SeekBar) {
                // write custom code for progress is stopped
            }
        })
    }



}


