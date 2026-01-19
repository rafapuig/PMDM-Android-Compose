package es.rafapuig.pmdm

import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    configureSerialization()
    //configureDatabases()
    //configureRouting()
    todoRoutes()
}
