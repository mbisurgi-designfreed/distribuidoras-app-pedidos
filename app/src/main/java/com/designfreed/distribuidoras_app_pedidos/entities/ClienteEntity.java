package com.designfreed.distribuidoras_app_pedidos.entities;

import java.io.Serializable;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ClienteEntity extends RealmObject implements Serializable {
    @PrimaryKey
    private Long id;
    private Long idCrm;
    private String razonSocial;
    private String calle;
    private String altura;
    private String telefono;
    private CondicionVentaEntity condicionVentaEntity;
    private ListaPrecioEntity listaPrecioEntity;

    public ClienteEntity() {
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

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public CondicionVentaEntity getCondicionVentaEntity() {
        return condicionVentaEntity;
    }

    public void setCondicionVentaEntity(CondicionVentaEntity condicionVentaEntity) {
        this.condicionVentaEntity = condicionVentaEntity;
    }

    public ListaPrecioEntity getListaPrecioEntity() {
        return listaPrecioEntity;
    }

    public void setListaPrecioEntity(ListaPrecioEntity listaPrecioEntity) {
        this.listaPrecioEntity = listaPrecioEntity;
    }
}
