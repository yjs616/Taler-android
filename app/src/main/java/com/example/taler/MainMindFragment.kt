package com.example.taler

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.taler.databinding.FragmentMainMindBinding

class MainMindFragment : Fragment() {

    private lateinit var mgv: MyGraphicView
    private lateinit var viewBinding: FragmentMainMindBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = FragmentMainMindBinding.inflate(layoutInflater)
        mgv = context?.let { MyGraphicView(it)}!!


        return mgv
    }

    companion object{
        val CIRCLE =2
        var curShape = CIRCLE
        var size = 4
        lateinit var textBox: EditText
        //var color = randomColor()

        internal var myShapes: MutableList<MyShape> = ArrayList(4)

    }

    private class MyGraphicView(context: Context) : View(context) {
        var sx = -1
        var sy = -1
        var ex = -1
        var ey = -1

        override fun onTouchEvent(event: MotionEvent?): Boolean {

            //touch 할 때 액션들
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> { //touch 시작, 화면에 손가락 올림.
                    //ChangeStartPoint(event)
                    sx = event.x.toInt()
                    sy = event.y.toInt()

                }
                MotionEvent.ACTION_MOVE -> {
                    // 화면에서 이동할 때, 화면에서 손가락을 띄였을 때.
                    //ChangeEndPoint(event)
                    ex = event.x.toInt()
                    ey = event.y.toInt()
                    this.invalidate() //명령이 완료되었으니 그리기를 호출(onDraw호출하는 것)

                }
                MotionEvent.ACTION_UP->{
                    val shape = MyShape()  //도형 데이터 1건을 저장시킬 객체 생성
                    shape.shapeType = curShape
                    shape.sx = sx
                    shape.sy = sy
                    shape.ex = ex
                    shape.ey = ey
                    //shape.color = color
                    shape.size = size

                    shape.isExist = true
                    myShapes.add(shape)   //ArrayList에 저장. 도형 누적.
                    this.invalidate()

                }

            }
            return true
        }

        override fun setOnClickListener(l: OnClickListener?) {
            super.setOnClickListener(l)
        }

        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)
            val paint = Paint()
            paint.style = Paint.Style.FILL_AND_STROKE
            paint.color = Color.argb(220,225,122,0)

            //paint.color = resources.getColor(randomColor())

            //캔버스 배경
            canvas!!.drawColor(Color.WHITE)

            for (i in myShapes.indices) {
                val shape2 = myShapes[i]
                paint.setStrokeWidth(shape2.size!!.toFloat())
                //paint.color = resources.getColor(randomColor())
                when (shape2.shapeType) {

                    //그리기
                    CIRCLE -> {
                        val radius = Math.sqrt(
                            Math.pow(
                                (shape2.ex!! - shape2.sx!!).toDouble(),
                                2.0
                            ) + Math.pow(
                                (shape2.ey!! - shape2.sy!!).toDouble(),
                                2.0
                            )
                        ).toInt()
                        canvas.drawCircle(
                            shape2.sx!!.toFloat(),
                            shape2.sy!!.toFloat(), radius.toFloat(), paint
                        )

                    }
                }


            }
            paint.strokeWidth = size.toFloat()

            //모양 잡아주기
            when (curShape) {

                CIRCLE -> {
                    val radius = Math.sqrt(
                        Math.pow(
                            (ex - sx).toDouble(), 2.0
                        ) +
                                Math.pow((ey - sy).toDouble(), 2.0)
                    )

                    canvas?.drawCircle(
                        sx.toFloat(), sy.toFloat(),
                        radius.toFloat(), paint
                    )
                }

            }

        }

    }
}