package com.example.textme

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.textme.adapter.ContactAdapter
import com.example.textme.dataclassmodel.ContactUser
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.view_contact_list.*

class ContactList: AppCompatActivity() {

    lateinit var database:DatabaseReference
    private lateinit var contactList:ArrayList<ContactUser>
    private lateinit var contactAdapter: ContactAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.view_contact_list)

        contactList = ArrayList()
        contactAdapter = ContactAdapter(this,contactList)
        recyclerContacts.layoutManager = LinearLayoutManager(this)
        recyclerContacts.setHasFixedSize(true)
        recyclerContacts.adapter = contactAdapter

        getContactInfo()

        val actionbar = supportActionBar
        actionbar!!.title = "Contact List"
        actionbar.setDisplayHomeAsUpEnabled(true)
        actionbar.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp():Boolean{
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun getContactInfo() {

        database = FirebaseDatabase.getInstance().getReference("users")
        database.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
               if(snapshot.exists()){
                   for (contactSnapshot in snapshot.children){
                       val contact = contactSnapshot.getValue(ContactUser::class.java)
                       contactList.add(contact!!)
                   }
                   recyclerContacts.adapter = contactAdapter
               }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@ContactList, error.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

}