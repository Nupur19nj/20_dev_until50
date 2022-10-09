package com.example.login_dev

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.inflate
import android.widget.Toast
import com.example.login_dev.databinding.ActivityMainBinding.inflate
import com.example.login_dev.databinding.ActivitySigninBinding.inflate
import com.example.login_dev.databinding.ActivitySignupBinding
import com.example.login_dev.databinding.ActivitySignupBinding.inflate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException

class signup : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth= FirebaseAuth.getInstance()
        binding.textview.setOnClickListener {
            val intent = Intent(this, signin::class.java)
            startActivity(intent)
        }

        binding.button2.setOnClickListener {
            val email=binding.email.text.toString()
            val password = binding.password.text.toString()
            val confirm= binding.confirmPassword.text.toString()
            if(email.isNotEmpty() && password.isNotEmpty() && confirm.isNotEmpty()){
                if(password==confirm){
                    firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if(it.isSuccessful){
                              val intent = Intent(this, signin::class.java)
                            startActivity(intent)
                        }
                        else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                else{
                    Toast.makeText(this, "Password is not matching!!", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "Complete every thing!!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}