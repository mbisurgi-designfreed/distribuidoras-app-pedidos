package com.designfreed.distribuidoras_app_pedidos.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.designfreed.distribuidoras_app_pedidos.R;
import com.designfreed.distribuidoras_app_pedidos.domain.Movimiento;

public class MovimientoDetalleActivity extends AppCompatActivity {
    private TextView txtCodigo;
    private TextView txtRazonSocial;
    private TextView txtDireccion;
    private TextView txtCondicionVenta;

    private Movimiento movimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento_detalle);

        movimiento = (Movimiento) getIntent().getSerializableExtra("movimiento");

        txtCodigo = (TextView) findViewById(R.id.codigo);
        txtCodigo.setText(movimiento.getCliente().getId().toString());
        txtRazonSocial = (TextView) findViewById(R.id.razon_social);
        txtRazonSocial.setText(movimiento.getCliente().getRazonSocial());
        txtDireccion = (TextView) findViewById(R.id.direccion);
        txtDireccion.setText(movimiento.getCliente().getCalle() + " " + movimiento.getCliente().getAltura());
        txtCondicionVenta = (TextView) findViewById(R.id.condicion_cliente);
        txtCondicionVenta.setText(movimiento.getCliente().getCondicionVenta().getCondicionVentaNombre());
    }
}
