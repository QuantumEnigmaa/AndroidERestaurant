package fr.isen.vaisseau.androiderestaurant2.model

import com.google.gson.annotations.SerializedName
import fr.isen.vaisseau.androiderestaurant2.model.Categories
import java.io.Serializable

data class  DataFoodJSON (
    @SerializedName("data") val data: List<Categories>
        ): Serializable