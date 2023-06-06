package com.example.textme

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.textme.databinding.ActivityUserProfileBinding
import com.example.textme.dataclassmodel.User
import com.example.textme.dataclassmodel.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class UserProfile : AppCompatActivity() {
    private lateinit var  binding: ActivityUserProfileBinding
    private lateinit var auth : FirebaseAuth
    private lateinit var firebasedatabase: FirebaseDatabase
    private lateinit var storage : FirebaseStorage
    private lateinit var selectedImg: Uri
    private lateinit var dialog: AlertDialog.Builder
    private lateinit var database: DatabaseReference
    private lateinit var mAuth: FirebaseAuth

    private lateinit var edtName:EditText
    private lateinit var edtDesc:EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()

        /*binding.btnSave.setOnClickListener {
            val name = binding.userName.text.toString()
            val bio = binding.userDescription.text.toString()
            database = FirebaseDatabase.getInstance().getReference("users")
            val User = User(name, bio, mAuth.currentUser?.uid!!)
            database.child(name).setValue(User).addOnSuccessListener {
                binding.userName.text.clear()
                binding.userDescription.text.clear()

                Toast.makeText(this, "Successfully saved", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show()
            }
        }*/


        dialog = AlertDialog.Builder(this)
            .setMessage("Updating Profile")
            .setCancelable(false)

        firebasedatabase = FirebaseDatabase.getInstance()
        storage = FirebaseStorage.getInstance()
        auth = FirebaseAuth.getInstance()

        binding.profilepic.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(intent,1)
        }

       //edtName = findViewById(R.id.userName)
       //edtDesc = findViewById(R.id.userDescription)

        binding.btnSave.setOnClickListener {
            if(binding.userName.text!!.isEmpty()){
                Toast.makeText(this, "Please enter your name", Toast.LENGTH_SHORT).show()
            }else if(binding.userDescription.text!!.isEmpty()){
                Toast.makeText(this, "Please enter your bio", Toast.LENGTH_SHORT).show()
            }else if(selectedImg == null){
                Toast.makeText(this, "Please select your Profile picture.", Toast.LENGTH_SHORT).show()
            }else uploadData()

           // val username  = edtName.text.toString().trim()
           // val description  = edtDesc.text.toString().trim()

           /* val passData = Intent(this,Dashboard::class.java)
            passData.putExtra("username",edtName)
            passData.putExtra("userDesc",edtDesc)
            startActivity(passData)*/
        }
    }

    private fun uploadData() {
        val reference = storage.reference.child("Profile").child(Date().time.toString())
        reference.putFile(selectedImg).addOnCompleteListener{
            if(it.isSuccessful){
                reference.downloadUrl.addOnSuccessListener { task ->
                    uploadInfo(task.toString())
                }
            }
        }
    }

    private fun uploadInfo(imgUrl: String) {
        val user = UserModel(binding.userName.text.toString(),binding.userDescription.text.toString(),imgUrl,mAuth.currentUser?.uid!!)
        firebasedatabase.reference.child("users")
            .child(auth.uid.toString())
            .setValue(user)
            .addOnSuccessListener {
                Toast.makeText(this,"Data Inserted.", Toast.LENGTH_SHORT)
                startActivity(Intent(this, Dashboard::class.java))
                finish()
            }
    }
   /* private fun addUserToDatabase(name:String, email:String, imageUrl:String, uid: String){

        database  = FirebaseDatabase.getInstance().getReference()
        database.child("user").child(uid).setValue(User(name,email,imageUrl, uid))
    }
*/
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if(data.data !=null){
                selectedImg = data.data!!
                binding.profilepic.setImageURI(selectedImg)
            }
        }
    }

}