package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.cache_pool.CachePoolActivity
import com.example.myapplication.cache_pool.gson_fromJson.DealGsonFromJsonActivity
import com.example.myapplication.callable.TestCallable
import kotlinx.android.synthetic.main.activity_home.*
import java.util.concurrent.Executors

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        btn1.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        btn2.setOnClickListener {
            startActivity(Intent(this,CachePoolActivity::class.java))
        }
        btn3.setOnClickListener{
            //创建Callable实例
            val testCallable1= TestCallable("url1")
            val testCallable2= TestCallable("url2")
            //开启一个线程池
            val ser= Executors.newFixedThreadPool(5)
            val f1=ser.submit(testCallable1)
            val f2=ser.submit(testCallable2)
            //返回的数据
            var r1=f1.get()
            var r2=f2.get()
            //关闭服务
            ser.shutdownNow()
        }
    }
}