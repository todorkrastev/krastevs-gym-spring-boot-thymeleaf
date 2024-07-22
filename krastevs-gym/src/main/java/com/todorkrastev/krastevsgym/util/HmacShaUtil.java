package com.todorkrastev.krastevsgym.util;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.KeyGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.InvalidKeyException;
import java.util.Base64;

public class HmacShaUtil {

    // Method to generate HMAC-SHA key larger than 256 bits
    public static SecretKey generateKey(int keySize) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA512");
        keyGenerator.init(keySize);
        return keyGenerator.generateKey();
    }

    // Method to compute HMAC-SHA signature
    public static String computeHmac(String data, SecretKey key) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA512");
        mac.init(key);
        byte[] hmacBytes = mac.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(hmacBytes);
    }

    public static void main(String[] args) {
        try {
            // Example usage
            SecretKey key = generateKey(512); // Generate a 512-bit key
            String data = "Hello, World!";
            String hmac = computeHmac(data, key);
            System.out.println("HMAC: " + hmac);
        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }
}