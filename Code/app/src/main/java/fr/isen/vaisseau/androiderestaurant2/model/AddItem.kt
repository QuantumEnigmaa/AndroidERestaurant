package fr.isen.vaisseau.androiderestaurant2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddItem (
        @SerializedName("dish") var dish: Item,
        @SerializedName("quantity") var quantity: Int
        ) : Serializable