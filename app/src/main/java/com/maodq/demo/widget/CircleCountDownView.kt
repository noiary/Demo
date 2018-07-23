package com.maodq.demo.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Build
import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Log
import android.view.View

/**
 * 圆形倒计时控件,用于Splash页面
 * Create by maodq on 2018/7/21
 */
class CircleCountDownView : View {
    companion object {
        private const val TAG = "CircleCountDownView"
        // 背景色
        @ColorInt
        private val backgroundColor: Int = Color.GRAY
        // 边框色
        @ColorInt
        private val borderColor: Int = Color.RED
        // 动画时长(毫秒)
        private const val DURATION: Long = 3000
        // 间隔时长(毫秒)
        private const val INTERVAL: Long = 20

        // 当前动画角度
        private var currentAngel: Float = 0f
    }

    private var valueAnimator: ValueAnimator? = null
    private var borderWidth: Float = 16f
    // 实心圆画笔
    private val fillPaint: Paint = Paint()
    // 空心圆画笔
    private val strokePaint: Paint = Paint()
    private var startTime: Long = 0L

    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context?) {
        val density = context?.resources?.displayMetrics?.density
        borderWidth *= density!!
        fillPaint.isAntiAlias = true
        fillPaint.color = backgroundColor

        strokePaint.isAntiAlias = true
        strokePaint.color = borderColor
        strokePaint.strokeWidth = borderWidth
        strokePaint.style = Paint.Style.STROKE
        strokePaint.strokeCap = Paint.Cap.ROUND // 设置stroke形状为圆头
//        strokePaint!!.strokeJoin = Paint.Join.MITER  // 拐角形状 有MITER，BEVEL和ROUND

        initAnimator()
    }

    private fun initAnimator() {
        valueAnimator = ValueAnimator.ofFloat(0f, 100f)
        valueAnimator!!.duration = DURATION
        valueAnimator!!.addUpdateListener {
            update(it.animatedValue as Float)
        }
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val cx = measuredWidth / 2f
        // 画实心圆
        fillPaint.color = 0xaa3c3f41.toInt()
        canvas?.drawRect(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat(), fillPaint)
        fillPaint.color = 0xff2b2b2b.toInt()
        canvas?.drawCircle(cx, measuredHeight / 2f, cx, fillPaint)
        val halfBorderWidth = borderWidth / 2
        canvas?.drawArc(halfBorderWidth, halfBorderWidth, measuredWidth - halfBorderWidth, measuredHeight - halfBorderWidth, 0f, currentAngel, false, strokePaint)
    }

    fun start() {
        if (valueAnimator!!.isRunning) {
            Log.i(TAG, "try start repeatedly during drawing")
            return
        }
        startTime = System.currentTimeMillis()
        currentAngel = 0F
        valueAnimator!!.start()
    }

    private fun update(@FloatRange(from = 0.0, to = 100.0) progress: Float): Boolean {
        Log.d(TAG, "update: currentAngel is $currentAngel")
        if (currentAngel >= 359) {
//            valueAnimator.cancel()
            val endTime = System.currentTimeMillis()
            val duration = endTime - startTime
            Log.i(TAG, "duration = $duration")
            return false
        }
        currentAngel = progress * 3.6f
        invalidate()
        return true
    }
}