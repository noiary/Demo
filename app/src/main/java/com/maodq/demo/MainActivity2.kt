package com.maodq.demo

import android.os.Bundle
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.maodq.demo.internal.*
import com.maodq.demo.internal.transition.TransitionActivity

class MainActivity2 : AppCompatActivity() {

    // 入口list
    private val items = mutableListOf(
            Item("Behavior", BehaviorActivity::class),
            Item("AppbarLayout", AppbarLayoutDemo::class),
//            Item("BottomSheetDialog", null),
            Item("HandlerDemo", HandlerDemoActivity::class),
            Item("Event", EventDispatchActivity::class),
            Item("OkHttpDemo", OkHttpDemoActivity::class),
            Item("CircleCountDownView", CircleCountDownActivity::class),
            Item("Transition", TransitionActivity::class),
            Item("WebView", WebViewActivity::class)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        MainPresenter().initView(findViewById(R.id.rv_list), items)
    }
}
