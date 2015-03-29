package com.datazuul.framework.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Util for calculating and handling hashes. See
 * https://www.owasp.org/index.php/Hashing_Java
 *
 * @author reiching
 */
public class HashUtil {

    private final static Logger LOGGER = LoggerFactory.getLogger(HashUtil.class);
    private final static int ITERATIONS = 1000;

    public static HashResult hash(String cleartext) {
        return hash(ITERATIONS, cleartext);
    }

    public static byte[] hash(String cleartext, byte[] salt) {
        try {
            return hash(ITERATIONS, cleartext, salt);
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("hash failed", ex);
            return null;
        }
    }

    public static HashResult hash(int iterations, String cleartext) {
        SecureRandom random;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            // Salt generation 64 bits long
            byte[] bSalt = new byte[8];
            random.nextBytes(bSalt);
            // Digest computation
            byte[] bDigest = hash(iterations, cleartext, bSalt);
            String sDigest = byteToBase64(bDigest);
            String sSalt = byteToBase64(bSalt);
            return new HashResult(sDigest, sSalt);
        } catch (NoSuchAlgorithmException ex) {
            LOGGER.error("hash failed", ex);
            return null;
        }
    }

    /**
     * From a cleartext, a number of iterations and a salt, returns the
     * corresponding digest.
     *
     * @param iterations int The number of iterations of the algorithm
     * @param cleartext String The cleartext to encrypt
     * @param salt byte[] The salt
     * @return byte[] The digested cleartext
     * @throws NoSuchAlgorithmException If the algorithm doesn't exist
     */
    public static byte[] hash(int iterations, String cleartext, byte[] salt) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(salt);
        byte[] input = null;
        try {
            input = digest.digest(cleartext.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            LOGGER.error("hash failed", ex);
            return null;
        }
        for (int i = 0; i < iterations; i++) {
            digest.reset();
            input = digest.digest(input);
        }
        return input;
    }

    /**
     * From a base 64 representation, returns the corresponding byte[]
     *
     * @param data String The base64 representation
     * @return byte[]
     * @throws IOException
     */
    public static byte[] base64ToByte(String data) throws IOException {
        return Base64.decodeBase64(data);
    }

    /**
     * From a byte[] returns a base 64 representation
     *
     * @param data byte[]
     * @return String
     * @throws IOException
     */
    public static String byteToBase64(byte[] data) {
        return new String(Base64.encodeBase64(data));
    }

    public static String md5(String input) throws NoSuchAlgorithmException {
        String result = null;
        try {
            result = md5(input.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("md5 failed", e);
        }
        return result;
    }

    public static String md5(byte[] input) throws NoSuchAlgorithmException {
        String result = null;
        if (input != null) {
            MessageDigest md = MessageDigest.getInstance("MD5"); //or "SHA-1"
            md.update(input);
            BigInteger hash = new BigInteger(1, md.digest());
            result = hash.toString(16);
            while (result.length() < 32) {
                result = "0" + result;
            }
        }
        return result;
    }
}
