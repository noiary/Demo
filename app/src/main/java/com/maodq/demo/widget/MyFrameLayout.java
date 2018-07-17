package com.maodq.demo.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import com.maodq.demo.Util;

/**
 * for Android event delivery
 */
public class MyFrameLayout extends FrameLayout {
    private static final String TAG = "ViewGroup";

    public MyFrameLayout(@NonNull Context context) {
        super(context);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override public boolean dispatchTouchEvent(MotionEvent ev) {
        Util.log(TAG, "dispatchTouchEvent:", ev.getAction());
        super.dispatchTouchEvent(ev);
        return true;
    }

    @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
        Util.log(TAG, "onInterceptTouchEvent:", ev.getAction());
        return super.onInterceptTouchEvent(ev);
//        return true;
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        Util.log(TAG, "onTouchEvent:", event.getAction());
        return super.onTouchEvent(event);
    }
}
