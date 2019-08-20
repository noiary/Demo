package com.maodq.demo.internal.transition

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maodq.demo.R
import com.maodq.demo.internal.ItemF
import com.maodq.demo.internal.transition.fragments.*

class TransitionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition)
        val fragment = TransitionListFragment()
        fragment.listProvider = MyListProvider()
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fl_container, fragment)
                .commit()
    }

    private fun onItemClick(index: Int) {
        val fragment = items[index].fragment
        supportFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.fade_in, R.anim.fade_out,
                        R.anim.fade_in, R.anim.fade_out)
                .replace(R.id.fl_container, fragment)
                .addToBackStack(index.toString())
                .commit()
    }


    // 入口list
    private val items = mutableListOf(
            ItemF("Auto Transition", AutoTransitionFragment()),
            ItemF("Change Bounds", ChangeBoundsFragment()),
            ItemF("Path Motion", PathMotion()),
            ItemF("Path Motion", SlideFragment()),
            ItemF("Scale and Fade", ScaleAndFadeFragment())
    )

    private inner class MyListProvider : SampleListProvider {
        override val sampleCount: Int
            get() = items.size

        override fun onSampleSelected(index: Int) {
            onItemClick(index)
        }

        override fun getTitleForPosition(index: Int): String {
            return items[index].name
        }

    }
}
