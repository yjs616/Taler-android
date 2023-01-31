package com.example.taler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import androidx.core.content.ContextCompat
import com.example.taler.databinding.ActivityCharacterCustomBinding

class CharacterCustomActivity : AppCompatActivity() {
    private lateinit var binding:ActivityCharacterCustomBinding
    var costumeVar:Int = 1
    var clothesState:Int = 1
    var hairState:Int = 1
    var pantsState:Int = 1
    var shoesState:Int = 1
    var faceState:Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.genderRg.setOnCheckedChangeListener{ radioGroup,checkedId ->
            when(checkedId){
                R.id.manBtn ->{
                    binding.manBtn.setTextColor(ContextCompat.getColor(applicationContext,R.color.white))
                    binding.womanBtn.setTextColor(ContextCompat.getColor(applicationContext,R.color.black))
                }
                R.id.womanBtn ->{
                    binding.womanBtn.setTextColor(ContextCompat.getColor(applicationContext,R.color.white))
                    binding.manBtn.setTextColor(ContextCompat.getColor(applicationContext,R.color.black))
                }
            }
        }


        binding.nextBtn.setOnClickListener{
            startActivity(Intent(this,MyTalerActivity::class.java))
        }

        binding.costumeRg.setOnCheckedChangeListener{ radioGroup,checkedId ->
            when(checkedId){
                R.id.hairBtn -> {
                    clickCostumeSeekBar(binding.costumeSb,binding.hairIv,"img_hair_")
                    //clickColorSeekBar(binding.colorSb,binding.hairIv,"hairColor_")
                    binding.hairBtn.setTextColor(ContextCompat.getColor(applicationContext,R.color.white))
                    setFourButtonTextBlack(binding.faceBtn,binding.clothesBtn,binding.shoesBtn,binding.pantsBtn)

                }
                R.id.clothesBtn -> {
                    //binding.costumeSb.setProgress(0)
                    clickCostumeSeekBar(binding.costumeSb,binding.clothesIv,"img_clothes_")
                    //clickColorSeekBar(binding.colorSb,binding.clothesIv,"hairColor_")
                    binding.clothesBtn.setTextColor(ContextCompat.getColor(applicationContext,R.color.white))
                    setFourButtonTextBlack(binding.hairBtn,binding.faceBtn,binding.shoesBtn,binding.pantsBtn)
                }
                R.id.pantsBtn -> {
                    //binding.costumeSb.setProgress(0)
                    clickCostumeSeekBar(binding.costumeSb,binding.pantsIv,"img_pants_")
                    //clickColorSeekBar(binding.colorSb,binding.pantsIv,"hairColor_")
                    binding.pantsBtn.setTextColor(ContextCompat.getColor(applicationContext,R.color.white))
                    setFourButtonTextBlack(binding.hairBtn,binding.faceBtn,binding.clothesBtn,binding.shoesBtn)
                    //binding.costumeSb.setProgress(pants)
                }
                R.id.shoesBtn -> {
                    //binding.costumeSb.setProgress(0)
                    clickCostumeSeekBar(binding.costumeSb,binding.shoesIv,"img_shoes_")
                    //clickColorSeekBar(binding.colorSb,binding.shoesIv,"hairColor_")
                    binding.shoesBtn.setTextColor(ContextCompat.getColor(applicationContext,R.color.white))
                    setFourButtonTextBlack(binding.hairBtn,binding.faceBtn,binding.clothesBtn,binding.pantsBtn)
                    //binding.costumeSb.setProgress(shoes)
                }
                R.id.faceBtn -> {
                    //binding.costumeSb.setProgress(0)
                    clickCostumeSeekBar(binding.costumeSb,binding.faceIv,"img_face_")
                    //clickColorSeekBar(binding.colorSb,binding.faceIv,"hairColor_")
                    binding.faceBtn.setTextColor(ContextCompat.getColor(applicationContext,R.color.white))
                    setFourButtonTextBlack(binding.hairBtn,binding.clothesBtn,binding.shoesBtn,binding.pantsBtn)
                    //binding.costumeSb.setProgress(face)
                }

            }
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
                    progress-> {
                        imageView.setImageResource(getDrawableResId(costumeType, progress))
                        setCostumeVar(progress)
                    }
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
                    progress-> {
                        imageView.setColorFilter(ContextCompat.getColor(applicationContext,getColorResId(colorType,progress)))

                    }

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

    fun setFourButtonTextBlack(b1:Button,b2:Button,b3:Button,b4:Button){
        b1.setTextColor(ContextCompat.getColor(applicationContext,R.color.black))
        b2.setTextColor(ContextCompat.getColor(applicationContext,R.color.black))
        b3.setTextColor(ContextCompat.getColor(applicationContext,R.color.black))
        b4.setTextColor(ContextCompat.getColor(applicationContext,R.color.black))
    }



    @JvmName("setCostumeVar1")
    fun setCostumeVar(progress: Int) {
        costumeVar = progress
    }

}


