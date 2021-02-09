package fr.isen.vaisseau.androiderestaurant2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

        binding.activityBasketBuy.setOnClickListener {
            val sharePreferences = getSharedPreferences(ProductActivity.USER_PREF, MODE_PRIVATE)
            val uid = sharePreferences.getInt(USER_ID, 0)

            val intent = Intent(this, AccountLoginActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setRecycler(list: ArrayList<AddItem>, file: File, gson: Basket) {
        val recycler: RecyclerView = binding.activityBasketRecycler
        recycler.layoutManager = LinearLayoutManager(this)
        val adapter = basketAdapter(list, file, gson)
        recycler.adapter = adapter

        /*var total = 0.0
        for (item in list) {
            total = total + item.dish.getPrice()
        }
        binding.activityBasketBuy.text = "Commander " + "$total €"
        adapter.notifyDataSetChanged()*/
    }

    private fun retrieveBasket() {
        val readFile = File(cacheDir.absolutePath+"Basket.json")

        if (readFile.exists()) {
            val gson: Basket = Gson().fromJson(readFile.readText(), Basket::class.java)
            gson.itemList.let {
                setRecycler(it, readFile, gson)
                }
            }
        }
}
