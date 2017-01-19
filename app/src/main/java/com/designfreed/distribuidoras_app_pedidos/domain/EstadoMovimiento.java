package com.designfreed.distribuidoras_app_pedidos.domain;

import java.io.Serializable;

public class EstadoMovimiento implements Serializable {
    private Long id;
    private String estadoMovimientoNombre;

    public EstadoMovimiento() {
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
