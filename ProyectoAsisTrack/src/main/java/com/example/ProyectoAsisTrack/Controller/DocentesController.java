package com.example.ProyectoAsisTrack.Controller;

import com.example.ProyectoAsisTrack.Model.Docentes;
import com.example.ProyectoAsisTrack.Service.DocentesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/docentes")
public class DocentesController {

    @Autowired
    private DocentesService docentesService;

    @PostMapping("/guardar")
    public ResponseEntity<Docentes> guardar(@RequestBody Docentes docente) {
        return ResponseEntity.ok(docentesService.guardar(docente));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Docentes>> listar() {
        return ResponseEntity.ok(docentesService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docentes> buscarPorId(@PathVariable Long id) {
        Optional<Docentes> docente = docentesService.buscarPorId(id);
        return docente.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Docentes> actualizar(@PathVariable Long id, @RequestBody Docentes datos) {
        Optional<Docentes> existente = docentesService.buscarPorId(id);
        if (existente.isPresent()) {
            Docentes docente = existente.get();
            docente.setNombre(datos.getNombre());
            docente.setIdentificacion(datos.getIdentificacion());
            return ResponseEntity.ok(docentesService.guardar(docente));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        docentesService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}




