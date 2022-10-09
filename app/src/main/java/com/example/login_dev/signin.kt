package com.example.login_dev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login_dev.databinding.ActivitySigninBinding
import com.google.firebase.auth.FirebaseAuth

class signin : AppCompatActivity() {
    private lateinit var binding: ActivitySigninBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySigninBinding.inflate(layoutInflater)

        setContentView(binding.root)
        firebaseAuth= FirebaseAuth.getInstance()

        binding.textview.setOnClickListener {
            val intent = Intent(this, signup::class.java)
            startActivity(intent)
        }

        binding.button.setOnClickListener {
            val email = binding.email.text.toString()
            val password = binding.password.text.toString()
            if (email.isNotEmpty() && password.isNotEmpty()) {
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                            if (it.isSuccessful) {
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                } else {
                    Toast.makeText(this, "Empty fields are not allowed!!", Toast.LENGTH_SHORT).show()
                }
        }
    }
override fun onStart() {
    super.onStart()

    if(firebaseAuth.currentUser != null){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
}