package com.example.ProyectoAsisTrack.Service;

import com.example.ProyectoAsisTrack.Model.Asignaturas;
import com.example.ProyectoAsisTrack.Repository.AsignaturasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsignaturasService {

    @Autowired
    private AsignaturasRepository asignaturasRepository;

    public Asignaturas guardar(Asignaturas asignatura) {
        return asignaturasRepository.save(asignatura);
    }

    public List<Asignaturas> listar() {
        return asignaturasRepository.findAll();
    }

    public Optional<Asignaturas> buscarPorId(Long id) {
        return asignaturasRepository.findById(id);
    }

    public void eliminarPorId(Long id) {
        asignaturasRepository.deleteById(id);
    }
}


