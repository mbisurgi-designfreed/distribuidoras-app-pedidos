package com.designfreed.distribuidoras_app_pedidos.converters;

import com.designfreed.distribuidoras_app_pedidos.domain.Envase;
import com.designfreed.distribuidoras_app_pedidos.entities.EnvaseEntity;

import java.util.ArrayList;
import java.util.List;

public class EnvaseEntityEnvaseConverter {
    public EnvaseEntityEnvaseConverter () {

    }

    public EnvaseEntity envaseToEnvaseEntity(Envase envase) {
        EnvaseEntity envaseEntity = new EnvaseEntity();
        envaseEntity.setId(envase.getId());
        envaseEntity.setIdCrm(envase.getId());
        envaseEntity.setEnvaseCodigo(envase.getEnvaseCodigo());
        envaseEntity.setEnvaseNombre(envase.getEnvaseNombre());
        envaseEntity.setKilos(envase.getKilos());

        return envaseEntity;
    }

    public List<EnvaseEntity> envasesToEnvasesEntity(List<Envase> envases) {
        List<EnvaseEntity> envasesEntity = new ArrayList<>();

        for (Envase envase: envases) {
            EnvaseEntity envaseEntity = new EnvaseEntity();
            envaseEntity.setId(envase.getId());
            envaseEntity.setIdCrm(envase.getId());
            envaseEntity.setEnvaseCodigo(envase.getEnvaseCodigo());
            envaseEntity.setEnvaseNombre(envase.getEnvaseNombre());
            envaseEntity.setKilos(envase.getKilos());

            envasesEntity.add(envaseEntity);
        }

        return envasesEntity;
    }
}
