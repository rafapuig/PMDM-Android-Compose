package es.rafapuig.pmdm.compose.exercises.navigation.exercise2

data class FootballTeam(
    val id: Int,
    val name: String,
    val badgeURL: String,
    val city: String?,
    val stadium: String?
)

val FootballTeams = listOf(
    FootballTeam(
        1,
        "Real Madrid",
        "https://upload.wikimedia.org/wikipedia/sco/thumb/5/56/Real_Madrid_CF.svg/1464px-Real_Madrid_CF.svg.png", //"https://upload.wikimedia.org/wikipedia/sco/5/56/Real_Madrid_CF.svg",// "https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png",
        "Madrid",
        "Santiago Bernabeu"
    ),
    FootballTeam(
        2,
        "FC Barcelona",
        "https://upload.wikimedia.org/wikipedia/en/4/47/FC_Barcelona_%28crest%29.svg", // "https://upload.wikimedia.org/wikipedia/en/thumb/4/47/FC_Barcelona_%28crest%29.svg/142px-FC_Barcelona_%28crest%29.svg.png",
        "Barcelona",
        "Camp Nou"
    )
)