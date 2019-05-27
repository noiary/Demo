package com.maodq.demo.internal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.maodq.demo.R
import com.maodq.demo.widget.CircleCountDownView

class CircleCountDownActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle_count_down)
        val ccdv = findViewById<CircleCountDownView>(R.id.ccdv)
        val btn = findViewById<Button>(R.id.btn)

        demo(ccdv, btn)
    }

    private fun demo(ccdv: CircleCountDownView?, btn: Button?) {
        btn?.setOnClickListener(View.OnClickListener {
            ccdv?.start()
        })
    }
}
