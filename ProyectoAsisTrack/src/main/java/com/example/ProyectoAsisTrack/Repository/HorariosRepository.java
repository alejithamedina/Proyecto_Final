package com.example.ProyectoAsisTrack.Repository;

import com.example.ProyectoAsisTrack.Model.Horarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HorariosRepository extends JpaRepository<Horarios, Long> {
}