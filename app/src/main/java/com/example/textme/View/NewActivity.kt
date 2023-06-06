package com.example.textme.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.textme.ChatActivity
import com.example.textme.Dashboard
import com.example.textme.R
import com.example.textme.uitel.getProgressDrawable
import com.example.textme.uitel.loadImage
import kotlinx.android.synthetic.main.activity_new.*

class NewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        val contactIntent = intent
        val contactname = contactIntent.getStringExtra("name")
        //val contactnum = contactIntent.getStringExtra("number")
        val contactstatus = contactIntent.getStringExtra("description")
        val contactimage = contactIntent.getStringExtra("imageUrl")

        name.text = "Name:   "+contactname
       // num.text = "Number:   "+contactnum
        status.text = "Bio:   "+contactstatus
        img.loadImage(contactimage, getProgressDrawable(this))

        val actionbar = supportActionBar
        actionbar!!.title = "Contact Details"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp():Boolean{
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}