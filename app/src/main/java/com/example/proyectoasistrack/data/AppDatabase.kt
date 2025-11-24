package com.example.proyectoasistrack.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


import com.example.proyectoasistrack.data.dao.*
import com.example.proyectoasistrack.data.model.*



@Database(
    entities = [
        Usuario::class,
        Estudiante::class,
        Docente::class,
        Asignatura::class,
        Horario::class,
        Asistencia::class,
        Notificacion::class,
        Reporte::class
    ],
    version = 6,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    abstract fun usuarioDao(): UsuarioDao
    abstract fun estudianteDao(): EstudianteDao
    abstract fun docenteDao(): DocenteDao
    abstract fun horarioDao(): HorarioDao
    abstract fun asistenciaDao(): AsistenciaDao
    abstract fun notificacionDao(): NotificacionDao
    abstract fun reporteDao(): ReporteDao
    abstract fun asignaturaDao(): AsignaturaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "proyecto_asistrack_db"
                )
                    .fallbackToDestructiveMigration() // evita crash por cambios de esquema
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}








