package com.example.student_hacks.Chat_activity.MessageAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.student_hacks.Custom_classes.Message_class.Message
import com.example.student_hacks.R

class MessageAdapter(private val context: Context, messageArrayList: ArrayList<Message>) : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {
    private val messageArrayList: ArrayList<Message>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.friend_card_view, parent, false)
        return ViewHolder(view)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val msg = messageArrayList[position]
        holder.messageText.text = msg.text
        holder.messageDateText.text = msg.time
        if (msg.myMessage) {
            holder.itemView.setBackgroundColor(android.R.color.holo_purple)
        }
    }

    override fun getItemCount(): Int {
        return messageArrayList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val messageText: TextView
        val messageDateText: TextView

        init {
            messageText = itemView.findViewById(R.id.messageTextView)
            messageDateText = itemView.findViewById(R.id.messageDateTextView)
        }
    }

    init {
        this.messageArrayList = messageArrayList
    }
}