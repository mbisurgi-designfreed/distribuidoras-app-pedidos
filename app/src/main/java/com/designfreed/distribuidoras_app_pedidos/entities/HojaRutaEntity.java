package com.designfreed.distribuidoras_app_pedidos.entities;

import java.io.Serializable;
import java.util.Date;

public class HojaRutaEntity implements Serializable {
    private Long id;
    private Date fecha;
    private ChoferEntity choferEntity;
    private Boolean estado;
    private Boolean controlStock;

    public HojaRutaEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ChoferEntity getChoferEntity() {
        return choferEntity;
    }

    public void setChoferEntity(ChoferEntity choferEntity) {
        this.choferEntity = choferEntity;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public Boolean getControlStock() {
        return controlStock;
    }

    public void setControlStock(Boolean controlStock) {
        this.controlStock = controlStock;
    }

    @Override
    public String toString() {
        return "HojaRutaEntity{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", choferEntity=" + choferEntity +
                ", estado=" + estado +
                ", controlStock=" + controlStock +
                '}';
    }
}
