package org.gonnaup.common.util;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;
import static org.apache.commons.codec.digest.DigestUtils.sha256Hex;

/**
 * 加密工具
 *
 * @author hy
 * @version 1.0
 * @Created on 2020/12/8 16:12
 */
public class CryptUtil {

    private CryptUtil() {
    }

    /**
     * mds散列
     *
     * @param data 数据
     * @return md5散列值 32
     */
    public static String md5Encode(String data) {
        return md5Hex(data).toUpperCase();
    }

    /**
     * mds散列
     *
     * @param data 数据
     * @param salt 盐
     * @return md5散列值 32
     */
    public static String md5Encode(String data, String salt) {
        return md5Hex(data + salt).toUpperCase();
    }

    /**
     * sha256散列
     *
     * @param data 数据
     * @return sha256散列值 64
     */
    public static String sha256Encode(String data) {
        return sha256Hex(data).toUpperCase();
    }

    /**
     * sha256散列值
     *
     * @param data 数据
     * @param salt 盐
     * @return sha256散列值 64
     */
    public static String sha256Encode(String data, String salt) {
        return sha256Hex(data + salt).toUpperCase();
    }

    public static String desEncrypt(String data, String sKey) {
        Objects.requireNonNull(data, "加密内容不能为空！");
        Objects.requireNonNull(sKey, "密钥不能为空！");
        if (sKey.length() < 8) {
            throw new IllegalArgumentException("密钥长度不能小于8");
        }
        try {
            DESKeySpec desKeySpec = new DESKeySpec(sKey.getBytes(StandardCharsets.UTF_8));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            Cipher desCipher = Cipher.getInstance("DES");
            desCipher.init(Cipher.ENCRYPT_MODE, secretKey, new SecureRandom());
            return Hex.encodeHexString(desCipher.doFinal(data.getBytes(StandardCharsets.UTF_8)), false);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException("DES加密异常", e);
        }
    }

    public static String desDecrypt(String data, String sKey) {
        Objects.requireNonNull(data, "加密内容不能为空！");
        Objects.requireNonNull(sKey, "密钥不能为空！");
        if (sKey.length() < 8) {
            throw new IllegalArgumentException("密钥长度不能小于8");
        }
        try {
            DESKeySpec desKeySpec = new DESKeySpec(sKey.getBytes(StandardCharsets.UTF_8));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            Cipher desCipher = Cipher.getInstance("DES");
            desCipher.init(Cipher.DECRYPT_MODE, secretKey, new SecureRandom());
            return new String(desCipher.doFinal(Hex.decodeHex(data)), StandardCharsets.UTF_8);
        } catch (InvalidKeyException | NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | DecoderException e) {
            throw new RuntimeException("DES解密异常", e);
        }
    }


}
