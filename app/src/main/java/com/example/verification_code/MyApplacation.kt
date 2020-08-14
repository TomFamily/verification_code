package com.example.verification_code

import android.app.Application
import cn.bmob.v3.Bmob

class MyApplacation: Application() {
    override fun onCreate() {
        super.onCreate()
        //第一：默认初始化
        Bmob.initialize(this, "d97c8da9b493f0a4fb317b8b8c858166");
    }
}