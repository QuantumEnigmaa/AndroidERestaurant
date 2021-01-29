package fr.isen.vaisseau.androiderestaurant2.model

class AddItem {
    var name: String? = null
    var price: Int? = null

    constructor(): super() {}

    constructor(Name: String, Price: Int): super() {
        this.name = Name
        this.price = Price
    }
}