package com.designfreed.distribuidoras_app_pedidos.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.designfreed.distribuidoras_app_pedidos.R;
import com.designfreed.distribuidoras_app_pedidos.domain.Movimiento;

public class MovimientoDetalleActivity extends AppCompatActivity {
    private Movimiento movimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento_detalle);

        movimiento = (Movimiento) getIntent().getSerializableExtra("movimiento");
    }
}
