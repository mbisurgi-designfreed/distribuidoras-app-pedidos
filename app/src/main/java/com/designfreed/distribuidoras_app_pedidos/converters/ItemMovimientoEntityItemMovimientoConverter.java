package com.designfreed.distribuidoras_app_pedidos.converters;

import com.designfreed.distribuidoras_app_pedidos.domain.ItemMovimiento;
import com.designfreed.distribuidoras_app_pedidos.entities.ItemMovimientoEntity;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

public class ItemMovimientoEntityItemMovimientoConverter {
    public ItemMovimientoEntityItemMovimientoConverter() {
    }

    public RealmList<ItemMovimientoEntity> itemsMovimientoToItemsMovimientoEntity(List<ItemMovimiento> items) {
        RealmList<ItemMovimientoEntity> itemsEntity = new RealmList<>();

        for (ItemMovimiento item: items) {
            ItemMovimientoEntity itemEntity = new ItemMovimientoEntity();
            itemEntity.setId(item.getId());
            itemEntity.setIdCrm(item.getId());
            itemEntity.setEnvaseEntity(new EnvaseEntityEnvaseConverter().envaseToEnvaseEntity(item.getEnvase()));
            itemEntity.setCantidad(item.getCantidad());
            itemEntity.setMonto(item.getMonto());

            itemsEntity.add(itemEntity);
        }

        return itemsEntity;
    }

    public List<ItemMovimiento> itemsMovimientoEntityToItemsMovimiento(RealmList<ItemMovimientoEntity> itemsEntity) {
        List<ItemMovimiento> items = new ArrayList<>();

        for (ItemMovimientoEntity itemEntity: itemsEntity) {
            ItemMovimiento item = new ItemMovimiento();
            item.setId(itemEntity.getIdCrm());
            item.setEnvase(new EnvaseEntityEnvaseConverter().envaseEntityToEnvase(itemEntity.getEnvaseEntity()));
            item.setCantidad(itemEntity.getCantidad());
            item.setMonto(itemEntity.getMonto());

            items.add(item);
        }

        return items;
    }
}
