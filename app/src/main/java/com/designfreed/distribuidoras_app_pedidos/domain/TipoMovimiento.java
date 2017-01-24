package com.designfreed.distribuidoras_app_pedidos.domain;

public class TipoMovimiento {
    private Long id;
    private String tipoMovimientoNombre;

    public TipoMovimiento() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoMovimientoNombre() {
        return tipoMovimientoNombre;
    }

    public void setTipoMovimientoNombre(String tipoMovimientoNombre) {
        this.tipoMovimientoNombre = tipoMovimientoNombre;
    }
}
