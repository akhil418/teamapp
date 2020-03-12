package com.example.teamapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_signup.*

class signup : AppCompatActivity() {
    lateinit var firebaseAuth: FirebaseAuth
    lateinit var db: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        firebaseAuth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance().reference
        signup2.setOnClickListener {
            sign()
        }
    }
    private fun sign() {

        if (email2.text.toString().isNotEmpty() && pass2.text.toString().isNotEmpty()) {
            firebaseAuth.createUserWithEmailAndPassword(
                email2.text.toString(),
                pass2.text.toString()
            )
                .addOnSuccessListener{ authResult ->
                    Log.d("sss","done")
                    db.child("users").child(authResult.user!!.uid).child("email").setValue(email2.text.toString())
                    db.child("users").child(authResult.user!!.uid).child("username").setValue(username.text.toString())
                    db.child("users").child(authResult.user!!.uid).child("ph").setValue(phno.text.toString())
                    startActivity(Intent(applicationContext, home::class.java))
                }
                .addOnFailureListener{
                    Toast.makeText(
                        applicationContext,
                        "Please Try Again",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        } else {
            Toast.makeText(
                applicationContext,
                "Please enter valid details",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
