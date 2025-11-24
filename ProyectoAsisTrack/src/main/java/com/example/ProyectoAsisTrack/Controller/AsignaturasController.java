package com.example.ProyectoAsisTrack.Controller;

import com.example.ProyectoAsisTrack.Model.Asignaturas;
import com.example.ProyectoAsisTrack.Service.AsignaturasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturasController {

    @Autowired
    private AsignaturasService asignaturasService;

    @PostMapping("/guardar")
    public ResponseEntity<Asignaturas> guardar(@RequestBody Asignaturas asignatura) {
        return ResponseEntity.ok(asignaturasService.guardar(asignatura));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Asignaturas>> listar() {
        return ResponseEntity.ok(asignaturasService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asignaturas> buscarPorId(@PathVariable Long id) {
        Optional<Asignaturas> asignatura = asignaturasService.buscarPorId(id);
        return asignatura.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Asignaturas> actualizar(@PathVariable Long id, @RequestBody Asignaturas datos) {
        Optional<Asignaturas> existente = asignaturasService.buscarPorId(id);
        if (existente.isPresent()) {
            Asignaturas asignatura = existente.get();
            asignatura.setNombre(datos.getNombre());
            asignatura.setCreditos(datos.getCreditos());
            asignatura.setDocente(datos.getDocente());
            return ResponseEntity.ok(asignaturasService.guardar(asignatura));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        asignaturasService.eliminarPorId(id);
        return ResponseEntity.noContent().build();
    }
}





