package com.example.textme

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.textme.adapter.MyAdapter
import com.example.textme.dataclassmodel.User
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class Dashboard : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle

    private lateinit var dbref: DatabaseReference
    private lateinit var userRecyclerView: RecyclerView
    private lateinit var userList: ArrayList<User>
    private lateinit var mAuth: FirebaseAuth
    private lateinit var adapter: MyAdapter

    private lateinit var name: TextView
    private lateinit var desc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> Toast.makeText(applicationContext,"Clicked Home", Toast.LENGTH_SHORT).show()
                R.id.nav_message -> Toast.makeText(applicationContext,"Clicked Message", Toast.LENGTH_SHORT).show()
                R.id.nav_setting -> Toast.makeText(applicationContext,"Clicked Setting", Toast.LENGTH_SHORT).show()
                R.id.nvg_login -> Toast.makeText(applicationContext,"Clicked Login", Toast.LENGTH_SHORT).show()
            }
            true
        }

        val viewContact: FloatingActionButton = findViewById(R.id.vcontact)

        viewContact.setOnClickListener{
            val intent = Intent(this,ContactList::class.java)
            startActivity(intent)
        }

        mAuth = FirebaseAuth.getInstance()
        dbref = FirebaseDatabase.getInstance().getReference()

        userList = ArrayList()
        adapter = MyAdapter(this,userList)


        //userRecyclerView = findViewById(R.id.userList)
        userRecyclerView = findViewById(R.id.userListR)
        userRecyclerView.layoutManager = LinearLayoutManager(this)
        //userRecyclerView.setHasFixedSize(true)
        userRecyclerView.adapter = adapter

        dbref.child("users").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userList.clear()
                for(postSnapshot in snapshot.children){
                    val currentUser = postSnapshot.getValue(User::class.java)
                    if(mAuth.currentUser?.uid != currentUser?.uid){
                        userList.add(currentUser!!)
                    }
                }
                adapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })

       /* name = findViewById(R.id.user_name_nav)
        desc = findViewById(R.id.user_desc_nav)

        val userName = intent.getStringExtra("username")
        val description = intent.getStringExtra("userDesc")

        name.setText(userName)
        desc.setText(description)*/

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}