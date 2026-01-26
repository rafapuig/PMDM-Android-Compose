package es.rafapuig.pmdm.clean.ktor.authenticacion.features.home.presentation

import es.rafapuig.pmdm.clean.ktor.authenticacion.features.session.presentation.SessionManager

class HomeViewModel(private val sessionManager: SessionManager) : ViewModel() {
    fun logout() = sessionManager.logout()
}