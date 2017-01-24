package com.designfreed.distribuidoras_app_pedidos.converters;

import com.designfreed.distribuidoras_app_pedidos.domain.Chofer;
import com.designfreed.distribuidoras_app_pedidos.entities.ChoferEntity;

public class ChoferEntityChoferConverter {
    public ChoferEntityChoferConverter() {
    }

    public ChoferEntity ChoferToChoferEntity(Chofer chofer) {
        ChoferEntity choferEntity = new ChoferEntity();
        choferEntity.setId(chofer.getId());
        choferEntity.setIdCrm(chofer.getId());
        choferEntity.setNombre(chofer.getNombre());
        choferEntity.setApellido(chofer.getApellido());
        choferEntity.setDni(chofer.getDni());
        choferEntity.setPassword(chofer.getPassword());

        return choferEntity;
    }
}
