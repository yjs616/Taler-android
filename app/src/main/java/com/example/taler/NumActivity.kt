package com.example.taler

import android.Manifest.permission.RECEIVE_SMS
import android.Manifest.permission.SEND_SMS
import android.app.PendingIntent
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.taler.databinding.ActivityNumBinding
import java.util.jar.Manifest

class NumActivity : AppCompatActivity() {
    lateinit var binding: ActivityNumBinding
    private val PERMISSIONS_REQUEST_SEND_SMS = 2323

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var etPhoneNumber = findViewById<EditText>(R.id.auth_phone_et)

        //버튼을 클릭하면 SMS 전송 SmsSend() 호출
        binding.authRecBtn.setOnClickListener(View.OnClickListener {
            val strPhoneNumber: String = etPhoneNumber.getText().toString().replace("[^0-9]", "")
            if (strPhoneNumber == "" || strPhoneNumber.length < 10) {
                Toast.makeText(applicationContext, "전화번호를 확인하세요", Toast.LENGTH_LONG).show()
                return@OnClickListener
            }
            SmsSend(strPhoneNumber, "The test sms-123456")
        })
    }


    //권한이 있으면 문자 전송
    fun SmsSend(strPhoneNumber: String?, strMsg: String?) {
        if (ContextCompat.checkSelfPermission(this,
                SEND_SMS)
            != PackageManager.PERMISSION_GRANTED
        ) { //권한이 없다면
            ActivityCompat.requestPermissions(this, arrayOf(SEND_SMS),
                PERMISSIONS_REQUEST_SEND_SMS)
        } else { //권한이 있다면 SMS를 보낸다.
            val sendIntent = PendingIntent.getBroadcast(this, 0, Intent("SMS_SENT"), 0)
            val deliveredIntent = PendingIntent.getBroadcast(this, 0, Intent("SMS_DELIVERED"), 0)
            val smsManager: SmsManager = SmsManager.getDefault()
            try {
                smsManager.sendTextMessage(strPhoneNumber,
                    null,
                    strMsg,
                    sendIntent,
                    deliveredIntent)
            } catch (ex: Exception) {
                ex.printStackTrace()
                Toast.makeText(baseContext, ex.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    //권한요청
    private val SMS_RECEIVE_PERMISSON = 1
    fun checkPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                SEND_SMS)
        ) {
            ActivityCompat.requestPermissions(this,
                arrayOf(SEND_SMS),
                SMS_RECEIVE_PERMISSON)
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(SEND_SMS),
                SMS_RECEIVE_PERMISSON)
        }
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                RECEIVE_SMS)
        ) {
            ActivityCompat.requestPermissions(this,
                arrayOf(RECEIVE_SMS),
                SMS_RECEIVE_PERMISSON)
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(RECEIVE_SMS),
                SMS_RECEIVE_PERMISSON)
        }
    }
}