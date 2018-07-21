package com.maodq.demo.internal

import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.maodq.demo.base.BActivity
import com.maodq.demo.base.BPresenter

class MainPresenter : BPresenter() {
    fun initView(rvList: RecyclerView, items: MutableList<Item>) {
        val linearLayoutManager =
                LinearLayoutManager(rvList.context, LinearLayoutManager.VERTICAL, false)
        rvList.layoutManager = linearLayoutManager
        rvList.adapter = MyAdapter(items)
    }

    class MyAdapter(private val items: MutableList<Item>) :
            RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
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

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val item = items[position]
            val button = holder.itemView as Button
            button.text = item.name
        }

        class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    }
}