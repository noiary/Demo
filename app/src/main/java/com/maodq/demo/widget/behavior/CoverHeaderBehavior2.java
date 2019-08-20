package com.maodq.demo.widget.behavior;

import android.content.Context;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.view.View;

import com.maodq.demo.R;

/**
 * com.maodq.demo.widget.behavior.MyBehavior
 * Created by maodq on 17/2/28.
 */

public class CoverHeaderBehavior2 extends CoordinatorLayout.Behavior<View> {
    private static final String TAG = "CoverHeaderBehavior";
    private int headerHeight;
    int offsetTotal = 0;
    boolean scrolling = false;

    public CoverHeaderBehavior2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, View child, View directTargetChild, View target, int nestedScrollAxes) {
        return true;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, View child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        offset(child, dyConsumed);
    }

    public void offset(View child,int dy){
        int old = offsetTotal;
        int top = offsetTotal - dy;
        top = Math.max(top, -child.getHeight());
        top = Math.min(top, 0);
        offsetTotal = top;
        if (old == offsetTotal){
            scrolling = false;
            return;
        }
        int delta = offsetTotal-old;
        child.offsetTopAndBottom(delta);
        scrolling = true;
    }

    private float getHeaderHeight(Context context) {
        if (headerHeight == 0) {
            headerHeight = context.getResources().getDimensionPixelOffset(R.dimen.header_height);
        }
        return headerHeight;
    }
}
