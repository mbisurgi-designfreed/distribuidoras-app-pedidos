package com.designfreed.distribuidoras_app_pedidos.converters;

import com.designfreed.distribuidoras_app_pedidos.domain.HojaRuta;
import com.designfreed.distribuidoras_app_pedidos.entities.HojaRutaEntity;

public class HojaRutaEntityHojaRutaConverter {
    public HojaRutaEntityHojaRutaConverter() {
    }

    public HojaRutaEntity HojaRutaToHojaRutaEntity(HojaRuta hojaRuta) {
        HojaRutaEntity hojaRutaEntity = new HojaRutaEntity();
        hojaRutaEntity.setId(hojaRuta.getId());
        hojaRutaEntity.setIdCrm(hojaRuta.getId());
        hojaRutaEntity.setFecha(hojaRuta.getFecha());
        hojaRutaEntity.setChoferEntity(new ChoferEntityChoferConverter().ChoferToChoferEntity(hojaRuta.getChofer()));
        hojaRutaEntity.setEstado(hojaRuta.getEstado());
        hojaRutaEntity.setControlStock(hojaRuta.getControlStock());

        return hojaRutaEntity;
    }
}
