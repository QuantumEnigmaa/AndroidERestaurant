package fr.isen.vaisseau.androiderestaurant2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.isen.vaisseau.androiderestaurant2.databinding.ActivityFoodBinding

private lateinit var binding: ActivityFoodBinding

class FoodActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Getting info from home activity
        val result:String = intent.getStringExtra(START_FOOD).toString()
        binding.activityFoodTitle.text = result

        // Using recycler view
        when (result) {
            "Entrée" -> {
                val foodTitle = resources.getStringArray(R.array.entries_title).toList()

                val recycler: RecyclerView = binding.activityFoodRecycler
                recycler.layoutManager = LinearLayoutManager(this)
                recycler.adapter = MyAdaptater(foodTitle)
            }
            "Plats" -> {
                val foodTitle = resources.getStringArray(R.array.main_title).toList()

                val recycler: RecyclerView = binding.activityFoodRecycler
                recycler.layoutManager = LinearLayoutManager(this)
                recycler.adapter = MyAdaptater(foodTitle)
            }
            "Dessert" -> {
                val foodTitle = resources.getStringArray(R.array.dessert_title).toList()

                val recycler: RecyclerView = binding.activityFoodRecycler
                recycler.layoutManager = LinearLayoutManager(this)
                recycler.adapter = MyAdaptater(foodTitle)
            }
        }
    }
}