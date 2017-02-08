package com.designfreed.distribuidoras_app_pedidos.converters;

import com.designfreed.distribuidoras_app_pedidos.domain.Motivo;
import com.designfreed.distribuidoras_app_pedidos.entities.MotivoEntity;

import java.util.ArrayList;
import java.util.List;

public class MotivoEntityMotivoConverter {
    public MotivoEntityMotivoConverter() {

    }

    public MotivoEntity motivoToMotivoEntity(Motivo motivo) {
        MotivoEntity motivoEntity = new MotivoEntity();
        motivoEntity.setId(motivo.getId());
        motivoEntity.setIdCrm(motivo.getId());
        motivoEntity.setNombreMotivo(motivo.getMotivoNombre());

        return motivoEntity;
    }

    public Motivo motivoEntityToMotivo(MotivoEntity motivoEntity) {
        Motivo motivo = new Motivo();
        motivo.setId(motivoEntity.getIdCrm());
        motivo.setMotivoNombre(motivoEntity.getNombreMotivo());

        return motivo;
    }

    public List<MotivoEntity> motivosToMotivosEntity(List<Motivo> motivos) {
        List<MotivoEntity> motivosEntity = new ArrayList<>();

        for (Motivo motivo: motivos) {
            MotivoEntity motivoEntity = new MotivoEntity();
            motivoEntity.setId(motivo.getId());
            motivoEntity.setIdCrm(motivo.getId());
            motivoEntity.setNombreMotivo(motivo.getMotivoNombre());

            motivosEntity.add(motivoEntity);
        }

        return motivosEntity;
    }
}
