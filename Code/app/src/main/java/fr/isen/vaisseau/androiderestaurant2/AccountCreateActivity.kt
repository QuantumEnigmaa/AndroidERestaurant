package fr.isen.vaisseau.androiderestaurant2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.vaisseau.androiderestaurant2.databinding.ActivityAccountCreateBinding
import fr.isen.vaisseau.androiderestaurant2.model.DataFoodJSON
import fr.isen.vaisseau.androiderestaurant2.model.UserDataJSON
import org.json.JSONException
import org.json.JSONObject

private lateinit var binding: ActivityAccountCreateBinding
val USER_ID = "id"

class AccountCreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Hiding password input
        binding.accountCreationPassword.transformationMethod = PasswordTransformationMethod.getInstance()

        binding.accountCreationButton.setOnClickListener {
            if (binding.accountCreationAdress.text.isEmpty() || binding.accountCreationEmail.text.isEmpty()
                || binding.accountCreationEnterFirstname.text.isEmpty() || binding.accountCreationEnterName.text.isEmpty()
                || binding.accountCreationPassword.text.isEmpty()) {

                    Log.i("creation", "Champs nuls")
                    // Alert window creation
                    val dialogBuilder = AlertDialog.Builder(this)

                    dialogBuilder.setMessage("Tous les champs doivent être remplis !").setCancelable(true)

                    val alert = dialogBuilder.create()
                    alert.show()

            } else {
                createAccout()
            }
        }
    }

    private fun createAccout() {
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/user/register"
        val data = JSONObject()

        try {
            data.put("id_shop", "1")
            data.put("firstname", binding.accountCreationFirstname.text.toString())
            data.put("lastname", binding.accountCreationName.text.toString())
            data.put("address", binding.accountCreationAdress.text.toString())
            data.put("email", binding.accountCreationEmail.text.toString())
            data.put("password", binding.accountCreationPassword.text.toString())
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        // Request a string response from the provided URL.
        val request = JsonObjectRequest(Request.Method.POST, url, data,
            {
                val gson: UserDataJSON = Gson().fromJson(it.toString(), UserDataJSON::class.java)
                Log.i("api_return_code", gson.return_code.toString())
                val sharedPreferences = getSharedPreferences(USER_PREF, MODE_PRIVATE)
                sharedPreferences.edit().putInt(USER_ID, gson.data.id).apply()
                val intent = Intent(this, CommandActivity::class.java)
                startActivity(intent)
                finish()
                /*if (gson.return_code == 200) {
                    Log.i("request", "ça marche")
                    val sharedPreferences = getSharedPreferences(USER_PREF, MODE_PRIVATE)
                    sharedPreferences.edit().putInt(USER_ID, gson.data.id).apply()
                }*/
            },
            { error -> Log.d("error", error.toString()) }
        )

        // Add the request to the RequestQueue.
        queue.add(request)
    }
}