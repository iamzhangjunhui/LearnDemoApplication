package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.cache_pool.CachePoolActivity
import com.example.myapplication.cache_pool.gson_fromJson.DealGsonFromJsonActivity
import kotlinx.android.synthetic.main.activity_home.*

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
    }
}