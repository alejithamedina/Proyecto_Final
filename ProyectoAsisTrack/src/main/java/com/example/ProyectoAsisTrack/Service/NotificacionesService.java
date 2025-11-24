package com.example.ProyectoAsisTrack.Service;

import com.example.ProyectoAsisTrack.Model.Notificaciones;
import com.example.ProyectoAsisTrack.Repository.NotificacionesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NotificacionesService {

    @Autowired
    private NotificacionesRepository notificacionesRepository;

    public Notificaciones guardar(Notificaciones notificacion) {
        return notificacionesRepository.save(notificacion);
    }

    public List<Notificaciones> listar() {
        return notificacionesRepository.findAll();
    }

    public Optional<Notificaciones> buscarPorId(Long id) {
        return notificacionesRepository.findById(id);
    }

    public void eliminar(Long id) {
        notificacionesRepository.deleteById(id);
    }
}



