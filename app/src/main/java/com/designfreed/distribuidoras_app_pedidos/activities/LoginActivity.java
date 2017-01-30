package com.designfreed.distribuidoras_app_pedidos.activities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.designfreed.distribuidoras_app_pedidos.R;
import com.designfreed.distribuidoras_app_pedidos.domain.Chofer;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

public class LoginActivity extends AppCompatActivity {
    private EditText txtUsername;
    private EditText txtPassword;
    private ImageButton btnLogin;
    private ProgressBar pbProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUsername = (EditText) findViewById(R.id.username);
        txtPassword = (EditText) findViewById(R.id.password);
        btnLogin = (ImageButton) findViewById(R.id.login);
        pbProgress = (ProgressBar) findViewById(R.id.progress);

        pbProgress.setVisibility(View.GONE);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

                if (networkInfo != null && networkInfo.isConnected()) {
                    new UserLoginTask().execute(txtUsername.getText().toString(), txtPassword.getText().toString());

                    pbProgress.setVisibility(View.VISIBLE);
                } else {
                    pbProgress.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "No hay conexion a internet", Toast.LENGTH_LONG);
                }
            }
        });
    }

    private class UserLoginTask extends AsyncTask<String, Void, Chofer> {
        @Override
        protected Chofer doInBackground(String... params) {
            String username = params[0];
            String password = params[1];

            String url = "http://bybgas.dyndns.org:8080/distribuidoras-backend/chofer/login/" + username + "/" + password;

            try {
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                Chofer chofer = restTemplate.getForObject(url, Chofer.class);

                return chofer;
            } catch (ResourceAccessException connectException) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(Chofer chofer) {
            super.onPostExecute(chofer);

            if (chofer != null) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("chofer", chofer);

                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Datos de acceso invalidos", Toast.LENGTH_LONG);
            }

            pbProgress.setVisibility(View.GONE);
        }
    }
}
