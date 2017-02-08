package com.fujica.bisai.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Torneo.
 */
@Entity
@Table(name = "torneo")
public class Torneo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "numero_participantes", nullable = false)
    private Integer numeroParticipantes;

    @ManyToOne
    private Juego juego;

    @OneToMany(mappedBy = "torneo")
    @JsonIgnore
    private Set<Clasificacion> clasificacions = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Torneo nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumeroParticipantes() {
        return numeroParticipantes;
    }

    public Torneo numeroParticipantes(Integer numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
        return this;
    }

    public void setNumeroParticipantes(Integer numeroParticipantes) {
        this.numeroParticipantes = numeroParticipantes;
    }

    public Juego getJuego() {
        return juego;
    }

    public Torneo juego(Juego juego) {
        this.juego = juego;
        return this;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Set<Clasificacion> getClasificacions() {
        return clasificacions;
    }

    public Torneo clasificacions(Set<Clasificacion> clasificacions) {
        this.clasificacions = clasificacions;
        return this;
    }

    public Torneo addClasificacion(Clasificacion clasificacion) {
        clasificacions.add(clasificacion);
        clasificacion.setTorneo(this);
        return this;
    }

    public Torneo removeClasificacion(Clasificacion clasificacion) {
        clasificacions.remove(clasificacion);
        clasificacion.setTorneo(null);
        return this;
    }

    public void setClasificacions(Set<Clasificacion> clasificacions) {
        this.clasificacions = clasificacions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Torneo torneo = (Torneo) o;
        if (torneo.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, torneo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Torneo{" +
            "id=" + id +
            ", nombre='" + nombre + "'" +
            ", numeroParticipantes='" + numeroParticipantes + "'" +
            '}';
    }
}
