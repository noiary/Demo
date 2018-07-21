package com.maodq.demo.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.*
import android.support.annotation.ColorInt
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.util.Log
import android.view.View
import java.lang.ref.WeakReference

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
        // 间隔时长
        const val INTERVAL: Long = DURATION / 360

        // 当前动画角度
        private var currentAngel: Float = 0f
    }

    private var borderWidth: Float = 16f

    // 实心圆画笔
    private val fillPaint: Paint = Paint()
    // 空心圆画笔
    private val strokePaint: Paint = Paint()

    private lateinit var mHandler: Handler
    private var isDrawing: Boolean = false


    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context?) {
        val thread = HandlerThread("CircleCountDownHandlerThread")
        thread.start()
        mHandler = CircleCountDownHandler(thread.looper, this@CircleCountDownView)

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
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
//        val tWidthMeasureSpec = MeasureSpec.makeMeasureSpec(mWidth, MeasureSpec.EXACTLY)
//        val tHeightMeasureSpec = MeasureSpec.makeMeasureSpec(mHeight, MeasureSpec.EXACTLY)
//        setMeasuredDimension(tWidthMeasureSpec, tHeightMeasureSpec)
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
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
        if (isDrawing) {
            Log.i(TAG, "try start repeatedly during drawing")
            return
        }

        isDrawing = true
        currentAngel = 0F
        mHandler.sendEmptyMessage(0)
    }


    class CircleCountDownHandler(looper: Looper?, view: CircleCountDownView) : Handler(looper) {
        private var viewRef: WeakReference<CircleCountDownView> = WeakReference(view)

        override fun handleMessage(msg: Message?) {
            val update = viewRef.get()?.update()
            if (update!!) {
                sendEmptyMessageDelayed(0, INTERVAL)
            }

        }
    }

    private fun update(): Boolean {
        Log.d(TAG, "update: currentAngel is $currentAngel")
        if (currentAngel >= 360) {
            isDrawing = false
            return false
        }
        currentAngel += 1
        post { invalidate() }
        return true
    }
}