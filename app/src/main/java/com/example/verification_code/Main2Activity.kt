package com.example.verification_code

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import cn.bmob.v3.BmobSMS
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.QueryListener
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    private val codeNumberArray:Array<TextView> by lazy {
        arrayOf(ykT1,ykT2,ykT3,ykT4,ykT5,ykT6)
    }
    private var phoneNumber:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

//        intent.getIntExtra("phone",0)
        val phone = intent.getStringExtra("phone")
        if (phone != null) {
            Log.v("yk","wiroe $phone")
            phoneTextView.text = phone
            phoneNumber = phone
//                    向bomb请求数据
//            BmobSMS.requestSMSCode(phone,"",object : QueryListener<Int>(){
//                override fun done(p0: Int?, p1: BmobException?) {
//                    if (p1 == null){
//                        Toast.makeText(this@Main2Activity,"验证码发送成功",Toast.LENGTH_SHORT).show()
//                    }else{
//                        Toast.makeText(this@Main2Activity,"验证码发送失败",Toast.LENGTH_SHORT).show()
//                    }
//                }
//
//            })
        }


        ykeditText.addTextChangedListener(object : ykTextWacher(){
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    for ((i,item) in p0?.withIndex()!!){
                        Log.v("yk","$i $item")
                        if (item != null){
                            codeNumberArray[i].text = item.toString()
                        }
                        for (i in p0.length..5){
                            codeNumberArray[i].text = ""
                        }
                        if (p0.toString().isEmpty()){
                                codeNumberArray[0].text = ""
                        }
                    }
                if (p0.toString().length == 6){
                    BombUtil.verifSmsCode(phoneNumber!!,p0.toString()){
                        if (it == BombUtil.SUCCESS){
                            Intent().also { intent ->
                                intent.setClass(this@Main2Activity,HomeActivity::class.java)
                                startActivity(intent)
                            }
                        }else{
                            Toast.makeText(this@Main2Activity,"短信验证失败",Toast.LENGTH_SHORT).show()
                            for (i in 0..5){
                                codeNumberArray[i].text = ""
                            }
                            Log.v("yk", "1.0= ${ykeditText.text.toString()}")
                            ykeditText.text.clear()
                            Log.v("yk", "2.0= ${ykeditText.text.toString()}")
                        }
                    }
                }
            }
        })
    }
}




