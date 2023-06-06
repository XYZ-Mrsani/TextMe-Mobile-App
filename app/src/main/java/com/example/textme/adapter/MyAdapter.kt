package com.example.textme.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.textme.ChatActivity
import com.example.textme.R
import com.example.textme.dataclassmodel.User

class MyAdapter(val context: Context, val userList:ArrayList<User>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val Name : TextView = itemView.findViewById(R.id.tvfirstName)
        //val image:ImageView = itemView.findViewById(R.id.imageUrl)
        // val Bio : TextView = itemView.findViewById(R.id.tvlastName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view:View = LayoutInflater.from(parent.context).inflate(R.layout.user_item,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentitem = userList[position]
        holder.Name.text = currentitem.name
      //  holder.image.setImageResource(currentitem.imageUrl!!.toInt())
     //   holder.Bio.text = currentitem.bio

        holder.itemView.setOnClickListener{
            val intent = Intent(context, ChatActivity::class.java)
            intent.putExtra("name",currentitem.name)
            intent.putExtra("uid",currentitem.uid)
           // intent.putExtra("imageUrl",currentitem.imageUrl)

            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}