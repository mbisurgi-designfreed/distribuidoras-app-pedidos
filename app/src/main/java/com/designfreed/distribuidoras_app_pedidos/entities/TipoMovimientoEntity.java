package com.designfreed.distribuidoras_app_pedidos.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class TipoMovimientoEntity extends RealmObject {
    @PrimaryKey
    private Long id;
    private Long idCrm;
    private String tipoMovimientoNombre;

    public TipoMovimientoEntity() {
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

    public String getTipoMovimientoNombre() {
        return tipoMovimientoNombre;
    }

    public void setTipoMovimientoNombre(String tipoMovimientoNombre) {
        this.tipoMovimientoNombre = tipoMovimientoNombre;
    }
}
