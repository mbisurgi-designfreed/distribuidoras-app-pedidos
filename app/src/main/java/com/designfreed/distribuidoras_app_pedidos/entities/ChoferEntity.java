package com.designfreed.distribuidoras_app_pedidos.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ChoferEntity extends RealmObject {
    @PrimaryKey
    private Long id;
    private Long idCrm;
    private String nombre;
    private String apellido;
    private String dni;
    private String password;

    public ChoferEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdCrm() {
        return idCrm;
    }

    public void setIdCrm(Long idCrm) {
        this.idCrm = idCrm;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ChoferEntity{" +
                "idCrm=" + idCrm +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
