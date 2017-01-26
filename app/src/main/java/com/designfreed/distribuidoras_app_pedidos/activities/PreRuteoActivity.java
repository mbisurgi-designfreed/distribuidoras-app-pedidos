package com.designfreed.distribuidoras_app_pedidos.activities;

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
import com.designfreed.distribuidoras_app_pedidos.domain.Movimiento;
import com.designfreed.distribuidoras_app_pedidos.domain.TipoMovimiento;
import com.designfreed.distribuidoras_app_pedidos.entities.MovimientoEntity;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class PreRuteoActivity extends AppCompatActivity {
    private TextView emptyView;
    private ListView movimientosListView;
    private ProgressBar progressBar;

    private MovimientoAdapter adapter;
    private List<Movimiento> activeMovimientos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movimiento);

        emptyView = (TextView) findViewById(R.id.empty);
        progressBar = (ProgressBar) findViewById(R.id.progress);

        adapter = new MovimientoAdapter(this, new ArrayList<Movimiento>());

        movimientosListView = (ListView) findViewById(R.id.list);
        movimientosListView.setEmptyView(emptyView);
        movimientosListView.setAdapter(adapter);
        movimientosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        TipoMovimiento tipo = new TipoMovimiento();
        tipo.setId(1L);
        tipo.setTipoMovimientoNombre("Pre Ruteo");

        new LoadMovimientosTask().execute(tipo);
    }

    private class LoadMovimientosTask extends AsyncTask<TipoMovimiento, Void, List<Movimiento>> {
        @Override
        protected List<Movimiento> doInBackground(TipoMovimiento... params) {
            Realm realm = Realm.getDefaultInstance();

            final TipoMovimiento tipo = params[0];

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<MovimientoEntity> movimientos = realm.where(MovimientoEntity.class)
                            .equalTo("tipoMovimientoEntity.idCrm", tipo.getId())
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
