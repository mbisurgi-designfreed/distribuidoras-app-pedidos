package com.designfreed.distribuidoras_app_pedidos.utils;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static String formatSaldo(Float saldo) {
        Locale locale = new Locale("es", "AR");

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        return currencyFormatter.format(saldo);
    }

    public static String formatKilos(Float kilos) {
        return kilos.toString() + " kg";
    }

    public static String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        return formatter.format(date);
    }
}
