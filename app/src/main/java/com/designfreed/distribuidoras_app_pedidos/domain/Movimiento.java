package com.designfreed.distribuidoras_app_pedidos.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Movimiento implements Serializable {
    private Long id;
    private Date fecha;
    private Cliente cliente;
    private CondicionVenta condicionVenta;
    private TipoMovimiento tipoMovimiento;
    private EstadoMovimiento estadoMovimiento;
    private HojaRuta hojaRuta;
    private Boolean visito;
    private Boolean vendio;
    private Motivo motivo;
    private List<ItemMovimiento> items = new ArrayList<>();
    private String observaciones;
    private Boolean sincronizado;

    public Movimiento() {
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public CondicionVenta getCondicionVenta() {
        return condicionVenta;
    }

    public void setCondicionVenta(CondicionVenta condicionVenta) {
        this.condicionVenta = condicionVenta;
    }

    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public EstadoMovimiento getEstadoMovimiento() {
        return estadoMovimiento;
    }

    public void setEstadoMovimiento(EstadoMovimiento estadoMovimiento) {
        this.estadoMovimiento = estadoMovimiento;
    }

    public HojaRuta getHojaRuta() {
        return hojaRuta;
    }

    public void setHojaRuta(HojaRuta hojaRuta) {
        this.hojaRuta = hojaRuta;
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

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public List<ItemMovimiento> getItems() {
        return items;
    }

    public void setItems(List<ItemMovimiento> items) {
        this.items = items;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Boolean getSincronizado() {
        return sincronizado;
    }

    public void setSincronizado(Boolean sincronizado) {
        this.sincronizado = sincronizado;
    }
}
