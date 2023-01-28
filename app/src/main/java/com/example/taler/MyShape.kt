package com.example.taler

import kotlin.random.Random

class MyShape {//전화번호부처럼 데이터 1건을 저장
var shapeType:Int?=null
    var sx:Int?=null
    var sy:Int?=null
    var ex:Int?=null
    var ey:Int?=null
    //var color = randomColor()
    var size:Int?=null
    var isExist:Boolean = false

}

fun randomColor(): Int{
    val list= ArrayList<Int>()
    list.add(R.color.RandomColor1)
    list.add(R.color.RandomColor2)
    list.add(R.color.RandomColor3)
    list.add(R.color.RandomColor4)
    list.add(R.color.RandomColor5)
    list.add(R.color.RandomColor6)

    val seed = System.currentTimeMillis().toInt()
    val randomIndex= Random(seed).nextInt(list.size)
    return list[randomIndex]

}