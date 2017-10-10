/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 *
 * @author vhphat
 */
public class NewClass {

    private static final int KEY_LENGTH = 256; // bits

    public static String createHash(String password, String salt, int iterations) {
        try {
            char[] passwordChars = password.toCharArray();

            if (salt.equals("")) {
                salt = getSalt("");
            }

            byte[] saltBytes = hexStringToByteArray(salt);

            if (iterations == 0) {
                iterations = 1;
            }

            PBEKeySpec spec = new PBEKeySpec(
                    passwordChars,
                    saltBytes,
                    iterations,
                    KEY_LENGTH
            );

            SecretKeyFactory key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hashedPassword = key.generateSecret(spec).getEncoded();
            return toHex(hashedPassword).toUpperCase();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String getSalt(String id) throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[8];

        if (!id.equals("")) {
            sr.setSeed(sr.generateSeed(id.length()));
        }
        sr.nextBytes(salt);

        return toHex(salt);
    }

    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }
    }

}

