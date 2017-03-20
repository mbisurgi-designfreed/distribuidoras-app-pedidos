package com.designfreed.distribuidoras_app_pedidos.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.designfreed.distribuidoras_app_pedidos.R;
import com.designfreed.distribuidoras_app_pedidos.entities.ItemMovimientoEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.MovimientoEntity;
import com.designfreed.distribuidoras_app_pedidos.utils.Utils;

import java.util.List;

public class MovimientoAdapter extends ArrayAdapter<MovimientoEntity> {
    public MovimientoAdapter(Context context, List<MovimientoEntity> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_movimientos, parent, false);
        }

        MovimientoEntity movimiento = getItem(position);

        TextView razonSocial = (TextView) listItemView.findViewById(R.id.razon_social);
        razonSocial.setText(movimiento.getClienteEntity().getRazonSocial());

        TextView direccion = (TextView) listItemView.findViewById(R.id.direccion);
        direccion.setText(movimiento.getClienteEntity().getCalle() + " " + movimiento.getClienteEntity().getAltura());

        TextView estado = (TextView) listItemView.findViewById(R.id.estado);
        estado.setText(movimiento.getEstadoMovimientoEntity().getEstadoMovimientoNombre());

        CheckBox visito = (CheckBox) listItemView.findViewById(R.id.visito);
        visito.setChecked(movimiento.getVisito());

        Float kg = 0f;
        Float pes = 0f;

        for (ItemMovimientoEntity item: movimiento.getItems()) {
            kg = kg + (item.getEnvaseEntity().getKilos() * item.getCantidad());
            pes = pes + item.getMonto();
        }

        TextView kilos = (TextView) listItemView.findViewById(R.id.kilos);
        kilos.setText(Utils.formatKilos(kg));

        TextView pesos = (TextView) listItemView.findViewById(R.id.pesos);
        pesos.setText(Utils.formatSaldo(pes));

        return listItemView;
    }
}
