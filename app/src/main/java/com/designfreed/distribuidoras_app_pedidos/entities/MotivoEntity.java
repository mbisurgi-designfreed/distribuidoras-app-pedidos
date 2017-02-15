package com.designfreed.distribuidoras_app_pedidos.entities;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MotivoEntity extends RealmObject implements Serializable {
    @PrimaryKey
    private Long id;
    private Long idCrm;
    private String nombreMotivo;

    public MotivoEntity() {
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

    public String getNombreMotivo() {
        return nombreMotivo;
    }

    public void setNombreMotivo(String nombreMotivo) {
        this.nombreMotivo = nombreMotivo;
    }

    @Override
    public String toString() {
        return nombreMotivo;
    }
}
