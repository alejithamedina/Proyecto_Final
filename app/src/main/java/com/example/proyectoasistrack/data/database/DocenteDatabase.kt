package com.example.proyectoasistrack.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proyectoasistrack.data.dao.DocenteDao
import com.example.proyectoasistrack.data.model.Docente

@Database(entities = [Docente::class], version = 1)
abstract class DocenteDatabase : RoomDatabase() {
    abstract fun docenteDao(): DocenteDao

    companion object {
        @Volatile
        private var INSTANCE: DocenteDatabase? = null

        fun getDatabase(context: Context): DocenteDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DocenteDatabase::class.java,
                    "docente_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
