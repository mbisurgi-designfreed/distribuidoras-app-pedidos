package com.designfreed.distribuidoras_app_pedidos.converters;

import com.designfreed.distribuidoras_app_pedidos.domain.Movimiento;
import com.designfreed.distribuidoras_app_pedidos.entities.MovimientoEntity;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

public class MovimientoEntityMovimientoConverter {
    public MovimientoEntityMovimientoConverter() {
    }

    public MovimientoEntity movimientoToMovimientoEntity(Movimiento movimiento) {
        MovimientoEntity movimientoEntity = new MovimientoEntity();
        movimientoEntity.setId(movimiento.getId());
        movimientoEntity.setIdCrm(movimiento.getId());
        movimientoEntity.setFecha(movimiento.getFecha());
        movimientoEntity.setClienteEntity(new ClienteEntityClienteConverter().clienteToClienteEntity(movimiento.getCliente()));
        movimientoEntity.setCondicionVentaEntity(new CondicionVentaEntityCondicionVentaConverter().condicionVentaToCondicionVentaEntity(movimiento.getCondicionVenta()));
        movimientoEntity.setTipoMovimientoEntity(new TipoMovimientoEntityTipoMovimientoConverter().tipoMovimientoToTipoMovimientoEntity(movimiento.getTipoMovimiento()));
        movimientoEntity.setEstadoMovimientoEntity(new EstadoMovimientoEntityEstadoMovimientoConverter().estadoMovimientoToEstadoMovimientoEntity(movimiento.getEstadoMovimiento()));
        movimientoEntity.setHojaRutaEntity(new HojaRutaEntityHojaRutaConverter().hojaRutaToHojaRutaEntity(movimiento.getHojaRuta()));
        movimientoEntity.setVisito(movimiento.getVisito());
        movimientoEntity.setVendio(movimiento.getVendio());
        if (movimiento.getMotivo() != null) {
            movimientoEntity.setMotivoEntity(new MotivoEntityMotivoConverter().motivoToMotivoEntity(movimiento.getMotivo()));
        }
        movimientoEntity.setItems(new ItemMovimientoEntityItemMovimientoConverter().itemsMovimientoToItemsMovimientoEntity(movimiento.getItems()));
        movimientoEntity.setObservaciones(movimiento.getObservaciones());
        movimientoEntity.setSincronizado(movimiento.getSincronizado());

        return movimientoEntity;
    }

    public MovimientoEntity movimientoToMovimientoEntity2(Movimiento movimiento) {
        MovimientoEntity movimientoEntity = new MovimientoEntity();
        movimientoEntity.setIdCrm(movimiento.getId());
        movimientoEntity.setFecha(movimiento.getFecha());
        movimientoEntity.setClienteEntity(new ClienteEntityClienteConverter().clienteToClienteEntity(movimiento.getCliente()));
        movimientoEntity.setCondicionVentaEntity(new CondicionVentaEntityCondicionVentaConverter().condicionVentaToCondicionVentaEntity(movimiento.getCondicionVenta()));
        movimientoEntity.setTipoMovimientoEntity(new TipoMovimientoEntityTipoMovimientoConverter().tipoMovimientoToTipoMovimientoEntity(movimiento.getTipoMovimiento()));
        movimientoEntity.setEstadoMovimientoEntity(new EstadoMovimientoEntityEstadoMovimientoConverter().estadoMovimientoToEstadoMovimientoEntity(movimiento.getEstadoMovimiento()));
        movimientoEntity.setHojaRutaEntity(new HojaRutaEntityHojaRutaConverter().hojaRutaToHojaRutaEntity(movimiento.getHojaRuta()));
        movimientoEntity.setVisito(movimiento.getVisito());
        movimientoEntity.setVendio(movimiento.getVendio());
        if (movimiento.getMotivo() != null) {
            movimientoEntity.setMotivoEntity(new MotivoEntityMotivoConverter().motivoToMotivoEntity(movimiento.getMotivo()));
        }
        //movimientoEntity.setItems(new ItemMovimientoEntityItemMovimientoConverter().itemsMovimientoToItemsMovimientoEntity(movimiento.getItems()));
        movimientoEntity.setObservaciones(movimiento.getObservaciones());
        movimientoEntity.setSincronizado(movimiento.getSincronizado());

        return movimientoEntity;
    }

