package com.example.proyectoasistrack.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reportes")
data class Reporte(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val titulo: String,
    val descripcion: String,
    val fecha: String
)



