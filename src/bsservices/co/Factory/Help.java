/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsservices.co.Factory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author doroteia
 */
public class Help {

    public static Date date_from_string(String date)  {

        try {
            SimpleDateFormat parse = new SimpleDateFormat("dd.MM.yyyy");
            date = date.replaceAll("/", ".");
            java.util.Date d = parse.parse(date);
            Date df = new Date(d.getTime());
            
            return df;
        } catch (ParseException ex) {
         
        }
        return null;

    }

    public static String incriptar_senha(String senha) {
        byte[] defaultBytes = senha.getBytes();
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(defaultBytes);
            byte messageDigest[] = algorithm.digest();

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < messageDigest.length; i++) {
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            }

            senha = hexString + "";

        } catch (NoSuchAlgorithmException nsae) {

        }

        return senha;
    }

   

    static public List<Float> tamanhos(String tamanhos) {
        List<Float> tamanho = new ArrayList<>();
        String converter = "";

        for (int i = 0; i < tamanhos.length(); i++) {
//            if(i == tamanhos.length() -1){
//                converter = converter + "" + tamanhos.charAt(i);
//                tamanho.add(Float.parseFloat(converter));
//            }
            if (tamanhos.charAt(i) == ',') {
                tamanho.add(Float.parseFloat(converter));

                converter = "";
                continue;
            } else {
                converter = converter + "" + tamanhos.charAt(i);

            }
        }

        return tamanho;
    }

    public static String removerTamanho(String tamanho) {
        int cont = 0;
        String tamanhoRemovido="";
        for (int i = tamanho.length()-1; i >= 0; i--) {
           
            if (i != tamanho.length()-1 && tamanho.charAt(i) == ',') {
                cont = i;
                break;
            }
        }

        for (int i = 0; i < cont; i++) {
            tamanhoRemovido +=tamanho.charAt(i);
        }
        return tamanhoRemovido+",";
    }

    public static void main(String[] args) {

        System.out.println(date_from_string("9/2/2020"));
    }

}
