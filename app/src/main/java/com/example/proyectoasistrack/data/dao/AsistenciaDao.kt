package com.example.proyectoasistrack.data.dao

import androidx.room.*
import com.example.proyectoasistrack.data.model.Asistencia
import kotlinx.coroutines.flow.Flow

@Dao
interface AsistenciaDao {

    @Query("SELECT * FROM asistencia ORDER BY fecha DESC")
    fun getAllAsistencias(): Flow<List<Asistencia>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(asistencia: Asistencia)

    @Update
    suspend fun update(asistencia: Asistencia)

    @Delete
    suspend fun delete(asistencia: Asistencia)
}



