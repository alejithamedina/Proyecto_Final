package com.example.proyectoasistrack.data.dao

import androidx.room.*
import com.example.proyectoasistrack.data.model.Asignatura
import kotlinx.coroutines.flow.Flow

@Dao
interface AsignaturaDao {

    @Query("SELECT * FROM asignaturas")
    fun getAllAsignaturas(): Flow<List<Asignatura>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(asignatura: Asignatura)

    @Update
    suspend fun update(asignatura: Asignatura)

    @Delete
    suspend fun delete(asignatura: Asignatura)
}






