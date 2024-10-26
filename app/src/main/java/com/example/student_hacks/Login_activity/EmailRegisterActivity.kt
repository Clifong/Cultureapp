package com.example.student_hacks.Login_activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.student_hacks.Main_page_activity.MainPageActivity
import com.example.student_hacks.R

class EmailRegisterActivity : AppCompatActivity() {

    private lateinit var registerButton : Button
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_register)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        registerButton = findViewById(R.id.register_button)

        registerButton.setOnClickListener() {
            var intent = Intent(this, MainPageActivity::class.java)
            startActivity(intent)
        }
    }
}