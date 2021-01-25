package fr.isen.vaisseau.androiderestaurant2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import fr.isen.vaisseau.androiderestaurant2.databinding.ActivityHomeBinding

private lateinit var binding: ActivityHomeBinding
public const val START_FOOD = "startFood"

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root);

        // onClick handler
        binding.activityHomeEntree.setOnClickListener {
            val intent = Intent(this, FoodActivity::class.java)
            intent.putExtra(START_FOOD, "Entrée")
            startActivity(intent)
        }

        binding.activityHomePlats.setOnClickListener {
            val intent = Intent(this, FoodActivity::class.java)
            intent.putExtra(START_FOOD, "Plats")
            startActivity(intent)
        }

        binding.activityHomeDessert.setOnClickListener {
            val intent = Intent(this, FoodActivity::class.java)
            intent.putExtra(START_FOOD, "Dessert")
            startActivity(intent)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "Activity destroyed")
    }

    companion object {
        const val TAG:String = "HomeActivity"
    }
}