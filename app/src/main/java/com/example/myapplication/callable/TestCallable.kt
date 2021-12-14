package com.example.myapplication.callable

import java.util.concurrent.Callable

//可以返回值，这里以Boolean为例
class TestCallable(val url:String):Callable<Boolean>{
    override fun call(): Boolean {
       //耗时操作
        return true
    }
}
