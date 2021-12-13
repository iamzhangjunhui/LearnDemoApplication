package com.example.myapplication.cache_pool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.cache_pool.gson_fromJson.DealGsonFromJsonActivity
import kotlinx.android.synthetic.main.activity_cache_pool.*
import kotlinx.android.synthetic.main.activity_cache_pool.btn1
import kotlinx.android.synthetic.main.activity_cache_pool.btn2
import kotlinx.android.synthetic.main.activity_home.*

class CachePoolActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache_pool)
        btn1.setOnClickListener {
            startActivity(Intent(this, CacheLinkActivity::class.java))
        }
        btn2.setOnClickListener {
            startActivity(Intent(this,CacheMapActivity::class.java))
        }
        btn3.setOnClickListener {
            startActivity(Intent(this, DealGsonFromJsonActivity::class.java))
        }
    }
}