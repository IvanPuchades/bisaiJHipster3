package com.fujica.bisai.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Local.
 */
@Entity
@Table(name = "local")
public class Local implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "url_localizacion")
    private String urlLocalizacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public Local nombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUrlLocalizacion() {
        return urlLocalizacion;
    }

    public Local urlLocalizacion(String urlLocalizacion) {
        this.urlLocalizacion = urlLocalizacion;
        return this;
    }

    public void setUrlLocalizacion(String urlLocalizacion) {
        this.urlLocalizacion = urlLocalizacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Local local = (Local) o;
        if (local.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, local.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Local{" +
            "id=" + id +
            ", nombre='" + nombre + "'" +
            ", urlLocalizacion='" + urlLocalizacion + "'" +
            '}';
    }
}
