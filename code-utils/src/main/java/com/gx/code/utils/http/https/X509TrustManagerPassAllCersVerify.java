package com.gx.code.utils.http.https;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * For HttpsURLConnection
 * @author Administrator
 *
 */
public class X509TrustManagerPassAllCersVerify implements X509TrustManager {
    /*
     * The default X509TrustManager returned by SunX509.  We'll delegate
     * decisions to it, and fall back to the logic in this class if the
     * default X509TrustManager doesn't trust it.
     */
    X509TrustManager sunJSSEX509TrustManager;
    public X509TrustManagerPassAllCersVerify() throws Exception {
    }
    /*
     * Delegate to the default trust manager.
     */
    public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
    }
    /*
     * Delegate to the default trust manager.
     */
    public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
    }
    /*
     * Merely pass this through.
     */
    public X509Certificate[] getAcceptedIssuers() {
        return null;//sunJSSEX509TrustManager.getAcceptedIssuers();
    }
    
    public static SSLSocketFactory getSSLFactory() throws Exception {
    	// 创建SSLContext对象，并使用我们指定的信任管理器初始化
        TrustManager[] tm = { new X509TrustManagerPassAllCersVerify() };
        //SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, tm, new java.security.SecureRandom());
        // 从上述SSLContext对象中得到SSLSocketFactory对象
        SSLSocketFactory ssf = sslContext.getSocketFactory();

        return ssf;
    }
}