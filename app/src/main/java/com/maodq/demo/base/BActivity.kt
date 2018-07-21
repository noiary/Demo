package com.maodq.demo.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

@SuppressLint("Registered")
open class BActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val data = intent.getStringExtra("data")
        actionBar?.title = data
//        if (data != null) {
//            val item = data as Item
//            if (actionBar != null) {
//                actionBar!!.title = item.name
//            }
//        }
    }

    companion object {
        const val EXTRA_DATA = "data"
    }
}
