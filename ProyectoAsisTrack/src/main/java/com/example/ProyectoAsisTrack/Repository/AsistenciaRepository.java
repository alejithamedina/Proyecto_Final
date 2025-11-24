package com.example.ProyectoAsisTrack.Repository;

import com.example.ProyectoAsisTrack.Model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Long> {
}

