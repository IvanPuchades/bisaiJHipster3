package com.fujica.bisai.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Jugador.
 */
@Entity
@Table(name = "jugador")
public class Jugador implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "nick_name", nullable = false)
    private String nickName;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @ManyToMany(mappedBy = "jugadors")
    @JsonIgnore
    private Set<Equipo> equipos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public Jugador nickName(String nickName) {
        this.nickName = nickName;
        return this;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public User getUser() {
        return user;
    }

    public Jugador user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Equipo> getEquipos() {
        return equipos;
    }

    public Jugador equipos(Set<Equipo> equipos) {
        this.equipos = equipos;
        return this;
    }

    public Jugador addEquipo(Equipo equipo) {
        equipos.add(equipo);
        equipo.getJugadors().add(this);
        return this;
    }

    public Jugador removeEquipo(Equipo equipo) {
        equipos.remove(equipo);
        equipo.getJugadors().remove(this);
        return this;
    }

    public void setEquipos(Set<Equipo> equipos) {
        this.equipos = equipos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Jugador jugador = (Jugador) o;
        if (jugador.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, jugador.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Jugador{" +
            "id=" + id +
            ", nickName='" + nickName + "'" +
            '}';
    }
}
