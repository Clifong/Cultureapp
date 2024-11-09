package com.example.student_hacks.Cultural_diary_activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.student_hacks.Custom_classes.Database.Database
import com.example.student_hacks.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Calendar

class CulturalDiaryActivity : AppCompatActivity() {

    private lateinit var titleEditText: EditText
    private lateinit var contentEditText: EditText
    private lateinit var saveDiaryButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cultural_diary)

        var bundle = intent.extras
        var id = bundle!!.getString("postId")
        var title = bundle!!.getString("title")
        var description = bundle!!.getString("description")

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        titleEditText = findViewById(R.id.titleEditText)
        contentEditText = findViewById(R.id.contentEditText)
        saveDiaryButton = findViewById(R.id.saveDiaryButton)
        titleEditText.setText(title)
        contentEditText.setText(description)

        saveDiaryButton.setOnClickListener({
            Database.updateDiaryContent(
                id!!,
                titleEditText.text.toString(),
                contentEditText.text.toString(),
                Calendar.getInstance().time.toString(),
                onSuccess = {
                    Toast.makeText(this, "Successfully created diary!", Toast.LENGTH_SHORT).show()
                },
                onFailure = {
                    e -> Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
                }
            )
        })

    }
}