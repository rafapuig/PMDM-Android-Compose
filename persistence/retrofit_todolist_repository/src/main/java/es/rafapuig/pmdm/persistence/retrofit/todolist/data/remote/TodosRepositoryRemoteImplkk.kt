package es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote

import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.dto.CreateTodoRequest
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.dto.TodoCompletedPatch
import es.rafapuig.pmdm.persistence.retrofit.todolist.data.remote.mappers.toDomain
import es.rafapuig.pmdm.persistence.retrofit.todolist.domain.model.Todo
import es.rafapuig.pmdm.persistence.retrofit.todolist.domain.repositories.TodosRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodosRepositoryRemoteImplkk(private val api: TodosApiService) : TodosRepository {

    private val refreshTrigger =
        MutableSharedFlow<Unit>(
            replay = 1 // importante para la primera carga
        )
    val operationTrigger = refreshTrigger.asSharedFlow()


    private val _isLoading = MutableStateFlow(false)
    override val isUpdating: StateFlow<Boolean> get() = _isLoading.asStateFlow()

    fun <T> Flow<T>.withDelayedLoading(
        loadingState: MutableStateFlow<Boolean>,
        delayMillis: Long
    ): Flow<T> = channelFlow {

        val loadingJob = launch {
            delay(delayMillis)
            loadingState.update { true }
        }

        try {
            collect { send(it) }
        } finally {
            loadingJob.cancel()
            loadingState.update { false }
        }
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    override val todos: Flow<List<Todo>>
        get() = refreshTrigger
            .onStart { emit(Unit) }
            .flatMapLatest { // Cancelar cargas anteriores si se dispara otra
                flow { emit(api.fetchAllTodos()) }
                    //.withDelayedLoading(_isLoading, 250)
            }
            .map { todosDto ->
                todosDto.map { it.toDomain() }
            }.flowOn(Dispatchers.IO)


    override suspend fun setTodoIsDone(
        todo: Todo,
        isDone: Boolean
    ) {
        api.setTodoDone(
            id = todo.id,
            patch = TodoCompletedPatch(isDone)
        )
        refresh()
    }

    override suspend fun deleteTodo(todo: Todo) {
        api.deleteTodo(todo.id)
        refresh()
    }

    override suspend fun addTodo(task: String) {
        api.addTodo(CreateTodoRequest(task))
        refresh()
    }

    override suspend fun refresh() {
        refreshTrigger.emit(Unit) // 🔥 fuerza recarga
    }
}