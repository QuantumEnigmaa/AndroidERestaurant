package fr.isen.vaisseau.androiderestaurant2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class User (
    @SerializedName("id") val id: Int,
    @SerializedName("lastname") val lastname: String,
    @SerializedName("firstname") val firstname: String,
    @SerializedName("adress") val adress: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val pwd: String
    ) : Serializable
