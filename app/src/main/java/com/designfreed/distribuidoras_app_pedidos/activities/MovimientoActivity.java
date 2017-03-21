package com.designfreed.distribuidoras_app_pedidos.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.designfreed.distribuidoras_app_pedidos.R;
import com.designfreed.distribuidoras_app_pedidos.adapters.MovimientoAdapter;
import com.designfreed.distribuidoras_app_pedidos.domain.Chofer;
import com.designfreed.distribuidoras_app_pedidos.entities.MovimientoEntity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MovimientoActivity extends AppCompatActivity {
    private TextView emptyView;
    private ListView movimientosListView;
    private ProgressBar progressBar;

    private Chofer activeChofer;
    private Long tipoMovimientoId;
    private MovimientoAdapter adapter;
    private List<MovimientoEntity> activeMovimientos;

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
            activeMovimientos = (RealmResults<MovimientoEntity>) element;

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
