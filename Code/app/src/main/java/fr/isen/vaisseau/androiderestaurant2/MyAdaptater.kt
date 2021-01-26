package fr.isen.vaisseau.androiderestaurant2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import fr.isen.vaisseau.androiderestaurant2.databinding.RecyclerTemplateBinding
import fr.isen.vaisseau.androiderestaurant2.model.DataFoodJSON
import fr.isen.vaisseau.androiderestaurant2.model.Item

public const val START_PRODUCT = "startProduct"

class MyAdaptater (private var itemList: List<Item>, private val ct: Context) : RecyclerView.Adapter<MyAdaptater.ViewHolder>() {

    class ViewHolder(binding: RecyclerTemplateBinding) : RecyclerView.ViewHolder(binding.root){
        val title: TextView = binding.recyclerTitle
        val container: LinearLayout = binding.root
        val price: TextView = binding.recyclerPrice
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdaptater.ViewHolder {
        val itemBinding = RecyclerTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)//LayoutInflater.from(parent.context).inflate(R.layout.recycler_template, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyAdaptater.ViewHolder, position: Int) {
        holder.title.text = itemList[position].name
        holder.price.text = itemList[position].getFormattedPrice()

        holder.container.setOnClickListener {
            val intent = Intent(ct, ProductActivity::class.java)
            intent.putExtra(START_PRODUCT, holder.title.text)
            ct.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

}