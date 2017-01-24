package com.designfreed.distribuidoras_app_pedidos.converters;

import com.designfreed.distribuidoras_app_pedidos.domain.Chofer;
import com.designfreed.distribuidoras_app_pedidos.entities.ChoferEntity;

public class ChoferEntityChoferConverter {
    public ChoferEntityChoferConverter() {
    }

    public ChoferEntity choferToChoferEntity(Chofer chofer) {
        ChoferEntity choferEntity = new ChoferEntity();
        choferEntity.setId(chofer.getId());
        choferEntity.setIdCrm(chofer.getId());
        choferEntity.setNombre(chofer.getNombre());
        choferEntity.setApellido(chofer.getApellido());
        choferEntity.setDni(chofer.getDni());
        choferEntity.setPassword(chofer.getPassword());

        return choferEntity;
    }

    public Chofer choferEntityToChofer(ChoferEntity choferEntity) {
        Chofer chofer = new Chofer();
        chofer.setId(choferEntity.getIdCrm());
        chofer.setNombre(choferEntity.getNombre());
        chofer.setApellido(choferEntity.getApellido());
        chofer.setDni(choferEntity.getDni());
        chofer.setPassword(choferEntity.getPassword());

        return chofer;
    }
}
