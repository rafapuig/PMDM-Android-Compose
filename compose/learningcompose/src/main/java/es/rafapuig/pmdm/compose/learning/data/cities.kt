package es.rafapuig.pmdm.compose.learning.data


data class City(val id: Int, val name: String)

private val cities = listOf(
    City(1, "Madrid"),
    City(2, "Barcelona"),
    City(3, "Valencia"),
    City(4, "Sevilla"),
    City(5, "Zaragoza"),
    City(6, "Málaga"),
    City(7, "Murcia"),
    City(8, "Palencia"),
    City(9, "Bilbao"),
    City(10, "Oviedo"),
    City(11, "Santander"),
    City(12, "Pamplona"),
    City(13, "Almería"),
    City(14, "Córdoba"),
    City(15, "Teruel"),
    City(16, "Castellón"),
    City(17, "Valladolid"),
    City(18, "Vitoria"),
    City(19, "Gijón"),
    City(20, "Ourense"),
    City(21, "Badajoz"),
    City(22, "La Coruña"),
    City(23, "Alicante"),
    City(24, "Soria"),
    City(25, "Elche"),
    City(26, "Guadalajara"),
    City(27, "Cuenca"),
    City(28, "Toledo"),
    City(29, "Logroño"),
    City(30, "Huelva"),
    City(31, "Jaén")
)

val SampleData.Companion.Cities get() = cities