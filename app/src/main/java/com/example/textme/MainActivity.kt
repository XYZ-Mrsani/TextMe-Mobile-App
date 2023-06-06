package com.example.textme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.textme.activity.NumberActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Button
import kotlinx.coroutines.newFixedThreadPoolContext

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        auth = FirebaseAuth.getInstance()

      /*  if (auth.currentUser == null){
            startActivity(Intent(this, NumberActivity::class.java))
            finish()

        }*/

        val btnClick = findViewById<Button>(R.id.nextBtnCtn)
        btnClick.setOnClickListener {
            val intent = Intent(this, NumberActivity::class.java)
            startActivity(intent)
        }

        /*val nextButton: Button = findViewById(R.id.nextBtn)

        nextButton.setOnClickListener{
            val intent = Intent(this, NumberActivity::class.java)
            startActivity(intent)

        }*/
    }
}