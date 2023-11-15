package dev.kuromiichi.databasenavigation.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import dev.kuromiichi.databasenavigation.entity.Nota

@Dao
interface NotaDao {
    @Query("SELECT * FROM Nota")
    fun getAll(): List<Nota>

    @Query("SELECT * FROM Nota WHERE id = :id")
    fun getById(id: Int): Nota

    @Insert
    fun insert(nota: Nota)
}
