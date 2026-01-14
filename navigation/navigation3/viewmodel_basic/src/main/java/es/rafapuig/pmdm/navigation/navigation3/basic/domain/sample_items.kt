package es.rafapuig.pmdm.navigation.navigation3.basic.domain

import es.rafapuig.pmdm.navigation.navigation3.basic.domain.model.Item

val sampleItems = (1..20).map {
    Item(
        id = it,
        title = "Item $it",
        description = "Descripción del item $it",
    )
}