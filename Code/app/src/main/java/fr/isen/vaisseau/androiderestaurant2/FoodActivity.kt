package fr.isen.vaisseau.androiderestaurant2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import fr.isen.vaisseau.androiderestaurant2.databinding.ActivityFoodBinding
import fr.isen.vaisseau.androiderestaurant2.model.DataFoodJSON
import fr.isen.vaisseau.androiderestaurant2.model.Item
import org.json.JSONException
import org.json.JSONObject

private lateinit var binding: ActivityFoodBinding

class FoodActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Getting info from home activity
        val result:String = intent.getStringExtra(START_FOOD).toString()
        binding.activityFoodTitle.text = result

        // Loading data from API
        loadData(result)
    }

    private fun setRecycler(list: List<Item>) {
        val recycler: RecyclerView = binding.activityFoodRecycler
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = MyAdaptater(list, this)
    }

    private fun loadData(category: String) {
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
            {
                val gson: DataFoodJSON = Gson().fromJson(it.toString(), DataFoodJSON::class.java)
                gson.data.firstOrNull() { it.name == category}?.items?.let {
                    setRecycler(it)
                }
            },
            { error -> Log.d("error", error.toString()) }
        )

        // Add the request to the RequestQueue.
        queue.add(request)
    }
}