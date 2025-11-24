package com.example.proyectoasistrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey

@Entity(tableName = "notificaciones")
data class Notificacion(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val estudianteId: Int,
    val tipo: String,
    val fecha: String,
    val descripcion: String,
    val motivo: String,
    val enviado: Boolean = false
)



