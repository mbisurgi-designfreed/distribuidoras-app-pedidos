package com.designfreed.distribuidoras_app_pedidos.activities;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.designfreed.distribuidoras_app_pedidos.R;
import com.designfreed.distribuidoras_app_pedidos.converters.DateConverter;
import com.designfreed.distribuidoras_app_pedidos.converters.EnvaseEntityEnvaseConverter;
import com.designfreed.distribuidoras_app_pedidos.converters.HojaRutaEntityHojaRutaConverter;
import com.designfreed.distribuidoras_app_pedidos.converters.MovimientoEntityMovimientoConverter;
import com.designfreed.distribuidoras_app_pedidos.domain.Chofer;
import com.designfreed.distribuidoras_app_pedidos.domain.Envase;
import com.designfreed.distribuidoras_app_pedidos.domain.HojaRuta;
import com.designfreed.distribuidoras_app_pedidos.domain.Movimiento;
import com.designfreed.distribuidoras_app_pedidos.entities.EnvaseEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.HojaRutaEntity;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private TextView txtChofer;
    private ProgressBar pbProgress;

    private Chofer chofer;
    private HojaRuta hojaRuta;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        chofer = (Chofer) getIntent().getSerializableExtra("chofer");

        txtChofer = (TextView) findViewById(R.id.chofer);
        txtChofer.setText(chofer.getNombre() + " " + chofer.getApellido());
        pbProgress = (ProgressBar) findViewById(R.id.progress);

        pbProgress.setVisibility(View.GONE);

        if (!existeHojaRuta()) {
            new LoadHojaRutaTask().execute();

            pbProgress.setVisibility(View.VISIBLE);
        }

        if (!existenEnvases()) {
            new LoadEnvasesTask().execute();

            pbProgress.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu:
                new SyncMovimientosRemotoTask().execute();

                pbProgress.setVisibility(View.VISIBLE);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class LoadHojaRutaTask extends AsyncTask<Void, Void, HojaRuta> {
        @Override
        protected HojaRuta doInBackground(Void... params) {
            Long fecha = new DateConverter().dateToLong(new Date());

            String url = "http://bybgas.dyndns.org:8080/distribuidoras-backend/hojaRuta/findByFechaChofer/" + fecha + "/" + chofer.getId();

            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                HojaRuta hojaRuta = restTemplate.getForObject(url, HojaRuta.class);

                return hojaRuta;
            } catch (ResourceAccessException connectException) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final HojaRuta hojaRuta) {
            super.onPostExecute(hojaRuta);

            if (hojaRuta != null) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(new HojaRutaEntityHojaRutaConverter().hojaRutaToHojaRutaEntity(hojaRuta));
                    }
                });
            }

            pbProgress.setVisibility(View.GONE);
        }
    }

    private class LoadEnvasesTask extends AsyncTask<Void, Void, List<Envase>> {
        @Override
        protected List<Envase> doInBackground(Void... params) {
            String url = "http://bybgas.dyndns.org:8080/distribuidoras-backend/envase/list";

            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                Envase[] envases = restTemplate.getForObject(url, Envase[].class);

                return Arrays.asList(envases);
            } catch (ResourceAccessException connectException) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final List<Envase> envases) {
            super.onPostExecute(envases);

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(new EnvaseEntityEnvaseConverter().envasesToEnvasesEntity(envases));
                }
            });

            pbProgress.setVisibility(View.GONE);
        }
    }

    private class SyncMovimientosRemotoTask extends AsyncTask<Void, Void, List<Movimiento>> {
        @Override
        protected List<Movimiento> doInBackground(Void... params) {
            String url = "http://bybgas.dyndns.org:8080/distribuidoras-backend/movimiento/findByHojaRuta/" + hojaRuta.getId();

            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                Movimiento[] movimientos = restTemplate.getForObject(url, Movimiento[].class);



                return Arrays.asList(movimientos);
            } catch (ResourceAccessException connectException) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final List<Movimiento> movimientos) {
            super.onPostExecute(movimientos);

            if (movimientos != null) {
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(new MovimientoEntityMovimientoConverter().movimientosToMovimientosEntity(movimientos));
                    }
                });

                pbProgress.setVisibility(View.GONE);
            }
        }
    }

    private Boolean existeHojaRuta() {
        Long fecha = new DateConverter().dateToLong(new Date());

        HojaRutaEntity hojaRuta = realm.where(HojaRutaEntity.class)
                .equalTo("fecha", new Date(fecha))
                .equalTo("choferEntity.idCrm", chofer.getId())
                .findFirst();

        if (hojaRuta != null) {
            this.hojaRuta = new HojaRutaEntityHojaRutaConverter().hojaRutaEntityToHojaRuta(hojaRuta);
            return true;
        } else {
            return false;
        }
    }

    private Boolean existenEnvases() {
        RealmResults<EnvaseEntity> envases = realm.where(EnvaseEntity.class).findAll();

        if (envases.size() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
