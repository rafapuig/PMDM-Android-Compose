package es.rafapuig.pmdm.di.koin.navigation.viewmodel.list_details.domain.model

data class Item(
    val id: Int,
    val title: String,
    val description: String = "",
    val imageUrl: String = ""
)