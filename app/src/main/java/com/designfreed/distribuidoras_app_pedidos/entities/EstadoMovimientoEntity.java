package com.designfreed.distribuidoras_app_pedidos.entities;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class EstadoMovimientoEntity extends RealmObject implements Serializable {
    @PrimaryKey
    private Long id;
    private Long idCrm;
    private String estadoMovimientoNombre;

    public EstadoMovimientoEntity() {
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

    public String getEstadoMovimientoNombre() {
        return estadoMovimientoNombre;
    }

    public void setEstadoMovimientoNombre(String estadoMovimientoNombre) {
        this.estadoMovimientoNombre = estadoMovimientoNombre;
    }

    @Override
    public String toString() {
        return estadoMovimientoNombre;
    }
}
