package com.example.ProyectoAsisTrack.Controller;

import com.example.ProyectoAsisTrack.Model.Notificaciones;
import com.example.ProyectoAsisTrack.Service.NotificacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionesController {

    @Autowired
    private NotificacionesService notificacionesService;

    @PostMapping("/enviar")
    public ResponseEntity<Notificaciones> enviar(@RequestBody Notificaciones notificacion) {
        return ResponseEntity.ok(notificacionesService.guardar(notificacion));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Notificaciones>> listar() {
        return ResponseEntity.ok(notificacionesService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificaciones> buscarPorId(@PathVariable Long id) {
        Optional<Notificaciones> notificacion = notificacionesService.buscarPorId(id);
        return notificacion.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        notificacionesService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}


