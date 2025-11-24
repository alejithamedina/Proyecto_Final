package com.example.proyectoasistrack.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoasistrack.R
import com.example.proyectoasistrack.data.model.Docente

class DocenteAdapter(private val data: List<Docente>) :
    RecyclerView.Adapter<DocenteAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val nombre = v.findViewById<TextView>(R.id.txtNombre)
        val email = v.findViewById<TextView>(R.id.txtEmail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_docente, parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val d = data[position]
        holder.nombre.text = "${d.nombre} ${d.apellido}"
        holder.email.text = d.email
    }

    override fun getItemCount() = data.size
}

