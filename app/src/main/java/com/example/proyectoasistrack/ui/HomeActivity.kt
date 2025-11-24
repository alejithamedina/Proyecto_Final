package com.example.proyectoasistrack.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.proyectoasistrack.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Botones
        val btnHorarios = findViewById<Button>(R.id.btnHorarios)
        val btnAsistencia = findViewById<Button>(R.id.btnAsistencia)
        val btnEstudiantes = findViewById<Button>(R.id.btnEstudiantes)
        val btnDocentes = findViewById<Button>(R.id.btnDocentes)
        val btnAsignaturas = findViewById<Button>(R.id.btnAsignaturas)
        val btnReportes = findViewById<Button>(R.id.btnReportes)
        val btnPerfil = findViewById<Button>(R.id.btnPerfil)
        val btnLogout = findViewById<Button>(R.id.btnLogout)
        val btnNotificaciones = findViewById<Button>(R.id.btnNotificaciones)

        // Listeners
        btnHorarios.setOnClickListener {
            startActivity(Intent(this, HorariosActivity::class.java))
        }

        btnAsistencia.setOnClickListener {
            startActivity(Intent(this, AsistenciaActivity::class.java))
        }

        btnEstudiantes.setOnClickListener {
            startActivity(Intent(this, EstudiantesActivity::class.java))
        }

        btnDocentes.setOnClickListener {
            startActivity(Intent(this, DocentesActivity::class.java))
        }

        btnAsignaturas.setOnClickListener {
            startActivity(Intent(this, AsignaturasActivity::class.java))
        }

        btnReportes.setOnClickListener {
            startActivity(Intent(this, ReportesActivity::class.java))
        }

        btnPerfil.setOnClickListener {
            startActivity(Intent(this, PerfilActivity::class.java))
        }

        btnNotificaciones.setOnClickListener {
            startActivity(Intent(this, NotificacionesActivity::class.java))
        }

        btnLogout.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}





