package com.example.ProyectoAsisTrack.Controller;

import com.example.ProyectoAsisTrack.Model.Asistencia;
import com.example.ProyectoAsisTrack.Service.AsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asistencia")
public class AsistenciaController {

    @Autowired
    private AsistenciaService asistenciaService;

    @PostMapping("/guardar")
    public ResponseEntity<Asistencia> guardar(@RequestBody Asistencia asistencia) {
        return ResponseEntity.ok(asistenciaService.guardar(asistencia));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Asistencia>> listar() {
        return ResponseEntity.ok(asistenciaService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistencia> buscarPorId(@PathVariable Long id) {
        Optional<Asistencia> asistencia = asistenciaService.buscarPorId(id);
        return asistencia.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        asistenciaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}



