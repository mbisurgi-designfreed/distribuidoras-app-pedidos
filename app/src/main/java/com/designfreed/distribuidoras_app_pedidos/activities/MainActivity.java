package com.designfreed.distribuidoras_app_pedidos.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.designfreed.distribuidoras_app_pedidos.R;
import com.designfreed.distribuidoras_app_pedidos.domain.Chofer;

public class MainActivity extends AppCompatActivity {
    private TextView txtChofer;

    private Chofer chofer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chofer = (Chofer) getIntent().getSerializableExtra("chofer");

        txtChofer = (TextView) findViewById(R.id.chofer);
        txtChofer.setText(chofer.getNombre() + " " + chofer.getApellido());
    }
}
