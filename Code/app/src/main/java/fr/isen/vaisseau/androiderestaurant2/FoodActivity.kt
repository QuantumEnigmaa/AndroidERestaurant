package fr.isen.vaisseau.androiderestaurant2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import fr.isen.vaisseau.androiderestaurant2.databinding.ActivityFoodBinding
import org.json.JSONException
import org.json.JSONObject

private lateinit var binding: ActivityFoodBinding

class FoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "http://test.api.catering.bluecodegames.com/menu"
        val data = JSONObject()

        try {
            data.put("id_shop", "1")
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        // Request a string response from the provided URL.
        val request = JsonObjectRequest(Request.Method.POST, url, data,
            { response -> Log.d("response", response.toString())},
            { error -> Log.d("error", error.toString()) }
        )

        // Add the request to the RequestQueue.
        queue.add(request)

        // Getting info from home activity
        val result:String = intent.getStringExtra(START_FOOD).toString()
        binding.activityFoodTitle.text = result

        // Using recycler view
        when (result) {
            "Entrée" -> {
                val foodTitle = resources.getStringArray(R.array.entries_title).toList()
                setRecycler(foodTitle)
            }
            "Plats" -> {
                val foodTitle = resources.getStringArray(R.array.main_title).toList()
                setRecycler(foodTitle)
            }
            "Dessert" -> {
                val foodTitle = resources.getStringArray(R.array.dessert_title).toList()
                setRecycler(foodTitle)
            }
        }
    }

    fun setRecycler(titles: List<String>) {
        val recycler: RecyclerView = binding.activityFoodRecycler
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = MyAdaptater(titles, this)
    }
}