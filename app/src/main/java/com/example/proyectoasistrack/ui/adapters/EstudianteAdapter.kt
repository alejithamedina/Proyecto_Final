package com.example.proyectoasistrack.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoasistrack.R
import com.example.proyectoasistrack.data.model.Estudiante

class EstudianteAdapter(private val data: List<Estudiante>) :
    RecyclerView.Adapter<EstudianteAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val txtNombre = v.findViewById<TextView>(R.id.txtNombre)
        val txtDetalle = v.findViewById<TextView>(R.id.txtDetalle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_estudiante, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val e = data[position]
        holder.txtNombre.text = "${e.nombre} ${e.apellido}"
        holder.txtDetalle.text = "Documento: ${e.documento} | Email: ${e.email} | Grado: ${e.grado}"
    }

    override fun getItemCount() = data.size
}
