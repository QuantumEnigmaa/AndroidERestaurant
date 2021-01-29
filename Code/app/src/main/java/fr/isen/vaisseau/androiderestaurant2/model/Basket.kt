package fr.isen.vaisseau.androiderestaurant2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Basket ( @SerializedName("itemList") var itemList: ArrayList<AddItem>
): Serializable