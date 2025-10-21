package es.rafapuig.pmdm.compose.learning.lists.lazy

import android.content.res.Resources
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalResources
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.compose.learning.R
import es.rafapuig.pmdm.compose.learning.lists.FootballTeam
import es.rafapuig.pmdm.compose.learning.lists.FootballTeamItem
import es.rafapuig.pmdm.compose.learning.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.delay

fun Resources.loadFootballTeamNames(): List<String> =
    getStringArray(R.array.football_teams_names).toList()

fun Resources.loadFootballTeamsBadges(): List<Int> =
    with(obtainTypedArray(R.array.football_teams_badges)) {
        val badges = List(length()) { index ->
            getResourceId(index, 0)
        }
        recycle()
        badges
    }


/**
 * Función para cargar los equipos de fútbol desde los recursos de la aplicación.
 * En el archivo football_teams.xml se definen dos arrays con
 * los nombres y los identificadores de las imágenes de los equipos.
 */
private fun Resources.loadFootballTeams(): List<FootballTeam> =
    run {
        val names = loadFootballTeamNames()
        val badges = loadFootballTeamsBadges()

        names.zip(badges).map { (name, badge) ->
            FootballTeam(name, badge)
        }
    }


/**
 * Función para cargar los equipos de fútbol desde los recursos de la aplicación
 * utilizando una corrutina.
 * Simulando que tarda unos segundos como si fuera una llamada a una API.
 */
private suspend fun loadFootballTeamsAsync(resources: Resources): List<FootballTeam> {
    delay(2000)
    return resources.loadFootballTeams()
}

/**
 * Este remember recuerda un objeto de tipo MutableState<List<FootballTeam>>.
 * Es decir, un estado, el cual cuando se modifica desencando un recomposición
 * de los composables afectados por el valor del estado.
 *
 * Al utilizar la función produceState el estado creado es State
 * es decir, un estado de sólo lectura que no puede ser modificado.
 * y por eso si delegaramos en una propiedad
 * esta propiedad tambien seria  val (de sólo lectura).
 *
 */
@Composable
fun rememberFootballTeamsReadonly(): State<List<FootballTeam>> {

    val resources = LocalResources.current

    // Dentro de la lambda de produceState se puede llamar a funciones suspendidas
    val teams = produceState(emptyList()) {
        value = loadFootballTeamsAsync(resources)
    }
    return teams
}

/**
 * Este remember recuerda un objeto de tipo MutableState<List<FootballTeam>>.
 * Es decir, un estado, que cuando se modifica desencadena un recomposición
 * de los composables afectados por el valor del estado.
 */
@Composable
fun rememberFootballTeams(): MutableState<List<FootballTeam>?> {

    // Estado para guardar los equipos de fútbol (o null)
    val teams: MutableState<List<FootballTeam>?> = remember { mutableStateOf(null) }

    val resources = LocalResources.current

    // Efecto que se ejecuta al componer el composable o si cambia el contexto
    /**
     * Aprovechamos que la lambda argumento de LaunchedEffect
     * tiene un receptor implícito de tipo CoroutineScope, que será donde se
     * ejecute la lambda y por tanto, puede llamar a funciones suspendidas
     */
    LaunchedEffect(resources.configuration) {
        teams.value = loadFootballTeamsAsync(resources)
    }
    return teams
}


@Preview(showSystemUi = true)
@Composable
fun FootballTeamsScreenPreview() {

    var teams by rememberFootballTeams()

    PMDMComposeTheme {
        Scaffold { paddingValues ->
            val modifier = Modifier.padding(paddingValues)

            /**
             * Si teams contiene una referencia a un lista (operador ?.)
             * se ejecuta el bloque de let (donde it es la lista)
             * si no ?. devuelve null y no se ejecuta el bloque let
             * ese null lo comprobamos con el operador Elvis ?: para
             * es ese caso ejecutar el bloque run
             */
            teams?.let {
                FootballTeamsScreen(it, modifier)
            } ?: run {
                LoadingScreen(modifier)
            }

        }
    }
}


@Composable
fun FootballTeamLazyList(
    teams: List<FootballTeam>,
    modifier: Modifier = Modifier,
    onItemClick: (FootballTeam) -> Unit = {}
) {

    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = modifier) {
        items(teams) { team ->
            FootballTeamItem(team = team, onItemClick = onItemClick)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun FootballTeamLazyListPreview() {
    val resources = LocalResources.current

    /**
     * El remember recuerda y cachea el valor del objeto
     * mientras el valor de la clave resources no cambie
     */
    val teams = remember(resources.configuration) {
        resources.loadFootballTeams() // Aquí utilizamos la carga síncrona por simplicidad
    }
    FootballTeamLazyList(teams)
}


@Composable
fun FootballTeamsScreen(
    teams: List<FootballTeam>,
    modifier: Modifier = Modifier
) {
    FootballTeamLazyList(teams, modifier = modifier)
}