package com.example.proyectoasistrack.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.proyectoasistrack.R
import com.example.proyectoasistrack.data.model.Horario
import com.example.proyectoasistrack.data.repository.HorariosRepository
import com.example.proyectoasistrack.database.AppDatabase
import com.example.proyectoasistrack.ui.viewmodel.HorariosViewModel
import com.example.proyectoasistrack.ui.viewmodel.HorariosViewModelFactory
import kotlinx.coroutines.launch

class HorariosActivity : AppCompatActivity() {

    private lateinit var contenedorHorarios: LinearLayout
    private lateinit var horarioViewModel: HorariosViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horarios)

        contenedorHorarios = findViewById(R.id.contenedorHorarios)
        val btnAgregar: Button = findViewById(R.id.btnAgregarHorario)
        val btnDevolver: ImageButton = findViewById(R.id.btnDevolverHorarios)

        val dao = AppDatabase.getDatabase(application).horarioDao()
        val repository = HorariosRepository(dao)
        val factory = HorariosViewModelFactory(repository)
        horarioViewModel = ViewModelProvider(this, factory)[HorariosViewModel::class.java]

        btnAgregar.setOnClickListener { mostrarDialogo(null) }
        btnDevolver.setOnClickListener { finish() }

        lifecycleScope.launch {
            horarioViewModel.getAllHorarios().collect { lista ->
                mostrarHorarios(lista)
            }
        }
    }

    private fun mostrarHorarios(lista: List<Horario>) {
        contenedorHorarios.removeAllViews()

        for (horario in lista) {

            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(16, 16, 16, 16)
            }

            val tvAsignatura = TextView(this).apply {
                text = "Asignatura: ${horario.asignatura}"
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
            }
            val tvDia = TextView(this).apply {
                text = "Día: ${horario.dia}"
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
            }
            val tvHora = TextView(this).apply {
                text = "Horario: ${horario.horaInicio} - ${horario.horaFin}"
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
            }
            val tvSalon = TextView(this).apply {
                text = "Salón: ${horario.salon}"
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
            }

            val layoutBotones = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.END
            }

            // ICONO EDITAR
            val btnEditar = ImageButton(this).apply {
                setImageResource(R.drawable.ic_editar)
                background = null
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.FIT_CENTER
                layoutParams = LinearLayout.LayoutParams(190, 190)
                setOnClickListener { mostrarDialogo(horario) }
            }

            // ICONO ELIMINAR
            val btnEliminar = ImageButton(this).apply {
                setImageResource(R.drawable.ic_eliminar)
                background = null
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.FIT_CENTER
                layoutParams = LinearLayout.LayoutParams(190, 190)
                setOnClickListener { horarioViewModel.deleteHorario(horario) }
            }

            layoutBotones.addView(btnEditar)
            layoutBotones.addView(btnEliminar)

            layout.addView(tvAsignatura)
            layout.addView(tvDia)
            layout.addView(tvHora)
            layout.addView(tvSalon)
            layout.addView(layoutBotones)

            contenedorHorarios.addView(layout)
        }
    }

    private fun mostrarDialogo(horario: Horario?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(if (horario == null) "Agregar Horario" else "Editar Horario")

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(20, 20, 20, 20)
        }

        val etDia = EditText(this).apply { hint = "Día"; setText(horario?.dia ?: "") }
        val etHoraInicio = EditText(this).apply { hint = "Hora Inicio"; setText(horario?.horaInicio ?: "") }
        val etHoraFin = EditText(this).apply { hint = "Hora Fin"; setText(horario?.horaFin ?: "") }
        val etAsignatura = EditText(this).apply { hint = "Asignatura"; setText(horario?.asignatura ?: "") }
        val etDocente = EditText(this).apply { hint = "Docente"; setText(horario?.docente ?: "") }
        val etSalon = EditText(this).apply { hint = "Salón"; setText(horario?.salon ?: "") }

        layout.addView(etDia)
        layout.addView(etHoraInicio)
        layout.addView(etHoraFin)
        layout.addView(etAsignatura)
        layout.addView(etDocente)
        layout.addView(etSalon)

        builder.setView(layout)

        builder.setPositiveButton("Guardar") { dialog, _ ->
            val nuevoHorario = Horario(
                id = horario?.id ?: 0,
                dia = etDia.text.toString(),
                horaInicio = etHoraInicio.text.toString(),
                horaFin = etHoraFin.text.toString(),
                asignatura = etAsignatura.text.toString(),
                docente = etDocente.text.toString(),
                salon = etSalon.text.toString()
            )

            if (horario == null)
                horarioViewModel.insertHorario(nuevoHorario)
            else
                horarioViewModel.updateHorario(nuevoHorario)

            dialog.dismiss()
        }

        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }

        builder.show()
    }
}







