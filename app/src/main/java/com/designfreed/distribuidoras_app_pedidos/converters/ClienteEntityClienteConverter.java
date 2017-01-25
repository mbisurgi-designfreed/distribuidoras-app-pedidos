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

    public Cliente clienteEntityToCliente(ClienteEntity clienteEntity) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteEntity.getIdCrm());
        cliente.setRazonSocial(clienteEntity.getRazonSocial());
        cliente.setCalle(clienteEntity.getCalle());
        cliente.setAltura(clienteEntity.getAltura());
        cliente.setTelefono(clienteEntity.getTelefono());
        cliente.setCondicionVenta(new CondicionVentaEntityCondicionVentaConverter().condicionVentaEntityToCondicionVenta(clienteEntity.getCondicionVentaEntity()));

        return cliente;
    }
}
