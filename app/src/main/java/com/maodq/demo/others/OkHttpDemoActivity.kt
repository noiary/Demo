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
        val okHttpClient = OkHttpClient()
        val request = Request.Builder().url("http://www.baidu.com").build()
        val call = okHttpClient.newCall(request)
        call.enqueue(object : Callback {
            override fun onFailure(call: Call?, e: IOException?) {

            }

            override fun onResponse(call: Call?, response: Response?) {
            }
        })
    }
}
