package com.example.taler

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage

class SMSReceiver : BroadcastReceiver() {

    private val SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"


    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == SMS_RECEIVED) {
            val bundle = intent.extras
            val messages = parseSmsMessage(bundle)
            if (messages.size > 0) {
                val content = messages[0]!!.messageBody
                sendToActivity(context, content)
            }
        }
    }

    private fun sendToActivity(context: Context, content: String) {
        //val intent = Intent(context, SmsActivity::class.java) // Flag 설정
        /*intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP) // 메세지의 내용을 Extra에 넣어줌
        intent.putExtra("content", content)
        context.startActivity(intent)*/
    }

    private fun parseSmsMessage(bundle: Bundle?): Array<SmsMessage?> {
        val objs = bundle!!["pdus"] as Array<Any>?
        val messages = arrayOfNulls<SmsMessage>(
            objs!!.size)
        for (i in objs.indices) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val format = bundle.getString("format")
                messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray, format)
            } else {
                messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray)
            }
        }
        return messages
    }

    /*override fun onReceive(context: Context, intent: Intent) {
//        문자가 왔을 때 동작
        if(intent?.action.equals("android.provider.Telephony.SMS_RECEIVED")){
            val bundle = intent?.extras
            val messages = smsMessageParse(bundle!!)
            if(messages?.size!! > 0){
                val content = messages[0]?.messageBody.toString()
                val certNumber = content?.replace("[^0-9]".toRegex(),"")
                if (certNumber != null) {
                    Log.d("인증번호 추출 ",certNumber)
                }
            }
        }
    }
//    문자 내용을 파싱해오는 함수
    fun smsMessageParse(bundle: Bundle): Array<SmsMessage?>? {
        val objs = bundle["pdus"] as Array<Any>?
        val messages: Array<SmsMessage?> = arrayOfNulls<SmsMessage>(objs!!.size)
        for (i in objs!!.indices) {
            messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray)
        }
        return messages
    }*/
    /*private val TAG: String? = "In SMS_Receiver"
    //문자에서 번호 추출하기
    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "onReceive() called")
        var number = ""
        val bundle = intent.extras
        //parsing message
        val messages = parseMessage(bundle)
        if (messages.size > 0) {
            val sender = messages[0]!!.originatingAddress
            val content = messages[0]!!.messageBody.toString()
            val date = Date(messages[0]!!.timestampMillis)
            //extract numbers in SMS message
            number = content.replace("[^0-9]".toRegex(), "")
            //for Debug
            Log.d(TAG, "sender : $sender")
            Log.d(TAG, "content : $content")
            Log.d(TAG, "date : $date")
            //send number to activity
            sendToActivity(context, number)
        }
    }
    private fun parseMessage(bundle: Bundle?): Array<SmsMessage?> {
        Log.d(TAG, "parseSmsMessage() called")
        val objs = bundle!!["pdus"] as Array<Any>?
        val messages = arrayOfNulls<SmsMessage>(
            objs!!.size)
        for (i in objs.indices) {
            messages[i] = SmsMessage.createFromPdu(objs[i] as ByteArray)
        }
        return messages
    }
    private val format: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    private fun sendToActivity(context: Context, number: String) {
        Log.d(TAG, "sendToActivity() called")
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                or Intent.FLAG_ACTIVITY_SINGLE_TOP
                or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent.putExtra("number", number)
        context.startActivity(intent)
    }*/

}