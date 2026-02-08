package es.rafapuig.pmdm.di.koin.navigation.viewmodel.list_details.domain

import es.rafapuig.pmdm.di.koin.navigation.viewmodel.list_details.domain.model.Item

val sampleItems = (1..20).map {
    Item(
        id = it,
        title = "Item $it",
        description = "Descripción del item $it",
    )
}