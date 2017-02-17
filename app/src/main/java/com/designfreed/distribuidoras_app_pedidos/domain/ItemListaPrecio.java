package com.designfreed.distribuidoras_app_pedidos.domain;

public class ItemListaPrecio {
    private Long id;
    private Envase envase;
    private Float precio;

    public ItemListaPrecio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Envase getEnvase() {
        return envase;
    }

    public void setEnvase(Envase envase) {
        this.envase = envase;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }
}
