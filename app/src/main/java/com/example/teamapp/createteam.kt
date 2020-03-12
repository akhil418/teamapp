package com.example.teamapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_createteam.*

class createteam : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var db: DatabaseReference
    lateinit var memlist: ArrayList<teammem>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createteam)
        firebaseAuth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance().reference
        memlist = ArrayList()
        creatteam.setOnClickListener {
            createTeamUser()
        }
        floatingActionButton.setOnClickListener{
            addmem()
        }
    }

    private fun addmem() {
        startActivity(Intent(this,addmem::class.java))
    }

    private fun createTeamUser() {
        val tname = teamname.text.toString().trim()
        val loc = location.text.toString().trim()
        if (tname.isNotEmpty() && loc.isNotEmpty()) {
            if (memlist.size in 12..15) {
                val teamhashMap = hashMapOf<String, HashMap<String, String>>()
                val memhashMap = hashMapOf<String, String>()
                for (l in memlist) {
                    memhashMap["name"] = l.name
                    memhashMap["email"] = l.email
                    teamhashMap[l.uid] = memhashMap
                }
                val r = db.child("users")
                    .child(firebaseAuth.currentUser?.uid.toString())
                    .child("teams")
                    .child(tname)
                r.setValue(teamhashMap)
                r.child("location").setValue(loc).addOnSuccessListener {
                    Toast.makeText(
                        applicationContext,
                        "Team is created successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } else {
                Toast.makeText(
                    applicationContext,
                    "Member count should be in between 11 to 15",
                    Toast.LENGTH_SHORT
                ).show()
            }
        } else {
            Toast.makeText(applicationContext, "Please Enter details", Toast.LENGTH_SHORT).show()
        }
    }
}
