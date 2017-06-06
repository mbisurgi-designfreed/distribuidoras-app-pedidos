package com.designfreed.distribuidoras_app_pedidos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.designfreed.distribuidoras_app_pedidos.R;
import com.designfreed.distribuidoras_app_pedidos.adapters.MovimientoAdapter;
import com.designfreed.distribuidoras_app_pedidos.domain.Chofer;
import com.designfreed.distribuidoras_app_pedidos.entities.ClienteEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.MovimientoEntity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MovimientoActivity extends AppCompatActivity {
    private EditText txtFiltro;
    private TextView emptyView;
    private ListView movimientosListView;
    private ProgressBar progressBar;

    private Chofer activeChofer;
    private Long tipoMovimientoId;
    private MovimientoAdapter adapter;
    private List<MovimientoEntity> activeMovimientos = new ArrayList<>();

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);

        realm = Realm.getDefaultInstance();

        activeChofer = (Chofer) getIntent().getSerializableExtra("chofer");
        tipoMovimientoId = (Long) getIntent().getSerializableExtra("tipo");

        emptyView = (TextView) findViewById(R.id.empty);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        adapter = new MovimientoAdapter(this, new ArrayList<MovimientoEntity>());

        movimientosListView = (ListView) findViewById(R.id.list);
        movimientosListView.setEmptyView(emptyView);
        movimientosListView.setAdapter(adapter);
        movimientosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MovimientoEntity movimiento = (MovimientoEntity) parent.getItemAtPosition(position);

                Intent intent = new Intent(getApplicationContext(), MovimientoDetalleActivity.class);
                intent.putExtra("chofer", activeChofer);
                intent.putExtra("voleo", false);
                intent.putExtra("movimiento", movimiento.getId());
                startActivity(intent);
            }
        });

        txtFiltro = (EditText) findViewById(R.id.filter);
        txtFiltro.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        LoadMovimientos(tipoMovimientoId);
        //new LoadMovimientosTask().execute(tipoMovimientoId);
    }

    private void LoadMovimientos(Long tipoId) {
        RealmResults<MovimientoEntity> movimientos = realm.where(MovimientoEntity.class)
                    .equalTo("tipoMovimientoEntity.idCrm", tipoId)
                    .findAllAsync();

        movimientos.addChangeListener(callback);
    }


    private RealmChangeListener callback = new RealmChangeListener() {
        @Override
        public void onChange(Object element) {
            List<MovimientoEntity> movs = (RealmResults<MovimientoEntity>) element;

            for (MovimientoEntity entity: movs) {
                MovimientoEntity mov = new MovimientoEntity();
                mov.setId(entity.getId());
                mov.setIdCrm(entity.getIdCrm());
                mov.setFecha(entity.getFecha());

                ClienteEntity cli = new ClienteEntity();
                cli.setId(entity.getClienteEntity().getId());
                cli.setIdCrm(entity.getClienteEntity().getIdCrm());
                cli.setRazonSocial(entity.getClienteEntity().getRazonSocial());
                cli.setCalle(entity.getClienteEntity().getCalle());
                cli.setAltura(entity.getClienteEntity().getAltura());
                cli.setTelefono(entity.getClienteEntity().getTelefono());
                cli.setCondicionVentaEntity(entity.getClienteEntity().getCondicionVentaEntity());
                cli.setListaPrecioEntity(entity.getClienteEntity().getListaPrecioEntity());

                mov.setClienteEntity(cli);
                mov.setCondicionVentaEntity(entity.getCondicionVentaEntity());
                mov.setEstadoMovimientoEntity(entity.getEstadoMovimientoEntity());
                mov.setHojaRutaEntity(entity.getHojaRutaEntity());
                mov.setVisito(entity.getVisito());
                mov.setVendio(entity.getVendio());
                mov.setMotivoEntity(entity.getMotivoEntity());
                mov.setItems(entity.getItems());
                mov.setSincronizado(entity.getSincronizado());

                activeMovimientos.add(mov);
            }

            if (activeMovimientos != null && !activeMovimientos.isEmpty()) {
                emptyView.setText("");
                adapter.addAll(activeMovimientos);
            } else {
                emptyView.setText("No existen movimientos. Realice una sincronizacion para obtener nuevos movimientos.");
            }

            progressBar.setVisibility(View.GONE);
        }

    };

//    private class LoadMovimientosTask extends AsyncTask<Long, Void, List<MovimientoEntity>> {
//        private Realm realm = Realm.getDefaultInstance();
//
//        @Override
//        protected List<MovimientoEntity> doInBackground(Long... params) {
//            final Long tipoId = params[0];
//
//            RealmResults<MovimientoEntity> movimientos = realm.where(MovimientoEntity.class)
//                    .equalTo("tipoMovimientoEntity.idCrm", tipoId)
//                    .findAllAsync();
//
//            activeMovimientos = movimientos;
//
//            return activeMovimientos;
//        }
//
//        @Override
//        protected void onPostExecute(List<MovimientoEntity> movimientos) {
//            super.onPostExecute(movimientos);
//
//            if (activeMovimientos != null && !activeMovimientos.isEmpty()) {
//                emptyView.setText("");
//                adapter.addAll(activeMovimientos);
//            } else {
//                emptyView.setText("No existen movimientos. Realice una sincronizacion para obtener nuevos movimientos.");
//            }
//
//            progressBar.setVisibility(View.GONE);
//        }
//    }
}
