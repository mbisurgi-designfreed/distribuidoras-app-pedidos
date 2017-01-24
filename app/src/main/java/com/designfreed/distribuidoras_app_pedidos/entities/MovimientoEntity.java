package com.designfreed.distribuidoras_app_pedidos.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovimientoEntity implements Serializable {
    private Long id;
    private Date fecha;
    private ClienteEntity clienteEntity;
    private CondicionVentaEntity condicionVentaEntity;
    private EstadoMovimientoEntity estadoMovimientoEntity;
    private HojaRutaEntity hojaRutaEntity;
    private List<ItemMovimientoEntity> items = new ArrayList<>();

    public MovimientoEntity() {
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

    public List<ItemMovimientoEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemMovimientoEntity> items) {
        this.items = items;
    }
}
