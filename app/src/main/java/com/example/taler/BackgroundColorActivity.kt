package com.example.taler

import android.graphics.Color.WHITE
import android.graphics.Color.parseColor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.taler.databinding.ActivityBackgroundColorBinding
import com.github.dhaval2404.colorpicker.ColorPickerDialog
import com.github.dhaval2404.colorpicker.MaterialColorPickerDialog
import com.github.dhaval2404.colorpicker.model.ColorShape
import com.github.dhaval2404.colorpicker.model.ColorSwatch

class BackgroundColorActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityBackgroundColorBinding
    private val mDefaultColor = R.color.white
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityBackgroundColorBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        // Kotlin Code
        ColorPickerDialog
            .Builder(this)        				// Pass Activity Instance
            .setTitle("배경색 선택")           	// Default "Choose Color"
            .setColorShape(ColorShape.SQAURE)   // Default ColorShape.CIRCLE
            .setDefaultColor(mDefaultColor)     // Pass Default Color
            .setColorListener { color, colorHex ->
                // Handle Color Selection

            }
            .show()

    }
}