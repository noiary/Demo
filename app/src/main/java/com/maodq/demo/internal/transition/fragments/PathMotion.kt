package com.maodq.demo.internal.transition.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.transition.ArcMotion
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager
import com.maodq.demo.R
import kotlinx.android.synthetic.main.fragment_change_bounds.*

class PathMotion : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_change_bounds, container, false)
    }

    var mToRightAnimation: Boolean = false
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lp = btn_change_bounds.layoutParams as FrameLayout.LayoutParams
        btn_change_bounds.setOnClickListener {
            mToRightAnimation = !mToRightAnimation
            val transition = ChangeBounds()
            transition.duration = 500
            transition.setPathMotion(ArcMotion())
            TransitionManager.beginDelayedTransition(fl_container,transition)
            lp.gravity = if (mToRightAnimation) Gravity.BOTTOM or Gravity.END else Gravity.TOP or Gravity.START
            btn_change_bounds.layoutParams = lp
        }

    }
}