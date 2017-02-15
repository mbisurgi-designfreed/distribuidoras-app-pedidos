package com.designfreed.distribuidoras_app_pedidos.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.designfreed.distribuidoras_app_pedidos.R;
import com.designfreed.distribuidoras_app_pedidos.adapters.ItemMovimientoAdapter;
import com.designfreed.distribuidoras_app_pedidos.converters.EnvaseEntityEnvaseConverter;
import com.designfreed.distribuidoras_app_pedidos.converters.EstadoMovimientoEntityEstadoMovimientoConverter;
import com.designfreed.distribuidoras_app_pedidos.converters.MotivoEntityMotivoConverter;
import com.designfreed.distribuidoras_app_pedidos.converters.MovimientoEntityMovimientoConverter;
import com.designfreed.distribuidoras_app_pedidos.domain.Chofer;
import com.designfreed.distribuidoras_app_pedidos.domain.Envase;
import com.designfreed.distribuidoras_app_pedidos.domain.EstadoMovimiento;
import com.designfreed.distribuidoras_app_pedidos.domain.ItemMovimiento;
import com.designfreed.distribuidoras_app_pedidos.domain.Motivo;
import com.designfreed.distribuidoras_app_pedidos.domain.Movimiento;
import com.designfreed.distribuidoras_app_pedidos.entities.EnvaseEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.EstadoMovimientoEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.ItemMovimientoEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.MotivoEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.MovimientoEntity;
import com.designfreed.distribuidoras_app_pedidos.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
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
    private Spinner cboEstados;
    private Spinner cboMotivos;
    private CheckBox cbVisito;
    private ImageButton btnGrabar;

    private Chofer activeChofer;
    private MovimientoEntity movimiento;
    private List<EnvaseEntity> envases = new ArrayList<>();
    private List<EstadoMovimientoEntity> estados = new ArrayList<>();
    private List<MotivoEntity> motivos = new ArrayList<>();
    private List<ItemMovimientoEntity> items = new ArrayList<>();

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento_detalle);

        realm = Realm.getDefaultInstance();

        LoadEnvases();
        LoadEstados();
        LoadMotivos();

        ArrayAdapter<EnvaseEntity> envaseArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, envases);
        ArrayAdapter<EstadoMovimientoEntity> estadoArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, estados);
        ArrayAdapter<MotivoEntity> motivoArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, motivos);

        activeChofer = (Chofer) getIntent().getSerializableExtra("chofer");
        movimiento = realm.where(MovimientoEntity.class).equalTo("id", (Long) getIntent().getSerializableExtra("movimiento")).findFirst();

        txtCodigo = (TextView) findViewById(R.id.codigo);
        txtCodigo.setText(movimiento.getClienteEntity().getId().toString());
        txtRazonSocial = (TextView) findViewById(R.id.razon_social);
        txtRazonSocial.setText(movimiento.getClienteEntity().getRazonSocial());
        txtDireccion = (TextView) findViewById(R.id.direccion);
        txtDireccion.setText(movimiento.getClienteEntity().getCalle() + " " + movimiento.getClienteEntity().getAltura());
        txtCondicionVentaCliente = (TextView) findViewById(R.id.condicion_cliente);
        txtCondicionVentaCliente.setText(movimiento.getClienteEntity().getCondicionVentaEntity().getCondicionVentaNombre());
        txtFecha = (TextView) findViewById(R.id.fecha);
        txtFecha.setText(Utils.formatDate(movimiento.getFecha()));
        txtCondicionVentaMovimiento = (TextView) findViewById(R.id.condicion_movimiento);
        txtCondicionVentaMovimiento.setText(movimiento.getCondicionVentaEntity().getCondicionVentaNombre());

        itemMovimientoAdapter = new ItemMovimientoAdapter(this, items);

        itemsListView = (ListView) findViewById(R.id.list);
        itemsListView.setAdapter(itemMovimientoAdapter);

        cboProducto = (Spinner) findViewById(R.id.producto);
        txtCantidad = (EditText) findViewById(R.id.cantidad);
        txtPrecio = (EditText) findViewById(R.id.precio);

        cboEstados = (Spinner) findViewById(R.id.estado);
        cboMotivos = (Spinner) findViewById(R.id.motivo);

        cboProducto.setAdapter(envaseArrayAdapter);
        cboEstados.setAdapter(estadoArrayAdapter);
        cboMotivos.setAdapter(motivoArrayAdapter);

        cbVisito = (CheckBox) findViewById(R.id.visito);

        LoadItems(movimiento);

        cboEstados.setSelection(getIndexEstadoMovimiento(cboEstados, movimiento.getEstadoMovimientoEntity().getId()));

        cbVisito.setChecked(movimiento.getVisito());

        if (movimiento.getMotivoEntity() != null) {
            cboMotivos.setSelection(getIndexMotivo(cboMotivos, movimiento.getMotivoEntity().getId()));
        }

        btnAgregar = (ImageButton) findViewById(R.id.agregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ItemMovimientoEntity item = new ItemMovimientoEntity();

                EnvaseEntity env = (EnvaseEntity) cboProducto.getSelectedItem();

                Integer cantidad = Integer.valueOf(txtCantidad.getText().toString());
                Float precio = Float.valueOf(txtPrecio.getText().toString());

                item.setEnvaseEntity(env);
                item.setCantidad(cantidad);
                item.setMonto(precio);

                items.add(item);

                itemMovimientoAdapter.notifyDataSetChanged();

                cboProducto.setSelection(0);
                txtCantidad.setText("");
                txtPrecio.setText("");
            }
        });

        btnGrabar = (ImageButton) findViewById(R.id.grabar);
        btnGrabar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final MovimientoEntity movimientoEntity = movimiento;
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        //movimiento.setItems(itemsModificados);
                        //movimiento.setEstadoMovimientoEntity((EstadoMovimientoEntity) cboEstados.getSelectedItem());
                        //movimiento.setVisito(cbVisito.isChecked());

