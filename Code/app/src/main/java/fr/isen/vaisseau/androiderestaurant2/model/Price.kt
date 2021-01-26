package fr.isen.vaisseau.androiderestaurant2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Price (
    @SerializedName("id") val id: String,
    @SerializedName ("id_pizza") val idPizza: String,
    @SerializedName ("id_size") val idSize: String,
    @SerializedName ("price") val price: String,
    @SerializedName ("create_date") val createDate: String,
    @SerializedName ("update_date") val updateDate: String,
    @SerializedName ("size") val size: String
        ): Serializable
