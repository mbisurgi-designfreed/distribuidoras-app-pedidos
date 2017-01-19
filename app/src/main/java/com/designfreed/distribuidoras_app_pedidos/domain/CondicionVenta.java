package com.designfreed.distribuidoras_app_pedidos.domain;

import java.io.Serializable;

public class CondicionVenta implements Serializable {
    private Long id;
    private String condicionVentaNombre;

    public CondicionVenta() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCondicionVentaNombre() {
        return condicionVentaNombre;
    }

    public void setCondicionVentaNombre(String condicionVentaNombre) {
        this.condicionVentaNombre = condicionVentaNombre;
    }
}
