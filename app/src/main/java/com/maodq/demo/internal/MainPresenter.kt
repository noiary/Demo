package com.maodq.demo.internal

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.maodq.demo.base.BActivity
import com.maodq.demo.base.BPresenter

class MainPresenter : BPresenter() {
    fun initView(rvList: androidx.recyclerview.widget.RecyclerView, items: MutableList<Item>) {
        val linearLayoutManager =
                androidx.recyclerview.widget.LinearLayoutManager(rvList.context, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
        rvList.layoutManager = linearLayoutManager
        rvList.adapter = MyAdapter(items)
    }

    class MyAdapter(private val items: MutableList<Item>) :
            androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
            val button = Button(parent.context)
            val density = parent.context.resources.displayMetrics.density
            button.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (density * 50).toInt())

            val holder = MyViewHolder(button)
            holder.itemView.setOnClickListener {
                val item = items[holder.adapterPosition]
                val intent = Intent(parent.context, item.clazz?.java)
                intent.putExtra(BActivity.EXTRA_DATA, item.name)
                parent.context.startActivity(intent)
            }
            return holder
        }

        override fun getItemCount(): Int {
            return items.size
        }

        override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
            val item = items[position]
            val button = holder.itemView as Button
            button.text = item.name
        }

        class MyViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView)
    }
}