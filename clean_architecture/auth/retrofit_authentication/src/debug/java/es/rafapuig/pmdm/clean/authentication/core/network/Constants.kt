package es.rafapuig.pmdm.clean.authentication.core.network

import es.rafapuig.pmdm.clean.authentication.backend.FakeAuthAPIDispatcher
import mockwebserver3.MockWebServer


val server = MockWebServer().apply {
    dispatcher = FakeAuthAPIDispatcher()
    start()
}