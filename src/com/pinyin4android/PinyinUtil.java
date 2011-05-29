package com.pinyin4android;

import android.content.Context;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * User: Ryan
 * Date: 11-5-29
 * Time: 下午9:13
 */
public abstract class PinyinUtil {

    public static String toPinyin(Context context,char c) {
        if (c >= 'A' && c <= 'Z') {
            return String.valueOf((char) (c + 32));
        }
        if (c >= 'a' && c <= 'z') {
            return String.valueOf(c);
        }
        if (c == 0x3007) return "ling";
        if (c < 4E00 || c > 0x9FA5) {
            return null;
        }
        RandomAccessFile is = null;
        try {
            is = new RandomAccessFile(PinyinSource.getFile(context), "r");
            long sp = (c - 0x4E00) * 6;
            is.seek(sp);
            byte[] buf = new byte[6];
            is.read(buf);
            return new String(buf).trim();
        } catch (FileNotFoundException e) {
            //
        } catch (IOException e) {
            //
        } finally {
            try {
                if (null != is) is.close();
            } catch (IOException e) {
                //
            }
        }
        return null;
    }

    public static String toPinyin(Context context,String hanzi) {
        StringBuffer sb = new StringBuffer("");
        RandomAccessFile is = null;
        try {
            is = new RandomAccessFile(PinyinSource.getFile(context), "r");
            for (int i = 0; i < hanzi.length(); i++) {
                char ch = hanzi.charAt(i);
                if (ch >= 'A' && ch <= 'Z') {
                    sb.append((char) (ch + 32));
                    continue;
                }
                if (ch >= 'a' && ch <= 'z') {
                    sb.append(ch);
                    continue;
                }
                if (ch == 0x3007) {
                    sb.append("ling").append(' ');
                } else if (ch >= 0x4E00 || ch <= 0x9FA5) {
                    long sp = (ch - 0x4E00) * 6;
                    is.seek(sp);
                    byte[] buf = new byte[6];
                    is.read(buf);
                    sb.append(new String(buf).trim()).append(' ');
                }
            }
        } catch (IOException e) {
            //
        } finally {
            try {
                if (null != is) is.close();
            } catch (IOException e) {
                //
            }
        }
        return sb.toString().trim();
    }
}
