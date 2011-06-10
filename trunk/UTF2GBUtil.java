package com.ryan;

/**
 * User: Ryan
 * Date: 11-6-10
 * Time: 下午11:15
 */
public abstract class UTF2GBUtil {

    public static String covert(String src) {
        StringBuffer sb = new StringBuffer();
        char[] chars = src.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '\\' && chars[i + 1] == 'u') {
                int one = h(chars[i + 2]) * 4096;
                if (-1 == one) {
                    sb.append(chars[i]);
                    continue;
                }
                int two = h(chars[i + 3]) * 256;
                if (-1 == two) {
                    sb.append(chars[i]);
                    continue;
                }
                int three = h(chars[i + 4]) * 16;
                if (-1 == three) {
                    sb.append(chars[i]);
                    continue;
                }
                int four = h(chars[i + 5]);
                if (-1 == four) {
                    sb.append(chars[i]);
                    continue;
                }
                int count = one + two + three + four;
                sb.append((char) count);
                i += 5;
                continue;
            }
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    private static int h(char c) {
        if (c > 47 && c < 58) {
            return c - 48;
        } else if (c > 64 && c < 71) {
            return c - 55;
        } else if (c > 96 && c < 103) {
            return c - 87;
        }
        return -1;
    }
}
