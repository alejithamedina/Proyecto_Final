package com.example.proyectoasistrack.data.repository

import com.example.proyectoasistrack.data.dao.AsistenciaDao
import com.example.proyectoasistrack.data.model.Asistencia
import kotlinx.coroutines.flow.Flow

class AsistenciaRepository(private val dao: AsistenciaDao) {

    fun getAllAsistencias(): Flow<List<Asistencia>> = dao.getAllAsistencias()

    suspend fun insert(asistencia: Asistencia) = dao.insert(asistencia)

    suspend fun update(asistencia: Asistencia) = dao.update(asistencia)

    suspend fun delete(asistencia: Asistencia) = dao.delete(asistencia)
}
