package fr.isen.vaisseau.androiderestaurant2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserList (
    @SerializedName("list") var userList: ArrayList<User>
        ) : Serializable