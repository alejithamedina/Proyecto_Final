package com.example.ProyectoAsisTrack.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "estudiantes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estudiantes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String matricula;

    @OneToMany(mappedBy = "estudiante", cascade = CascadeType.ALL)
    private List<Asistencia> asistencias;
}




