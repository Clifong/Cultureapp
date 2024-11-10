package layout

import com.example.student_hacks.R
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.student_hacks.Cultural_diary_activity.CulturalDiaryActivity
import com.example.student_hacks.Custom_classes.Post_class.Post

class PostAdapter(private val context: Context, postArrayList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>()
    {
        private val postArrayList: ArrayList<Post>
        private var onClickListener: OnClickListener? = null

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(R.layout.diary_card_view, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
            val post = postArrayList[position]
            holder.diaryTitle.text = post.title
            holder.dateTitle.text = post.time
            holder.likedBy.setText("Likes: " +  post.likedBy.size.toString())
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
            fun onClick(position: Int, post : Post)
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val diaryTitle : TextView
            val dateTitle : TextView
            val likedBy : TextView

            init {
                diaryTitle = itemView.findViewById(R.id.diary_card_title)
                dateTitle = itemView.findViewById(R.id.diary_card_date)
                likedBy = itemView.findViewById(R.id.DIaryLikedByCountTextView)
            }
        }

        init {
            this.postArrayList = postArrayList
        }
}