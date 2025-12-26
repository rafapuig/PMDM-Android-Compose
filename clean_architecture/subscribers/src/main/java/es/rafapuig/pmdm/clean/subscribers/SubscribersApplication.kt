package es.rafapuig.pmdm.clean.subscribers

import android.app.Application
import es.rafapuig.pmdm.clean.subscribers.repository_logic.data.SubscribersRepositoryImpl
import es.rafapuig.pmdm.clean.subscribers.repository_logic.domain.use_cases.GetCurrentSubscribersUseCase

/**
 * Clase de la Aplicación a instaciar
 * Recordar que se especifica en el Manifest
 */
class SubscribersApplication : Application() {

    /**
     * En este caso concreto niguna de estas propiedades depende
     * del contexto para instanciarse
     * Por tanto, no hace falta crearlas en el onCreate() de la aplicación
     */
    val subscribersRepository by
    lazy { SubscribersRepositoryImpl() }

    val getCurrentSubscribersUseCase by
    lazy { GetCurrentSubscribersUseCase(subscribersRepository) }

}