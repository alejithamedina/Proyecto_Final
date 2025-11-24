package com.example.ProyectoAsisTrack.Controller;

import com.example.ProyectoAsisTrack.Model.Estudiantes;
import com.example.ProyectoAsisTrack.Service.EstudiantesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudiantesController {

    @Autowired
    private EstudiantesService estudiantesService;

    @PostMapping("/guardar")
    public ResponseEntity<Estudiantes> guardar(@RequestBody Estudiantes estudiante) {
        return ResponseEntity.ok(estudiantesService.guardar(estudiante));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Estudiantes>> listar() {
        return ResponseEntity.ok(estudiantesService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudiantes> buscarPorId(@PathVariable Long id) {
        Optional<Estudiantes> estudiante = estudiantesService.buscarPorId(id);
        return estudiante.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Estudiantes> actualizar(@PathVariable Long id, @RequestBody Estudiantes datos) {
        Optional<Estudiantes> existente = estudiantesService.buscarPorId(id);
        if (existente.isPresent()) {
            Estudiantes estudiante = existente.get();
            estudiante.setNombre(datos.getNombre());
            estudiante.setMatricula(datos.getMatricula());
            return ResponseEntity.ok(estudiantesService.guardar(estudiante));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        estudiantesService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}



