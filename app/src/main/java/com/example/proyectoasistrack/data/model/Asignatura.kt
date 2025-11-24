package com.example.proyectoasistrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "asignaturas")
data class Asignatura(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nombre: String,
    val salon: String,
    val horario: String
)


