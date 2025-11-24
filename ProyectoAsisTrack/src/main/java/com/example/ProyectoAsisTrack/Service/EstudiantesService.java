package com.example.ProyectoAsisTrack.Service;

import com.example.ProyectoAsisTrack.Model.Estudiantes;
import com.example.ProyectoAsisTrack.Repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudiantesService {

    @Autowired
    private EstudianteRepository estudianteRepository;

    public Estudiantes guardar(Estudiantes estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public List<Estudiantes> listar() {
        return estudianteRepository.findAll();
    }

    public Optional<Estudiantes> buscarPorId(Long id) {
        return estudianteRepository.findById(id);
    }

    public void eliminar(Long id) {
        estudianteRepository.deleteById(id);
    }
}


