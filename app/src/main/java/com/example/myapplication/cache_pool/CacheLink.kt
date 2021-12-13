package com.example.myapplication.cache_pool

import android.util.Log
import android.widget.Toast

class CacheLink {
    var num: Int = 0
    var next: CacheLink? = null//链表的下一个元素
    companion object {
        private var sPoolSize = 0//对象池中现有的对象数
        private val MAX_POOL_SIZE = 10//对象池的最大长度
        private val sPoolSync = Any()//对象锁
        private var sPool: CacheLink? = null//链表第一个元素（代表对象池）
        fun obtain(): CacheLink {
            synchronized(sPoolSync) {
                if (sPool != null) {
                    val c: CacheLink = sPool!!
                    sPool = c.next
                    c.next = null
                    sPoolSize--
                    Log.i("CacheLink","从对象池取对象sPoolSize")
                    return c
                }
            }
            Log.i("CacheLink","新建对象")
            return CacheLink()
        }
    }
    fun recycle() {
        num=0
        synchronized(sPoolSync) {
            if (sPoolSize <MAX_POOL_SIZE) {
                next = sPool
                sPool = this
                sPoolSize++
                Log.i("CacheLink","回收对象${num}到对象池中，当前PoolSize${sPoolSize}")
            }
        }
    }
}