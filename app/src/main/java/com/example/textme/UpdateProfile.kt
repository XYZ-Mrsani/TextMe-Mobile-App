package com.example.textme

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class UpdateProfile : AppCompatActivity() {

    private lateinit var name:TextView
    private lateinit var desc:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_profile)

        name = findViewById(R.id.userName)
        desc = findViewById(R.id.userDescription)

        val userName = intent.getStringExtra("username")
        val description = intent.getStringExtra("userDesc")

        name.setText(userName)
        desc.setText(description)
    }
}