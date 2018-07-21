package com.maodq.demo.internal;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.maodq.demo.R;
import com.maodq.demo.util.Util;

public class EventDispatchActivity extends Activity {

    private static final String TAG = "Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_dispatch_demo);
    }

    @Override public boolean dispatchTouchEvent(MotionEvent ev) {
        Util.log(TAG, "dispatchTouchEvent",ev.getAction());
        return super.dispatchTouchEvent(ev);
//        return true;
    }

    @Override public boolean onTouchEvent(MotionEvent event) {
        Util.log(TAG, "onTouchEvent",event.getAction());
        return super.onTouchEvent(event);
//        return true;
    }
}
