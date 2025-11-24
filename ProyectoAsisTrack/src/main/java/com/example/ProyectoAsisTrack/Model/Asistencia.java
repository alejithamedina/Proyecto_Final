package com.example.ProyectoAsisTrack.Model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "asistencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asistencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoAsistencia estado;

    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiantes estudiante;

    @ManyToOne
    @JoinColumn(name = "asignatura_id")
    private Asignaturas asignatura;
}



