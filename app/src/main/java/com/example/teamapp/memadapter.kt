package com.example.teamapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessControlContext

class memadapter(var userlist:ArrayList<teammem>,var context:Context):RecyclerView.Adapter<BHolder>(){
    lateinit var checkmemlist:ArrayList<teammem>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BHolder {
        val view=LayoutInflater.from(context).inflate(R.layout.teamuser,parent,false)
        checkmemlist=ArrayList()
        return BHolder(view)
    }

    override fun getItemCount(): Int {
        return userlist.size
    }

    override fun onBindViewHolder(holder: BHolder, position: Int) {
        holder.username.setText(userlist[position].name)
        holder.check.setOnCheckedChangeListener { buttonView, isChecked ->
            if (holder.check.isChecked) {
                checkmemlist.add(userlist[0])
            }
        }
        Log.d("username",userlist[position].name)
    }
    fun update(userlist: ArrayList<teammem>){
        this.userlist=userlist
        notifyDataSetChanged()
    }

}
class BHolder(itemview:View):RecyclerView.ViewHolder(itemview){
    var username=itemview.findViewById(R.id.username) as TextView
    var check=itemview.findViewById(R.id.checkboxuser) as CheckBox

}