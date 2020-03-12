package com.example.teamapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_addmem.*

class addmem : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var db: DatabaseReference
    lateinit var list: ArrayList<teammem>
    lateinit var adapter:memadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_addmem)
        firebaseAuth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance().reference
        list = ArrayList()
        adapter= memadapter(list,applicationContext)
        listmem.layoutManager=LinearLayoutManager(applicationContext)
        listmem.adapter=adapter
        db.child("users").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists() and p0.hasChildren()) {
                    for (ds in p0.children) {
                        list.add(
                            teammem(
                                ds.child("username").value.toString(),
                                ds.key.toString(),
                                ds.child("email").value.toString()
                            )
                        )
                        Log.d("name",ds.child("username").value.toString())
                    }
                    adapter.update(list)
                }
            }



        })

    }

}
