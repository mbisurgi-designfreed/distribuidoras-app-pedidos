package com.designfreed.distribuidoras_app_pedidos.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.designfreed.distribuidoras_app_pedidos.R;
import com.designfreed.distribuidoras_app_pedidos.adapters.MovimientoAdapter;
import com.designfreed.distribuidoras_app_pedidos.converters.MovimientoEntityMovimientoConverter;
import com.designfreed.distribuidoras_app_pedidos.domain.Chofer;
import com.designfreed.distribuidoras_app_pedidos.domain.Movimiento;
import com.designfreed.distribuidoras_app_pedidos.domain.TipoMovimiento;
import com.designfreed.distribuidoras_app_pedidos.entities.MovimientoEntity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MovimientoActivity extends AppCompatActivity {
    private TextView emptyView;
    private ListView movimientosListView;
    private ProgressBar progressBar;

    private Chofer activeChofer;
    private Long tipoMovimientoId;
    private MovimientoAdapter adapter;
    private List<Movimiento> activeMovimientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);

        activeChofer = (Chofer) getIntent().getSerializableExtra("chofer");
        tipoMovimientoId = (Long) getIntent().getSerializableExtra("tipo");

        emptyView = (TextView) findViewById(R.id.empty);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        adapter = new MovimientoAdapter(this, new ArrayList<Movimiento>());

        movimientosListView = (ListView) findViewById(R.id.list);
        movimientosListView.setEmptyView(emptyView);
        movimientosListView.setAdapter(adapter);
        movimientosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movimiento movimiento = (Movimiento) parent.getItemAtPosition(position);

                Intent intent = new Intent(getApplicationContext(), MovimientoDetalleActivity.class);
                intent.putExtra("chofer", activeChofer);
                intent.putExtra("movimiento", movimiento);
                startActivity(intent);
            }
        });

        new LoadMovimientosTask().execute(tipoMovimientoId);
    }

    private class LoadMovimientosTask extends AsyncTask<Long, Void, List<Movimiento>> {
        @Override
        protected List<Movimiento> doInBackground(Long... params) {
            Realm realm = Realm.getDefaultInstance();

            final Long tipoId = params[0];

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<MovimientoEntity> movimientos = realm.where(MovimientoEntity.class)
                            .equalTo("tipoMovimientoEntity.idCrm", tipoId)
                            .findAll();

                    activeMovimientos = new MovimientoEntityMovimientoConverter().movimientosEntityToMovimientos(movimientos);
                }
            });

            return activeMovimientos;
        }

        @Override
        protected void onPostExecute(List<Movimiento> movimientos) {
            super.onPostExecute(movimientos);

            if (activeMovimientos != null && !activeMovimientos.isEmpty()) {
                emptyView.setText("");
                adapter.addAll(activeMovimientos);
            } else {
                emptyView.setText("No existen movimientos. Realice una sincronizacion para obtener nuevos movimientos.");
            }

            progressBar.setVisibility(View.GONE);
        }
    }
}
