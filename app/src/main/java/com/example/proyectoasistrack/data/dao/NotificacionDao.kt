package com.example.proyectoasistrack.data.dao

import androidx.room.*
import com.example.proyectoasistrack.data.model.Notificacion
import kotlinx.coroutines.flow.Flow

@Dao
interface NotificacionDao {

    @Query("SELECT * FROM notificaciones ORDER BY fecha DESC")
    fun getAllNotificaciones(): Flow<List<Notificacion>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notificacion: Notificacion)

    @Update
    suspend fun update(notificacion: Notificacion)

    @Delete
    suspend fun delete(notificacion: Notificacion)
}



