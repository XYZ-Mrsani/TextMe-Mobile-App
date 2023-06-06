package com.example.textme.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.textme.R
import com.example.textme.View.NewActivity
import com.example.textme.databinding.ContactItemBinding
import com.example.textme.dataclassmodel.ContactUser

class ContactAdapter(
    var c:Context, var contactList:ArrayList<ContactUser>
):RecyclerView.Adapter<ContactAdapter.ContactViewHolder>()
{
    inner class ContactViewHolder(var v: ContactItemBinding):RecyclerView.ViewHolder(v.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val inflter = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<ContactItemBinding>(
            inflter, R.layout.contact_item,parent,
            false)
        return ContactViewHolder(v)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val newList = contactList[position]
        holder.v.isContact = contactList[position]
        holder.v.root.setOnClickListener{
            val image = newList.imageUrl
            val name = newList.name
            //val number = newList.number
            val status = newList.description

            val mIntent = Intent(c, NewActivity::class.java)
            mIntent.putExtra("description",status)
            mIntent.putExtra("imageUrl",image)
            mIntent.putExtra("name",name)
            //mIntent.putExtra("number",number)
            c.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int {
        return contactList.size
    }
}