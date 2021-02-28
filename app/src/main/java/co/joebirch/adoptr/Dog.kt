package co.joebirch.adoptr

data class Dog(
    val id: String,
    val name: String,
    val age: String,
    val gender: String,
    val breed: String,
    val url: String,
    val image: Int,
    val description: String,
    val reserved: Boolean,
    val attributes: List<DogAttributes>,
    val favorite: Boolean
)