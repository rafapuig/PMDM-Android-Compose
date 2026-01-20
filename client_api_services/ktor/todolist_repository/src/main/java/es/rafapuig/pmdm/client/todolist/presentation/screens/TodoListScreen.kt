@file:OptIn(ExperimentalMaterial3Api::class)

package es.rafapuig.pmdm.client.todolist.presentation.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.PullToRefreshDefaults.Indicator
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.client.todolist.domain.model.Todo
import es.rafapuig.pmdm.client.todolist.domain.sampleTodos
import es.rafapuig.pmdm.client.todolist.presentation.components.AddTodoDialog
import es.rafapuig.pmdm.client.todolist.presentation.components.TodoListItem
import es.rafapuig.pmdm.client.todolist.ui.theme.PMDMComposeTheme

@Composable
fun TodoListScreen(
    todos: List<Todo>,
    isLoading: Boolean = false,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    onTodoIsDoneChange: (Todo, Boolean) -> Unit = { _, _ -> },
    onTodoDelete: (Todo) -> Unit = {},
    onTodoAdd: (String) -> Unit = {},
    onRefresh: () -> Unit = {}
) {
    var showDialog by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    val pullRefreshState = rememberPullToRefreshState()


    var shouldScrollToBottom by remember { mutableStateOf(false) }

    LaunchedEffect(todos, shouldScrollToBottom) {

        if (shouldScrollToBottom && todos.isNotEmpty()) {
            listState.animateScrollToItem(todos.lastIndex)
        }
    }


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
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->

        if (showDialog) {
            AddTodoDialog(
                onDismiss = { showDialog = false },
                onConfirm = { taskName ->
                    onTodoAdd(taskName)

                    /** Evento a controlar en el viewModel */
                    shouldScrollToBottom = true

                    showDialog = false
                }
            )
        }

        PullToRefreshBox(
            modifier = Modifier.fillMaxSize(),
            isRefreshing = isLoading,
            state = pullRefreshState,
            onRefresh = { onRefresh() },
            indicator = {
                Indicator(
                    color = MaterialTheme.colorScheme.primary,
                    state = pullRefreshState,
                    isRefreshing = isLoading,
                    modifier = Modifier
                        .align(Alignment.Center)
                        //.size(200.dp)
                )
            }

        ) {

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

        if (false) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
                    .clickable(enabled = false) {}
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
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