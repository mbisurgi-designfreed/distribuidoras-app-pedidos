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
import com.designfreed.distribuidoras_app_pedidos.entities.ItemMovimientoEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.MovimientoEntity;
import com.designfreed.distribuidoras_app_pedidos.utils.Utils;

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
    private TextView lblKilos;
    private TextView lblPesos;
    private TextView lblcontadoKilos;
    private TextView lblcontadoPesos;
    private TextView lblctacteKilos;
    private TextView lblctactePesos;
    private TextView lblCantidad10;
    private TextView lblKilos10;
    private TextView lblCantidad15;
    private TextView lblKilos15;
    private TextView lblCantidad15me;
    private TextView lblKilos15me;
    private TextView lblCantidad30;
    private TextView lblKilos30;
    private TextView lblCantidad45;
    private TextView lblKilos45;
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
        lblKilos = (TextView) findViewById(R.id.kilos);
        lblPesos = (TextView) findViewById(R.id.pesos);
        lblcontadoKilos = (TextView) findViewById(R.id.contadoKilos);
        lblcontadoPesos = (TextView) findViewById(R.id.contadoPesos);
        lblctacteKilos = (TextView) findViewById(R.id.ctacteKilos);
        lblctactePesos = (TextView) findViewById(R.id.ctactePesos);
        lblCantidad10 = (TextView) findViewById(R.id.garrafa10_cant);
        lblKilos10 = (TextView) findViewById(R.id.garrafa10_kilos);
        lblCantidad15 = (TextView) findViewById(R.id.garrafa15_cant);
        lblKilos15 = (TextView) findViewById(R.id.garrafa15_kilos);
        lblCantidad15me = (TextView) findViewById(R.id.garrafa15me_cant);
        lblKilos15me = (TextView) findViewById(R.id.garrafa15me_kilos);
        lblCantidad30 = (TextView) findViewById(R.id.garrafa30_cant);
        lblKilos30 = (TextView) findViewById(R.id.garrafa30_kilos);
        lblCantidad45 = (TextView) findViewById(R.id.garrafa45_cant);
        lblKilos45 = (TextView) findViewById(R.id.garrafa45_kilos);

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

                hojaRuta.setEstado(false);
                hojaRuta.setControlStock(true);
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

        Float kilos = 0F;
        Float pesos = 0F;
        Float contadoKilos = 0F;
        Float contadoPesos = 0F;
        Float ctacteKilos = 0F;
        Float ctactePesos = 0F;

        Integer cantidad10 = 0;
        Integer cantidad15 = 0;
        Integer cantidad15me = 0;
        Integer cantidad30 = 0;
        Integer cantidad45 = 0;

        for (MovimientoEntity mov: movimientos) {
            if (mov.getEstadoMovimientoEntity().getId().equals(3L)) {
                for (ItemMovimientoEntity item: mov.getItems()) {
                    kilos = kilos + (item.getCantidad() * item.getEnvaseEntity().getKilos());
                    pesos = pesos + item.getMonto();

                    if (mov.getCondicionVentaEntity().getIdCrm() == 1) {
                        contadoKilos = contadoKilos + (item.getCantidad() * item.getEnvaseEntity().getKilos());
                        contadoPesos = contadoPesos + item.getMonto();
                    }

                    if (mov.getCondicionVentaEntity().getIdCrm() == 2) {
                        ctacteKilos = ctacteKilos + (item.getCantidad() * item.getEnvaseEntity().getKilos());
                        ctactePesos = ctactePesos + item.getMonto();
                    }

                    if (item.getEnvaseEntity().getEnvaseCodigo() == 1001) {
                        cantidad10 = cantidad10 + item.getCantidad();
                    }

                    if (item.getEnvaseEntity().getEnvaseCodigo() == 1002) {
                        cantidad15 = cantidad15 + item.getCantidad();
                    }

                    if (item.getEnvaseEntity().getEnvaseCodigo() == 1003) {
                        cantidad15me = cantidad15me + item.getCantidad();
                    }

                    if (item.getEnvaseEntity().getEnvaseCodigo() == 2001) {
                        cantidad30 = cantidad30 + item.getCantidad();
                    }

                    if (item.getEnvaseEntity().getEnvaseCodigo() == 2002) {
                        cantidad45 = cantidad45 + item.getCantidad();
                    }
                }
            }
        }

        lblPreRuteos.setText(String.valueOf(ruteos.size()));
        lblPreRuteosEntregados.setText(String.valueOf(entregados));

        lblKilos.setText(String.valueOf(kilos));
        lblPesos.setText(Utils.formatSaldo(pesos));
        lblcontadoKilos.setText(String.valueOf(contadoKilos));
        lblcontadoPesos.setText(Utils.formatSaldo(contadoPesos));
        lblctacteKilos.setText(String.valueOf(ctacteKilos));
        lblctactePesos.setText(Utils.formatSaldo(ctactePesos));

        lblCantidad10.setText(String.valueOf(cantidad10));
        lblCantidad15.setText(String.valueOf(cantidad15));
        lblCantidad15me.setText(String.valueOf(cantidad15me));
        lblCantidad30.setText(String.valueOf(cantidad30));
        lblCantidad45.setText(String.valueOf(cantidad45));

        lblKilos10.setText(String.valueOf(cantidad10 * 10));
        lblKilos15.setText(String.valueOf(cantidad15 * 15));
        lblKilos15me.setText(String.valueOf(cantidad15me * 15));
        lblKilos30.setText(String.valueOf(cantidad30 * 30));
        lblKilos45.setText(String.valueOf(cantidad45 * 45));
    }
}
