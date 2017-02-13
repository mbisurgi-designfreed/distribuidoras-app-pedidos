package com.designfreed.distribuidoras_app_pedidos.converters;

import com.designfreed.distribuidoras_app_pedidos.domain.HojaRuta;
import com.designfreed.distribuidoras_app_pedidos.entities.HojaRutaEntity;

public class HojaRutaEntityHojaRutaConverter {
    public HojaRutaEntityHojaRutaConverter() {
    }

    public HojaRutaEntity hojaRutaToHojaRutaEntity(HojaRuta hojaRuta) {
        HojaRutaEntity hojaRutaEntity = new HojaRutaEntity();
        hojaRutaEntity.setId(hojaRuta.getId());
        hojaRutaEntity.setIdCrm(hojaRuta.getId());
        hojaRutaEntity.setFecha(hojaRuta.getFecha());
        hojaRutaEntity.setChoferEntity(new ChoferEntityChoferConverter().choferToChoferEntity(hojaRuta.getChofer()));
        hojaRutaEntity.setEstado(hojaRuta.getEstado());
        hojaRutaEntity.setControlStock(hojaRuta.getControlStock());
        hojaRutaEntity.setCierreMobile(hojaRuta.getCierreMobile());

        return hojaRutaEntity;
    }

    public HojaRuta hojaRutaEntityToHojaRuta(HojaRutaEntity hojaRutaEntity) {
        HojaRuta hojaRuta = new HojaRuta();
        hojaRuta.setId(hojaRutaEntity.getIdCrm());
        hojaRuta.setFecha(hojaRutaEntity.getFecha());
        hojaRuta.setChofer(new ChoferEntityChoferConverter().choferEntityToChofer(hojaRutaEntity.getChoferEntity()));
        hojaRuta.setEstado(hojaRutaEntity.getEstado());
        hojaRuta.setControlStock(hojaRutaEntity.getControlStock());
        hojaRuta.setCierreMobile(hojaRutaEntity.getCierreMobile());

        return hojaRuta;
    }
}
