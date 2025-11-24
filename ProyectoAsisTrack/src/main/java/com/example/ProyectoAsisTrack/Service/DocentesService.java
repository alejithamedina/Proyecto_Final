package com.example.ProyectoAsisTrack.Service;

import com.example.ProyectoAsisTrack.Model.Docentes;
import com.example.ProyectoAsisTrack.Repository.DocentesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocentesService {

    @Autowired
    private DocentesRepository docentesRepository;

    public Docentes guardar(Docentes docente) {
        return docentesRepository.save(docente);
    }

    public List<Docentes> listar() {
        return docentesRepository.findAll();
    }

    public Optional<Docentes> buscarPorId(Long id) {
        return docentesRepository.findById(id);
    }

    public void eliminar(Long id) {
        docentesRepository.deleteById(id);
    }
}




