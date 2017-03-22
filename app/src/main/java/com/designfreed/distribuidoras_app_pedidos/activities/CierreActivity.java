package com.designfreed.distribuidoras_app_pedidos.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.designfreed.distribuidoras_app_pedidos.R;
import com.designfreed.distribuidoras_app_pedidos.constants.Constants;
import com.designfreed.distribuidoras_app_pedidos.domain.HojaRuta;
import com.designfreed.distribuidoras_app_pedidos.entities.HojaRutaEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.MovimientoEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class CierreActivity extends AppCompatActivity {
    private TextView lblTelefonicos;
    private TextView lblTelefonicosEntregados;
    private TextView lblPreRuteos;
    private TextView lblPreRuteosEntregados;
    private TextView lblVoleos;
    private Button btnCerrar;

    private HojaRuta activeHojaRuta;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cierre);

        realm = Realm.getDefaultInstance();

        activeHojaRuta = (HojaRuta) getIntent().getSerializableExtra("hoja");

        lblTelefonicos = (TextView) findViewById(R.id.telefonicos);
        lblTelefonicosEntregados = (TextView) findViewById(R.id.telefonicos_entregados);
        lblPreRuteos = (TextView) findViewById(R.id.pre_ruteos);
        lblPreRuteosEntregados = (TextView) findViewById(R.id.pre_ruteos_entregados);
        lblVoleos = (TextView) findViewById(R.id.voleos);

        btnCerrar = (Button) findViewById(R.id.cerrar);
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new CerrarHojaRutaTask().execute(activeHojaRuta);
            }
        });

        CargarListados();
    }

    private class CerrarHojaRutaTask extends AsyncTask<HojaRuta, Void, Boolean> {
        @Override
        protected Boolean doInBackground(HojaRuta... params) {
            String url = Constants.SERVER + "distribuidoras-backend/hojaRuta/update";

            try {
                RestTemplate restTemplate = new RestTemplate();

                HojaRuta hojaRuta = params[0];

                hojaRuta.setCierreMobile(true);

                ResponseEntity<HojaRuta> response = restTemplate.postForEntity(url, hojaRuta, HojaRuta.class);

                if (response.getStatusCode() != HttpStatus.OK) {
                    return false;
                }

                return true;
            } catch (ResourceAccessException connectException) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);

            if (aBoolean) {
                final RealmResults<HojaRutaEntity> hojas = realm.where(HojaRutaEntity.class).findAll();
                final RealmResults<MovimientoEntity> movimientos = realm.where(MovimientoEntity.class).findAll();

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        hojas.deleteAllFromRealm();
                        movimientos.deleteAllFromRealm();
                    }
                });

                Toast.makeText(getApplicationContext(), "Cierre satisfactorio", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Cierre fallido", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void CargarListados() {
        RealmResults<MovimientoEntity> movimientos = realm.where(MovimientoEntity.class).findAll();

        List<MovimientoEntity> telefonicos = new ArrayList<>();
        List<MovimientoEntity> ruteos = new ArrayList<>();
        List<MovimientoEntity> voleos = new ArrayList<>();

        for (MovimientoEntity mov: movimientos) {
            if (mov.getTipoMovimientoEntity().getIdCrm().equals(1L)) {
                ruteos.add(mov);
            }

            if (mov.getTipoMovimientoEntity().getIdCrm().equals(2L)) {
                telefonicos.add(mov);
            }

            if (mov.getTipoMovimientoEntity().getIdCrm().equals(3L)) {
                voleos.add(mov);
            }
        }

        Integer entregados = 0;

        for (MovimientoEntity tel: telefonicos) {
            if (tel.getEstadoMovimientoEntity().getId().equals(3L)) {
                entregados++;
            }
        }

        lblTelefonicos.setText(String.valueOf(telefonicos.size()));
        lblTelefonicosEntregados.setText(String.valueOf(entregados));

        entregados = 0;

        for (MovimientoEntity pre: ruteos) {
            if (pre.getEstadoMovimientoEntity().getId().equals(3L)) {
                entregados++;
            }
        }

        lblPreRuteos.setText(String.valueOf(ruteos.size()));
        lblPreRuteosEntregados.setText(String.valueOf(entregados));

        lblVoleos.setText(String.valueOf(voleos.size()));
    }
}
