package es.rafapuig.pmdm.clean.authentication.core.network

import mockwebserver3.MockWebServer

class DebugBaseUrlProvider(
    private val mockWebServer: MockWebServer
) : BaseUrlProvider {

    override fun baseUrl(): String =
        mockWebServer.url("/").toString()
}
