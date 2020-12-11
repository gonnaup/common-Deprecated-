package org.gonnaup.common.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author gonnaup
 * @version 2020/12/11 14:40
 */
class EncryptUtilTest {

    @Test
    void md5Encode() {
        String s1 = CryptUtil.md5Encode("helloworld");
        String s2 = CryptUtil.md5Encode("helloworld");
        System.out.println(s1);
        assertEquals(s1, s2);
        String s3 = CryptUtil.md5Encode("helloworld", "XXYY");
        String s4 = CryptUtil.md5Encode("helloworld", "XXYY");
        System.out.println(s3);
        assertEquals(s3, s4);
    }

    @Test
    void shaEncode() {
        String s1 = CryptUtil.sha256Encode("helloworld");
        String s2 = CryptUtil.sha256Encode("helloworld");
        System.out.println(s1);
        assertEquals(s1, s2);
        String s3 = CryptUtil.sha256Encode("helloworld", "XXYY");
        String s4 = CryptUtil.sha256Encode("helloworld", "XXYY");
        System.out.println(s3);
        assertEquals(s3, s4);
    }

    @Test
    void des() {
        String data = "helloworld,this is a DES test";
        String sKey = "34242342344534534345";
        String encrypt = CryptUtil.desEncrypt(data, sKey);
        System.out.println(encrypt);
        String decrypt = CryptUtil.desDecrypt(encrypt, sKey);
        System.out.println(decrypt);
        assertEquals(data, decrypt);
    }

}