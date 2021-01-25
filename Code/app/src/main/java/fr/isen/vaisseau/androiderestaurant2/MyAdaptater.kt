package fr.isen.vaisseau.androiderestaurant2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class MyAdaptater (private var itemList: List<String>) : RecyclerView.Adapter<MyAdaptater.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val title: TextView = itemView.findViewById(R.id.recyler_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdaptater.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_template, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyAdaptater.ViewHolder, position: Int) {
        holder.title.text = itemList[position]
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}