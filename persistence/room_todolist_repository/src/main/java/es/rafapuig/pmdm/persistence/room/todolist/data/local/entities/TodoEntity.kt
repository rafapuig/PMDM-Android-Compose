package es.rafapuig.pmdm.persistence.room.todolist.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "task") val task : String,
    @ColumnInfo(name = "is_done") val isDone: Boolean = false
)


