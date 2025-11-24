
package com.example.proyectoasistrack.data.dao

import androidx.room.*
import com.example.proyectoasistrack.data.model.Docente
import kotlinx.coroutines.flow.Flow

@Dao
interface DocenteDao {

    @Query("SELECT * FROM docentes")
    fun getAll(): Flow<List<Docente>>

    @Insert
    suspend fun insert(docente: Docente)

    @Update
    suspend fun update(docente: Docente)

    @Delete
    suspend fun delete(docente: Docente)
}













