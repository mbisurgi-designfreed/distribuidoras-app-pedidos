package com.designfreed.distribuidoras_app_pedidos.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.designfreed.distribuidoras_app_pedidos.R;
import com.designfreed.distribuidoras_app_pedidos.domain.ItemMovimiento;
import com.designfreed.distribuidoras_app_pedidos.domain.Movimiento;

import java.util.List;

public class MovimientoAdapter extends ArrayAdapter<Movimiento> {
    public MovimientoAdapter(Context context, List<Movimiento> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_movimientos, parent, false);
        }

        Movimiento movimiento = getItem(position);

        TextView razonSocial = (TextView) listItemView.findViewById(R.id.razon_social);
        razonSocial.setText(movimiento.getCliente().getRazonSocial());

        TextView calle = (TextView) listItemView.findViewById(R.id.calle);
        calle.setText(movimiento.getCliente().getCalle());

        TextView altura = (TextView) listItemView.findViewById(R.id.altura);
        altura.setText(movimiento.getCliente().getAltura());

        TextView estado = (TextView) listItemView.findViewById(R.id.estado);
        estado.setText(movimiento.getEstadoMovimiento().getEstadoMovimientoNombre());

        Float kg = 0f;

        for (ItemMovimiento item: movimiento.getItems()) {
            kg = kg + (item.getEnvase().getKilos() * item.getCantidad());
        }

        TextView kilos = (TextView) listItemView.findViewById(R.id.kilos);
        kilos.setText(formatKilos(kg));

        return listItemView;
    }

    private String formatKilos(Float kilos) {
        return kilos.toString() + " kg";
    }
}
