package fr.isen.vaisseau.androiderestaurant2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.vaisseau.androiderestaurant2.model.UserDataJSON
import org.json.JSONObject
import java.io.File

class CommandActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command)

        //sendBasket()
    }

    private fun sendBasket() {
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/user/order"
        val data = JSONObject()

        // Get user id
        val sharePreferences = getSharedPreferences(USER_PREF, MODE_PRIVATE)
        val uid = sharePreferences.getInt(QUANT_PREF, 0)

        // Get user Basket
        val file = File(cacheDir.absolutePath+"Basket.json")
        val msg: String = file.readText()

        data.put("id_shop", "1")
        data.put("id_user", uid)
        data.put("msg", msg)

        val request = JsonObjectRequest(
            Request.Method.POST, url, data,
            {
                val gson: UserDataJSON = Gson().fromJson(it.toString(), UserDataJSON::class.java)
                Log.i("api_return_code", gson.return_code.toString())
            },
            { error -> Log.d("commandError", error.toString()) }
        )

        // Add the request to the RequestQueue.
        queue.add(request)

    }
}