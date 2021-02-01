package fr.isen.vaisseau.androiderestaurant2

import android.content.Intent
import android.view.Menu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

open class BaseActivity: AppCompatActivity() {

    // Toolbar handlers
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val toolMenu = menu?.findItem(R.id.menu_basket)?.actionView
        val counter = toolMenu?.findViewById<TextView>(R.id.basket_icon_pastille)

        counter?.isVisible = getBasketItems() > 0
        counter?.text = getBasketItems().toString()

        toolMenu?.setOnClickListener {
            val intent = Intent(this, BasketActivity::class.java)
            startActivity(intent)
        }

        return super.onCreateOptionsMenu(menu)
    }

    private fun getBasketItems(): Int {
        val sharePreferences = getSharedPreferences(USER_PREF, MODE_PRIVATE)
        return sharePreferences.getInt(QUANT_PREF, 0)
    }
}