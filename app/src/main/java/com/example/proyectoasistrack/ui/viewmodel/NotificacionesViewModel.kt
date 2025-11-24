package com.example.proyectoasistrack.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectoasistrack.data.model.Notificacion
import com.example.proyectoasistrack.data.repository.NotificacionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NotificacionesViewModel(private val repository: NotificacionRepository) : ViewModel() {

    fun getAllNotificaciones(): Flow<List<Notificacion>> = repository.getAllNotificaciones()

    fun insert(notificacion: Notificacion) {
        viewModelScope.launch {
            repository.insert(notificacion)
        }
    }

    fun update(notificacion: Notificacion) {
        viewModelScope.launch {
            repository.update(notificacion)
        }
    }

    fun delete(notificacion: Notificacion) {
        viewModelScope.launch {
            repository.delete(notificacion)
        }
    }
}


