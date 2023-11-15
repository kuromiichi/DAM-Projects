package dev.kuromiichi.databasenavigation.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.kuromiichi.databasenavigation.entity.Nota

@Database(entities = [Nota::class], version = 1)
abstract class NotaDatabase : RoomDatabase() {
    abstract fun notaDao(): NotaDao

    companion object {
        private var instance: NotaDatabase? = null

        fun getDb(context: Context): NotaDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotaDatabase::class.java,
                    "notas"
                ).build()
            }

            return instance!!
        }
    }
}
