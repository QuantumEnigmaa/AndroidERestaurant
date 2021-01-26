package fr.isen.vaisseau.androiderestaurant2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Ingredient (
    @SerializedName ("id") val id: Int,
    @SerializedName ("id_shop") val idShop: Int,
    @SerializedName ("name_fr") val name: String,
    @SerializedName ("create_date") val createDate: Date,
    @SerializedName ("update_date") val updateDate: Date,
    @SerializedName ("id_pizza") val idPizza: Int
        ): Serializable
