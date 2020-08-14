package com.example.verification_code

import cn.bmob.v3.BmobSMS
import cn.bmob.v3.exception.BmobException
import cn.bmob.v3.listener.UpdateListener

object BombUtil {

    val SUCCESS = 1
    val FAIL = 0
//    向服务器请求验证码

//    检验验证码
    fun verifSmsCode(phoneNumber:String, code: String, callback: (Int) -> Unit){
        BmobSMS.verifySmsCode(phoneNumber,code,object : UpdateListener(){
            override fun done(p0: BmobException?) {
                if (p0 == null){
                    callback(SUCCESS)

                }else{
                    callback(FAIL)
                }
            }
        })
    }
}