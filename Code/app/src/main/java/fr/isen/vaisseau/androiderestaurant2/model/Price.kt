package fr.isen.vaisseau.androiderestaurant2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class Price (
    @SerializedName("id") val id: Int,
    @SerializedName ("id_pizza") val idPizza: Int,
    @SerializedName ("id_size") val idSize: Int,
    @SerializedName ("price") val name: Int,
    @SerializedName ("create_date") val createDate: Date,
    @SerializedName ("update_date") val updateDate: Date,
    @SerializedName ("size") val size: String
        ): Serializable
