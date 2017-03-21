package com.designfreed.distribuidoras_app_pedidos.converters;

import com.designfreed.distribuidoras_app_pedidos.domain.TipoMovimiento;
import com.designfreed.distribuidoras_app_pedidos.entities.TipoMovimientoEntity;

import java.util.ArrayList;
import java.util.List;

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

    public TipoMovimiento tipoMovimientoEntityToTipoMovimiento(TipoMovimientoEntity tipoMovimientoEntity) {
        TipoMovimiento tipoMovimiento = new TipoMovimiento();
        tipoMovimiento.setId(tipoMovimientoEntity.getIdCrm());
        tipoMovimiento.setTipoMovimientoNombre(tipoMovimientoEntity.getTipoMovimientoNombre());

        return tipoMovimiento;
    }

    public List<TipoMovimientoEntity> tipoMovimientoToTipoMovimientoEntity(List<TipoMovimiento> tiposMovimiento) {
        List<TipoMovimientoEntity> tiposMovimientoEntity = new ArrayList<>();

        for (TipoMovimiento tipo: tiposMovimiento) {
            TipoMovimientoEntity tipoMovimientoEntity = new TipoMovimientoEntity();
            tipoMovimientoEntity.setId(tipo.getId());
            tipoMovimientoEntity.setIdCrm(tipo.getId());
            tipoMovimientoEntity.setTipoMovimientoNombre(tipo.getTipoMovimientoNombre());

            tiposMovimientoEntity.add(tipoMovimientoEntity);
        }

        return tiposMovimientoEntity;
    }
}
