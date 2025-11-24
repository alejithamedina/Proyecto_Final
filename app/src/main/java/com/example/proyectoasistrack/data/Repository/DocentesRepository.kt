package com.example.proyectoasistrack.data.repository

import com.example.proyectoasistrack.data.dao.DocenteDao
import com.example.proyectoasistrack.data.model.Docente
import kotlinx.coroutines.flow.Flow

class DocentesRepository(private val docenteDao: DocenteDao) {

    fun getAllDocentes(): Flow<List<Docente>> = docenteDao.getAll()
    suspend fun insertDocente(docente: Docente) = docenteDao.insert(docente)
    suspend fun updateDocente(docente: Docente) = docenteDao.update(docente)
    suspend fun deleteDocente(docente: Docente) = docenteDao.delete(docente)
}