package es.rafapuig.pmdm.di.todolist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.rafapuig.pmdm.di.todolist.di.Dependencies
import es.rafapuig.pmdm.di.todolist.domain.model.Todo
import es.rafapuig.pmdm.di.todolist.domain.repositories.TodosRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds

class TodoListViewModel(
    /** Se inyecta la dependencia del repositorio */
    private val repository: TodosRepository // Ya lo inyecta Koin //Dependencies.get()
) : ViewModel() {

    /*val todos = repository.todos
    .stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )*/

    val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos = _todos.asStateFlow()

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    init {
        observeQuery()
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    private fun observeQuery() {
        query.map { query -> query.trim() }
            .distinctUntilChanged()
            .debounce(500.milliseconds)
            //.filter { query -> query.isNotBlank() && query.length >= 3 }
            .flatMapLatest { query ->
                repository.searchTodos(query)
            }.onEach { todoList ->
                _todos.update { todoList }
            }.launchIn(viewModelScope)

    }

    fun onQueryChange(query: String) {
        _query.update { query }
    }

    fun onTodoIsDoneChange(todo: Todo, isDone: Boolean) {
        viewModelScope.launch {
            repository.setTodoIsDone(todo, isDone)
        }
    }

    fun onTodoDelete(todo: Todo) {
        viewModelScope.launch {
            repository.deleteTodo(todo)
        }
    }

    fun onTodoAdd(taskName: String) {
        viewModelScope.launch {
            repository.addTodo(taskName)
        }
    }

    /** Con inyección de dependencias no es necesario una factoria */
    /*companion object {
        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val repository = Dependencies.get<TodosRepository>()
                    TodoListViewModel(repository)
                }
            }
    }*/

}