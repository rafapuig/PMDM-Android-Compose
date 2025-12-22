@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.persistence.room.todolist.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.persistence.room.todolist.domain.model.Todo
import es.rafapuig.pmdm.persistence.room.todolist.domain.sampleTodos
import es.rafapuig.pmdm.persistence.room.todolist.presentation.components.AddTodoDialog
import es.rafapuig.pmdm.persistence.room.todolist.presentation.components.TodoListItem
import es.rafapuig.pmdm.persistence.room.todolist.ui.theme.PMDMComposeTheme
import kotlinx.coroutines.launch

@Composable
fun TodoListScreen(
    todos: List<Todo>,
    onTodoIsDoneChange: (Todo, Boolean) -> Unit = { _, _ -> },
    onTodoDelete: (Todo) -> Unit = {},
    onTodoAdd: (String) -> Unit = {}
) {
    var showDialog by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Lista de tareas") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(
                    Icons.Default.Add,
                    contentDescription = "Nueva tarea"
                )
            }
        }
    ) { innerPadding ->

        if (showDialog) {
            AddTodoDialog(
                onDismiss = { showDialog = false },
                onConfirm = { taskName ->
                    onTodoAdd(taskName)

                    /** Scroll hasta el último elemento de la lista */
                    scope.launch {
                        listState.animateScrollToItem(todos.size - 1)
                    }

                    showDialog = false
                }
            )
        }

        LazyColumn(
            state = listState,
            modifier = Modifier.padding(innerPadding),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(
                items = todos,
                key = { it.id }
            ) {
                TodoListItem(
                    todo = it,
                    onTodoCheck = onTodoIsDoneChange,
                    onTodoDelete = onTodoDelete
                )
            }
        }
    }
}


@Preview(
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun TodoListScreenPreview() {

    PMDMComposeTheme {
        TodoListScreen(
            todos = sampleTodos
        )
    }
}