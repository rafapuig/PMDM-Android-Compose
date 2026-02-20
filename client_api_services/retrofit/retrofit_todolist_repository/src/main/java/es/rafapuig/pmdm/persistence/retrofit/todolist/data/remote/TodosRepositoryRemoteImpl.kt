package es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote

import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.dto.CreateTodoRequest
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.dto.TodoCompletedPatch
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.mappers.toDomain
import es.rafapuig.pmdm.persistence.retrofit.todolist.domain.model.Todo
import es.rafapuig.pmdm.persistence.retrofit.todolist.domain.repositories.TodosRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodosRepositoryRemoteImpl(private val api: TodosApiService) : TodosRepository {

    private val _refreshTrigger =
        MutableSharedFlow<Unit>(
            replay = 1 // importante para la primera carga
        )
    val refreshTrigger = _refreshTrigger.asSharedFlow()

    private val _isUpdating = MutableStateFlow(false)
    override val isUpdating: StateFlow<Boolean> get() = _isUpdating.asStateFlow()

    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    override val todos: Flow<List<Todo>> = _todos.asStateFlow()


    @OptIn(ExperimentalCoroutinesApi::class)
    val refreshedTodos: Flow<List<Todo>>
        get() = _refreshTrigger
            .onStart { emit(Unit) }
            .onEach {
                _isUpdating.value = true
                delay(1000)
            }
            .flatMapLatest { // Cancelar cargas anteriores si se dispara otra
                flow {
                    emit(api.fetchAllTodos())
                }
                //.withDelayedLoading(_isLoading, 250)
            }
            .map { todosDto ->
                todosDto.map { it.toDomain() }
            }
            .onEach { _isUpdating.value = false }
            .flowOn(Dispatchers.IO)


    init {
        observeTodos()
    }

    fun observeTodos() {
        CoroutineScope(Dispatchers.IO).launch {
            refreshedTodos.collect { todos ->
                _todos.update { todos }
            }
        }
    }

    override suspend fun setTodoIsDone(
        todo: Todo,
        isDone: Boolean
    ) {
        _isUpdating.value = true
        //delay(150)

        //refresh()
        _todos.update { todos ->
            todos.map {
                if (it.id == todo.id) it.copy(isDone = isDone) else it
            }
        }
        _isUpdating.value = false

        api.setTodoDone(
            id = todo.id,
            patch = TodoCompletedPatch(isDone)
        )
        //refresh()
    }

    override suspend fun deleteTodo(todo: Todo) {
        _isUpdating.value = true
        //delay(150)
        _todos.update { todos -> todos.filter { it.id != todo.id } }
        _isUpdating.value = false
        api.deleteTodo(todo.id)
        //refresh()
    }

    override suspend fun addTodo(task: String) {
        _isUpdating.value = true
        _todos.update { todos ->
            todos + Todo(
                id = (todos.maxByOrNull { it.id }?.id ?: 0) + 1,
                task = task,
                isDone = false
            )
        }
        _isUpdating.value = false
        api.addTodo(CreateTodoRequest(task))
        //refresh()
    }

    override suspend fun refresh() {
        _refreshTrigger.emit(Unit) // 🔥 fuerza recarga
    }
}