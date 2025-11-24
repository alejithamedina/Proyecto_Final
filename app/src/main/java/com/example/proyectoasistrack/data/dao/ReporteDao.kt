package com.example.proyectoasistrack.data.dao

import androidx.room.*
import com.example.proyectoasistrack.data.model.Reporte
import kotlinx.coroutines.flow.Flow

@Dao
interface ReporteDao {

    @Query("SELECT * FROM reportes ORDER BY id DESC")
    fun getAll(): Flow<List<Reporte>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reporte: Reporte)

    @Update
    suspend fun update(reporte: Reporte)

    @Delete
    suspend fun delete(reporte: Reporte)
}


