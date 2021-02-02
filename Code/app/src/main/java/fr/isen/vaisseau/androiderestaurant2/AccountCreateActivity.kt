package fr.isen.vaisseau.androiderestaurant2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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

class AccountCreateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var idShop = 1

        binding.accountCreationButton.setOnClickListener {
            createAccout(idShop)
            idShop++
        }
    }

    private fun createAccout(id: Int) {
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/user/register"
        val data = JSONObject()

        try {
            data.put("id_shop", id)
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
                Log.i("api", gson.return_code.toString())
                if (gson.return_code == 200) {
                    Toast.makeText(this, "Compte créé !", Toast.LENGTH_SHORT).show()

                }
            },
            { error -> Log.d("error", error.toString()) }
        )

        // Add the request to the RequestQueue.
        queue.add(request)
    }
}