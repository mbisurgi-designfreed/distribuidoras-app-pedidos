package com.designfreed.distribuidoras_app_pedidos.utils;

import java.text.NumberFormat;
import java.util.Locale;

public class Utils {
    public static String formatSaldo(Float saldo) {
        Locale locale = new Locale("es", "AR");

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        return currencyFormatter.format(saldo);
    }
}
