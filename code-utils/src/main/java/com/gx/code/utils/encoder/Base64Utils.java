package com.gx.code.utils.encoder;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Base64 utility class which can be utilized to encode and decode a String with
 * Base64 and a specific charsetName
 * 
 * @author kevin.zk
 *
 */
@Slf4j
public class Base64Utils {

    public static final String UTF8 = "UTF8";

    /**
     * Encode a given text using UTF-8 charset
     * 
     * @param text
     *            a given text
     * @return a result if successfully encoding text with charsetName; null if
     *         failed
     */
    public static String encodeUtf8(String text) {
        return encode(text, UTF8);
    }

    /**
     * Decode a given text using UTF-8 charset
     * 
     * @param text
     *            a given text
     * @return a result if successfully decoding text with charsetName; null if
     *         failed
     */
    public static String decodeUtf8(String text) {
        return decode(text, UTF8);
    }

    /**
     * Encode a given text in terms of charsetName
     * 
     * @param text
     *            input text to be encoded
     * @param charsetName
     *            with this specific charsetName, encode the input text
     * @return a result if successfully encoding text with charsetName; null if
     *         failed
     */
    public static String encode(String text, String charsetName) {
        try {
            return Base64.encodeBase64URLSafeString(text.getBytes(charsetName));
        } catch (UnsupportedEncodingException e) {
            log.error("Base64Util base64Encode failed", e);
        }
        return null;
    }

    /**
     * Decode a given text in terms of charsetName
     * 
     * @param text
     *            input text to be decoded
     * @param charsetName
     *            with this specific charsetName, decode the input text
     * @return a result if successfully decoding text with charsetName; null if
     *         failed
     */
    public static String decode(String text, String charsetName) {
        try {
            return new String(Base64.decodeBase64(text.getBytes(charsetName)), charsetName);
        } catch (UnsupportedEncodingException e) {
            log.error("Base64Util base64Encode failed", e);
        }
        return null;
    }

    /**
     * Encode with default system charset
     * 
     * @param text
     * @return
     */
    public static String encode(String text) {
        return encode(text, Charset.defaultCharset().name());
    }

    /**
     * Decode with default system charset
     * 
     * @param text
     * @return
     */
    public static String decode(String text) {
        return decode(text, Charset.defaultCharset().name());
    }

}