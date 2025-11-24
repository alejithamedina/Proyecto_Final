package com.example.proyectoasistrack.data.repository

import com.example.proyectoasistrack.data.dao.ReporteDao
import com.example.proyectoasistrack.data.model.Reporte
import kotlinx.coroutines.flow.Flow

class ReportesRepository(private val reporteDao: ReporteDao) {

    fun getAllReportes(): Flow<List<Reporte>> {
        return reporteDao.getAll()
    }

    suspend fun insertReporte(reporte: Reporte) {
        reporteDao.insert(reporte)
    }

    suspend fun updateReporte(reporte: Reporte) {
        reporteDao.update(reporte)
    }

    suspend fun deleteReporte(reporte: Reporte) {
        reporteDao.delete(reporte)
    }
}


