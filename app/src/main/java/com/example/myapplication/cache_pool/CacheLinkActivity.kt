package com.example.myapplication.cache_pool

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import kotlinx.android.synthetic.main.activity_cache_link.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

class CacheLinkActivity : AppCompatActivity() {
    lateinit var cacheLink: CacheLink
    var num = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cache_link)
        btGet.setOnClickListener {
            //todo 添加线程池
            runBlocking(Dispatchers.IO) {
                cacheLink = CacheLink.obtain()
                cacheLink.num = ++num
                //使用delay和sleep函数需要导入协程的包"org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7"
                delay(5000)
                cacheLink.recycle()
            }
        }
    }
}