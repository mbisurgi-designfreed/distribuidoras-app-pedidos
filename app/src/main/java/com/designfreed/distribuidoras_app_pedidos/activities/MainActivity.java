package com.designfreed.distribuidoras_app_pedidos.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.designfreed.distribuidoras_app_pedidos.R;
import com.designfreed.distribuidoras_app_pedidos.constants.Constants;
import com.designfreed.distribuidoras_app_pedidos.converters.ClienteEntityClienteConverter;
import com.designfreed.distribuidoras_app_pedidos.converters.CondicionVentaEntityCondicionVentaConverter;
import com.designfreed.distribuidoras_app_pedidos.converters.DateConverter;
import com.designfreed.distribuidoras_app_pedidos.converters.EnvaseEntityEnvaseConverter;
import com.designfreed.distribuidoras_app_pedidos.converters.EstadoMovimientoEntityEstadoMovimientoConverter;
import com.designfreed.distribuidoras_app_pedidos.converters.HojaRutaEntityHojaRutaConverter;
import com.designfreed.distribuidoras_app_pedidos.converters.ItemMovimientoEntityItemMovimientoConverter;
import com.designfreed.distribuidoras_app_pedidos.converters.MotivoEntityMotivoConverter;
import com.designfreed.distribuidoras_app_pedidos.converters.MovimientoEntityMovimientoConverter;
import com.designfreed.distribuidoras_app_pedidos.converters.TipoMovimientoEntityTipoMovimientoConverter;
import com.designfreed.distribuidoras_app_pedidos.domain.Chofer;
import com.designfreed.distribuidoras_app_pedidos.domain.Cliente;
import com.designfreed.distribuidoras_app_pedidos.domain.CondicionVenta;
import com.designfreed.distribuidoras_app_pedidos.domain.Envase;
import com.designfreed.distribuidoras_app_pedidos.domain.EstadoMovimiento;
import com.designfreed.distribuidoras_app_pedidos.domain.HojaRuta;
import com.designfreed.distribuidoras_app_pedidos.domain.ItemMovimiento;
import com.designfreed.distribuidoras_app_pedidos.domain.Motivo;
import com.designfreed.distribuidoras_app_pedidos.domain.Movimiento;
import com.designfreed.distribuidoras_app_pedidos.domain.TipoMovimiento;
import com.designfreed.distribuidoras_app_pedidos.entities.ClienteEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.CondicionVentaEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.EnvaseEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.EstadoMovimientoEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.HojaRutaEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.ItemMovimientoEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.MotivoEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.MovimientoEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.TipoMovimientoEntity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {
    private TextView txtChofer;
    private ProgressBar pbProgress;
    private ImageButton preRuteos;
    private ImageButton pedidos;
    private ImageButton listados;
    private ImageButton voleos;

    private Chofer activeChofer;
    private HojaRuta activeHojaRuta;

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        realm = Realm.getDefaultInstance();

        activeChofer = (Chofer) getIntent().getSerializableExtra("chofer");

        txtChofer = (TextView) findViewById(R.id.chofer);
        txtChofer.setText(activeChofer.getNombre() + " " + activeChofer.getApellido());
        pbProgress = (ProgressBar) findViewById(R.id.progress);
        preRuteos = (ImageButton) findViewById(R.id.pre_ruteos);
        preRuteos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MovimientoActivity.class);
                intent.putExtra("chofer", activeChofer);
                intent.putExtra("tipo", 1L);
                startActivity(intent);
            }
        });

        pedidos = (ImageButton) findViewById(R.id.pedidos);
        pedidos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MovimientoActivity.class);
                intent.putExtra("chofer", activeChofer);
                intent.putExtra("tipo", 2L);
                startActivity(intent);
            }
        });

        listados = (ImageButton) findViewById(R.id.listados);
        listados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CierreActivity.class);
                intent.putExtra("hoja", activeHojaRuta);
                startActivity(intent);
            }
        });

        voleos = (ImageButton) findViewById(R.id.voleos);
        voleos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MovimientoDetalleActivity.class);
                intent.putExtra("chofer", activeChofer);
                intent.putExtra("voleo", true);
                startActivity(intent);
            }
        });

        pbProgress.setVisibility(View.GONE);

        if (!existeHojaRuta()) {
            new LoadHojaRutaTask().execute();

            pbProgress.setVisibility(View.VISIBLE);
        }

        if (!existenEnvases()) {
            new LoadEnvasesTask().execute();

            pbProgress.setVisibility(View.VISIBLE);
        }

        if (!existenEstados()) {
            new LoadEstadosTask().execute();

            pbProgress.setVisibility(View.VISIBLE);
        }

        if (!existenCondiciones()) {
            new LoadCondicionesTask().execute();

            pbProgress.setVisibility(View.VISIBLE);
        }

        if (!existenTipos()) {
            new LoadTiposTask().execute();

            pbProgress.setVisibility(View.VISIBLE);
        }

        if (!existenMotivos()) {
            new LoadMotivosTask().execute();

            pbProgress.setVisibility(View.VISIBLE);
        }

        if (!existeCliente()) {
            new LoadClienteVoleoTask().execute();

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

                RealmResults<MovimientoEntity> movimientosEntity = realm.where(MovimientoEntity.class).equalTo("sincronizado", false).findAll();

                new SyncMovimientosLocalTask().execute(new MovimientoEntityMovimientoConverter().movimientosEntityToMovimientos(movimientosEntity));

                pbProgress.setVisibility(View.VISIBLE);

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class LoadHojaRutaTask extends AsyncTask<Void, Void, HojaRuta> {
        @Override
        protected HojaRuta doInBackground(Void... params) {
            Long fecha = new DateConverter().dateToLong(new Date());

            String url = Constants.SERVER + "distribuidoras-backend/hojaRuta/findByFechaChofer/" + fecha + "/" + activeChofer.getId();

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
                activeHojaRuta = hojaRuta;

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
            String url = Constants.SERVER + "distribuidoras-backend/envase/list";

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

    private class LoadEstadosTask extends AsyncTask<Void, Void, List<EstadoMovimiento>> {
        @Override
        protected List<EstadoMovimiento> doInBackground(Void... params) {
            String url = Constants.SERVER + "distribuidoras-backend/estadoMovimiento/list";

            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                EstadoMovimiento[] estados = restTemplate.getForObject(url, EstadoMovimiento[].class);

                return Arrays.asList(estados);
            } catch (ResourceAccessException connectException) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final List<EstadoMovimiento> estados) {
            super.onPostExecute(estados);

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(new EstadoMovimientoEntityEstadoMovimientoConverter().estadoMovimientoToEstadoMovimientoEntity(estados));
                }
            });

            pbProgress.setVisibility(View.GONE);
        }
    }

    private class LoadCondicionesTask extends AsyncTask<Void, Void, List<CondicionVenta>> {
        @Override
        protected List<CondicionVenta> doInBackground(Void... params) {
            String url = Constants.SERVER + "distribuidoras-backend/condicionVenta/list";

            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                CondicionVenta[] condiciones = restTemplate.getForObject(url, CondicionVenta[].class);

                return Arrays.asList(condiciones);
            } catch (ResourceAccessException connectException) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final List<CondicionVenta> condiciones) {
            super.onPostExecute(condiciones);

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(new CondicionVentaEntityCondicionVentaConverter().condicionVentaToCondicionVentaEntity(condiciones));
                }
            });

            pbProgress.setVisibility(View.GONE);
        }
    }

    private class LoadTiposTask extends AsyncTask<Void, Void, List<TipoMovimiento>> {
        @Override
        protected List<TipoMovimiento> doInBackground(Void... params) {
            String url = Constants.SERVER + "distribuidoras-backend/tipoMovimiento/list";

            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                TipoMovimiento[] tipos = restTemplate.getForObject(url, TipoMovimiento[].class);

                return Arrays.asList(tipos);
            } catch (ResourceAccessException connectException) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final List<TipoMovimiento> tipos) {
            super.onPostExecute(tipos);

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(new TipoMovimientoEntityTipoMovimientoConverter().tipoMovimientoToTipoMovimientoEntity(tipos));
                }
            });

            pbProgress.setVisibility(View.GONE);
        }
    }

    private class LoadMotivosTask extends AsyncTask<Void, Void, List<Motivo>> {
        @Override
        protected List<Motivo> doInBackground(Void... params) {
            String url = Constants.SERVER + "distribuidoras-backend/motivo/list";

            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                Motivo[] motivos = restTemplate.getForObject(url, Motivo[].class);

                return Arrays.asList(motivos);
            } catch (ResourceAccessException connectException) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final List<Motivo> motivos) {
            super.onPostExecute(motivos);

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(new MotivoEntityMotivoConverter().motivosToMotivosEntity(motivos));
                }
            });

            pbProgress.setVisibility(View.GONE);
        }
    }

    private class LoadClienteVoleoTask extends AsyncTask<Void, Void, Cliente> {
        @Override
        protected Cliente doInBackground(Void... params) {
            String url = Constants.SERVER + "distribuidoras-backend/cliente/find/" + Constants.VOLEO;

            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                Cliente cliente = restTemplate.getForObject(url, Cliente.class);

                return cliente;
            } catch (ResourceAccessException connectException) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final Cliente cliente) {
            super.onPostExecute(cliente);

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(new ClienteEntityClienteConverter().clienteToClienteEntity(cliente));
                }
            });

            pbProgress.setVisibility(View.GONE);
        }
    }

    private class SyncMovimientosRemotoTask extends AsyncTask<Void, Void, List<Movimiento>> {
        @Override
        protected List<Movimiento> doInBackground(Void... params) {
            String url = Constants.SERVER + "distribuidoras-backend/movimiento/findByHojaRutaSincronizado/" + activeHojaRuta.getId() + "/false";

            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                Movimiento[] movimientos = restTemplate.getForObject(url, Movimiento[].class);

                for (Movimiento movimiento: movimientos) {
                    movimiento.setSincronizado(true);
                }

                String post = Constants.SERVER + "distribuidoras-backend/movimiento/add";

                ResponseEntity<Movimiento[]> response = restTemplate.postForEntity(post, movimientos, Movimiento[].class);

                if (response.getStatusCode() != HttpStatus.OK) {
                    return null;
                }

                return Arrays.asList(movimientos);
            } catch (ResourceAccessException connectException) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(final List<Movimiento> movimientos) {
            super.onPostExecute(movimientos);

            if (movimientos != null) {
                Number primaryKeyMovimiento = realm.where(MovimientoEntity.class).max("id");
                Number primaryKeyItem = realm.where(ItemMovimientoEntity.class).max("id");

                Long idMovimiento;
                Long idItem;

                if (primaryKeyMovimiento == null) {
                    idMovimiento = 1L;
                } else {
                    idMovimiento = primaryKeyMovimiento.longValue() + 1;
                }

                if (primaryKeyItem == null) {
                    idItem = 1L;
                } else {
                    idItem = primaryKeyItem.longValue() + 1;
                }

                final List<MovimientoEntity> movimientosEntity = new ArrayList<>();

                for (Movimiento movimiento: movimientos) {
                    MovimientoEntity movimientoEntity = realm.where(MovimientoEntity.class).equalTo("idCrm", movimiento.getId()).findFirst();

                    if (movimientoEntity == null) {
                        movimientoEntity = new MovimientoEntityMovimientoConverter().movimientoToMovimientoEntity2(movimiento);
                        movimientoEntity.setId(idMovimiento);
                        idMovimiento++;

                        RealmList<ItemMovimientoEntity> itemsMovimientoEntity = new RealmList<>();

                        for (ItemMovimiento item: movimiento.getItems()) {
                            ItemMovimientoEntity itemMovimientoEntity = realm.where(ItemMovimientoEntity.class).equalTo("idCrm", item.getId()).findFirst();

                            if (itemMovimientoEntity == null) {
                                itemMovimientoEntity = new ItemMovimientoEntityItemMovimientoConverter().itemMovimientoToItemMovimientoEntity(item);
                                itemMovimientoEntity.setId(idItem);
                                idItem++;
                            }

                            itemsMovimientoEntity.add(itemMovimientoEntity);
                        }

                        movimientoEntity.setItems(itemsMovimientoEntity);
                    }

                    movimientosEntity.add(movimientoEntity);
                }

                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.copyToRealmOrUpdate(movimientosEntity);
                    }
                });

                pbProgress.setVisibility(View.GONE);
            }
        }
    }

    private class SyncMovimientosLocalTask extends AsyncTask<List<Movimiento>, Void, List<Movimiento>> {
        @Override
        protected List<Movimiento> doInBackground(List<Movimiento>... params) {
            String url = Constants.SERVER + "distribuidoras-backend/movimiento/add";

            try {
                RestTemplate restTemplate = new RestTemplate();

                Movimiento[] movimientos = new Movimiento[params[0].size()];
                movimientos = params[0].toArray(movimientos);

                for (Movimiento movimiento: movimientos) {
                    movimiento.setSincronizado(true);
                }

                ResponseEntity<Movimiento[]> response = restTemplate.postForEntity(url, movimientos, Movimiento[].class);

                if (response.getStatusCode() != HttpStatus.OK) {
                    return null;
                }

                return Arrays.asList(response.getBody());
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
                        RealmResults<MovimientoEntity> movimientosEntity = realm.where(MovimientoEntity.class).equalTo("sincronizado", false).findAll();

                        for (int i = 0; i < movimientosEntity.size(); i++) {
                            Movimiento movimiento = movimientos.get(i);
                            MovimientoEntity movimientoEntity = movimientosEntity.get(i);

                            if (movimientoEntity.getIdCrm() == null) {
                                movimientoEntity.setIdCrm(movimiento.getId());
                            }

                            movimientoEntity.setSincronizado(true);

                            for (int j = 0; j < movimientoEntity.getItems().size(); j++) {
                                ItemMovimiento itemMovimiento = movimiento.getItems().get(j);
                                ItemMovimientoEntity itemMovimientoEntity = movimientoEntity.getItems().get(j);

                                if (itemMovimientoEntity.getIdCrm() == null) {
                                    itemMovimientoEntity.setIdCrm(itemMovimiento.getId());
                                }
                            }
                        }

                        realm.copyToRealmOrUpdate(movimientosEntity);
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
                .equalTo("choferEntity.idCrm", activeChofer.getId())
                .findFirst();

        if (hojaRuta != null) {
            this.activeHojaRuta = new HojaRutaEntityHojaRutaConverter().hojaRutaEntityToHojaRuta(hojaRuta);
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

    private Boolean existenEstados() {
        RealmResults<EstadoMovimientoEntity> estados = realm.where(EstadoMovimientoEntity.class).findAll();

        if (estados.size() == 6) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean existenCondiciones() {
        RealmResults<CondicionVentaEntity> condiciones = realm.where(CondicionVentaEntity.class).findAll();

        if (condiciones.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean existenTipos() {
        RealmResults<TipoMovimientoEntity> tipos = realm.where(TipoMovimientoEntity.class).findAll();

        if (tipos.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean existenMotivos() {
        RealmResults<MotivoEntity> motivos = realm.where(MotivoEntity.class).findAll();

        if (motivos.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private Boolean existeCliente() {
        ClienteEntity clienteEntity = realm.where(ClienteEntity.class)
                .equalTo("idCrm", Constants.VOLEO)
                .findFirst();

        if (clienteEntity != null) {
            return true;
        } else {
            return false;
        }
    }
}
