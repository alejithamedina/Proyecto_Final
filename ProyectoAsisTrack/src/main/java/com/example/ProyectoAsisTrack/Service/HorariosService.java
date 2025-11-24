package com.example.ProyectoAsisTrack.Service;

import com.example.ProyectoAsisTrack.Model.Horarios;
import com.example.ProyectoAsisTrack.Repository.HorariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HorariosService {

    @Autowired
    private HorariosRepository horariosRepository;

    public Horarios guardar(Horarios horario) {
        return horariosRepository.save(horario);
    }

    public List<Horarios> listar() {
        return horariosRepository.findAll();
    }

    public Optional<Horarios> buscarPorId(Long id) {
        return horariosRepository.findById(id);
    }

    public void eliminar(Long id) {
        horariosRepository.deleteById(id);
    }
}



