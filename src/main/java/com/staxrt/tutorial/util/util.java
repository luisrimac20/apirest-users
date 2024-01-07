package com.staxrt.tutorial.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class util {

    public static String generateHash() {
        // Genera un número aleatorio
        Random random = new Random();
        int randomNumber = random.nextInt();

        // Obtiene la fecha actual con horas, minutos y milisegundos
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String currentDate = dateFormat.format(new Date());

        // Concatena el número aleatorio y la fecha actual
        String combinedString = String.valueOf(randomNumber) + currentDate;

        try {
            // Calcula el hash SHA-256
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(combinedString.getBytes());

            // Convierte el hash a una cadena hexadecimal
            StringBuilder hexStringBuilder = new StringBuilder();
            for (byte hashByte : hashBytes) {
                hexStringBuilder.append(String.format("%02x", hashByte));
            }

            // Limita la cadena a 15 caracteres
            String finalHash = hexStringBuilder.toString().substring(0, 15);

            return finalHash;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
