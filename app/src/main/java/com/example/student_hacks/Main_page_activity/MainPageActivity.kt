package com.example.student_hacks.Main_page_activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.student_hacks.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainPageActivity : AppCompatActivity() {

    private lateinit var bottomMenu : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        bottomMenu = findViewById(R.id.main_page_bottom_menu_bar)
    }
}