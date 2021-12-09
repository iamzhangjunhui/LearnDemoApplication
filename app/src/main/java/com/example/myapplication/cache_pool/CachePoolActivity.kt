package com.example.myapplication.cache_pool

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.CacheLinkActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_cache_pool.*

class CachePoolActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache_pool)
        btn1.setOnClickListener {
            startActivity(Intent(this, CacheLinkActivity::class.java))
        }
    }
}