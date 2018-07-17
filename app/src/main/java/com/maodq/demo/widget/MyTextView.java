package com.maodq.demo.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.maodq.demo.Util;

/**
 * for Android event delivery
 */
public class MyTextView extends android.support.v7.widget.AppCompatTextView {
    private static final String TAG = "TextView";

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override public boolean dispatchTouchEvent(MotionEvent ev) {
        Util.log(TAG, "dispatchTouchEvent", ev.getAction());
        return super.dispatchTouchEvent(ev);
//        return true;
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        Util.log(TAG, "onTouchEvent", event.getAction());
        return super.onTouchEvent(event);
//        return true;
    }

}
