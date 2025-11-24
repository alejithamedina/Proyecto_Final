package com.example.ProyectoAsisTrack.Repository;

import com.example.ProyectoAsisTrack.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios, Long> {
}

