package com.example.proyectoasistrack.data.repository

import com.example.proyectoasistrack.data.dao.HorarioDao
import com.example.proyectoasistrack.data.model.Horario
import kotlinx.coroutines.flow.Flow

class HorariosRepository(private val horarioDao: HorarioDao) {

    fun getAllHorarios(): Flow<List<Horario>> = horarioDao.getAllHorarios()

    suspend fun insertHorario(horario: Horario) = horarioDao.insertHorario(horario)

    suspend fun updateHorario(horario: Horario) = horarioDao.updateHorario(horario)

    suspend fun deleteHorario(horario: Horario) = horarioDao.deleteHorario(horario)
}

