package fr.isen.vaisseau.androiderestaurant2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.gson.Gson
import fr.isen.vaisseau.androiderestaurant2.databinding.ActivityProductBinding
import fr.isen.vaisseau.androiderestaurant2.model.AddItem
import fr.isen.vaisseau.androiderestaurant2.model.Basket
import fr.isen.vaisseau.androiderestaurant2.model.Item
import org.json.JSONObject
import java.io.File

private lateinit var binding: ActivityProductBinding
val USER_PREF = "user_preferences"
val QUANT_PREF = "quantity"

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

    // Toolbar handlers
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_basket -> {
                val intent = Intent(this, BasketActivity::class.java)
                startActivity(intent)
                Log.i("toolbar", "panier sélectionné")
            }
            else -> Log.i("toolbar", "problème")
        }
        return super.onOptionsItemSelected(item)
    }

    private fun buyingHandler(addedItem: Item, file: String) {
        binding.activityProductBuy.setOnClickListener {
            val toWrite = File(cacheDir.absolutePath+file)
            val itemAdded = AddItem(addedItem, binding.activityProductQuantity.text.toString().toInt())

            if (toWrite.exists()) {
                val gson: Basket = Gson().fromJson(toWrite.readText(), Basket::class.java)
                //firstorNull ~ filter et let n'execute la suite du code que si le reste s'est bien exécuté
                gson.itemList.firstOrNull { it.dish == addedItem }?.let {
                    it.quantity += binding.activityProductQuantity.text.toString().toInt()
                    addPreferences(gson)
                    toWrite.writeText(Gson().toJson(gson))
                }?: run {
                    gson.itemList.add(itemAdded)
                    addPreferences(gson)
                    toWrite.writeText(Gson().toJson(gson))
                }
            } else {
                val itemList: ArrayList<AddItem> = ArrayList()
                itemList.add(itemAdded)
                val basket = Basket(itemList)
                addPreferences(basket)
                toWrite.writeText(Gson().toJson(basket))
            }

            // Alert window creation
            val dialogBuilder = AlertDialog.Builder(this)

            dialogBuilder.setMessage("Article ajouté à votre panier").setCancelable(true)

            val alert = dialogBuilder.create()
            alert.show()
        }
    }

    private fun addPreferences(basket: Basket) {
        val count = basket.itemList.sumOf { it.quantity }

        val sharedPreferences = getSharedPreferences(USER_PREF, MODE_PRIVATE)
        sharedPreferences.edit().putInt(QUANT_PREF, count).apply()
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
        val priceInit: Double = result.getPrice()
        var price: Double = result.getPrice()

        binding.activityProductTotalPrice.text = result.getFormattedPrice()

        binding.activityProductRemove.setOnClickListener {
            if (quantity > 0) {
                quantity --
                binding.activityProductQuantity.text = quantity.toString()
                Log.i("quantité", "$quantity")
                price -= priceInit
                val priceText = "$price €"
                binding.activityProductTotalPrice.text = priceText
            }
        }

        binding.activityProductAdd.setOnClickListener {
            quantity ++
            binding.activityProductQuantity.text = quantity.toString()
            Log.i("quantité", "$quantity")
            price += priceInit
            val priceText = "$price €"
            binding.activityProductTotalPrice.text = priceText
        }
    }
}