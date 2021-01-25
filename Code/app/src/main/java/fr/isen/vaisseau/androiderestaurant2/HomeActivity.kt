package fr.isen.vaisseau.androiderestaurant2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import fr.isen.vaisseau.androiderestaurant2.databinding.ActivityHomeBinding

private lateinit var binding: ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root);

        // onClick handler
        binding.activityHomeEntree.setOnClickListener {
            val intent = Intent(this, FoodActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.activityHomePlats.setOnClickListener {

        }

        binding.activityHomeDessert.setOnClickListener {

        }

    }
}