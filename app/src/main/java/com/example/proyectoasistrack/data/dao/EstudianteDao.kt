package com.example.proyectoasistrack.data.dao

import androidx.room.*
import com.example.proyectoasistrack.data.model.Estudiante
import kotlinx.coroutines.flow.Flow

@Dao
interface EstudianteDao {

    @Query("SELECT * FROM estudiantes ORDER BY nombre ASC")
    fun getAllEstudiantes(): Flow<List<Estudiante>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(estudiante: Estudiante)

    @Update
    suspend fun update(estudiante: Estudiante)

    @Delete
    suspend fun delete(estudiante: Estudiante)
}










