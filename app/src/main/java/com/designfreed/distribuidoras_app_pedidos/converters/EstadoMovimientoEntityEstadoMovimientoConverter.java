package com.designfreed.distribuidoras_app_pedidos.converters;

import com.designfreed.distribuidoras_app_pedidos.domain.EstadoMovimiento;
import com.designfreed.distribuidoras_app_pedidos.entities.EstadoMovimientoEntity;

public class EstadoMovimientoEntityEstadoMovimientoConverter {
    public EstadoMovimientoEntityEstadoMovimientoConverter() {
    }

    public EstadoMovimientoEntity estadoMovimientoToEstadoMovimientoEntity(EstadoMovimiento estadoMovimiento) {
        EstadoMovimientoEntity estadoMovimientoEntity = new EstadoMovimientoEntity();
        estadoMovimientoEntity.setId(estadoMovimiento.getId());
        estadoMovimientoEntity.setIdCrm(estadoMovimiento.getId());
        estadoMovimientoEntity.setEstadoMovimientoNombre(estadoMovimiento.getEstadoMovimientoNombre());

        return estadoMovimientoEntity;
    }

    public EstadoMovimiento estadoMovimientoEntityToEstadoMovimiento(EstadoMovimientoEntity estadoMovimientoEntity) {
        EstadoMovimiento estadoMovimiento = new EstadoMovimiento();
        estadoMovimiento.setId(estadoMovimientoEntity.getIdCrm());
        estadoMovimiento.setEstadoMovimientoNombre(estadoMovimientoEntity.getEstadoMovimientoNombre());

        return estadoMovimiento;
    }
}
