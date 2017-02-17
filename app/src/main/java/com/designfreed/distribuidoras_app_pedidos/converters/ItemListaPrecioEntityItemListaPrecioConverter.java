package com.designfreed.distribuidoras_app_pedidos.converters;

import com.designfreed.distribuidoras_app_pedidos.domain.ItemListaPrecio;
import com.designfreed.distribuidoras_app_pedidos.entities.ItemListaPrecioEntity;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class ItemListaPrecioEntityItemListaPrecioConverter {
    public ItemListaPrecioEntityItemListaPrecioConverter() {

    }

    public RealmList<ItemListaPrecioEntity> itemsListaPrecioToItemsListaPrecioEntity(List<ItemListaPrecio> items) {
        RealmList<ItemListaPrecioEntity> itemsEntity = new RealmList<>();

        for (ItemListaPrecio item: items) {
            ItemListaPrecioEntity itemEntity = new ItemListaPrecioEntity();
            itemEntity.setId(item.getId());
            itemEntity.setIdCrm(item.getId());
            itemEntity.setEnvaseEntity(new EnvaseEntityEnvaseConverter().envaseToEnvaseEntity(item.getEnvase()));
            itemEntity.setPrecio(item.getPrecio());

            itemsEntity.add(itemEntity);
        }

        return itemsEntity;
    }

    public List<ItemListaPrecio> itemsListaPrecioEntityToItemsListaPrecio(RealmList<ItemListaPrecioEntity> itemsEntity) {
        List<ItemListaPrecio> items = new ArrayList<>();

        for (ItemListaPrecioEntity itemEntity: itemsEntity) {
            ItemListaPrecio item = new ItemListaPrecio();
            item.setId(itemEntity.getIdCrm());
            item.setEnvase(new EnvaseEntityEnvaseConverter().envaseEntityToEnvase(itemEntity.getEnvaseEntity()));
            item.setPrecio(itemEntity.getPrecio());

            items.add(item);
        }

        return items;
    }
}
