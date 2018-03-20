package com.designfreed.distribuidoras_app_pedidos.utils;

import java.text.NumberFormat;
import java.text.ParseException;
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

   public static Date formatShortDate(Date date) {
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

       String shortDate = formatter.format(date);

       try {
           return formatter.parse(shortDate);
       } catch (ParseException e) {
           e.printStackTrace();
           return null;
       }
   }

   public static Boolean isInteger(String number) {
        Boolean isValid = false;

        try {
            Integer.parseInt(number);

            isValid = true;
        } catch (NumberFormatException ex) {

        }

        return isValid;
   }
}
