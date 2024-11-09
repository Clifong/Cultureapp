package com.example.student_hacks.Cultural_diary_activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.Custom_classes.Post_class.Post
import com.example.student_hacks.R
import layout.PostAdapter


class All_my_diary : AppCompatActivity() {

    private lateinit var addNewDiaryButton : Button
    private lateinit var  allPostListView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_my_diary)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        val allDiary : ArrayList<Post> = Database.getAllMyDiary()

        allPostListView = findViewById(R.id.allDiaryRecycleView)
        val postAdapter = PostAdapter(this, allDiary)
        allPostListView.adapter = postAdapter

        val linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        allPostListView.layoutManager = linearLayoutManager

        addNewDiaryButton = findViewById(R.id.addNewDiaryButton)
        addNewDiaryButton.setOnClickListener({
            var intent = Intent(this, CulturalDiaryActivity::class.java)
            intent.putExtra("postId", "")
            startActivity(intent)
        })
    }
}

