package com.fujica.bisai.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Equipo.
 */
@Entity
@Table(name = "equipo")
public class Equipo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @ManyToMany
    @JoinTable(name = "equipo_jugador",
               joinColumns = @JoinColumn(name="equipos_id", referencedColumnName="ID"),
               inverseJoinColumns = @JoinColumn(name="jugadors_id", referencedColumnName="ID"))
    private Set<Jugador> jugadors = new HashSet<>();

    @OneToMany(mappedBy = "equipo")
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

    public Equipo nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public Equipo fechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Set<Jugador> getJugadors() {
        return jugadors;
    }

    public Equipo jugadors(Set<Jugador> jugadors) {
        this.jugadors = jugadors;
        return this;
    }

    public Equipo addJugador(Jugador jugador) {
        jugadors.add(jugador);
        jugador.getEquipos().add(this);
        return this;
    }

    public Equipo removeJugador(Jugador jugador) {
        jugadors.remove(jugador);
        jugador.getEquipos().remove(this);
        return this;
    }

    public void setJugadors(Set<Jugador> jugadors) {
        this.jugadors = jugadors;
    }

    public Set<Clasificacion> getClasificacions() {
        return clasificacions;
    }

    public Equipo clasificacions(Set<Clasificacion> clasificacions) {
        this.clasificacions = clasificacions;
        return this;
    }

    public Equipo addClasificacion(Clasificacion clasificacion) {
        clasificacions.add(clasificacion);
        clasificacion.setEquipo(this);
        return this;
    }

    public Equipo removeClasificacion(Clasificacion clasificacion) {
        clasificacions.remove(clasificacion);
        clasificacion.setEquipo(null);
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
        Equipo equipo = (Equipo) o;
        if (equipo.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, equipo.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Equipo{" +
            "id=" + id +
            ", nombre='" + nombre + "'" +
            ", fechaCreacion='" + fechaCreacion + "'" +
            '}';
    }
}