    public List<MovimientoEntity> movimientosToMovimientosEntity(List<Movimiento> movimientos) {
        List<MovimientoEntity> movimientosEntity = new ArrayList<>();

        for (Movimiento movimiento: movimientos) {
            MovimientoEntity movimientoEntity = new MovimientoEntity();
            movimientoEntity.setId(movimiento.getId());
            movimientoEntity.setIdCrm(movimiento.getId());
            movimientoEntity.setFecha(movimiento.getFecha());
            movimientoEntity.setClienteEntity(new ClienteEntityClienteConverter().clienteToClienteEntity(movimiento.getCliente()));
            movimientoEntity.setCondicionVentaEntity(new CondicionVentaEntityCondicionVentaConverter().condicionVentaToCondicionVentaEntity(movimiento.getCondicionVenta()));
            movimientoEntity.setTipoMovimientoEntity(new TipoMovimientoEntityTipoMovimientoConverter().tipoMovimientoToTipoMovimientoEntity(movimiento.getTipoMovimiento()));
            movimientoEntity.setEstadoMovimientoEntity(new EstadoMovimientoEntityEstadoMovimientoConverter().estadoMovimientoToEstadoMovimientoEntity(movimiento.getEstadoMovimiento()));
            movimientoEntity.setHojaRutaEntity(new HojaRutaEntityHojaRutaConverter().hojaRutaToHojaRutaEntity(movimiento.getHojaRuta()));
            movimientoEntity.setVisito(movimiento.getVisito());
            movimientoEntity.setVendio(movimiento.getVendio());
            if (movimiento.getMotivo() != null) {
                movimientoEntity.setMotivoEntity(new MotivoEntityMotivoConverter().motivoToMotivoEntity(movimiento.getMotivo()));
            }
            movimientoEntity.setItems(new ItemMovimientoEntityItemMovimientoConverter().itemsMovimientoToItemsMovimientoEntity(movimiento.getItems()));
            movimientoEntity.setObservaciones(movimiento.getObservaciones());
            movimientoEntity.setSincronizado(movimiento.getSincronizado());

            movimientosEntity.add(movimientoEntity);
        }

        return movimientosEntity;
    }

    public List<Movimiento> movimientosEntityToMovimientos(RealmResults<MovimientoEntity> movimientosEntity) {
        List<Movimiento> movimientos = new ArrayList<>();

        for (MovimientoEntity movimientoEntity: movimientosEntity) {
            Movimiento movimiento = new Movimiento();
            movimiento.setId(movimientoEntity.getIdCrm());
            movimiento.setFecha(movimientoEntity.getFecha());
            movimiento.setCliente(new ClienteEntityClienteConverter().clienteEntityToCliente(movimientoEntity.getClienteEntity()));
            movimiento.setCondicionVenta(new CondicionVentaEntityCondicionVentaConverter().condicionVentaEntityToCondicionVenta(movimientoEntity.getCondicionVentaEntity()));
            movimiento.setTipoMovimiento(new TipoMovimientoEntityTipoMovimientoConverter().tipoMovimientoEntityToTipoMovimiento(movimientoEntity.getTipoMovimientoEntity()));
            movimiento.setEstadoMovimiento(new EstadoMovimientoEntityEstadoMovimientoConverter().estadoMovimientoEntityToEstadoMovimiento(movimientoEntity.getEstadoMovimientoEntity()));
            movimiento.setHojaRuta(new HojaRutaEntityHojaRutaConverter().hojaRutaEntityToHojaRuta(movimientoEntity.getHojaRutaEntity()));
            movimiento.setVisito(movimientoEntity.getVisito());
            movimiento.setVendio(movimientoEntity.getVendio());
            if (movimientoEntity.getMotivoEntity() != null) {
                movimiento.setMotivo(new MotivoEntityMotivoConverter().motivoEntityToMotivo(movimientoEntity.getMotivoEntity()));
            }
            movimiento.setItems(new ItemMovimientoEntityItemMovimientoConverter().itemsMovimientoEntityToItemsMovimiento(movimientoEntity.getItems()));
            movimiento.setObservaciones(movimientoEntity.getObservaciones());
            movimiento.setSincronizado(movimientoEntity.getSincronizado());

            movimientos.add(movimiento);
        }

        return movimientos;
    }

    public List<Movimiento> movimientosEntityToMovimientos(List<MovimientoEntity> movimientosEntity) {
        List<Movimiento> movimientos = new ArrayList<>();

        for (MovimientoEntity movimientoEntity: movimientosEntity) {
            Movimiento movimiento = new Movimiento();
            movimiento.setId(movimientoEntity.getIdCrm());
            movimiento.setFecha(movimientoEntity.getFecha());
            movimiento.setCliente(new ClienteEntityClienteConverter().clienteEntityToCliente(movimientoEntity.getClienteEntity()));
            movimiento.setCondicionVenta(new CondicionVentaEntityCondicionVentaConverter().condicionVentaEntityToCondicionVenta(movimientoEntity.getCondicionVentaEntity()));
            movimiento.setTipoMovimiento(new TipoMovimientoEntityTipoMovimientoConverter().tipoMovimientoEntityToTipoMovimiento(movimientoEntity.getTipoMovimientoEntity()));
            movimiento.setEstadoMovimiento(new EstadoMovimientoEntityEstadoMovimientoConverter().estadoMovimientoEntityToEstadoMovimiento(movimientoEntity.getEstadoMovimientoEntity()));
            movimiento.setHojaRuta(new HojaRutaEntityHojaRutaConverter().hojaRutaEntityToHojaRuta(movimientoEntity.getHojaRutaEntity()));
            movimiento.setVisito(movimientoEntity.getVisito());
            movimiento.setVendio(movimientoEntity.getVendio());
            if (movimientoEntity.getMotivoEntity() != null) {
                movimiento.setMotivo(new MotivoEntityMotivoConverter().motivoEntityToMotivo(movimientoEntity.getMotivoEntity()));
            }
            movimiento.setItems(new ItemMovimientoEntityItemMovimientoConverter().itemsMovimientoEntityToItemsMovimiento(movimientoEntity.getItems()));
            movimiento.setObservaciones(movimientoEntity.getObservaciones());
            movimiento.setSincronizado(movimientoEntity.getSincronizado());

            movimientos.add(movimiento);
        }

        return movimientos;
    }
}
