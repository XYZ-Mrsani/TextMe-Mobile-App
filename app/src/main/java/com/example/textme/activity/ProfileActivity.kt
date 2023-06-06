package com.example.textme.activity
//anjalee
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.textme.R
import com.example.textme.UserProfile

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val btnCtn: Button = findViewById(R.id.button)

        btnCtn.setOnClickListener{
            val intent = Intent(this,UserProfile::class.java)
            startActivity(intent)
        }
    }
}