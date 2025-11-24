package com.example.ProyectoAsisTrack.Controller;

import com.example.ProyectoAsisTrack.Model.Usuarios;
import com.example.ProyectoAsisTrack.Service.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuariosController {

    @Autowired
    private UsuariosService usuariosService;

    @PostMapping("/registrar")
    public ResponseEntity<Usuarios> registrar(@RequestBody Usuarios usuario) {
        return ResponseEntity.ok(usuariosService.guardar(usuario));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Usuarios>> listar() {
        return ResponseEntity.ok(usuariosService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuarios> buscarPorId(@PathVariable Long id) {
        Optional<Usuarios> usuario = usuariosService.buscarPorId(id);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuariosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}



