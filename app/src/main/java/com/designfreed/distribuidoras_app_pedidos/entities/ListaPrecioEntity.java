package com.designfreed.distribuidoras_app_pedidos.entities;

import java.io.Serializable;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ListaPrecioEntity extends RealmObject implements Serializable {
    @PrimaryKey
    private Long id;
    private Long idCrm;
    private String listaPrecioNombre;
    private RealmList<ItemListaPrecioEntity> items;

    public ListaPrecioEntity() {
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

    public String getListaPrecioNombre() {
        return listaPrecioNombre;
    }

    public void setListaPrecioNombre(String listaPrecioNombre) {
        this.listaPrecioNombre = listaPrecioNombre;
    }

    public RealmList<ItemListaPrecioEntity> getItems() {
        return items;
    }

    public void setItems(RealmList<ItemListaPrecioEntity> items) {
        this.items = items;
    }
}
