package com.example.teamapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var firebaseAuth: FirebaseAuth? = null
    var db: DatabaseReference? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        check()
        login.setOnClickListener {
            check()
        }

        signup_login.setOnClickListener {
            startActivity(Intent(applicationContext,signup::class.java))
        }
    }
    fun check() {
        firebaseAuth = FirebaseAuth.getInstance()
        if (firebaseAuth!!.currentUser != null) {
            startActivity(Intent(this, home::class.java))
            finish()
        } else {
            login()
        }
    }

    private fun login() {
        val pass = findViewById<EditText>(R.id.pass)
        val email = findViewById<EditText>(R.id.email)
        firebaseAuth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance().reference
        if (email.text.toString().isNotEmpty() && pass.text.toString().isNotEmpty()) {
            firebaseAuth!!.signInWithEmailAndPassword(
                email.text.toString(),
                pass.text.toString()
            )
                .addOnSuccessListener {
                    Toast.makeText(applicationContext, "Successful", Toast.LENGTH_SHORT)
                        .show()
                    startActivity(Intent(applicationContext, home::class.java))
                }
                .addOnFailureListener {
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
