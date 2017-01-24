package com.designfreed.distribuidoras_app_pedidos.entities;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ItemMovimientoEntity extends RealmObject {
    @PrimaryKey
    private Long id;
    private Long idCrm;
    private EnvaseEntity envaseEntity;
    private Integer cantidad;

    public ItemMovimientoEntity() {
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

    public EnvaseEntity getEnvaseEntity() {
        return envaseEntity;
    }

    public void setEnvaseEntity(EnvaseEntity envaseEntity) {
        this.envaseEntity = envaseEntity;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
