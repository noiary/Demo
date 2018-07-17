package com.maodq.demo.util;

import android.util.Log;

public final class Util {
    private static final String TAG = "MyLog";
    private static final String[] events = new String[]{"down", "up", "move"};

    private static android.os.Handler mHandler = new android.os.Handler();

    private static final StringBuilder stringBuilder = new StringBuilder();

    private static Runnable runnable = new Runnable() {

        @Override public void run() {
            Log.d(TAG, stringBuilder.toString());
            stringBuilder.delete(0, stringBuilder.length());
        }
    };

    public static void log(String tag, String eventName, int event) {
        StringBuilder eventNameBuilder = new StringBuilder(eventName);
        while (eventNameBuilder.length() < "onInterceptTouchEvent".length()) {
            eventNameBuilder.append(" ");
        }

        StringBuilder tagBuilder = new StringBuilder(tag);
        while (tagBuilder.length() < "↓ ViewGroup:".length()) {
            tagBuilder.append(" ");
        }


        mHandler.removeCallbacks(runnable);
        stringBuilder
                .append(" \n").append(" ↓ ")
                .append(tagBuilder).append("\t").append(eventNameBuilder)
                .append("\t").append(events[event]);
        mHandler.postDelayed(runnable, 200);
    }
}
