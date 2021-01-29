package fr.isen.vaisseau.androiderestaurant2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import fr.isen.vaisseau.androiderestaurant2.databinding.ActivityProductBinding
import fr.isen.vaisseau.androiderestaurant2.model.AddItem
import fr.isen.vaisseau.androiderestaurant2.model.Basket
import fr.isen.vaisseau.androiderestaurant2.model.Item
import org.json.JSONObject
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.nio.Buffer

private lateinit var binding: ActivityProductBinding

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getSerializableExtra(START_PRODUCT) as Item
        binding.activityProductTitle.text = result.name
        binding.activityProductIngredients.text = result.getIngredients()

        val listImageSrc: List<String> = result.getAllPictures()!!.toList()

        setViewPager(listImageSrc)
        setPrice(result)
        buyingHandler(result, "Basket.json")
    }

    private fun buyingHandler(addedItem: Item, file: String) {
        binding.activityProductBuy.setOnClickListener {
            val toWrite = File(cacheDir.absolutePath+file)
            val itemAdded = AddItem(addedItem, binding.activityProductQuantity.text.toString())

            if (toWrite.exists()) {
                val gson: Basket = Gson().fromJson(toWrite.readText(), Basket::class.java)
                //firstorNull ~ filter et let n'execute la suite du code que si le reste s'est bien exécuté
                gson.itemList.firstOrNull { it.dish == addedItem }?.let {
                    gson.itemList.map { it.quantity += binding.activityProductQuantity.text.toString() }
                }
                gson.itemList.add(itemAdded)
                toWrite.writeText(Gson().toJson(gson))
            } else {
                val itemList: ArrayList<AddItem> = ArrayList()
                itemList.add(itemAdded)
                val basket = Basket(itemList)
                toWrite.writeText(Gson().toJson(basket))
            }

            val dialogBuilder = AlertDialog.Builder(this)

            dialogBuilder.setMessage("Article ajouté à votre panier").setCancelable(true)

            val alert = dialogBuilder.create()
            alert.show()
        }
    }

    private fun setViewPager(imagesSrc: List<String>) {
        val adapter = myViewPagerAdapter(supportFragmentManager)

        for (image in imagesSrc) {
            val newFragment: FragmentOne = FragmentOne.newInstance(image)
            adapter.addFragment(newFragment)
        }

        binding.activityProductViewPager.adapter = adapter
    }

    private fun setPrice (result: Item) {
        var quantity: Int = 1
        val priceInit: Int = result.getPrice().toInt()
        var price: Int = result.getPrice().toInt()

        binding.activityProductTotalPrice.text = result.getFormattedPrice()

        binding.activityProductRemove.setOnClickListener {
            if (quantity > 0) {
                binding.activityProductQuantity.text = (quantity--).toString()
                Log.i("quantité", "$quantity")
                price -= priceInit
                val priceText = "$price €"
                binding.activityProductTotalPrice.text = priceText
            }
        }

        binding.activityProductAdd.setOnClickListener {
            binding.activityProductQuantity.text = (quantity++).toString()
            Log.i("quantité", "$quantity")
            price += priceInit
            val priceText = "$price €"
            binding.activityProductTotalPrice.text = priceText
        }
    }
}