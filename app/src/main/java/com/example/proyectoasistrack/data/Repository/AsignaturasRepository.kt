package com.example.proyectoasistrack.data.repository

import com.example.proyectoasistrack.data.dao.AsignaturaDao
import com.example.proyectoasistrack.data.model.Asignatura

class AsignaturasRepository(private val dao: AsignaturaDao) {

    fun getAllAsignaturas() = dao.getAllAsignaturas()

    suspend fun insert(asignatura: Asignatura) = dao.insert(asignatura)

    suspend fun update(asignatura: Asignatura) = dao.update(asignatura)

    suspend fun delete(asignatura: Asignatura) = dao.delete(asignatura)
}



