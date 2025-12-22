package es.rafapuig.pmdm.persistence.room.todolist.presentation.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import es.rafapuig.pmdm.persistence.room.todolist.data.local.TodosDatabase
import es.rafapuig.pmdm.persistence.room.todolist.data.local.factories.InMemoryTodosDatabaseFactory
import es.rafapuig.pmdm.persistence.room.todolist.data.local.factories.TodosDatabaseFactory
import es.rafapuig.pmdm.persistence.room.todolist.data.local.mappers.toDatabase
import es.rafapuig.pmdm.persistence.room.todolist.data.local.mappers.toDomain
import es.rafapuig.pmdm.persistence.room.todolist.data.local.providers.TodosDatabaseProvider
import es.rafapuig.pmdm.persistence.room.todolist.data.local.providers.getDatabaseInstance
import es.rafapuig.pmdm.persistence.room.todolist.domain.model.Todo
import es.rafapuig.pmdm.persistence.room.todolist.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@Preview
@Composable
fun TodosListScreenCompose(
    factory: TodosDatabaseFactory = InMemoryTodosDatabaseFactory()
) {

    val context = LocalContext.current

    val dao = remember {
        val db = context.getDatabaseInstance(factory = factory)
        db.todoDao()
    }

    val scope = rememberCoroutineScope()

    val todos by dao.getAllTodos().map { entities ->
        entities.map { entity -> entity.toDomain() }
    }.collectAsStateWithLifecycle(initialValue = emptyList())

    val onTodoIsDoneChange: (Todo, Boolean) -> Unit = { todo, isDone ->
        scope.launch {
            dao.upsert(todo.toDatabase().copy(isDone = isDone))
        }
    }

    val onTodoDelete: (Todo) -> Unit = { todo ->
        scope.launch {
            dao.delete(todo.toDatabase())
        }
    }

    val onTodoAdd: (String) -> Unit = { taskName ->
        scope.launch {
            dao.upsert(Todo(task = taskName).toDatabase())
        }
    }

    PMDMComposeTheme {
        TodoListScreen(
            todos = todos,
            onTodoIsDoneChange = onTodoIsDoneChange,
            onTodoDelete = onTodoDelete,
            onTodoAdd = onTodoAdd
        )
    }

}