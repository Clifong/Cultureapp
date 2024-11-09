package layout

import com.example.student_hacks.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.student_hacks.Custom_classes.Post_class.Post

class PostAdapter(private val context: Context, postArrayList: ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.ViewHolder>()
    {
        private val postArrayList: ArrayList<Post>

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostAdapter.ViewHolder {
            val view: View = LayoutInflater.from(parent.context).inflate(com.example.student_hacks.R.layout.diary_card_view, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: PostAdapter.ViewHolder, position: Int) {
            val post = postArrayList[position]
            holder.diaryTitle.text = post.title
            holder.dateTitle.text = "??"
        }

        override fun getItemCount(): Int {
            return postArrayList.size
        }

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val diaryTitle : TextView
            val dateTitle : TextView

            init {
                diaryTitle = itemView.findViewById(R.id.diary_card_title)
                dateTitle = itemView.findViewById(R.id.diary_card_date)
            }
        }

        init {
            this.postArrayList = postArrayList
        }
}