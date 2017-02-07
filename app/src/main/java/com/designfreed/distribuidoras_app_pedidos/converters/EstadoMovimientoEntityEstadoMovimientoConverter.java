package com.designfreed.distribuidoras_app_pedidos.converters;

import com.designfreed.distribuidoras_app_pedidos.domain.EstadoMovimiento;
import com.designfreed.distribuidoras_app_pedidos.entities.EstadoMovimientoEntity;

import java.util.ArrayList;
import java.util.List;

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

    public List<EstadoMovimientoEntity> estadoMovimientoToEstadoMovimientoEntity(List<EstadoMovimiento> estados) {
        List<EstadoMovimientoEntity> estadosMovimientoEntity = new ArrayList<>();

        for (EstadoMovimiento estado: estados) {
            EstadoMovimientoEntity estadoMovimientoEntity = new EstadoMovimientoEntity();
            estadoMovimientoEntity.setId(estado.getId());
            estadoMovimientoEntity.setIdCrm(estado.getId());
            estadoMovimientoEntity.setEstadoMovimientoNombre(estado.getEstadoMovimientoNombre());

            estadosMovimientoEntity.add(estadoMovimientoEntity);
        }

        return estadosMovimientoEntity;
    }
}
