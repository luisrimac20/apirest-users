package com.staxrt.tutorial;

import com.staxrt.tutorial.util.util;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * The type Application.
 *
 * @author Givantha Kalansuriya
 */
@SpringBootApplication
public class Application {

    /**
   * The entry point of application.
   *
   * @param args the input arguments
   */
  public static void main(String[] args) {
      String generatedHash = util.generateHash();
      System.out.println("Hash generado: " + generatedHash);

      SpringApplication.run(Application.class, args);
	}
}
