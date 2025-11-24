package com.example.proyectoasistrack.data.repository

import com.example.proyectoasistrack.data.dao.NotificacionDao
import com.example.proyectoasistrack.data.model.Notificacion
import kotlinx.coroutines.flow.Flow

class NotificacionRepository(private val dao: NotificacionDao) {

    fun getAllNotificaciones(): Flow<List<Notificacion>> = dao.getAllNotificaciones()

    suspend fun insert(notificacion: Notificacion) = dao.insert(notificacion)

    suspend fun update(notificacion: Notificacion) = dao.update(notificacion)

    suspend fun delete(notificacion: Notificacion) = dao.delete(notificacion)
}


