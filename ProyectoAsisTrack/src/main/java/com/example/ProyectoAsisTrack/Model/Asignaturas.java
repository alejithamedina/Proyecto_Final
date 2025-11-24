package com.example.ProyectoAsisTrack.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "asignaturas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Asignaturas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int creditos;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docentes docente;

    @OneToMany(mappedBy = "asignatura", cascade = CascadeType.ALL)
    private List<Horarios> horarios;
}


