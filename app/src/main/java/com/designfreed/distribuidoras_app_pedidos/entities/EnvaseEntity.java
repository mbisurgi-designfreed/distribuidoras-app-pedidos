package com.designfreed.distribuidoras_app_pedidos.entities;

import java.io.Serializable;

public class EnvaseEntity implements Serializable {
    private Long id;
    private Integer envaseCodigo;
    private String envaseNombre;
    private Float kilos;

    public EnvaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getEnvaseCodigo() {
        return envaseCodigo;
    }

    public void setEnvaseCodigo(Integer envaseCodigo) {
        this.envaseCodigo = envaseCodigo;
    }

    public String getEnvaseNombre() {
        return envaseNombre;
    }

    public void setEnvaseNombre(String envaseNombre) {
        this.envaseNombre = envaseNombre;
    }

    public Float getKilos() {
        return kilos;
    }

    public void setKilos(Float kilos) {
        this.kilos = kilos;
    }

    @Override
    public String toString() {
        return envaseCodigo + " - " + envaseNombre;
    }
}
