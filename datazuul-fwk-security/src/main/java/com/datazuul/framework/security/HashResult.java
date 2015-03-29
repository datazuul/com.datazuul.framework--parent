package com.datazuul.framework.security;

/**
 * Contains the result of hashing a cleartext.
 * @author Ralf Eichinger
 */
public class HashResult {
    private String digest;
    private String salt;

    public HashResult(String digest, String salt) {
        this.digest = digest;
        this.salt = salt;
    }

    public String getDigest() {
        return digest;
    }

    public String getSalt() {
        return salt;
    }
}
