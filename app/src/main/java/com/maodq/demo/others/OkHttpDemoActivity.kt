package com.maodq.demo.others

import android.os.Bundle
import com.maodq.demo.R
import com.maodq.demo.base.BActivity
import okhttp3.*
import java.io.IOException

class OkHttpDemoActivity : BActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ok_http_demo)

        demo()
    }

    private fun demo() {
        // 创建OkHttpClient对象，内部默认创建一系列成员变量对象
        val okHttpClient = OkHttpClient()
        // 创建Request，默认get请求，设置url
        val request = Request.Builder().url("http://www.jd.com").build()
        // 创建call对象，此时并没有进行网络连接
        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {
                e?.printStackTrace()
            }

            override fun onResponse(call: Call?, response: Response?) {
                if (response != null && response.isSuccessful) {
                    response.body().toString()
                }
            }
        })
    }
}
