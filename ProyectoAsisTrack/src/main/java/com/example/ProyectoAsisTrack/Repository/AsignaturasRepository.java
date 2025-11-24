package com.example.ProyectoAsisTrack.Repository;

import com.example.ProyectoAsisTrack.Model.Asignaturas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignaturasRepository extends JpaRepository<Asignaturas, Long> {
}
