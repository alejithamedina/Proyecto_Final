package com.example.proyectoasistrack.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.Gravity
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.proyectoasistrack.R
import com.example.proyectoasistrack.data.model.Notificacion
import com.example.proyectoasistrack.data.repository.NotificacionRepository
import com.example.proyectoasistrack.database.AppDatabase
import com.example.proyectoasistrack.ui.viewmodel.NotificacionesViewModel
import com.example.proyectoasistrack.ui.viewmodel.NotificacionesViewModelFactory
import kotlinx.coroutines.launch

class NotificacionesActivity : AppCompatActivity() {

    private lateinit var contenedor: LinearLayout
    private lateinit var viewModel: NotificacionesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notificaciones)

        contenedor = findViewById(R.id.contenedorNotificaciones)
        val btnAgregar: Button = findViewById(R.id.btnAgregar)

        val dao = AppDatabase.getDatabase(application).notificacionDao()
        val repository = NotificacionRepository(dao)
        val factory = NotificacionesViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[NotificacionesViewModel::class.java]

        btnAgregar.setOnClickListener { mostrarDialogo(null) }

        // Botón devolver al final
        val btnDevolver = findViewById<ImageButton>(R.id.btnDevolverNotificaciones)
        btnDevolver.setOnClickListener { finish() }

        lifecycleScope.launch {
            viewModel.getAllNotificaciones().collect { lista ->
                mostrarLista(lista)
            }
        }
    }

    private fun mostrarLista(lista: List<Notificacion>) {
        contenedor.removeAllViews()

        for (n in lista) {
            val layout = LinearLayout(this).apply {
                orientation = LinearLayout.VERTICAL
                setPadding(16, 16, 16, 16)
            }

            val tvTipo = TextView(this).apply {
                text = "Tipo: ${n.tipo}"
                setTextColor(resources.getColor(R.color.azul_oscuro, null))
            }
            val tvFecha = TextView(this).apply { text = "Fecha: ${n.fecha}"; setTextColor(resources.getColor(R.color.azul_oscuro, null)) }
            val tvDescripcion = TextView(this).apply { text = "Descripción: ${n.descripcion}"; setTextColor(resources.getColor(R.color.azul_oscuro, null)) }
            val tvMotivo = TextView(this).apply { text = "Motivo: ${n.motivo}"; setTextColor(resources.getColor(R.color.azul_oscuro, null)) }
            val tvEnviado = TextView(this).apply { text = "Enviado: ${if(n.enviado) "Sí" else "No"}"; setTextColor(resources.getColor(R.color.azul_oscuro, null)) }

            // Botones con iconos
            val botones = LinearLayout(this).apply {
                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.END
            }

            val btnEditar = ImageButton(this).apply {
                setImageResource(R.drawable.ic_editar)
                background = null
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.FIT_CENTER
                layoutParams = LinearLayout.LayoutParams(200, 200)
                setOnClickListener { mostrarDialogo(n) }
            }

            val btnEliminar = ImageButton(this).apply {
                setImageResource(R.drawable.ic_eliminar)
                background = null
                adjustViewBounds = true
                scaleType = ImageView.ScaleType.FIT_CENTER
                layoutParams = LinearLayout.LayoutParams(200, 200)
                setOnClickListener { viewModel.delete(n) }
            }

            botones.addView(btnEditar)
            botones.addView(btnEliminar)

            layout.addView(tvTipo)
            layout.addView(tvFecha)
            layout.addView(tvDescripcion)
            layout.addView(tvMotivo)
            layout.addView(tvEnviado)
            layout.addView(botones)

            contenedor.addView(layout)
        }
    }

    private fun mostrarDialogo(notificacion: Notificacion?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(if(notificacion == null) "Agregar Notificación" else "Editar Notificación")

        val layout = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(16,16,16,16) }

        val etTipo = EditText(this).apply { hint = "Tipo"; notificacion?.let { setText(it.tipo) } }
        val etFecha = EditText(this).apply { hint = "Fecha"; notificacion?.let { setText(it.fecha) } }
        val etDescripcion = EditText(this).apply { hint = "Descripción"; notificacion?.let { setText(it.descripcion) } }
        val etMotivo = EditText(this).apply { hint = "Motivo"; notificacion?.let { setText(it.motivo) } }
        val etEstudianteId = EditText(this).apply { hint = "ID Estudiante"; notificacion?.let { setText(it.estudianteId.toString()) } }

        layout.addView(etTipo)
        layout.addView(etFecha)
        layout.addView(etDescripcion)
        layout.addView(etMotivo)
        layout.addView(etEstudianteId)

        builder.setView(layout)

        builder.setPositiveButton("Guardar") { dialog, _ ->
            val n = Notificacion(
                id = notificacion?.id ?: 0,
                tipo = etTipo.text.toString(),
                fecha = etFecha.text.toString(),
                descripcion = etDescripcion.text.toString(),
                motivo = etMotivo.text.toString(),
                estudianteId = etEstudianteId.text.toString().toIntOrNull() ?: 0
            )
            if(notificacion == null) viewModel.insert(n)
            else viewModel.update(n)
            dialog.dismiss()
        }

        builder.setNegativeButton("Cancelar") { dialog, _ -> dialog.dismiss() }

        builder.show()
    }
}




