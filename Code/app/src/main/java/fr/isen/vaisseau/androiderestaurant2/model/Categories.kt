package fr.isen.vaisseau.androiderestaurant2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Categories (
    @SerializedName("name_fr") val name: String,
    @SerializedName("items") val items: List<Item>
        ): Serializable
