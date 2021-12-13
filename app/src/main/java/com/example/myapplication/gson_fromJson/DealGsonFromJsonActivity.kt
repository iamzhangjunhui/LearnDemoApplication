package com.example.myapplication.gson_fromJson

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.R
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class DealGsonFromJsonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deal_gson_from_json)
        testGson()
    }
    @SuppressLint("LongLogTag")
    fun testGson(){
        val gson= GsonBuilder().registerTypeAdapter(CacheLinkTestGsonBean::class.java,TestTypeAdapter()).create()
        for (i in 1..1000){
          val d=  gson.fromJson<CacheLinkTestGsonBean>("{\"name\"=\"kaylee${i}\"}",CacheLinkTestGsonBean::class.java)
            Log.i("DealGsonFromJsonActivity",d.name)
        }
    }
}