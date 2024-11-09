package com.example.student_hacks
import com.example.student_hacks.Main_page_activity.Profile
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.Custom_classes.Specific.FirebaseDB
import com.example.student_hacks.Login_activity.EmailLoginActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    private lateinit var emailLoginButton: Button
    private lateinit var saveButton: Button
    private lateinit var nameEditText: EditText
    private lateinit var ageSpinner: Spinner
    private lateinit var countrySpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)
        Database.initDatabase(FirebaseDB())

        emailLoginButton = findViewById(R.id.login_via_email_button)
        emailLoginButton.setOnClickListener {
            val intent = Intent(this, EmailLoginActivity::class.java)
            startActivity(intent)
        }
    }
}
