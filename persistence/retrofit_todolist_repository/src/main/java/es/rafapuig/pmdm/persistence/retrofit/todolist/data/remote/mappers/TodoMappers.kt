package es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.mappers

import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.dto.TodoDto
import es.rafapuig.pmdm.persistence.retrofit.todolist.domain.model.Todo

fun TodoDto.toDomain() = Todo(
    id = this.id,
    task = this.task,
    isDone = this.isCompleted
)

fun Todo.toDto() = TodoDto(
    id = this.id,
    task = this.task,
    isCompleted = this.isDone
)