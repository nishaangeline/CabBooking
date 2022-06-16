package com.example.cabapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
class MainActivity : AppCompatActivity() {
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    lateinit var signedInMail: TextView
    lateinit var email:EditText
    lateinit var password:EditText
    lateinit var btnSignUp:Button
    lateinit var btnSignIn:Button
    lateinit var btnLogin:Button

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        email=findViewById(R.id.editTextTextEmailAddress);
        password=findViewById(R.id.editTextTextPassword);
        btnLogin=findViewById(R.id.button3);
        btnSignUp=findViewById(R.id.button);
        val roles = resources.getStringArray(R.array.Roles)
        val arrayAdapter = ArrayAdapter(this,R.layout.dropdown_item,roles)
        val autoCompleteTextView=findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        autoCompleteTextView.setAdapter(arrayAdapter)
        btnSignUp.setOnClickListener{
            signUpUser()
        }
        btnLogin.setOnClickListener{
            loginUser()
        }


    }
    private fun signUpUser()
    {
        val email_val=email.text.toString();
        val pass_val=password.text.toString();
        firebaseAuth.createUserWithEmailAndPassword(email_val, pass_val)
            .addOnFailureListener {
                Toast.makeText(baseContext, "Failed", Toast.LENGTH_SHORT).show()
                Log.e("FireException", it.message.toString())
            }.addOnCompleteListener{
                Toast.makeText(baseContext, "Success", Toast.LENGTH_SHORT).show()


            }
    }
    private fun loginUser()
    {
        val email_val=email.text.toString()
        val pass_val=password.text.toString()
        firebaseAuth.signInWithEmailAndPassword(email_val, pass_val)
            .addOnFailureListener {
                Toast.makeText(baseContext, "Failed", Toast.LENGTH_SHORT).show()
                Log.e("FireException", it.message.toString())
            }.addOnSuccessListener {
                Toast.makeText(baseContext, "Success", Toast.LENGTH_SHORT).show()
                val intent= Intent(this,AdminLogin::class.java)
                startActivity(intent)
            }



    }


}