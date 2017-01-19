package com.designfreed.distribuidoras_app_pedidos.domain;

import java.io.Serializable;
import java.util.Date;

public class HojaRuta implements Serializable {
    private Long id;
    private Date fecha;
    private Chofer chofer;
    private Boolean estado;
    private Boolean controlStock;

    public HojaRuta() {
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

    public Chofer getChofer() {
        return chofer;
    }

    public void setChofer(Chofer chofer) {
        this.chofer = chofer;
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
        return "HojaRuta{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", chofer=" + chofer +
                ", estado=" + estado +
                ", controlStock=" + controlStock +
                '}';
    }
}
