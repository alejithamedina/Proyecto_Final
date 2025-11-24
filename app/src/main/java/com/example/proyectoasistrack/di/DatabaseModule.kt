package com.example.proyectoasistrack.di

import android.content.Context
import androidx.room.Room
import com.example.proyectoasistrack.database.AppDatabase
import com.example.proyectoasistrack.data.dao.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "proyecto_asistrack_db"
        ).build()

    @Provides
    fun provideUsuarioDao(db: AppDatabase): UsuarioDao = db.usuarioDao()

    @Provides
    fun provideDocenteDao(db: AppDatabase): DocenteDao = db.docenteDao()

    @Provides
    fun provideAsignaturaDao(db: AppDatabase): AsignaturaDao = db.asignaturaDao()

    @Provides
    fun provideEstudianteDao(db: AppDatabase): EstudianteDao = db.estudianteDao()

    @Provides
    fun provideHorarioDao(db: AppDatabase): HorarioDao = db.horarioDao()

    @Provides
    fun provideAsistenciaDao(db: AppDatabase): AsistenciaDao = db.asistenciaDao()

    @Provides
    fun provideNotificacionDao(db: AppDatabase): NotificacionDao = db.notificacionDao()

    @Provides
    fun provideReporteDao(db: AppDatabase): ReporteDao = db.reporteDao()
}
