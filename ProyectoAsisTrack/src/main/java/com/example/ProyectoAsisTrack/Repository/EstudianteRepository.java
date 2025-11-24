package com.example.ProyectoAsisTrack.Repository;


import com.example.ProyectoAsisTrack.Model.Estudiantes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiantes, Long> {
}

