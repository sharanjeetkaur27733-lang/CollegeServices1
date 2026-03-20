package com.example.collegeservices

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter

class ChatAdapter(val list: ArrayList<Person>,val onClick: OnItemClickListener): Adapter<ChatAdapter.ChatViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChatViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_item_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ChatViewHolder,
        position: Int
    ) {

        val item = list[position]

        holder.name.text = item.name
        holder.name.text = item.name
        holder.name.text = item.name
        holder.name.text = item.name
        holder.name.text = item.name
        holder.name.text = item.name


        holder.view.setOnClickListener {

            onClick.onItemClick(position)
        }

        holder.name.setOnClickListener {
            onClick.onTimeClick(position)
        }
    }

    override fun getItemCount(): Int {

        return list.size
    }


    inner class ChatViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val name = view.findViewById<TextView>(R.id.name)



    }

    interface OnItemClickListener {

        fun onItemClick(position: Int)
        fun onTimeClick(position: Int)


    }
}

