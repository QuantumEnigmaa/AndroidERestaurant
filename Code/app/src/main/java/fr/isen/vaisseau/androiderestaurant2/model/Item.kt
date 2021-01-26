package fr.isen.vaisseau.androiderestaurant2.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Item (
        @SerializedName("name_fr") val name: String,
        @SerializedName("ingredients") val ingredients: List<Ingredient>,
        @SerializedName("images") private val images: List<String>,
        @SerializedName("prices") private val prices: List<Price>
            ): Serializable {

                fun getPrice() = prices[0].price.toDouble()
                fun getFormattedPrice() = prices[0].price + " €"

                fun getFirstPicture () = if (images.isNotEmpty() && images[0].isNotEmpty()) {
                    images[0]
                } else {
                    null
                }
                fun getAllPictures () = if (images.isNotEmpty() && images.any { it.isNotEmpty() }) {
                    images.filter { it.isNotEmpty() }
                } else {
                    null
                }

            }
