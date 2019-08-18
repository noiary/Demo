package com.maodq.demo.internal.transition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.maodq.demo.R
import kotlinx.android.synthetic.main.item.view.*

class TransitionListFragment : Fragment() {
    var listProvider: SampleListProvider? = null
        set(value) {
            field = value
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transition_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (view as RecyclerView).apply {
            layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
            adapter = TransitionListAdapter()
        }
    }

    inner class TransitionListAdapter : RecyclerView.Adapter<TransitionViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransitionViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
            val holder = TransitionViewHolder(view)
            holder.apply {
                itemView.btn.setOnClickListener {
                    listProvider?.onSampleSelected(holder.adapterPosition)
                }
                return this
            }
        }

        override fun getItemCount(): Int {
            return listProvider!!.sampleCount
        }

        override fun onBindViewHolder(holder: TransitionViewHolder, position: Int) {
            val title = listProvider!!.getTitleForPosition(position)
            holder.itemView.btn.text = title
        }
    }

    class TransitionViewHolder(view: View) : RecyclerView.ViewHolder(view)
}

interface SampleListProvider {

    val sampleCount: Int
    fun onSampleSelected(index: Int)

    fun getTitleForPosition(index: Int): String
}
