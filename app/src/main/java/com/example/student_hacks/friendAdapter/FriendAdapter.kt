package com.example.student_hacks.friendAdapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.student_hacks.Custom_classes.User.User
import com.example.student_hacks.R

class FriendAdapter(private val context: Context, userArrayList: ArrayList<User>) : RecyclerView.Adapter<FriendAdapter.ViewHolder>() {
    private val friendArrayList: ArrayList<User>
//    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendAdapter.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.friend_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendAdapter.ViewHolder, position: Int) {
        val user = friendArrayList[position]
        holder.friendUsernameText.text = user.username
//        holder.itemView.setOnClickListener {
//            onClickListener?.onClick(position, user)
//        }
    }

    override fun getItemCount(): Int {
        return friendArrayList.size
    }

//    fun setOnClickListener(listener: OnClickListener?) {
//        this.onClickListener = listener
//    }
//
//    interface OnClickListener {
//        fun onClick(position: Int, post: User)
//    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val friendUsernameText: TextView

        init {
            friendUsernameText = itemView.findViewById(R.id.friendCardUsername)
        }
    }

    init {
        this.friendArrayList = userArrayList
    }
}