package com.designfreed.distribuidoras_app_pedidos.entities;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class HojaRutaEntity extends RealmObject {
    @PrimaryKey
    private Long id;
    private Long idCrm;
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

    public Long getIdCrm() {
        return idCrm;
    }

    public void setIdCrm(Long idCrm) {
        this.idCrm = idCrm;
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
                "idCrm=" + idCrm +
                ", fecha=" + fecha +
                ", choferEntity=" + choferEntity +
                ", estado=" + estado +
                ", controlStock=" + controlStock +
                '}';
    }
}
