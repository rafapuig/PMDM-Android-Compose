package es.rafapuig.pmdm.client.todolist.data.remote


object NetworkModule {

    val apiService by lazy {
        TodoApiServiceImpl(KtorClientFactory.create())
    }

}