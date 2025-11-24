package com.example.ProyectoAsisTrack.Service;

import com.example.ProyectoAsisTrack.Model.Asistencia;
import com.example.ProyectoAsisTrack.Repository.AsistenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    public Asistencia guardar(Asistencia asistencia) {
        return asistenciaRepository.save(asistencia);
    }

    public List<Asistencia> listar() {
        return asistenciaRepository.findAll();
    }

    public Optional<Asistencia> buscarPorId(Long id) {
        return asistenciaRepository.findById(id);
    }

    public void eliminar(Long id) {
        asistenciaRepository.deleteById(id);
    }
}





