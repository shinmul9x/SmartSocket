package com.smartsocket.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smartsocket.R
import com.smartsocket.service.model.Room
import com.smartsocket.view.adapter.RoomAdapter.ViewHolder
import kotlinx.android.synthetic.main.item_room.view.*

class RoomAdapter(
    private val context: Context,
    private var list: ArrayList<Room?>,
    private val listener: ItemRoomListener
) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_room, parent, false)
        return ViewHolder(view, listener)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    fun setRoomList(list: ArrayList<Room?>) {
        this.list = list
        notifyDataSetChanged()
    }

    class ViewHolder(
        itemView: View, private val listener: ItemRoomListener,
        private var droped: Boolean = false
    ) :
        RecyclerView.ViewHolder(itemView) {
        fun bind(room: Room?) {
            itemView.tv_room_name.text = room?.id.toString()
            itemView.btn_drop_device.setOnClickListener {
                itemView.btn_drop_device.setImageResource(
                    if (droped) {
                        listener.onClick(null)
                        R.drawable.ic_arrow_drop_down_black_24dp
                    } else {
                        listener.onClick(room)
                        R.drawable.ic_arrow_drop_up_black_24dp
                    }
                )
                droped = !droped
            }
        }
    }

    class ItemRoomListener(private val clickListener: (room: Room?) -> Unit) {
        fun onClick(room: Room?) = clickListener(room)
    }
}