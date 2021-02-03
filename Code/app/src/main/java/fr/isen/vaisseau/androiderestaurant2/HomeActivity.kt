package fr.isen.vaisseau.androiderestaurant2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import fr.isen.vaisseau.androiderestaurant2.Animations.LoadingAnimation
import fr.isen.vaisseau.androiderestaurant2.databinding.ActivityHomeBinding
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private lateinit var binding: ActivityHomeBinding
const val START_FOOD = "startFood"

class HomeActivity : BaseActivity() {

    private lateinit var loadingAnimation: LoadingAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Loading screen
        loadingAnimation = LoadingAnimation(this, "loading.json")

        MainScope().launch {
            loadingAnimation.playAnimation()
            delay(3000)
            loadingAnimation.stopAnimation(binding.root)
        }

        // onClick handler
        binding.activityHomeEntree.setOnClickListener {
            val intent = Intent(this, FoodActivity::class.java)
            intent.putExtra(START_FOOD, "Entr\u00e9es")
            startActivity(intent)
        }

        binding.activityHomePlats.setOnClickListener {
            val intent = Intent(this, FoodActivity::class.java)
            intent.putExtra(START_FOOD, "Plats")
            startActivity(intent)
        }

        binding.activityHomeDessert.setOnClickListener {
            val intent = Intent(this, FoodActivity::class.java)
            intent.putExtra(START_FOOD, "Desserts")
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