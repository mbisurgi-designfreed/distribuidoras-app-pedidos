package com.designfreed.distribuidoras_app_pedidos.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.designfreed.distribuidoras_app_pedidos.R;
import com.designfreed.distribuidoras_app_pedidos.converters.MovimientoEntityMovimientoConverter;
import com.designfreed.distribuidoras_app_pedidos.domain.Movimiento;
import com.designfreed.distribuidoras_app_pedidos.entities.ClienteEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.ItemMovimientoEntity;
import com.designfreed.distribuidoras_app_pedidos.entities.MovimientoEntity;
import com.designfreed.distribuidoras_app_pedidos.utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MovimientoAdapter extends ArrayAdapter<MovimientoEntity> implements Filterable {
    private List<MovimientoEntity> mMovimientos = new ArrayList<>();
    private List<MovimientoEntity> mMovimientosFiltrados = new ArrayList<>();
    private MovimientoFilter mFilter;

    public MovimientoAdapter(Context context, List<MovimientoEntity> objects) {
        super(context, 0, objects);
        this.mMovimientos = objects;
        this.mMovimientosFiltrados = objects;
    }

    @Override
    public int getCount() {
        return mMovimientosFiltrados.size();
    }

    @Override
    public MovimientoEntity getItem(int position) {
        return mMovimientosFiltrados.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_movimientos, parent, false);
        }

        MovimientoEntity movimiento = mMovimientosFiltrados.get(position);

        TextView razonSocial = (TextView) listItemView.findViewById(R.id.razon_social);
        razonSocial.setText(movimiento.getClienteEntity().getRazonSocial());

        TextView direccion = (TextView) listItemView.findViewById(R.id.direccion);
        direccion.setText(movimiento.getClienteEntity().getCalle() + " " + movimiento.getClienteEntity().getAltura());

        TextView estado = (TextView) listItemView.findViewById(R.id.estado);
        estado.setText(movimiento.getEstadoMovimientoEntity().getEstadoMovimientoNombre());

        CheckBox visito = (CheckBox) listItemView.findViewById(R.id.visito);
        visito.setChecked(movimiento.getVisito());

        if (movimiento.getReclamo() == null) {
            
        }

        ImageView image = (ImageView) listItemView.findViewById(R.id.img_observaciones);

        if (movimiento.getObservaciones() == null) {
            image.setImageDrawable(null);
        }

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

    @NonNull
    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new MovimientoFilter();
        }

        return mFilter;
    }

    @Override
    public void sort(@NonNull Comparator<? super MovimientoEntity> comparator) {
        super.sort(comparator);
    }

    private class MovimientoFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                results.values = mMovimientos;
                results.count = mMovimientos.size();
            } else {
                List<MovimientoEntity> filtrado = new ArrayList<>();

                for (MovimientoEntity mov: mMovimientos) {
                    if (mov.getClienteEntity().getCalle().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filtrado.add(mov);
                    }
                }

                results.values = filtrado;
                results.count = filtrado.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.count == 0)
                notifyDataSetInvalidated();
            else {
                mMovimientosFiltrados = (ArrayList<MovimientoEntity>) results.values;
                notifyDataSetChanged();
            }

            Collections.sort(mMovimientosFiltrados, new Comparator<MovimientoEntity>() {
                @Override
                public int compare(MovimientoEntity o1, MovimientoEntity o2) {
                    return o1.getEstadoMovimientoEntity().getEstadoMovimientoNombre().compareTo(o2.getEstadoMovimientoEntity().getEstadoMovimientoNombre());
                }
            });

            notifyDataSetChanged();
        }
    }
}


