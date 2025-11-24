package com.example.proyectoasistrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "docentes")
data class Docente(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val apellido: String,
    val email: String
)


