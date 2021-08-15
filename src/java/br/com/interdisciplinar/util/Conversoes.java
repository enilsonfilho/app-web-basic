package br.com.interdisciplinar.util;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Enilson Filho
 */
public class Conversoes {

    public static Date converterDate(String data) {
        try {
            if (data != null || !data.trim().equals("")) {
                SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
                return fmt.parse(data);
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao converter Data!Erro" + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }

    public static Time converterHora(String hora) {
        try {
            if (hora != null || !hora.trim().equals("")) {
                SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss");
                Date data = formatador.parse(hora);
                return new Time(data.getTime());
            } else {
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Problemas ao conectar time! Error:" + ex.getMessage());
            ex.printStackTrace();
            return null;
        }
    }
}
