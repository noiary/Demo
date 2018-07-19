package com.maodq.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.maodq.demo.others.*

class MainActivity2 : AppCompatActivity() {

    // 入口list
    private val items = mutableListOf(
            MainPresenter.Item("Behavior", BehaviorActivity::class),
            MainPresenter.Item("AppbarLayout", AppbarLayoutDemo::class),
            MainPresenter.Item("BottomSheetDialog", null),
            MainPresenter.Item("HandlerDemo", HandlerDemoActivity::class),
            MainPresenter.Item("Event", EventDispatchActivity::class),
            MainPresenter.Item("OkHttpDemo", OkHttpDemoActivity::class)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        MainPresenter().initView(findViewById(R.id.rv_list), items)
    }
}
