package com.example.myapplication.cache_pool.gson_fromJson

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

class TestTypeAdapter : TypeAdapter<CacheLinkTestGsonBean>(){
    override fun write(out: JsonWriter?, value: CacheLinkTestGsonBean?) {
        out?.beginObject()
        out?.name(value?.name)
        out?.endObject()
    }

    override fun read(`in`: JsonReader?): CacheLinkTestGsonBean {
        val testGsonBean=CacheLinkTestGsonBean.obtain()
        if (`in`!=null) {
            `in`.beginObject()
            while (`in`.hasNext()) {
                if (`in`.nextName()=="name") {
                    testGsonBean.name = `in`.nextString()
                }
            }
            `in`.endObject()
        }
        testGsonBean.recycle()
        return testGsonBean
    }

}