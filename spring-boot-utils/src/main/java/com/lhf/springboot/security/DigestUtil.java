package com.lhf.springboot.security;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName: DigestUtil
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/2 15:59
 */
public class DigestUtil {
    private static final String ALGORITHM_MD5 = "MD5";
    private static final String ALGORITHM_SHA = "SHA";
    private static final String ALGORITHM_SHA256 = "SHA-256";
    private static final String ALGORITHM_SHA384 = "SHA-384";
    private static final String ALGORITHM_SHA512 = "SHA-512";
    private static final Map<String, DigestUtil> instanceMap = new ConcurrentHashMap();
    private final String algorithm;

    private DigestUtil(String algorithm) {
        this.algorithm = algorithm;
    }

    public static DigestUtil md5() {
        return cache("MD5");
    }

    public static DigestUtil sha() {
        return cache("SHA");
    }

    public static DigestUtil sha256() {
        return cache("SHA-256");
    }

    public static DigestUtil sha384() {
        return cache("SHA-384");
    }

    public static DigestUtil sha512() {
        return cache("SHA-512");
    }

    private static DigestUtil cache(String alg) {
        DigestUtil o = (DigestUtil)instanceMap.get(alg);
        if (o == null) {
            instanceMap.put(alg, o = new DigestUtil(alg));
        }

        return o;
    }

    public String digest(String s) {
        if (StringUtils.isEmpty(s)) {
            return s;
        } else {
            try {
                byte[] in = s.getBytes("UTF-8");
                byte[] out = this.compute(in);
                char[] array = HexUtil.encodeHex(out);
                return new String(array);
            } catch (UnsupportedEncodingException var5) {
                throw new RuntimeException("digest failed!", var5);
            }
        }
    }

    private byte[] compute(byte[] buffer) {
        if (buffer == null) {
            throw new IllegalArgumentException("Argument buffer must not be null.");
        } else {
            MessageDigest md = this.getDigest();
            md.reset();
            md.update(buffer);
            return md.digest();
        }
    }

    private byte[] compute(byte[] buffer, int offset, int count) {
        if (buffer == null) {
            throw new IllegalArgumentException("Argument buffer must not be null.");
        } else if (offset >= buffer.length) {
            throw new IllegalArgumentException("Argument offset is out of range");
        } else {
            MessageDigest md = this.getDigest();
            md.reset();
            md.update(buffer, offset, count);
            return md.digest();
        }
    }

    private byte[] compute(ByteBuffer input) {
        if (input == null) {
            throw new IllegalArgumentException("Argument input must not be null.");
        } else {
            MessageDigest md = this.getDigest();
            md.reset();
            md.update(input);
            return md.digest();
        }
    }

    private MessageDigest getDigest() {
        try {
            return MessageDigest.getInstance(this.algorithm);
        } catch (Exception var2) {
            throw new RuntimeException("invalid digest algorithm!", var2);
        }
    }
}
