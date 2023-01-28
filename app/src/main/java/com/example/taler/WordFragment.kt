package com.example.taler

import android.app.ProgressDialog.show
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.Paint
import android.media.MediaRecorder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.example.taler.databinding.DialogInputBinding
import com.example.taler.databinding.DialogRecordBinding
import com.example.taler.databinding.FragmentWordBinding

class WordFragment : Fragment() {
    private lateinit var paint: Paint

    lateinit var mediaRcorder : MediaRecorder
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWordBinding.inflate(inflater, container, false)
        val dialogInputBinding= DialogInputBinding.inflate(inflater, container, false)
        val dialogRecordBinding = DialogRecordBinding.inflate(inflater, container, false)
        val items = arrayOf<String>("텍스트","음성")
        binding.wordTv1.setBackgroundResource(R.drawable.shape_circle_white)
        binding.wordTv1.setTextColor(Color.WHITE)
        /*
        binding.wordDl.setOnTouchListener(object: View.OnTouchListener{
            override fun onTouch(p0: View?, event: MotionEvent?): Boolean {
                when(event?.action){
                    MotionEvent.ACTION_DOWN->{
                        Log.d("안영준","${event.x}, ${event.y}")
                        return true
                    }
                }
                return false
            }
        })*/


        binding.wordTv1.setOnClickListener{
            if(binding.wordTv1.currentTextColor == Color.WHITE){
                binding.wordTv1.setBackgroundResource(R.drawable.shape_circle_gray)
                binding.wordTv1.setTextColor(Color.BLACK)
            }else if(binding.wordTv1.currentTextColor== Color.BLACK){
                AlertDialog.Builder(requireActivity()).run{
                    setTitle("입력")
                    setSingleChoiceItems(items,1,object: DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            if(p1 == 0){
                                AlertDialog.Builder(requireActivity()).run{
                                    setTitle("텍스트")
                                    setView(dialogInputBinding.root)
                                    setPositiveButton("완료", object: DialogInterface.OnClickListener{
                                        override fun onClick(p0: DialogInterface?, p1: Int) {
                                            binding.wordTv1.setText(dialogInputBinding.wordInputEt.text.toString())
                                        }
                                    }

                                    )
                                    show()

                                }

                            }else if(p1 == 1) {
                                AlertDialog.Builder(requireActivity()).run {
                                    setTitle("음성")
                                    setView(dialogRecordBinding.root)
                                    setPositiveButton("완료", null)
                                    show()
                                }
                            }
                        }
                    })
                    setPositiveButton("완료",null)
                    show()

                }
            }

            /*if(binding.wordTv1.visibility == View.VISIBLE){
                binding.wordTv1.visibility = View.INVISIBLE
            }else if(binding.wordTv1.visibility == View.INVISIBLE){
                binding.wordTv1.visibility = View.VISIBLE
            }*/

        }

        /*
        binding.wordTv1.setOnClickListener{
            AlertDialog.Builder(requireActivity()).run{
                setTitle("입력")
                setSingleChoiceItems(items,1,object: DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        if(p1 == 0){
                            AlertDialog.Builder(requireActivity()).run{
                                setTitle("텍스트")
                                setView(dialogInputBinding.root)
                                setPositiveButton("완료", object: DialogInterface.OnClickListener{
                                    override fun onClick(p0: DialogInterface?, p1: Int) {
                                        binding.wordTv1.setText(dialogInputBinding.wordInputEt.text.toString())
                                    }
                                }
                                )
                                show()
                            }
                        }else if(p1 == 1) {
                            AlertDialog.Builder(requireActivity()).run {
                                setTitle("음성")
                                setView(dialogRecordBinding.root)
                                setPositiveButton("완료", null)
                                show()
                            }
                        }
                    }
                })
                setPositiveButton("완료",null)
                show()
            }
        }*/
        return binding.root
    }
    private fun init() {
        paint = Paint()
        paint.setAntiAlias(true)
        paint.setStyle(Paint.Style.STROKE)
        paint.setColor(Color.GRAY)
        paint.setStrokeWidth(5F)
    }

}