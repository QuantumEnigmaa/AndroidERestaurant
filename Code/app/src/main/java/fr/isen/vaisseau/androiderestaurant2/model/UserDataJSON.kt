package fr.isen.vaisseau.androiderestaurant2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserDataJSON (
    @SerializedName("data") val data: User,
    @SerializedName("return_code") val return_code: Int
): Serializable