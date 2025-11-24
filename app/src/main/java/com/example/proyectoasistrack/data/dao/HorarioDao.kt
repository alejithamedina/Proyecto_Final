package com.example.proyectoasistrack.data.dao

import androidx.room.*
import com.example.proyectoasistrack.data.model.Horario
import kotlinx.coroutines.flow.Flow

@Dao
interface HorarioDao {

    @Query("SELECT * FROM horarios")
    fun getAllHorarios(): Flow<List<Horario>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHorario(horario: Horario)

    @Update
    suspend fun updateHorario(horario: Horario)

    @Delete
    suspend fun deleteHorario(horario: Horario)
}




