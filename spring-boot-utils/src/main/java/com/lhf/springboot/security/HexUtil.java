package com.lhf.springboot.security;

/**
 * @ClassName: HexUtil
 * @Author: liuhefei
 * @Description: TODD
 * @Date: 2019/8/2 16:03
 */
public class HexUtil {
    private static final char[] DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public HexUtil() {
    }

    private static byte[] decodeHexString(String hexString) {
        if (hexString == null) {
            throw new IllegalArgumentException("hexString must not be null!");
        } else {
            boolean flag = false;
            int num = 0;
            int length = hexString.length();
            if (length % 2 != 0 && length % 3 != 2) {
                throw new IllegalArgumentException("hexString length is invalid!");
            } else {
                if (length >= 2 && hexString.charAt(0) == '0' && (hexString.charAt(1) == 'x' || hexString.charAt(1) == 'X')) {
                    length = hexString.length() - 2;
                    num = 2;
                }

                byte[] buffer;
                if (length >= 3 && hexString.charAt(num + 2) == ' ') {
                    flag = true;
                    buffer = new byte[length / 3 + 1];
                } else {
                    buffer = new byte[length / 2];
                }

                for(int i = 0; num < length; ++i) {
                    int num4 = toHexDigit(hexString.charAt(num));
                    int num3 = toHexDigit(hexString.charAt(num + 1));
                    buffer[i] = (byte)(num3 | num4 << 4);
                    if (flag) {
                        ++num;
                    }

                    num += 2;
                }

                return buffer;
            }
        }
    }

    private static int toHexDigit(char ch) {
        if (ch <= '9' && ch >= '0') {
            return ch - 48;
        } else if (ch >= 'a' && ch <= 'f') {
            return ch - 97 + 10;
        } else if (ch >= 'A' && ch <= 'F') {
            return ch - 65 + 10;
        } else {
            throw new IllegalArgumentException("Illegal hexadecimal charcter + " + ch);
        }
    }

    private static byte[] decodeHex(char[] data) {
        int len = data.length;
        if ((len & 1) != 0) {
            throw new IllegalArgumentException("Odd number of characters.");
        } else {
            byte[] out = new byte[len >> 1];
            int i = 0;

            for(int j = 0; j < len; ++i) {
                int f = toHexDigit(data[j]) << 4;
                ++j;
                f |= toHexDigit(data[j]);
                ++j;
                out[i] = (byte)(f & 255);
            }

            return out;
        }
    }

    public static char[] encodeHex(byte[] data) {
        int l = data.length;
        char[] out = new char[l << 1];
        int i = 0;

        for(int var4 = 0; i < l; ++i) {
            out[var4++] = DIGITS[(240 & data[i]) >>> 4];
            out[var4++] = DIGITS[15 & data[i]];
        }

        return out;
    }

    private static long parseLong(CharSequence s) {
        long out = 0L;
        byte shifts = 0;

        for(int i = 0; i < s.length() && shifts < 16; ++i) {
            char c = s.charAt(i);
            if (c > '/' && c < ':') {
                ++shifts;
                out <<= 4;
                out |= (long)(c - 48);
            } else if (c > '@' && c < 'G') {
                ++shifts;
                out <<= 4;
                out |= (long)(c - 55);
            } else if (c > '`' && c < 'g') {
                ++shifts;
                out <<= 4;
                out |= (long)(c - 87);
            }
        }

        return out;
    }

    private static short parseShort(String s) {
        short out = 0;
        byte shifts = 0;

        for(int i = 0; i < s.length() && shifts < 4; ++i) {
            char c = s.charAt(i);
            if (c > '/' && c < ':') {
                ++shifts;
                out = (short)(out << 4);
                out = (short)(out | c - 48);
            } else if (c > '@' && c < 'G') {
                ++shifts;
                out = (short)(out << 4);
                out = (short)(out | c - 55);
            } else if (c > '`' && c < 'g') {
                ++shifts;
                out = (short)(out << 4);
                out = (short)(out | c - 87);
            }
        }

        return out;
    }
}
