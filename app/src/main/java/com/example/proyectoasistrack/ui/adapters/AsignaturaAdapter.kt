import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyectoasistrack.R
import com.example.proyectoasistrack.data.model.Asignatura

class AsignaturaAdapter(private val lista: List<Asignatura>) :
    RecyclerView.Adapter<AsignaturaAdapter.AsignaturaViewHolder>() {

    class AsignaturaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombreText: TextView = itemView.findViewById(R.id.tvNombre)
        val salonText: TextView = itemView.findViewById(R.id.tvSalon)
        val horarioText: TextView = itemView.findViewById(R.id.tvHorario)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsignaturaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_asignatura, parent, false)
        return AsignaturaViewHolder(view)
    }

    override fun onBindViewHolder(holder: AsignaturaViewHolder, position: Int) {
        val asignatura = lista[position]
        holder.nombreText.text = asignatura.nombre
        holder.salonText.text = asignatura.salon
        holder.horarioText.text = asignatura.horario
    }

    override fun getItemCount(): Int = lista.size
}


