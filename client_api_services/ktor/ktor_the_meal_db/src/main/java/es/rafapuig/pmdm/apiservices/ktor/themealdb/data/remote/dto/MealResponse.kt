package es.rafapuig.pmdm.apiservices.ktor.themealdb.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealResponse(
    @SerialName("idMeal")
    val idMeal: String = "",
    @SerialName("strMeal")
    val strMeal: String = "",
    @SerialName("strMealAlternate")
    val strMealAlternate: String? = null,
    @SerialName("strCategory")
    val strCategory: String = "",
    @SerialName("strArea")
    val strArea: String = "",
    @SerialName("strInstructions")
    val strInstructions: String = "",
    @SerialName("strMealThumb")
    val strMealThumb: String = "",
    @SerialName("strTags")
    val strTags: String? = null,
    @SerialName("strYoutube")
    val strYoutube: String = "",
    @SerialName("strIngredient1")
    val strIngredient1: String = "",
    @SerialName("strIngredient2")
    val strIngredient2: String = "",
    @SerialName("strIngredient3")
    val strIngredient3: String = "",
    @SerialName("strIngredient4")
    val strIngredient4: String = "",
    @SerialName("strIngredient5")
    val strIngredient5: String = "",
    @SerialName("strIngredient6")
    val strIngredient6: String = "",
    @SerialName("strIngredient7")
    val strIngredient7: String = "",
    @SerialName("strIngredient8")
    val strIngredient8: String = "",
    @SerialName("strIngredient9")
    val strIngredient9: String = "",
    @SerialName("strIngredient10")
    val strIngredient10: String = "",
    @SerialName("strIngredient11")
    val strIngredient11: String = "",
    @SerialName("strIngredient12")
    val strIngredient12: String = "",
    @SerialName("strIngredient13")
    val strIngredient13: String = "",
    @SerialName("strIngredient14")
    val strIngredient14: String = "",
    @SerialName("strIngredient15")
    val strIngredient15: String = "",
    @SerialName("strIngredient16")
    val strIngredient16: String? = null,
    @SerialName("strIngredient17")
    val strIngredient17: String? = null,
    @SerialName("strIngredient18")
    val strIngredient18: String? = null,
    @SerialName("strIngredient19")
    val strIngredient19: String? = null,
    @SerialName("strIngredient20")
    val strIngredient20: String? = null,
    @SerialName("strMeasure1")
    val strMeasure1: String = "",
    @SerialName("strMeasure2")
    val strMeasure2: String = "",
    @SerialName("strMeasure3")
    val strMeasure3: String = "",
    @SerialName("strMeasure4")
    val strMeasure4: String = "",
    @SerialName("strMeasure5")
    val strMeasure5: String = "",
    @SerialName("strMeasure6")
    val strMeasure6: String = "",
    @SerialName("strMeasure7")
    val strMeasure7: String = "",
    @SerialName("strMeasure8")
    val strMeasure8: String = "",
    @SerialName("strMeasure9")
    val strMeasure9: String = "",
    @SerialName("strMeasure10")
    val strMeasure10: String = "",
    @SerialName("strMeasure11")
    val strMeasure11: String = "",
    @SerialName("strMeasure12")
    val strMeasure12: String = "",
    @SerialName("strMeasure13")
    val strMeasure13: String = "",
    @SerialName("strMeasure14")
    val strMeasure14: String = "",
    @SerialName("strMeasure15")
    val strMeasure15: String = "",
    @SerialName("strMeasure16")
    val strMeasure16: String? = null,
    @SerialName("strMeasure17")
    val strMeasure17: String? = null,
    @SerialName("strMeasure18")
    val strMeasure18: String? = null,
    @SerialName("strMeasure19")
    val strMeasure19: String? = null,
    @SerialName("strMeasure20")
    val strMeasure20: String? = null,
    @SerialName("strSource")
    val strSource: String? = null,
    @SerialName("strImageSource")
    val strImageSource: String? = null,
    @SerialName("strCreativeCommonsConfirmed")
    val strCreativeCommonsConfirmed: String? = null,
    @SerialName("dateModified")
    val dateModified: String? = null
)