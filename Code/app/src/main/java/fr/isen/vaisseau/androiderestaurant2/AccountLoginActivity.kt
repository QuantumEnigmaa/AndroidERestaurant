package fr.isen.vaisseau.androiderestaurant2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.vaisseau.androiderestaurant2.databinding.ActivityAccountLoginBinding
import fr.isen.vaisseau.androiderestaurant2.model.UserDataJSON
import org.json.JSONObject

private lateinit var binding: ActivityAccountLoginBinding

class AccountLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hiding password text
        binding.accountLoginPassword.transformationMethod = PasswordTransformationMethod.getInstance()

        binding.accountLoginNew.setOnClickListener {
            val intent = Intent(this, AccountCreateActivity::class.java)
            startActivity(intent)
        }

        binding.accountLoginButton.setOnClickListener {
            if (binding.accountLoginEmail.text.isEmpty() || binding.accountLoginPassword.text.isEmpty()) {
                // Alert window creation
                val dialogBuilder = AlertDialog.Builder(this)

                dialogBuilder.setMessage("Tous les champs doivent être remplis !").setCancelable(true)

                val alert = dialogBuilder.create()
                alert.show()
            } else {
                val sharePreferences = getSharedPreferences(USER_PREF, MODE_PRIVATE)
                val uid = sharePreferences.getInt(USER_ID, 0)
                if (uid.toString().isNotEmpty()) {
                    val intent = Intent(this, CommandActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val dialogBuilder = AlertDialog.Builder(this)

                    dialogBuilder.setMessage("Erreur, ce compte n'existe pas").setCancelable(true)

                    val alert = dialogBuilder.create()
                    alert.show()
                }
            }
        }
    }
}