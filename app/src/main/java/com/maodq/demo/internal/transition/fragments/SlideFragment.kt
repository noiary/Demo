package com.maodq.demo.internal.transition.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.Slide
import androidx.transition.TransitionManager
import com.maodq.demo.R
import com.maodq.demo.util.isVisible
import kotlinx.android.synthetic.main.lay_btn.*
import kotlinx.android.synthetic.main.lay_tv.*

class SlideFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_slide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn.setOnClickListener {
            val transition = Slide(Gravity.END)
            transition.duration = 500
            TransitionManager.beginDelayedTransition(view as ViewGroup, transition)
            tv.isVisible = !tv.isVisible
        }

    }
}