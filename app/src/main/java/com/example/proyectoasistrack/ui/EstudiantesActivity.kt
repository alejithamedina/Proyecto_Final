package com.example.proyectoasistrack.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.proyectoasistrack.R
import com.example.proyectoasistrack.data.model.Estudiante
import com.example.proyectoasistrack.data.repository.EstudiantesRepository
import com.example.proyectoasistrack.database.AppDatabase
import com.example.proyectoasistrack.ui.viewmodel.EstudiantesViewModel
import com.example.proyectoasistrack.ui.viewmodel.EstudiantesViewModelFactory
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class EstudiantesActivity : AppCompatActivity() {

    private lateinit var contenedorEstudiantes: LinearLayout
    private lateinit var estudianteViewModel: EstudiantesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_estudiantes)

        contenedorEstudiantes = findViewById(R.id.contenedorEstudiantes)
        val btnAgregar: Button = findViewById(R.id.btnAgregar)
        val btnDevolver: ImageButton = findViewById(R.id.btnDevolverEstudiantes)

        // --- Botón devolver solo icono ---
        btnDevolver.background = null
        btnDevolver.adjustViewBounds = true
        btnDevolver.scaleType = ImageView.ScaleType.CENTER_INSIDE
        btnDevolver.setPadding(0, 0, 0, 0)
        btnDevolver.setOnClickListener { finish() }

        // --- DAO y ViewModel ---
        val estudianteDao = AppDatabase.getDatabase(application).estudianteDao()
        val repository = EstudiantesRepository(estudianteDao)
        val factory = EstudiantesViewModelFactory(repository)
        estudianteViewModel = ViewModelProvider(this, factory)[EstudiantesViewModel::class.java]

        // --- Botón agregar ---
        btnAgregar.setOnClickListener {
            mostrarDialogoAgregar(null)
        }

        // --- Observar lista ---
        lifecycleScope.launch {
            estudianteViewModel.getAllEstudiantes().collect { lista ->
                mostrarEstudiantes(lista)
            }
        }
    }

    private fun mostrarEstudiantes(lista: List<Estudiante>) {
        contenedorEstudiantes.removeAllViews()

        for (estudiante in lista) {
            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(16, 16, 16, 16)
            }

            val tvNombre = TextView(this).apply {
                text = "Nombre: ${estudiante.nombre}"
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
            }

            val tvApellido = TextView(this).apply {
                text = "Apellido: ${estudiante.apellido}"
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
            }

            val tvDocumento = TextView(this).apply {
                text = "Documento: ${estudiante.documento}"
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
            }

            val tvEmail = TextView(this).apply {
                text = "Email: ${estudiante.email}"
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
            }

            val tvGrado = TextView(this).apply {
                text = "Grado: ${estudiante.grado}"
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
            }

            // --- Botones iconos ---
            val layoutBotones = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.END
            }

            val btnEditar = ImageButton(this).apply {
                setImageResource(R.drawable.ic_editar)
                background = null
                layoutParams = LinearLayout.LayoutParams(dp(48), dp(48))
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.CENTER_INSIDE
                setPadding(0, 0, 0, 0)
                setOnClickListener { mostrarDialogoAgregar(estudiante) }
            }

            val btnEliminar = ImageButton(this).apply {
                setImageResource(R.drawable.ic_eliminar)
                background = null
                layoutParams = LinearLayout.LayoutParams(dp(48), dp(48))
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.CENTER_INSIDE
                setPadding(0, 0, 0, 0)
                setOnClickListener { estudianteViewModel.deleteEstudiante(estudiante) }
            }

            layoutBotones.addView(btnEditar)
            layoutBotones.addView(btnEliminar)

            // --- Agregar views al layout principal ---
            layout.addView(tvNombre)
            layout.addView(tvApellido)
            layout.addView(tvDocumento)
            layout.addView(tvEmail)
            layout.addView(tvGrado)
            layout.addView(layoutBotones)

            contenedorEstudiantes.addView(layout)
        }
    }

    private fun mostrarDialogoAgregar(estudiante: Estudiante?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(if (estudiante == null) "Agregar Estudiante" else "Editar Estudiante")

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        val etNombre = EditText(this).apply {
            hint = "Nombre"
            estudiante?.let { setText(it.nombre) }
        }

        val etApellido = EditText(this).apply {
            hint = "Apellido"
            estudiante?.let { setText(it.apellido) }
        }

        val etDocumento = EditText(this).apply {
            hint = "Documento"
            estudiante?.let { setText(it.documento) }
        }

        val etEmail = EditText(this).apply {
            hint = "Email"
            estudiante?.let { setText(it.email) }
        }

        val etGrado = EditText(this).apply {
            hint = "Grado"
            estudiante?.let { setText(it.grado) }
        }

        layout.addView(etNombre)
        layout.addView(etApellido)
        layout.addView(etDocumento)
        layout.addView(etEmail)
        layout.addView(etGrado)

        builder.setView(layout)

        builder.setPositiveButton("Guardar") { dialog, _ ->
            val nombre = etNombre.text.toString()
            val apellido = etApellido.text.toString()
            val documento = etDocumento.text.toString()
            val email = etEmail.text.toString()
            val grado = etGrado.text.toString()

            if (estudiante == null) {
                estudianteViewModel.insertEstudiante(
                    Estudiante(
                        nombre = nombre,
                        apellido = apellido,
                        documento = documento,
                        email = email,
                        grado = grado
                    )
                )
            } else {
                estudianteViewModel.updateEstudiante(
                    estudiante.copy(
                        nombre = nombre,
                        apellido = apellido,
                        documento = documento,
                        email = email,
                        grado = grado
                    )
                )
            }
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }

        builder.show()
    }

    // --- Función para dp ---
    private fun dp(value: Int): Int = (value * resources.displayMetrics.density).toInt()
}





