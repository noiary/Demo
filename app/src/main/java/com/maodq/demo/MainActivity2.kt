package com.maodq.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maodq.demo.internal.*

class MainActivity2 : AppCompatActivity() {

    // 入口list
    private val items = mutableListOf(
            Item("Behavior", BehaviorActivity::class),
            Item("AppbarLayout", AppbarLayoutDemo::class),
            Item("BottomSheetDialog", null),
            Item("HandlerDemo", HandlerDemoActivity::class),
            Item("Event", EventDispatchActivity::class),
            Item("OkHttpDemo", OkHttpDemoActivity::class),
            Item("CircleCountDownView", CircleCountDownActivity::class)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        MainPresenter().initView(findViewById(R.id.rv_list), items)
    }
}
