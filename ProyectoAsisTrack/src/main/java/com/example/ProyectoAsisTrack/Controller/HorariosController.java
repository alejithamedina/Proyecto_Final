package com.example.ProyectoAsisTrack.Controller;

import com.example.ProyectoAsisTrack.Model.Horarios;
import com.example.ProyectoAsisTrack.Service.HorariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/horarios")
public class HorariosController {

    @Autowired
    private HorariosService horariosService;

    @PostMapping("/guardar")
    public ResponseEntity<Horarios> guardar(@RequestBody Horarios horario) {
        return ResponseEntity.ok(horariosService.guardar(horario));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Horarios>> listar() {
        return ResponseEntity.ok(horariosService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Horarios> buscarPorId(@PathVariable Long id) {
        Optional<Horarios> horario = horariosService.buscarPorId(id);
        return horario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        horariosService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}




