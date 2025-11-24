package com.example.ProyectoAsisTrack.Repository;

import com.example.ProyectoAsisTrack.Model.Docentes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocentesRepository extends JpaRepository<Docentes, Long> {
}
