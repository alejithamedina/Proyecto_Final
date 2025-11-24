package com.example.proyectoasistrack.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.proyectoasistrack.R
import com.example.proyectoasistrack.data.model.Asistencia
import com.example.proyectoasistrack.data.repository.AsistenciaRepository
import com.example.proyectoasistrack.database.AppDatabase
import com.example.proyectoasistrack.ui.viewmodel.AsistenciaViewModel
import com.example.proyectoasistrack.ui.viewmodel.AsistenciaViewModelFactory
import kotlinx.coroutines.launch

class AsistenciaActivity : AppCompatActivity() {

    private lateinit var contenedor: LinearLayout
    private lateinit var viewModel: AsistenciaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asistencia)

        contenedor = findViewById(R.id.contenedorAsistencia)
        val btnAgregar: Button = findViewById(R.id.btnAgregar)
        val btnDevolver: ImageButton = findViewById(R.id.btnDevolverAsistencia)

        // --- BOTÓN DEVOLVER ---
        btnDevolver.background = null
        btnDevolver.adjustViewBounds = true
        btnDevolver.scaleType = ImageView.ScaleType.CENTER_INSIDE
        btnDevolver.setPadding(0, 0, 0, 0)
        btnDevolver.setOnClickListener { finish() }

        // --- VIEWMODEL ---
        val dao = AppDatabase.getDatabase(application).asistenciaDao()
        val repository = AsistenciaRepository(dao)
        val factory = AsistenciaViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[AsistenciaViewModel::class.java]

        // --- BOTÓN AGREGAR ---
        btnAgregar.setOnClickListener { mostrarDialogo(null) }

        lifecycleScope.launch {
            viewModel.getAllAsistencias().collect { lista ->
                mostrarLista(lista)
            }
        }
    }

    private fun mostrarLista(lista: List<Asistencia>) {
        contenedor.removeAllViews()

        for (a in lista) {

            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(16, 16, 16, 16)
            }

            val tvEstudiante = TextView(this).apply {
                text = "Estudiante: ${a.estudiante}"
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
            }

            val tvFecha = TextView(this).apply {
                text = "Fecha: ${a.fecha}"
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
            }

            val tvEstado = TextView(this).apply {
                text = "Estado: ${a.estado}"
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
            }

            // --- CONTENEDOR DE BOTONES ---
            val botones = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.END
            }

            // --- BOTÓN EDITAR (ICONO) ---
            val btnEditar = ImageButton(this).apply {
                setImageResource(R.drawable.ic_editar)
                background = null
                layoutParams = LinearLayout.LayoutParams(dp(48), dp(48))
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.CENTER_INSIDE
                setPadding(0, 0, 0, 0)
                setOnClickListener { mostrarDialogo(a) }
            }

            // --- BOTÓN ELIMINAR (ICONO) ---
            val btnEliminar = ImageButton(this).apply {
                setImageResource(R.drawable.ic_eliminar)
                background = null
                layoutParams = LinearLayout.LayoutParams(dp(48), dp(48))
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.CENTER_INSIDE
                setPadding(0, 0, 0, 0)
                setOnClickListener { viewModel.delete(a) }
            }

            botones.addView(btnEditar)
            botones.addView(btnEliminar)

            // --- AGREGAR ELEMENTOS ---
            layout.addView(tvEstudiante)
            layout.addView(tvFecha)
            layout.addView(tvEstado)
            layout.addView(botones)

            contenedor.addView(layout)
        }
    }

    // --- DIÁLOGO CREAR / EDITAR ---
    private fun mostrarDialogo(asistencia: Asistencia?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(if (asistencia == null) "Agregar Asistencia" else "Editar Asistencia")

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(16, 16, 16, 16)
        }

        val etEstudiante = EditText(this).apply {
            hint = "Estudiante"
            asistencia?.let { setText(it.estudiante) }
        }

        val etFecha = EditText(this).apply {
            hint = "Fecha"
            asistencia?.let { setText(it.fecha) }
        }

        val spinnerEstado = Spinner(this).apply {
            val opciones = arrayOf("Asistió", "No asistió")
            adapter = ArrayAdapter(this@AsistenciaActivity, android.R.layout.simple_spinner_item, opciones)
            asistencia?.let {
                setSelection(opciones.indexOf(it.estado))
            }
        }

        layout.addView(etEstudiante)
        layout.addView(etFecha)
        layout.addView(spinnerEstado)

        builder.setView(layout)

        builder.setPositiveButton("Guardar") { dialog, _ ->
            val est = etEstudiante.text.toString()
            val fecha = etFecha.text.toString()
            val estado = spinnerEstado.selectedItem.toString()

            if (asistencia == null) {
                viewModel.insert(Asistencia(estudiante = est, fecha = fecha, estado = estado))
            } else {
                viewModel.update(asistencia.copy(estudiante = est, fecha = fecha, estado = estado))
            }
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }

        builder.show()
    }

    private fun dp(value: Int): Int {
        return (value * resources.displayMetrics.density).toInt()
    }
}


