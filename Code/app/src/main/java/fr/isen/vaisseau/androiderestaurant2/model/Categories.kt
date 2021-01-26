package fr.isen.vaisseau.androiderestaurant2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Categories (
    @SerializedName("name_fr") val name: String,
    @SerializedName("ingredients") val ingredients: List<Ingredient>,
    @SerializedName("images") private val images: List<String>,
    @SerializedName("prices") private val prices: List<Price>
        ): Serializable
