package xyz.lannt.utils;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionUtility {

  public static String buildHmacSignature(String value, String secret) {
    String result;
    try {
      Mac hmacSHA512 = Mac.getInstance("HmacSHA512");
      SecretKeySpec secretKeySpec = new SecretKeySpec(secret.getBytes(), "HmacSHA512");
      hmacSHA512.init(secretKeySpec);

      byte[] digest = hmacSHA512.doFinal(value.getBytes());
      BigInteger hash = new BigInteger(1, digest);
      result = hash.toString(16);
      if ((result.length() % 2) != 0) {
        result = "0" + result;
      }
    } catch (IllegalStateException | InvalidKeyException | NoSuchAlgorithmException ex) {
      throw new RuntimeException("Problemas calculando HMAC", ex);
    }
    return result;
  }
}
