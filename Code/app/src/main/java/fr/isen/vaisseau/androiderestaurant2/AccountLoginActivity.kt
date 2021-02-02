package fr.isen.vaisseau.androiderestaurant2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.vaisseau.androiderestaurant2.databinding.ActivityAccountLoginBinding

private lateinit var binding: ActivityAccountLoginBinding

class AccountLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.accountLoginNew.setOnClickListener {
            val intent = Intent(this, AccountCreateActivity::class.java)
            startActivity(intent)
        }

        binding.accountLoginButton.setOnClickListener {
            checkUser(binding.accountLoginEmail.text.toString(), binding.accountLoginPassword.text.toString())
        }
    }

    private fun checkUser(email: String, pwd: String) {

    }
}