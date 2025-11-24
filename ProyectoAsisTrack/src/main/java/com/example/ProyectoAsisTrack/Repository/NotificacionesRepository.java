package com.example.ProyectoAsisTrack.Repository;

import com.example.ProyectoAsisTrack.Model.Notificaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificacionesRepository extends JpaRepository<Notificaciones, Long> {
}
