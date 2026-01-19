package es.rafapuig.pmdm

import es.rafapuig.pmdm.model.CreateTodoRequest
import es.rafapuig.pmdm.model.UpdateTodoRequest
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
    }
}


fun Application.todoRoutes() {

    val repository = TodoRepository()

    routing {
        route("/todos") {

            // GET /todos
            get {
                call.respond(repository.getAll())
            }

            // POST /todos
            post {
                val body = call.receive<CreateTodoRequest>()
                val todo = repository.add(body.task)
                call.respond(HttpStatusCode.Created, todo)
            }

            // PATCH /todos/{id}
            patch("{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@patch call.respond(HttpStatusCode.BadRequest)

                val body = call.receive<UpdateTodoRequest>()
                val updated = repository.update(id, body.completed)
                    ?: return@patch call.respond(HttpStatusCode.NotFound)

                call.respond(updated)
            }

            // DELETE /todos/{id}
            delete("{id}") {
                val id = call.parameters["id"]?.toIntOrNull()
                    ?: return@delete call.respond(HttpStatusCode.BadRequest)

                val removed = repository.delete(id)
                if (!removed) {
                    call.respond(HttpStatusCode.NotFound)
                } else {
                    call.respond(HttpStatusCode.NoContent)
                }
            }
        }
    }
}

