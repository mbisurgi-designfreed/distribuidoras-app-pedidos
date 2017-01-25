package com.designfreed.distribuidoras_app_pedidos.converters;

import com.designfreed.distribuidoras_app_pedidos.domain.CondicionVenta;
import com.designfreed.distribuidoras_app_pedidos.entities.CondicionVentaEntity;

public class CondicionVentaEntityCondicionVentaConverter {
    public CondicionVentaEntityCondicionVentaConverter() {
    }

    public CondicionVentaEntity condicionVentaToCondicionVentaEntity(CondicionVenta condicionVenta) {
        CondicionVentaEntity condicionVentaEntity = new CondicionVentaEntity();
        condicionVentaEntity.setId(condicionVenta.getId());
        condicionVentaEntity.setIdCrm(condicionVenta.getId());
        condicionVentaEntity.setCondicionVentaNombre(condicionVenta.getCondicionVentaNombre());

        return condicionVentaEntity;
    }

    public CondicionVenta condicionVentaEntityToCondicionVenta(CondicionVentaEntity condicionVentaEntity) {
        CondicionVenta condicionVenta = new CondicionVenta();
        condicionVenta.setId(condicionVentaEntity.getIdCrm());
        condicionVenta.setCondicionVentaNombre(condicionVentaEntity.getCondicionVentaNombre());

        return condicionVenta;
    }
}
