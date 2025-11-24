package com.example.proyectoasistrack.data.repository

import com.example.proyectoasistrack.data.dao.EstudianteDao
import com.example.proyectoasistrack.data.model.Estudiante
import kotlinx.coroutines.flow.Flow

class EstudiantesRepository(private val estudianteDao: EstudianteDao) {

    fun getAllEstudiantes(): Flow<List<Estudiante>> {
        return estudianteDao.getAllEstudiantes()
    }

    suspend fun insert(estudiante: Estudiante) {
        estudianteDao.insert(estudiante)
    }

    suspend fun update(estudiante: Estudiante) {
        estudianteDao.update(estudiante)
    }

    suspend fun delete(estudiante: Estudiante) {
        estudianteDao.delete(estudiante)
    }
}

