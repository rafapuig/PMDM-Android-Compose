package es.rafapuig.pmdm.persistence.room.todolist.data.local.factories

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import es.rafapuig.pmdm.persistence.room.todolist.data.local.TodosDatabase
import es.rafapuig.pmdm.persistence.room.todolist.data.local.providers.loadSampleData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


abstract class AbstractTodosDatabaseFactory(
    private val shouldLoadData: Boolean = true,
    private val loadData: suspend TodosDatabase.() -> Unit = {
        loadSampleData()
    }
) : TodosDatabaseFactory {
    protected abstract fun createBuilder(context: Context): RoomDatabase.Builder<TodosDatabase>

    override fun create(context: Context): TodosDatabase {

        val builder = createBuilder(context)

        val databaseLazy by lazy { builder.build() }

        val dataLoaderCallback = //by lazy {
            SampleDataLoaderCallback(
                databaseProvider = { databaseLazy },
                dispatcher = Dispatchers.IO
            ) { loadData() }
        //}

        val loadSampleDataCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

                val scope = CoroutineScope(Dispatchers.IO)

                scope.launch {
                    //database.loadData()
                    databaseLazy.loadData()
                }
            }
        }

        //database =
            builder.apply {
            if (shouldLoadData) addCallback(loadSampleDataCallback)
        }.build()

        return databaseLazy
    }
}
