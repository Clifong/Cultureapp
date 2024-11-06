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

        Database.setDatabase(FirebaseDB())

        emailLoginButton = findViewById(R.id.login_via_email_button)
        emailLoginButton.setOnClickListener {
            val intent = Intent(this, EmailLoginActivity::class.java)
            startActivity(intent)
        }

        // Initialize UI components
//        saveButton = findViewById(R.id.saveButton)
//        nameEditText = findViewById(R.id.editTextText)
//        ageSpinner = findViewById(R.id.ageSpinner)
//        countrySpinner = findViewById(R.id.countrySpinner)
//
//        // Set up save button click listener
//        saveButton.setOnClickListener {
//            saveProfile()
//        }

        // Load profiles from Firebase into the ScrollView's LinearLayout
//        loadProfiles()
    }

    // Function to save the profile to Firebase
//    private fun saveProfile() {
//        val name = nameEditText.text.toString()
//        val age = ageSpinner.selectedItem.toString().toIntOrNull() ?: 0
//        val country = countrySpinner.selectedItem.toString()
//
//        if (name.isNotEmpty()) {
//            val profile = Profile(name, age, country)
//
//
//            val profileId = database.push().key ?: return
//            database.child(profileId).setValue(profile)
//                .addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        Toast.makeText(this, "Profile saved successfully", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(this, "Failed to save profile", Toast.LENGTH_SHORT).show()
//                    }
//                }
//        } else {
//            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    // Function to load profiles from Firebase and add them to the ScrollView
//    private fun loadProfiles() {
//        // Get the LinearLayout inside the ScrollView where profiles will be displayed
//        val profileContainer: LinearLayout = findViewById(R.id.profileContainer)
//
//        // Listener to read data from Firebase database
//        database.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                // Clear any existing views in the profileContainer
//                profileContainer.removeAllViews()
//
//                for (profileSnapshot in snapshot.children) {
//                    val profile = profileSnapshot.getValue(Profile::class.java)
//
//                    profile?.let {
//                        // Textview to display each profile's data
//                        val profileView = TextView(this@MainActivity).apply {
//                            text = "${it.name}, ${it.age} years old, from ${it.country}"
//                            textSize = 16f
//                            setPadding(8, 8, 8, 8)
//                        }
//                        // Add the TextView to the LinearLayout
//                        profileContainer.addView(profileView)
//                    }
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(this@MainActivity, "Failed to load profiles", Toast.LENGTH_SHORT).show()
//            }
//        })
//    }
}
