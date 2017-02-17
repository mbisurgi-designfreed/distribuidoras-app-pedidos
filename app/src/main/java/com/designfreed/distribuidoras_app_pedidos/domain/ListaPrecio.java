package com.designfreed.distribuidoras_app_pedidos.domain;

import java.util.ArrayList;
import java.util.List;

public class ListaPrecio {
    private Long id;
    private String listaPrecioNombre;
    private List<ItemListaPrecio> items = new ArrayList<>();

    public ListaPrecio() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getListaPrecioNombre() {
        return listaPrecioNombre;
    }

    public void setListaPrecioNombre(String listaPrecioNombre) {
        this.listaPrecioNombre = listaPrecioNombre;
    }

    public List<ItemListaPrecio> getItems() {
        return items;
    }

    public void setItems(List<ItemListaPrecio> items) {
        this.items = items;
    }
}
