package com.example.myapplication.cache_pool

import android.util.Log
import androidx.collection.LruCache
import java.util.*
import kotlin.collections.ArrayList

/**
 * 1.从缓存池中获取的大小要大于等于所需的。
 * 2.允许缓存池中相同大小的对象有多个。
 */
class CacheMap(val maxlength:Int){
    //基于红黑树实现的Map
    private val map=TreeMap<Int,Int>()
    //LruCache已经实现了LRU算法（最近最少使用算法，其实是LinkedHashMap实现的，如果accessOrder为true，就表明实现了LRU缓存淘汰算法）
    private val cache = object : LruCache<Int, ArrayList<ByteArray>>(maxlength) {
        protected override fun sizeOf(key: Int, value: ArrayList<ByteArray>): Int {
            //重写sizeOf方法，返回图片的占用字节数而不是图片的个数，每次添加图片是会被调用
            var size=0
            for (v in value){
                size+=v.size
            }
            return size / 1024
        }

        override fun entryRemoved(
            evicted: Boolean,
            key: Int,
            oldValue: ArrayList<ByteArray>,
            newValue: ArrayList<ByteArray>?
        ) {
            super.entryRemoved(evicted, key, oldValue, newValue)
            if (newValue!=null&&newValue.isNotEmpty()){
                val num=map[key]
                if (num!=null) {
                    map[key] = num - 1
                }
            }else{
                map.remove(key)
            }

        }
    }

    @Synchronized
    fun get(len:Int):ByteArray{
        //大于等于len的
        val key=map.ceilingKey(len)
        if (key!=null) {
            val values=cache[key]
            if (values!=null) {
                val value = values.removeAt(0)
                val num = map[value.size]
                if (num != null) {
                    if (num == 1) {
                        map.remove(value.size)
                    } else {
                        map[value.size] = num - 1
                    }
                }
                if (values.size==1){
                    cache.remove(key)
                }
                Log.i("CacheMap", "get size: ${value.size} 相同大小对象的个数:${values.size}")
                return value
            }else{
                Log.i("CacheMap", "add 一个对象")
                return ByteArray(len)
            }
        }
        Log.i("CacheMap", "add 一个对象")
        return ByteArray(len)
    }
    //这里不用判断内存上限，因为LruCache内部进行了处理，如果超限了会把最久未使用的移除掉
    @Synchronized
    fun put(byteArray: ByteArray){
        if (byteArray.isNotEmpty()){
            val num=map[byteArray.size]
            map[byteArray.size] = if (num==null) 1 else num+1
            var values=cache[byteArray.size]
            if (values!=null){
                values.add(byteArray)
            }else{
                values= arrayListOf(byteArray)
            }
            cache.put(byteArray.size,values)
            Log.i("CacheMap", "put byteArray size: ${byteArray.size}  相同大小对象的个数：${values.size}}")
        }
    }
}