package com.example.student_hacks.Main_page_activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.student_hacks.Cultural_diary_activity.CulturalDiaryActivity
import com.example.student_hacks.Language_exchange_activity.LanguageExchangeActivity
import com.example.student_hacks.R
import com.example.student_hacks.profile_activity.ProfileActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainPageActivity : AppCompatActivity() {

    private lateinit var bottomMenu : BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        bottomMenu = findViewById(R.id.main_page_bottom_menu_bar)

        bottomMenu.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    var intent = Intent(this, MainPageActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.cultural_diary -> {
                    var intent = Intent(this, CulturalDiaryActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.language_exchange -> {
                    var intent = Intent(this, LanguageExchangeActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.profile -> {
                    var intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> {
                    true
                }
            }
        }
    }
}