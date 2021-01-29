package fr.isen.vaisseau.androiderestaurant2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        /*val imageSrc = result?.getFirstPicture()
        Picasso.get().load(imageSrc).placeholder(R.drawable.searching).error(R.drawable.error_image).fit().into(
            binding.activityProductFoodImage)*/

        setViewPager()
    }

    private fun setViewPager() {
        val adapter = myViewPagerAdapter(supportFragmentManager)

        val firstFragment: FragmentOne = FragmentOne.newInstance("Bonjour test")
        val seconndFragment: FragmentOne = FragmentOne.newInstance("C'EST PARTI OUI OUI")
        adapter.addFragment(firstFragment)
        adapter.addFragment(seconndFragment)

        binding.activityProductViewPager.adapter = adapter
    }
}