//                        if (((Motivo)cboMotivos.getSelectedItem()).getId() != null) {
//                            movimiento.setMotivoEntity((MotivoEntity) cboMotivos.getSelectedItem());
//                        } else {
//                            movimiento.setMotivoEntity(null);
//                        }

                        //movimiento.setSincronizado(false);

                        Number primaryKeyItem = realm.where(ItemMovimientoEntity.class).max("id");
                        Long idItem;

                        if (primaryKeyItem == null) {
                            idItem = 1L;
                        } else {
                            idItem = primaryKeyItem.longValue() + 1;
                        }

                        final RealmList<ItemMovimientoEntity> itemsModificados = new RealmList<>();

                        for (ItemMovimientoEntity item: items) {
                            ItemMovimientoEntity itemMovimientoEntity = item;

                            if (item.getId() == null) {
                                itemMovimientoEntity = realm.createObject(ItemMovimientoEntity.class, idItem);
                                itemMovimientoEntity.setIdCrm(item.getIdCrm());
                                itemMovimientoEntity.setEnvaseEntity(item.getEnvaseEntity());
                                itemMovimientoEntity.setCantidad(item.getCantidad());
                                itemMovimientoEntity.setMonto(item.getMonto());
                                idItem++;
                            }

                            itemsModificados.add(itemMovimientoEntity);
                        }

                        MovimientoEntity movimientoEntity = realm.where(MovimientoEntity.class).equalTo("id", movimiento.getId()).findFirst();
                        movimientoEntity.setEstadoMovimientoEntity((EstadoMovimientoEntity) cboEstados.getSelectedItem());
                        movimientoEntity.setVisito(cbVisito.isChecked());
                        if (((MotivoEntity)cboMotivos.getSelectedItem()).getId() != null) {
                            movimientoEntity.setMotivoEntity((MotivoEntity) cboMotivos.getSelectedItem());
                        } else {
                            movimientoEntity.setMotivoEntity(null);
                        }
                        movimientoEntity.setItems(itemsModificados);
                        movimientoEntity.setSincronizado(false);

                        realm.copyToRealmOrUpdate(movimientoEntity);
                    }
                });

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("chofer", activeChofer);

                startActivity(intent);
            }
        });
    }

    private void LoadEnvases() {
        RealmResults<EnvaseEntity> envases = realm.where(EnvaseEntity.class).findAll();

        for (EnvaseEntity envase: envases) {
            this.envases.add(envase);
        }
    }

    private void LoadEstados() {
        RealmResults<EstadoMovimientoEntity> estados = realm.where(EstadoMovimientoEntity.class).findAll();

        for (EstadoMovimientoEntity estado: estados) {
            this.estados.add(estado);
        }
    }

    private void LoadMotivos() {
        RealmResults<MotivoEntity> motivos = realm.where(MotivoEntity.class).findAll();

        this.motivos.add(new MotivoEntity());

        for (MotivoEntity motivo: motivos) {
            this.motivos.add(motivo);
        }
    }

    private void LoadItems(MovimientoEntity movimiento) {
        if (movimiento.getItems() != null) {
            items.addAll(movimiento.getItems());

            itemMovimientoAdapter.notifyDataSetChanged();
        }
    }

    private int getIndexEstadoMovimiento(Spinner spinner, Long id) {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (((EstadoMovimientoEntity)spinner.getItemAtPosition(i)).getIdCrm().equals(id)){
                index = i;
                break;
            }
        }

        return index;
    }

    private int getIndexMotivo(Spinner spinner, Long id) {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (((MotivoEntity)spinner.getItemAtPosition(i)).getIdCrm() != null && ((MotivoEntity)spinner.getItemAtPosition(i)).getIdCrm().equals(id)){
                index = i;
                break;
            }
        }

        return index;
    }
}
