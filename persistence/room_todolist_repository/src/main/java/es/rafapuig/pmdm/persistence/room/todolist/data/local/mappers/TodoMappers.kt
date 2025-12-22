package es.rafapuig.pmdm.persistence.room.todolist.data.local.mappers

import es.rafapuig.pmdm.persistence.room.todolist.data.local.entities.TodoEntity
import es.rafapuig.pmdm.persistence.room.todolist.domain.model.Todo


fun TodoEntity.toDomain() = Todo(
    id = this.id,
    task = this.task,
    isDone = this.isDone
)

fun Todo.toDatabase() = TodoEntity(
    id = id,
    task = task,
    isDone = isDone
)