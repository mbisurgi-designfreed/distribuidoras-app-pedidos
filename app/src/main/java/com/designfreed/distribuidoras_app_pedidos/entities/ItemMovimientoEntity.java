package com.designfreed.distribuidoras_app_pedidos.entities;

import java.io.Serializable;

public class ItemMovimientoEntity implements Serializable {
    private Long id;
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
