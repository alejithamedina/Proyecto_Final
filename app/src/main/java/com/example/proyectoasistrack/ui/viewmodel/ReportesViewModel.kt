package com.example.proyectoasistrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoasistrack.data.model.Reporte
import com.example.proyectoasistrack.data.repository.ReportesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ReportesViewModel(private val repository: ReportesRepository) : ViewModel() {

    fun getAllReportes(): Flow<List<Reporte>> {
        return repository.getAllReportes()
    }

    fun insertReporte(reporte: Reporte) {
        viewModelScope.launch {
            repository.insertReporte(reporte)
        }
    }

    fun updateReporte(reporte: Reporte) {
        viewModelScope.launch {
            repository.updateReporte(reporte)
        }
    }

    fun deleteReporte(reporte: Reporte) {
        viewModelScope.launch {
            repository.deleteReporte(reporte)
        }
    }
}

