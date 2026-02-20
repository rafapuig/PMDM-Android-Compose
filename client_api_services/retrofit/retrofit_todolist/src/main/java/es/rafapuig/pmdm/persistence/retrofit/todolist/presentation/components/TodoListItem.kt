package es.rafapuig.pmdm.persistence.retrofit.todolist.presentation.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import es.rafapuig.pmdm.persistence.retrofit.todolist.domain.model.Todo
import es.rafapuig.pmdm.persistence.retrofit.todolist.domain.sampleTodos
import es.rafapuig.pmdm.persistence.retrofit.todolist.ui.theme.PMDMComposeTheme

@Composable
fun TodoListItem(
    todo: Todo,
    onTodoCheck: (Todo, Boolean) -> Unit = { _, _ -> },
    onTodoDelete: (Todo) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement
            .spacedBy(8.dp, Alignment.Start)
    ) {
        //Spacer(modifier = Modifier.width(8.dp))
        Checkbox(
            checked = todo.isDone,
            onCheckedChange = { onTodoCheck(todo, it) }
        )

        Text(
            text = todo.task
        )

        Spacer(modifier = Modifier.weight(1f))

        IconButton(onClick = { onTodoDelete(todo) }) {
            Icon(
                Icons.Default.Delete,
                contentDescription = "Delete",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}


@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun TodoListItemPreview() {
    PMDMComposeTheme {
        Surface {
            TodoListItem(
                Todo(
                    id = 1,
                    task = "Lavar el coche",
                    isDone = true
                )
            )
        }
    }
}

class TodoPreviewParameterProvider
    : PreviewParameterProvider<Todo> {
    override val values: Sequence<Todo>
        get() = sampleTodos.take(3).asSequence()
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun TodoListItemPreview2(
    @PreviewParameter(TodoPreviewParameterProvider::class) todo: Todo
) {
    PMDMComposeTheme {
        Surface {
            TodoListItem(todo)
        }
    }
}


