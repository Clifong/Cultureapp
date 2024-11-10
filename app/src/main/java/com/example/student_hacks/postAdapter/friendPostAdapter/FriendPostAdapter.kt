package com.example.student_hacks.postAdapter.friendPostAdapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.student_hacks.Custom_classes.Post_class.Post
import com.example.student_hacks.R
import layout.PostAdapter

class FriendPostAdapter(private val context: Context, friendPostArrayList: ArrayList<Post>) : RecyclerView.Adapter<FriendPostAdapter.ViewHolder>() {
    private val postArrayList: ArrayList<Post>
    private var onClickListener: OnClickListener? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendPostAdapter.ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.diary_card_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendPostAdapter.ViewHolder, position: Int) {
        val post = postArrayList[position]
        holder.diaryTitle.text = post.title
        holder.dateTitle.text = post.time
        holder.itemView.setOnClickListener {
            onClickListener?.onClick(position, post)
        }
    }

    override fun getItemCount(): Int {
        return postArrayList.size
    }

    fun setOnClickListener(listener: OnClickListener?) {
        this.onClickListener = listener
    }

    interface OnClickListener {
        fun onClick(position: Int, post: Post)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val diaryTitle: TextView
        val dateTitle: TextView

        init {
            diaryTitle = itemView.findViewById(R.id.diary_card_title)
            dateTitle = itemView.findViewById(R.id.diary_card_date)
        }
    }

    init {
        this.postArrayList = friendPostArrayList
    }
}
