package es.rafapuig.pmdm.compose.lifecycle.navigation.sending_data.ui.models


/**
 * Interface comun para los tres ejemplos
 * Para que la pantalla LoggedScreen reciba una referencia del tipo
 * de esta interface y se abstraiga de la implementación concreta
 */
interface Credentials {
    val username: String
    val password: String
}
