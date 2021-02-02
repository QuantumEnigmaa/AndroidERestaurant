package fr.isen.vaisseau.androiderestaurant2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import fr.isen.vaisseau.androiderestaurant2.databinding.ActivityCommandBinding
import fr.isen.vaisseau.androiderestaurant2.model.UserDataJSON
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.json.JSONObject
import java.io.File

private lateinit var binding: ActivityCommandBinding

class CommandActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCommandBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sendBasket()
    }

    private fun sendBasket() {
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/user/order"
        val data = JSONObject()

        // Get user id
        val sharePreferences = getSharedPreferences(USER_PREF, MODE_PRIVATE)
        val uid = sharePreferences.getInt(USER_ID, 0)

        // Get user Basket
        val file = File(cacheDir.absolutePath+"Basket.json")
        val msg: String = file.readText()

        data.put("id_shop", "1")
        data.put("id_user", uid.toString())
        data.put("msg", msg)

        val request = JsonObjectRequest(
            Request.Method.POST, url, data,
            {
                // Cas du succès
                Log.i("return", it.toString())
                file.delete()
                MainScope().launch {
                    binding.commandImage.visibility = View.GONE
                    delay(3000)
                    Log.i("coroutine", "ça marche !")
                    binding.commandProgressBar.visibility = View.GONE
                    binding.commandTitle.text = getString(R.string.command_success)
                    Picasso.get().load(R.drawable.checked).error(R.drawable.error_image).fit().into(
                        binding.commandImage)
                    binding.commandImage.visibility = View.VISIBLE
                }
            },
            {
                Log.d("commandError", it.toString())
                binding.commandTitle.text = getString(R.string.command_failure)
            }
        )

        // Add the request to the RequestQueue.
        queue.add(request)

    }
}