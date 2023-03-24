package com.bbsw.myFirstApi.encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Encoder {

private static final String key ="qwertyuiopasdfghjklñzxcv'nmUYTRE";
private static final String salt = "qwertyuiopas'fghjklñzxcvbnmUYTRE";

private SecretKey secretKeyTemp;

public Encoder(){
    SecretKeyFactory secretkeyfactory;
    KeySpec keyspec;
    try{
        secretkeyfactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        keyspec = new PBEKeySpec(key.toCharArray(), salt.getBytes(), 65536,256);
        secretKeyTemp = secretkeyfactory.generateSecret(keyspec);

    }catch (Exception e){

    e.printStackTrace();
    }
}

    public String getCipher(String data){

        String encoded = "";
        byte[] iv = new byte[16];

        try{

            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyTemp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            encoded = Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes("UTF-8")));
            System.out.println(encoded);
            return encoded;

        }catch(Exception e){
            e.printStackTrace();
        }
            return null;
    }

    public String getDecipher(String encoded){
        String decoded = "";

        byte[] iv = new byte[16];

        try{

            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKeyTemp.getEncoded(), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            decoded = new String(cipher.doFinal(Base64.getDecoder().decode(encoded)));
            System.out.println(decoded);
            return decoded;

        }catch(Exception e){
            e.printStackTrace();

        }
            return null;
    }

}

