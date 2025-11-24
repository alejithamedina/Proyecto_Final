package com.example.proyectoasistrack.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoasistrack.R
import com.example.proyectoasistrack.data.model.Horario

class HorarioAdapter(private val data: List<Horario>) :
    RecyclerView.Adapter<HorarioAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val txtDia = v.findViewById<TextView>(R.id.txtDia)
        val txtDetalle = v.findViewById<TextView>(R.id.txtDetalle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_horario, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val h = data[position]
        holder.txtDia.text = h.dia
        holder.txtDetalle.text = "${h.horaInicio} - ${h.horaFin} | ${h.asignatura} | Docente: ${h.docente} | Salon: ${h.salon}"
    }

    override fun getItemCount() = data.size
}
