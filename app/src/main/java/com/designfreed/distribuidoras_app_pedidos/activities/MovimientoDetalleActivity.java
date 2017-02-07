package com.designfreed.distribuidoras_app_pedidos.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.designfreed.distribuidoras_app_pedidos.R;
import com.designfreed.distribuidoras_app_pedidos.adapters.ItemMovimientoAdapter;
import com.designfreed.distribuidoras_app_pedidos.converters.EnvaseEntityEnvaseConverter;
import com.designfreed.distribuidoras_app_pedidos.domain.Envase;
import com.designfreed.distribuidoras_app_pedidos.domain.ItemMovimiento;
import com.designfreed.distribuidoras_app_pedidos.domain.Movimiento;
import com.designfreed.distribuidoras_app_pedidos.entities.EnvaseEntity;
import com.designfreed.distribuidoras_app_pedidos.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MovimientoDetalleActivity extends AppCompatActivity {
    private ItemMovimientoAdapter itemMovimientoAdapter;
    private ListView itemsListView;
    private TextView txtCodigo;
    private TextView txtRazonSocial;
    private TextView txtDireccion;
    private TextView txtCondicionVentaCliente;
    private TextView txtFecha;
    private TextView txtCondicionVentaMovimiento;
    private Spinner cboProducto;
    private EditText txtCantidad;
    private EditText txtPrecio;
    private ImageButton btnAgregar;

    private Movimiento movimiento;
    private List<Envase> envases = new ArrayList<>();
    private List<ItemMovimiento> items = new ArrayList<>();

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento_detalle);

        realm = Realm.getDefaultInstance();

        LoadEnvases();

        ArrayAdapter<Envase> envaseArrayAdapter = new ArrayAdapter<Envase>(this, android.R.layout.simple_dropdown_item_1line, envases);

        movimiento = (Movimiento) getIntent().getSerializableExtra("movimiento");

        txtCodigo = (TextView) findViewById(R.id.codigo);
        txtCodigo.setText(movimiento.getCliente().getId().toString());
        txtRazonSocial = (TextView) findViewById(R.id.razon_social);
        txtRazonSocial.setText(movimiento.getCliente().getRazonSocial());
        txtDireccion = (TextView) findViewById(R.id.direccion);
        txtDireccion.setText(movimiento.getCliente().getCalle() + " " + movimiento.getCliente().getAltura());
        txtCondicionVentaCliente = (TextView) findViewById(R.id.condicion_cliente);
        txtCondicionVentaCliente.setText(movimiento.getCliente().getCondicionVenta().getCondicionVentaNombre());
        txtFecha = (TextView) findViewById(R.id.fecha);
        txtFecha.setText(Utils.formatDate(movimiento.getFecha()));
        txtCondicionVentaMovimiento = (TextView) findViewById(R.id.condicion_movimiento);
        txtCondicionVentaMovimiento.setText(movimiento.getCondicionVenta().getCondicionVentaNombre());

        itemMovimientoAdapter = new ItemMovimientoAdapter(this, items);

        itemsListView = (ListView) findViewById(R.id.list);
        itemsListView.setAdapter(itemMovimientoAdapter);

        cboProducto = (Spinner) findViewById(R.id.producto);
        txtCantidad = (EditText) findViewById(R.id.cantidad);
        txtPrecio = (EditText) findViewById(R.id.precio);

        cboProducto.setAdapter(envaseArrayAdapter);

        LoadItems(movimiento);

        btnAgregar = (ImageButton) findViewById(R.id.agregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemMovimiento item = new ItemMovimiento();

                Envase env = (Envase) cboProducto.getSelectedItem();

                Integer cantidad = Integer.valueOf(txtCantidad.getText().toString());
                Float precio = Float.valueOf(txtPrecio.getText().toString());

                item.setEnvase(env);
                item.setCantidad(cantidad);
                item.setMonto(precio);

                items.add(item);

                itemMovimientoAdapter.notifyDataSetChanged();

                cboProducto.setSelection(0);
                txtCantidad.setText("");
                txtPrecio.setText("");
            }
        });
    }

    private void LoadEnvases() {
        RealmResults<EnvaseEntity> envases = realm.where(EnvaseEntity.class).findAll();

        for (EnvaseEntity envase: envases) {
            this.envases.add(new EnvaseEntityEnvaseConverter().envaseEntityToEnvase(envase));
        }
    }

    private void LoadItems(Movimiento movimiento) {
        if (movimiento.getItems() != null) {
            items.addAll(movimiento.getItems());

            itemMovimientoAdapter.notifyDataSetChanged();
        }
    }
}
