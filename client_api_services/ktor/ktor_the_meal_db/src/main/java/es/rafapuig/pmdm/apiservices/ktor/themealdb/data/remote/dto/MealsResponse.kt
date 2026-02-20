package es.rafapuig.pmdm.apiservices.ktor.themealdb.data.remote.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MealsResponse(
    @SerialName("meals")
    val meals: List<MealResponse> = listOf()
)