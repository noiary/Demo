package com.maodq.demo.internal.transition.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.transition.Fade
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.maodq.demo.R
import com.maodq.demo.util.isVisible
import kotlinx.android.synthetic.main.lay_btn.*
import kotlinx.android.synthetic.main.lay_tv.*

class ScaleAndFadeFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_slide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn.setOnClickListener {
            val transitionSet = TransitionSet()
//                    .addTransition(Scale()) // 官方库并没有Scale，需要引入transitions everywhere或自己实现
                    .addTransition(Fade())
                    .setInterpolator(LinearOutSlowInInterpolator())
            TransitionManager.beginDelayedTransition(view as ViewGroup, transitionSet)
            tv.isVisible = !tv.isVisible
        }

    }
}