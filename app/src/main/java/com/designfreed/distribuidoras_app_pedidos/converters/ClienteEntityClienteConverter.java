package com.designfreed.distribuidoras_app_pedidos.converters;

import com.designfreed.distribuidoras_app_pedidos.domain.Cliente;
import com.designfreed.distribuidoras_app_pedidos.entities.ClienteEntity;

public class ClienteEntityClienteConverter {
    public ClienteEntityClienteConverter() {
    }

    public ClienteEntity clienteToClienteEntity(Cliente cliente) {
        ClienteEntity clienteEntity = new ClienteEntity();
        clienteEntity.setId(cliente.getId());
        clienteEntity.setIdCrm(cliente.getId());
        clienteEntity.setRazonSocial(cliente.getRazonSocial());
        clienteEntity.setCalle(cliente.getCalle());
        clienteEntity.setAltura(cliente.getAltura());
        clienteEntity.setTelefono(cliente.getTelefono());
        clienteEntity.setCondicionVentaEntity(new CondicionVentaEntityCondicionVentaConverter().condicionVentaToCondicionVentaEntity(cliente.getCondicionVenta()));

        return clienteEntity;
    }
}
