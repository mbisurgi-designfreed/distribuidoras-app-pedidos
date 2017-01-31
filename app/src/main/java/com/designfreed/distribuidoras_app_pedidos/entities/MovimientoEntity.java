package com.designfreed.distribuidoras_app_pedidos.entities;

import java.util.Date;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MovimientoEntity extends RealmObject {
    @PrimaryKey
    private Long id;
    private Long idCrm;
    private Date fecha;
    private ClienteEntity clienteEntity;
    private CondicionVentaEntity condicionVentaEntity;
    private TipoMovimientoEntity tipoMovimientoEntity;
    private EstadoMovimientoEntity estadoMovimientoEntity;
    private HojaRutaEntity hojaRutaEntity;
    private Boolean visito;
    private Boolean vendio;
    private RealmList<ItemMovimientoEntity> items;
    private Boolean sincronizado;

    public MovimientoEntity() {
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

    public ClienteEntity getClienteEntity() {
        return clienteEntity;
    }

    public void setClienteEntity(ClienteEntity clienteEntity) {
        this.clienteEntity = clienteEntity;
    }

    public CondicionVentaEntity getCondicionVentaEntity() {
        return condicionVentaEntity;
    }

    public void setCondicionVentaEntity(CondicionVentaEntity condicionVentaEntity) {
        this.condicionVentaEntity = condicionVentaEntity;
    }

    public TipoMovimientoEntity getTipoMovimientoEntity() {
        return tipoMovimientoEntity;
    }

    public void setTipoMovimientoEntity(TipoMovimientoEntity tipoMovimientoEntity) {
        this.tipoMovimientoEntity = tipoMovimientoEntity;
    }

    public EstadoMovimientoEntity getEstadoMovimientoEntity() {
        return estadoMovimientoEntity;
    }

    public void setEstadoMovimientoEntity(EstadoMovimientoEntity estadoMovimientoEntity) {
        this.estadoMovimientoEntity = estadoMovimientoEntity;
    }

    public HojaRutaEntity getHojaRutaEntity() {
        return hojaRutaEntity;
    }

    public void setHojaRutaEntity(HojaRutaEntity hojaRutaEntity) {
        this.hojaRutaEntity = hojaRutaEntity;
    }

    public Boolean getVisito() {
        return visito;
    }

    public void setVisito(Boolean visito) {
        this.visito = visito;
    }

    public Boolean getVendio() {
        return vendio;
    }

    public void setVendio(Boolean vendio) {
        this.vendio = vendio;
    }

    public RealmList<ItemMovimientoEntity> getItems() {
        return items;
    }

    public void setItems(RealmList<ItemMovimientoEntity> items) {
        this.items = items;
    }

    public Boolean getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(Boolean sincronizado) {
        this.sincronizado = sincronizado;
    }
}
