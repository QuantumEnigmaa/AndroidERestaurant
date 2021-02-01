package fr.isen.vaisseau.androiderestaurant2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import fr.isen.vaisseau.androiderestaurant2.databinding.ActivityBasketBinding
import fr.isen.vaisseau.androiderestaurant2.databinding.BasketRecyclerTemplateBinding
import fr.isen.vaisseau.androiderestaurant2.model.AddItem
import fr.isen.vaisseau.androiderestaurant2.model.Basket
import java.io.File

private lateinit var binding: ActivityBasketBinding

class BasketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBasketBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrieveBasket()
    }

    private fun setRecycler(list: List<AddItem>) {
        val recycler: RecyclerView = binding.activityBasketRecycler
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = basketAdapter(list, this)
    }

    private fun retrieveBasket() {
        val readFile = File(cacheDir.absolutePath+"Basket.json")

        if (readFile.exists()) {
            val gson: Basket = Gson().fromJson(readFile.readText(), Basket::class.java)
            gson.itemList.let {
                setRecycler(it.toList())
            }
        }
    }
}