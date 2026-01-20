package es.rafapuig.pmdm.persistence.room.todolist.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Una data entity representa una tabla de la base de datos.
 * Cada instancia de esta clase representa una fila de la tabla.
 * La entidad define un campo para cada columna de la tabla.
 * Incluye una o mas columnas que compondran la clave primaria.
 *
 * Por defecto, Room usa el nombre de la clase como nombre de la tabla.
 * Pero se puede cambiar definiendo la propiedad tableName de la anotacion @Entity.
 *
 * Lo mismo ocurre con el nombre de los campos.
 * Pero se puede cambiar definiendo la propiedad name de las anotaciones @ColumnInfo.
 *
 * (Internamente Room utiliza una base de datos SQLite
 * donde los nombre de la tabla y columnas no son case sensitive)
 *
 * Si se utilizar autogenerate Room asigará IDs automaticamente
 * a las instancias de entidad cuando se inserten en la base de datos.
 */
@Entity(tableName = "todos")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "task") val task : String,
    @ColumnInfo(name = "is_done") val isDone: Boolean = false
)


