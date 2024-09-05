package com.gx.code.utils.http;

import org.apache.http.HttpVersion;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

public class HttpsClientDemo {
    public static void main(String args[]) throws Exception {
        HttpsClientDemo httpsClientDemo = new HttpsClientDemo();
        httpsClientDemo.testHttps4Teamachine();
    }
    public void testHttps4Teamachine() throws Exception {
        SSLContextBuilder builder = SSLContexts.custom().useProtocol("TLS");
//        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(),
//                SSLConnectionSocketFactory.getDefaultHostnameVerifier());
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build(),
                NoopHostnameVerifier.INSTANCE);

        CloseableHttpClient closeableHttpClient = HttpClients.custom()
                .setConnectionManagerShared(true)
                .setSSLSocketFactory(sslsf)
                .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build();

        HttpGet httpGet = new HttpGet("https://langtuo.cartisan.top:446/teamachinebackend/mqtt/test");
        // HttpGet httpGet = new HttpGet("https://47.102.144.19:446/teamachinebackend/mqtt/test");
        // HttpGet httpGet = new HttpGet("https://www.baidu.com");

        httpGet.setProtocolVersion(HttpVersion.HTTP_1_1);
        httpGet.setHeader("Accept", "application/json, text/plain, */*");
        httpGet.setHeader("Accept-Encoding", "gzip, deflate, br, zstd");
        httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en-US;q=0.8,en;q=0.7");
        httpGet.setHeader("Connection", "keep-alive");
        httpGet.setHeader("Host", "langtuo.cartisan.top:446");
        httpGet.setHeader("Origin", "http://localhost:3000");
        httpGet.setHeader("Referer", "http://localhost:3000/");
        httpGet.setHeader("Sec-Ch-Ua", "\"Chromium\";v=\"128\", \"Not;A=Brand\";v=\"24\", \"Google Chrome\";v=\"128\"");
        httpGet.setHeader("Sec-Ch-Ua-Mobile", "?0");
        httpGet.setHeader("Sec-Ch-Ua-Platform", "\"macOS\"");
        httpGet.setHeader("Sec-Fetch-Dest", "empty");
        httpGet.setHeader("Sec-Fetch-Mode", "cors");
        httpGet.setHeader("Sec-Fetch-Site", "cross-site");
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.0.0 Safari/537.36");

        for (int i = 0; i < 50; i++) {
            try {
                CloseableHttpResponse response = closeableHttpClient.execute(httpGet);
                try {
                    String result = EntityUtils.toString(response.getEntity());
                    System.out.println("$$$$$ result=" + result);
                } finally {
                    response.close();
                }
            } catch (Exception e) {
                System.out.println("$$$$$ httpsClientDemo|execute|fatal|" + e.getMessage());
            } finally {
                try {
                    closeableHttpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            Thread.sleep(1000);
        }
    }
}
