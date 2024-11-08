package com.example.student_hacks.Login_activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.Main_page_activity.MainPageActivity
import com.example.student_hacks.R

class EmailRegisterActivity : AppCompatActivity() {

    private lateinit var registerButton : Button
    private lateinit var registerEmailField : EditText
    private lateinit var registerPasswordField : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_register)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        registerButton = findViewById(R.id.register_button)
        registerEmailField = findViewById(R.id.emailRegisterField)
        registerPasswordField = findViewById(R.id.passwordRegisterField)

        registerButton.setOnClickListener() {
            if (registerEmailField.text.length == 0 || registerPasswordField.text.length == 0) {
                Toast.makeText(this, "Email or password cannot be empty", Toast.LENGTH_SHORT).show()
            }
            else {
                try {
                    Database.signUp(registerEmailField.text.toString(), registerPasswordField.text.toString())
//                    var intent = Intent(this, MainPageActivity::class.java)
//                    startActivity(intent)
                } catch (e : Exception) {
                    Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show()
                }
            }

        }
    }
}