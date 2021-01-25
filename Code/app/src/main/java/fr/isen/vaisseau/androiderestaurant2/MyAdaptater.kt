package fr.isen.vaisseau.androiderestaurant2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.vaisseau.androiderestaurant2.databinding.RecyclerTemplateBinding

class MyAdaptater (private var itemList: List<String>) : RecyclerView.Adapter<MyAdaptater.ViewHolder>() {
    class ViewHolder(binding: RecyclerTemplateBinding) : RecyclerView.ViewHolder(binding.root){
        val title: TextView = binding.recylerTitle
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdaptater.ViewHolder {
        val itemBinding = RecyclerTemplateBinding.inflate(LayoutInflater.from(parent.context), parent, false)//LayoutInflater.from(parent.context).inflate(R.layout.recycler_template, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MyAdaptater.ViewHolder, position: Int) {
        holder.title.text = itemList[position]
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}