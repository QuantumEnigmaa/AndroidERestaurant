package fr.isen.vaisseau.androiderestaurant2

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import fr.isen.vaisseau.androiderestaurant2.databinding.BasketRecyclerTemplateBinding
import fr.isen.vaisseau.androiderestaurant2.model.AddItem
import fr.isen.vaisseau.androiderestaurant2.model.Basket
import java.io.File
import java.lang.Exception

class basketAdapter (private var itemList: ArrayList<AddItem>, private val file: File, private val gson: Basket) : RecyclerView.Adapter<basketAdapter.ViewHolder>() {

    class ViewHolder(binding: BasketRecyclerTemplateBinding): RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.basketRecyclerTitle
        val price: TextView = binding.basketRecyclerPrice
        val quantity: TextView = binding.basketRecyclerQuantityResult
        val delete: ImageView = binding.basketRecyclerDelete
        val image: ImageView = binding.basketRecyclerImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = BasketRecyclerTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return basketAdapter.ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = itemList[position].dish.name
        holder.price.text = itemList[position].dish.getFormattedPrice()
        holder.quantity.text = itemList[position].quantity.toString()

        val imageUrl = itemList[position].dish.getFirstPicture()
        Picasso.get().load(imageUrl).placeholder(R.drawable.searching).error(R.drawable.error_image).fit().into(holder.image)

        holder.delete.setOnClickListener {
            itemList.remove(itemList[position])
            file.writeText(Gson().toJson(gson))
            try {
                // Refreshing basket
                notifyDataSetChanged()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}