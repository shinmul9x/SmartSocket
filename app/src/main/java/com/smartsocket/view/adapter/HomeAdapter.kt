package com.smartsocket.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smartsocket.R
import com.smartsocket.service.model.Home
import kotlinx.android.synthetic.main.item_home.view.*

class HomeAdapter(
    private val context: Context,
    private var list: ArrayList<Home?>,
    private val listener: ItemHomeListener
) :
    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_home, parent, false)
        return ViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setHomeList(list: ArrayList<Home?>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, private val listener: ItemHomeListener) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(home: Home?) {
            itemView.tv_title.text = home?.id.toString()
            itemView.setOnClickListener {
                listener.onClick(home)
            }
        }
    }

    class ItemHomeListener(private val clickListener: (homeID: Int) -> Unit) {
        fun onClick(home: Home?) = home?.id?.let { clickListener(it) }
    }
}