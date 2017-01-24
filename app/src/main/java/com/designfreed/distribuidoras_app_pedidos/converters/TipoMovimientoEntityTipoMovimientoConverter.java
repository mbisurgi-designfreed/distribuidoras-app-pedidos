package com.designfreed.distribuidoras_app_pedidos.converters;

import com.designfreed.distribuidoras_app_pedidos.domain.TipoMovimiento;
import com.designfreed.distribuidoras_app_pedidos.entities.TipoMovimientoEntity;

public class TipoMovimientoEntityTipoMovimientoConverter {
    public TipoMovimientoEntityTipoMovimientoConverter() {
    }

    public TipoMovimientoEntity tipoMovimientoToTipoMovimientoEntity(TipoMovimiento tipoMovimiento) {
        TipoMovimientoEntity tipoMovimientoEntity = new TipoMovimientoEntity();
        tipoMovimientoEntity.setId(tipoMovimiento.getId());
        tipoMovimientoEntity.setIdCrm(tipoMovimiento.getId());
        tipoMovimientoEntity.setTipoMovimientoNombre(tipoMovimiento.getTipoMovimientoNombre());

        return tipoMovimientoEntity;
    }
}
