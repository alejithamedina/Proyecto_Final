package com.example.proyectoasistrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asistencia")
data class Asistencia(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val estudiante: String,
    val fecha: String,
    val estado: String
)

