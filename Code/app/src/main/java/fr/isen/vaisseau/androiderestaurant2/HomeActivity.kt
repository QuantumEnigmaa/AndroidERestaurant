package fr.isen.vaisseau.androiderestaurant2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Variable declaration
        val entry: TextView = findViewById(R.id.activity_home_entree);
        val mainFood: TextView = findViewById(R.id.activity_home_plats);
        val dessert: TextView = findViewById(R.id.activity_home_dessert);

        // onClick handler
        entry.setOnClickListener {
            //val intent = Intent(this, );
        }

        mainFood.setOnClickListener {

        }

        dessert.setOnClickListener {

        }
    }
}