package com.designfreed.distribuidoras_app_pedidos.entities;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CondicionVentaEntity extends RealmObject implements Serializable {
    @PrimaryKey
    private Long id;
    private Long idCrm;
    private String condicionVentaNombre;

    public CondicionVentaEntity() {
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

    public String getCondicionVentaNombre() {
        return condicionVentaNombre;
    }

    public void setCondicionVentaNombre(String condicionVentaNombre) {
        this.condicionVentaNombre = condicionVentaNombre;
    }
}
