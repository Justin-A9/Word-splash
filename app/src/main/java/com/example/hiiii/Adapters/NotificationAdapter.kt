package com.example.hiiii.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hiiii.R
import com.example.hiiii.data.NotificationDataClass

class NotificationAdapter(private val messageList: ArrayList<NotificationDataClass>) :RecyclerView.Adapter<NotificationAdapter.ViewHolder>(){
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val message = itemView.findViewById<TextView>(R.id.notification_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notification_card_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val messages : NotificationDataClass = messageList[position]
        holder.message.text = messages.message
    }

    override fun getItemCount(): Int {
      return  messageList.size
    }
}