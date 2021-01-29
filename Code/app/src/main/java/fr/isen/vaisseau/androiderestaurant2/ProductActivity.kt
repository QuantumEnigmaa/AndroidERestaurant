package fr.isen.vaisseau.androiderestaurant2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.squareup.picasso.Picasso
import fr.isen.vaisseau.androiderestaurant2.databinding.ActivityProductBinding
import fr.isen.vaisseau.androiderestaurant2.model.Item

private lateinit var binding: ActivityProductBinding

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val result = intent.getSerializableExtra(START_PRODUCT) as? Item
        binding.activityProductTitle.text = result?.name
        binding.activityProductIngredients.text = result?.getIngredients()

        val listImageSrc: List<String> = result?.getAllPictures()!!.toList()

        setViewPager(listImageSrc)

        setPrice(result)
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