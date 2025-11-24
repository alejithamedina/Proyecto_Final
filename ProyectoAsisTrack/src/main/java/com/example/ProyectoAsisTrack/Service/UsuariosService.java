package com.example.ProyectoAsisTrack.Service;

import com.example.ProyectoAsisTrack.Model.Usuarios;
import com.example.ProyectoAsisTrack.Repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuariosService {

    @Autowired
    private UsuariosRepository usuariosRepository;

    public Usuarios guardar(Usuarios usuario) {
        return usuariosRepository.save(usuario);
    }

    public List<Usuarios> listar() {
        return usuariosRepository.findAll();
    }

    public Optional<Usuarios> buscarPorId(Long id) {
        return usuariosRepository.findById(id);
    }

    public void eliminar(Long id) {
        usuariosRepository.deleteById(id);
    }
}



