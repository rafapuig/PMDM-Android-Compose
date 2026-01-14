package es.rafapuig.pmdm.navigation.navigation3.basic.domain.model

data class Item(
    val id: Int,
    val title: String,
    val description: String = "",
    val imageUrl: String = ""
)