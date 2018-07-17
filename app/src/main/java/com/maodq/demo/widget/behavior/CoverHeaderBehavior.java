package com.maodq.demo.widget.behavior;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Scroller;

import com.maodq.demo.R;

import java.util.Arrays;

/**
 * com.maodq.demo.widget.behavior.MyBehavior
 * Created by maodq on 17/2/28.
 */

public class CoverHeaderBehavior extends CoordinatorLayout.Behavior<RecyclerView> {
    private static final String TAG = "CoverHeaderBehavior";
    private int headerHeight;
    private Scroller mScroller;

    public CoverHeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(context);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, RecyclerView child, View dependency) {
        return super.layoutDependsOn(parent, child, dependency);
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, RecyclerView child, int layoutDirection) {
        Log.i(TAG, "onLayoutChild...");
        CoordinatorLayout.LayoutParams params =
                (CoordinatorLayout.LayoutParams) child.getLayoutParams();
        if (params != null && params.height == CoordinatorLayout.LayoutParams.MATCH_PARENT) {
            child.layout(0, 0, parent.getWidth(), parent.getHeight());
            child.setTranslationY(getHeaderHeight(parent.getContext()));
            return true;
        }

        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child,
                                       View directTargetChild, View target, int nestedScrollAxes) {
        Log.i(TAG, "onStartNestedScroll: nestedScrollAxes = " + nestedScrollAxes);
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }


    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target,
                                  int dx, int dy, int[] consumed) {
        Log.i(TAG, "onNestedPreScroll: dx = " + dx + ", dy = " + dy + ", consumed = "
                + Arrays.toString(consumed));

        int[] ints = onNested(child, dy);
        consumed[0] = ints[0];
        consumed[1] = ints[1];
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, RecyclerView child, View target,
                               int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        Log.i(TAG, "onNestedScroll: dxConsumed = " + dxConsumed + ", dyConsumed = " + dyConsumed
                + ", dxUnconsumed = " + dxUnconsumed + ", dyUnconsumed = " + dyUnconsumed);

        Log.i(TAG, "scrollY = " + child.getScaleY());
//        int dy = dyConsumed + dyUnconsumed;
//        onNested(child, dy);
    }

    @Override
    public boolean onNestedPreFling(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, float velocityX, float velocityY) {
        Log.i(TAG, "onNestedPreFling: velocityX = " + velocityX + ", velocityY = " + velocityY);
//        child.fling((int) velocityX, (int) velocityY);
        return false;
    }

    private int[] onNested(View scrollView, float dy) {
        int[] consumed = new int[2];
        final float translationY = scrollView.getTranslationY();
        final float headerHeight = getHeaderHeight(scrollView.getContext());

        if (dy < 0 && translationY < headerHeight && !ViewCompat.canScrollVertically(scrollView, -1)) {
            // We're scrolling down
            final float distance = Math.min(headerHeight, translationY - dy);
            scrollView.setTranslationY(distance);
            consumed[1] = (int) distance;
        } else if (dy > 0 && translationY > 0) {
            // We're scrolling up
            final float distance = Math.max(0, translationY - dy);
            scrollView.setTranslationY(distance);
            consumed[1] = (int) distance;
        }

        return consumed;
    }

    @Override
    public boolean onNestedFling(CoordinatorLayout coordinatorLayout, RecyclerView child, View target, float velocityX, float velocityY, boolean consumed) {
        return super.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
    }

    private float getHeaderHeight(Context context) {
        if (headerHeight == 0) {
            headerHeight = context.getResources().getDimensionPixelOffset(R.dimen.header_height);
        }
        return headerHeight;
    }
}
