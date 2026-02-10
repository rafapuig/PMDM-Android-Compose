package es.rafapuig.pmdm.di.todolist.data.local.factories

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import es.rafapuig.pmdm.di.todolist.data.local.TodosDatabase
import es.rafapuig.pmdm.persistence.room.todolist.data.local.providers.loadSampleData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SampleDataLoaderCallback(
    private val databaseProvider: () -> TodosDatabase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val loadData: suspend TodosDatabase.() -> Unit = {
        this.loadSampleData()
    }
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        val scope = CoroutineScope(dispatcher)

        scope.launch {
            databaseProvider().loadData()
        }
    }

}