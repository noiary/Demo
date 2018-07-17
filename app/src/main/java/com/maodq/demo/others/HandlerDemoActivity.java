package com.maodq.demo.others;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.maodq.demo.R;

import java.lang.ref.WeakReference;

/**
 * HandlerDemoActivity
 * Created by maodq on 2017/7/3.
 */

public class HandlerDemoActivity extends Activity {
    static WeakReference<Activity> mActivityRef;
    private static final String TAG = "HandlerDemoActivity";
    private Handler handler;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_demo);
        mActivityRef = new WeakReference<Activity>(this);
        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                test();
            }
        });
    }

    private void test() {
        new Thread(new Runnable() {
            @Override public void run() {
                Looper.prepare();
                handler = new MyHandler();
                Looper.loop();
            }
        }).start();

        new Thread(new Runnable() {
            @Override public void run() {
                for (int i = 0; i < 2100000000; i++) {
                    if (handler != null)
                        handler.sendEmptyMessage(i);
                }
            }
        }).start();
    }

    private static class MyHandler extends Handler {
        public MyHandler() {
            super();
        }

        @Override public void handleMessage(Message msg) {
            Activity activity = mActivityRef.get();
            if (activity != null && msg.what % 3 == 0) {
                Log.d(TAG, "test " + msg.what);
            }
        }
    }

    @Override protected void onDestroy() {
        handler = null;
        super.onDestroy();
    }
}
