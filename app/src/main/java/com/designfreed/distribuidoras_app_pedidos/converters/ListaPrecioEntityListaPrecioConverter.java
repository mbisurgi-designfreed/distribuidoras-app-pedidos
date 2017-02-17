package com.designfreed.distribuidoras_app_pedidos.converters;

import com.designfreed.distribuidoras_app_pedidos.domain.ListaPrecio;
import com.designfreed.distribuidoras_app_pedidos.entities.ListaPrecioEntity;

public class ListaPrecioEntityListaPrecioConverter {
    public ListaPrecioEntityListaPrecioConverter() {

    }

    public ListaPrecioEntity listaPrecioToListaPrecioEntity(ListaPrecio listaPrecio) {
        ListaPrecioEntity listaPrecioEntity = new ListaPrecioEntity();
        listaPrecioEntity.setId(listaPrecio.getId());
        listaPrecioEntity.setIdCrm(listaPrecio.getId());
        listaPrecioEntity.setListaPrecioNombre(listaPrecio.getListaPrecioNombre());
        listaPrecioEntity.setItems(new ItemListaPrecioEntityItemListaPrecioConverter().itemsListaPrecioToItemsListaPrecioEntity(listaPrecio.getItems()));

        return listaPrecioEntity;
    }

    public ListaPrecio listaPrecioEntityToListaPrecio(ListaPrecioEntity listaPrecioEntity) {
        ListaPrecio listaPrecio = new ListaPrecio();
        listaPrecio.setId(listaPrecioEntity.getIdCrm());
        listaPrecio.setListaPrecioNombre(listaPrecioEntity.getListaPrecioNombre());
        listaPrecio.setItems(new ItemListaPrecioEntityItemListaPrecioConverter().itemsListaPrecioEntityToItemsListaPrecio(listaPrecioEntity.getItems()));

        return listaPrecio;
    }
}
