package com.kiki.storyapp.Login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kiki.storyapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            when {
                email.isEmpty() -> {
                    binding.etEmail.error = "Email tidak boleh kosong"
                }
                password.isEmpty() -> {
                    binding.etEmail.error = "Password tidak boleh kosong"
                }

                else -> {
                    loginViewModel.authenticate(email, password)
                }
            }
        }
        binding.tvRegister.setOnClickListener {
//            startActivity(Intent(this, RegisterActivity::class.java))
        }

    }
}