package com.designfreed.distribuidoras_app_pedidos.entities;

import java.io.Serializable;

public class EstadoMovimientoEntity implements Serializable {
    private Long id;
    private String estadoMovimientoNombre;

    public EstadoMovimientoEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstadoMovimientoNombre() {
        return estadoMovimientoNombre;
    }

    public void setEstadoMovimientoNombre(String estadoMovimientoNombre) {
        this.estadoMovimientoNombre = estadoMovimientoNombre;
    }
}
