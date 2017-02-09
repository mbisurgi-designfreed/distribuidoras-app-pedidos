package com.designfreed.distribuidoras_app_pedidos.domain;

import java.io.Serializable;

public class Motivo implements Serializable {
    private Long id;
    private String motivoNombre;

    public Motivo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMotivoNombre() {
        return motivoNombre;
    }

    public void setMotivoNombre(String motivoNombre) {
        this.motivoNombre = motivoNombre;
    }

    @Override
    public String toString() {
        return motivoNombre;
    }
}
