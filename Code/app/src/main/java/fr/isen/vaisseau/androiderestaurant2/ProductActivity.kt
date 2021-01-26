package fr.isen.vaisseau.androiderestaurant2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import fr.isen.vaisseau.androiderestaurant2.databinding.ActivityProductBinding

private lateinit var binding: ActivityProductBinding

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result:String = intent.getStringExtra(START_PRODUCT).toString()
        binding.activityProductTitle.text = result
    }
}