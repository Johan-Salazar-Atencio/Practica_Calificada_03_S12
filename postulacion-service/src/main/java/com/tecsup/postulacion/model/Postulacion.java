package com.tecsup.postulacion.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

// Entidad JPA: cada objeto Postulacion es una fila de la tabla "postulacion".
@Entity
@Table(name = "postulacion")
public class Postulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Solo el ID de la vacante: la vacante vive en otro microservicio y otra base de datos.
    @Column(name = "id_vacante", nullable = false)
    private Long idVacante;

    @Column(name = "nombre_postulante", nullable = false, length = 120)
    private String nombrePostulante;

    @Column(nullable = false, length = 120)
    private String correo;

    @Column(nullable = false)
    private LocalDate fecha;

    @Enumerated(EnumType.STRING)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(nullable = false, length = 20)
    private EstadoPostulacion estado;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdVacante() { return idVacante; }
    public void setIdVacante(Long idVacante) { this.idVacante = idVacante; }

    public String getNombrePostulante() { return nombrePostulante; }
    public void setNombrePostulante(String nombrePostulante) { this.nombrePostulante = nombrePostulante; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public EstadoPostulacion getEstado() { return estado; }
    public void setEstado(EstadoPostulacion estado) { this.estado = estado; }
}
