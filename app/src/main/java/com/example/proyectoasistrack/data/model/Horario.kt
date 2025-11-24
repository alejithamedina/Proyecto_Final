package com.example.proyectoasistrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "horarios")
data class Horario(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val dia: String,
    val horaInicio: String,
    val horaFin: String,
    val asignatura: String,
    val docente: String,
    val salon: String
)


