package fr.isen.vaisseau.androiderestaurant2

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class  DataFoodJSON (
    @SerializedName("name_fr") val frName: String? = null,
    @SerializedName("name_en") val enName: String? = null,
    @SerializedName("items") val items: Items? = null
        ): Serializable

data class Items (
    @SerializedName("id") val id: Int? = null,
    @SerializedName ("id_category") val idCategory: Int? = null,
    @SerializedName ("categ_name_fr") val frNameCategory: String? = null,
    @SerializedName ("categ_name_fr") val enNameCategory: String? = null,
    @SerializedName ("images") val img: List<String>? = null,
    @SerializedName ("ingredients") val ingredients: Ingredients? = null,
    @SerializedName ("prices") val prices: Prices? = null
        )

data class Ingredients (
    @SerializedName ("id") val id: Int? = null,
    @SerializedName ("id_shop") val idShop: Int? = null,
    @SerializedName ("name_fr") val frName: String? = null,
    @SerializedName ("name_en") val enName: String? = null,
    @SerializedName ("create_date") val createDate: Date? = null,
    @SerializedName ("update_date") val updateDate: Date? = null,
    @SerializedName ("id_pizza") val idPizza: Int? = null,
        )

data class Prices(
    @SerializedName ("id") val id: Int? = null,
    @SerializedName ("id_pizza") val idPizza: Int? = null,
    @SerializedName ("id_size") val idSize: Int? = null,
    @SerializedName ("price") val name: Int? = null,
    @SerializedName ("create_date") val createDate: Date? = null,
    @SerializedName ("update_date") val updateDate: Date? = null,
    @SerializedName ("size") val size: String? = null,
        )