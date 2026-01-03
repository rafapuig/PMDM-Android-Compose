package es.rafapuig.pmdm.clean.dictionary.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import es.rafapuig.pmdm.clean.dictionary.core.data.local.converter.StringListConverter
import es.rafapuig.pmdm.clean.dictionary.core.data.local.entity.WordInfoEntity

@Database(
    entities = [WordInfoEntity::class],
    version = 1
)
@TypeConverters(Converters::class, StringListConverter::class)
abstract class WordInfoDatabase : RoomDatabase() {
    abstract val dao: WordInfoDao
}