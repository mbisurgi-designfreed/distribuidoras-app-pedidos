package com.designfreed.distribuidoras_app_pedidos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.designfreed.distribuidoras_app_pedidos.R;
import com.designfreed.distribuidoras_app_pedidos.domain.ItemMovimiento;
import com.designfreed.distribuidoras_app_pedidos.entities.ItemMovimientoEntity;
import com.designfreed.distribuidoras_app_pedidos.utils.Utils;

import java.util.List;

public class ItemMovimientoAdapter extends ArrayAdapter<ItemMovimientoEntity> {
    public ItemMovimientoAdapter(Context context, List<ItemMovimientoEntity> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        final ItemMovimientoEntity item = getItem(position);

        TextView articulo = (TextView) listItemView.findViewById(R.id.producto);
        articulo.setText(item.getEnvaseEntity().getEnvaseNombre());

        TextView cantidad = (TextView) listItemView.findViewById(R.id.cantidad);
        cantidad.setText(item.getCantidad().toString());

        TextView precio = (TextView) listItemView.findViewById(R.id.precio);
        precio.setText(Utils.formatSaldo(item.getMonto()));

        ImageButton remover = (ImageButton) listItemView.findViewById(R.id.eliminar);
        remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remove(item);
            }
        });

        return listItemView;
    }
}
