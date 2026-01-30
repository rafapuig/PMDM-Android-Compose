package es.rafapuig.pmdm.clean.authentication

import es.rafapuig.pmdm.clean.authentication.backend.FakeAuthAPIDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import mockwebserver3.MockWebServer

class DebugAuthApp : AuthApp() {

    lateinit var mockWebServer: MockWebServer


    override fun onCreate() {

        // inicia MockWebServer primero
        mockWebServer = MockWebServer()
        //if (BuildConfig.FLAVOR == "real") {
        mockWebServer.dispatcher = FakeAuthAPIDispatcher()

        runBlocking(Dispatchers.IO) {
            mockWebServer.start()
        }


        super.onCreate()
    }

}