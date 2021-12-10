package com.example.myapplication.cache_pool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_cache_map.*

class CacheMapActivity : AppCompatActivity() {
    val cacheMap=CacheMap(20)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache_map)
        btnGet.setOnClickListener {
            val c3=cacheMap.get(10)
            cacheMap.put(c3)
            cacheMap.put(c3)
            val c1=cacheMap.get(10)
            val c2=cacheMap.get(20)
            cacheMap.put(c2)
            cacheMap.put(c1)
            val c4=cacheMap.get(10)
        }
    }
}