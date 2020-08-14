package com.example.verification_code

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import cn.bmob.v3.BmobSMS
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var phoneNumber:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ykbutton.setOnClickListener{

            if (phoneNumber?.length == 11){
                Log.v("yk", phoneNumber!!)

                Intent().also {
                    it.setClass(this,Main2Activity::class.java)
                    it.putExtra("phone",phoneNumber)
                    startActivity(it)
                }

//                BmobSMS.requestSMSCode(phoneNumber, "", object : QueryListener<Int>() {
//                    override fun done(smsId: Int, e: BmobException) {
//                        if (e == null){
//                            Toast.makeText(this@MainActivity,"验证码发送成功",Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                })
            }
        }

        ykeditText.addTextChangedListener(object: ykTextWacher(){
            override fun afterTextChanged(p0: Editable?) {
                if (p0.toString().length == 11){
                    ykbutton.isEnabled = true
                    phoneNumber = p0.toString()
                }
                if (p0.toString().length < 11){
                    ykbutton.isEnabled = false
                }
            }
        })
    }
}
