package com.example.ProyectoAsisTrack.Model;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "docentes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Docentes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String identificacion;

    @OneToMany(mappedBy = "docente", cascade = CascadeType.ALL)
    private List<Asignaturas> asignaturas;
}


