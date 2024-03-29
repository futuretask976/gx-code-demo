package com.gx.code.utils.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import com.gx.code.utils.http.https.X509TrustManagerPassAllCersVerify;


public class HttpURLConnectionUtils {
    public static void main(String args[]) throws Exception {
        for (int i = 0; i < 10; i++) {
            ConcurrentThread t = new HttpURLConnectionUtils.ConcurrentThread();
            t.run();
        }
    }

    static class ConcurrentThread extends Thread {
        public void run() {
            try {
                HttpURLConnectionUtils.sendTest();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void sendTest() throws Exception {
        URL url = new URL("http://guimi.taobao.com/ajax/abnormalOrderHttpService/getNoDealData.do");
        //URL url = new URL("http://lcauto11.swg.usma.ibm.com/files/oauth/api/library/b0c64c2a-3a72-4ec4-8138-651a68fa87a1/document/922bc088-17d5-4d81-bb86-acf3a67095f1/media/212.jpg");


        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);

        if (httpURLConnection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(X509TrustManagerPassAllCersVerify.getSSLFactory());
            ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new TrustAnyHostnameVerifier());
        }

        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Authorization", "Bearer pgXq5UMHdn2veJVuieA0cBedh7S3dc3dnkWoo0qc");
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:34.0) Gecko/20100101 Firefox/34.0");
        httpURLConnection.setRequestProperty("X-shindig-dos", "on");

        httpURLConnection.connect();

//        String postBody = "";
//        OutputStream out = httpURLConnection.getOutputStream();
//        out.write(postBody.getBytes());
//        out.flush();
//        out.close();

        System.out.println("Response: " + httpURLConnection.getResponseCode() + ", " + httpURLConnection.getResponseMessage());

        InputStream instream = (InputStream) httpURLConnection.getInputStream();
        BufferedInputStream buf = new BufferedInputStream(instream);
        InputStreamReader isr = new InputStreamReader(buf);
        BufferedReader br = new BufferedReader(isr);
        String readLine = null;

        while ((readLine = br.readLine()) != null) {
            System.out.println(readLine);
        }

        br.close();
        instream.close();


        for (final Map.Entry<String, List<String>> entry : httpURLConnection.getHeaderFields().entrySet()) {
            final String requestKey = entry.getKey();
            for (final String value : entry.getValue()) {
                System.out.println("reponseKey = " + requestKey + ", value = " + value);
            }
        }
        httpURLConnection.disconnect();
//        for (final Map.Entry<String, List<String>> entry : httpURLConnection.getRequestProperties().entrySet()) {
//  	        final String requestKey = entry.getKey();
//  	        for (final String value : entry.getValue()) {
//  	    	    System.out.println("requestKey = " + requestKey + ", value = " + value);
//  	        }
//  	    }
    }

    public void sendDelete() throws Exception {
        this.enableFiddler(true);

        String realTarget = "http://jiaqinglaptop.ibm.com/sampleweb/xxx";
        String realTargetParam = URLEncoder.encode(realTarget, "UTF-8");
        URL url = new URL(
                "http://jiaqinglaptop.ibm.com:9080/cre/common/proxyt?url="
                        + realTargetParam);

        HttpURLConnection httpURLConnection = (HttpURLConnection) url
                .openConnection();
        httpURLConnection.setRequestMethod("DELETE");
        httpURLConnection.setDoOutput(false);
        httpURLConnection.setDoInput(true);

        // Common
        httpURLConnection.setRequestProperty("Accept", "*/*");
        httpURLConnection
                .setRequestProperty("Accept-Language",
                        "en-GB,en;q=0.8,en-US;q=0.6,ar;q=0.4,ru;q=0.2,de;q=0.2,ja;q=0.2,th;q=0.2");
        httpURLConnection.setRequestProperty("Refer", "");
        httpURLConnection
                .setRequestProperty("User-Agent",
                        "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:24.0) Gecko/20100101 Firefox/24.0");
        httpURLConnection.setRequestProperty("Via",
                "HTTP/1.1 srb79d.hursley.ibm.com:4790");

        // Only for SSL
        if (httpURLConnection instanceof HttpsURLConnection) {
            HttpsURLConnection https = (HttpsURLConnection) httpURLConnection;
            https.setSSLSocketFactory(X509TrustManagerPassAllCersVerify
                    .getSSLFactory());
            https.setHostnameVerifier(new TrustAnyHostnameVerifier());
        }

        httpURLConnection.connect();

        System.out.println(httpURLConnection.getResponseCode() + " = "
                + httpURLConnection.getResponseMessage());
        System.out.println(httpURLConnection.getHeaderFields());

        InputStream instream = (InputStream) httpURLConnection.getContent();
        BufferedInputStream buf = new BufferedInputStream(instream);
        InputStreamReader isr = new InputStreamReader(buf);
        BufferedReader br = new BufferedReader(isr);
        String readLine = null;

        while ((readLine = br.readLine()) != null) {
            System.out.println(readLine);
        }

        br.close();
        instream.close();
    }

    public static void sendGet() throws Exception {
        // this.enableFiddler(true);

        URL url = new URL("http://localhost:8080/cre/gadgets/js/cre.util.object.js?debug=1&c=1&container=default");

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setDoOutput(false);
        httpURLConnection.setDoInput(true);

        // Common
        httpURLConnection.setRequestProperty("Accept", "*/*");
        httpURLConnection.setRequestProperty("Accept-Language", "en-GB,en;q=0.8,en-US;q=0.6,ar;q=0.4,ru;q=0.2,de;q=0.2,ja;q=0.2,th;q=0.2");
        httpURLConnection.setRequestProperty("Authorization", "*****");
        httpURLConnection.setRequestProperty("Client-Loaded-Features", "container::cre.commoncontainer::cre.iruntime::cre.iwidget::cre.iwidget.event::cre.iwidget.itemset::cre.oah::cre.service.event.autowire::cre.service.event.autowire.eventnamematching::cre.service.persistence.itemset::cre.util.json::cre.wire::pubsub-2::rpc");
        httpURLConnection.setRequestProperty("iv-creds", "Version=1, BAKs3DCCBSoMADCCBSQwggUgAgIGETBdMCowHgIELaYleAICNYkCAhHiAgIAjAICAIkEBgBQVpxEOwwId2FzYWRtaW4wLzAtMB4CBBtCKnwCAje4AgIR4gICAIwCAgCJBAYAUFacRDsMC3Bkd2FzLWFkbWluAgEBMIIEtjCCBLIwIgwUQVVUSEVOVElDQVRJT05fTEVWRUwwCjAIAgEEDAExBAAwMQwXQVpOX0NSRURfQVVUSE5NRUNIX0lORk8wFjAUAgEEDA1MREFQIFJlZ2lzdHJ5BAAwRAwSQVpOX0NSRURfQVVUSFpOX0lEMC4wLAIBBAwlY249d2FzYWRtaW4sb3U9Y29yZSxvPXNlcnZpY2VyZWdpc3RyeQQAMCkMFEFaTl9DUkVEX0FVVEhfTUVUSE9EMBEwDwIBBAwIcGFzc3dvcmQEADCBjwwVQVpOX0NSRURfQlJPV1NFUl9JTkZPMHYwdAIBBAxtTW96aWxsYS81LjAgKFdpbmRvd3MgTlQgNi4xOyBXT1c2NCkgQXBwbGVXZWJLaXQvNTM3LjM2IChLSFRNTCwgbGlrZSBHZWNrbykgQ2hyb21lLzMyLjAuMTcwMC4xMDcgU2FmYXJpLzUzNy4zNgQAMCcMD0FaTl9DUkVEX0dST1VQUzAUMBICAQQMC3Bkd2FzLWFkbWluBAAwSAwbQVpOX0NSRURfR1JPVVBfUkVHSVNUUllfSURTMCkwJwIBBAwgY249cGR3YXMtYWRtaW4sbz1zZXJ2aWNlcmVnaXN0cnkEADBFDBRBWk5fQ1JFRF9HUk9VUF9VVUlEUzAtMCsCAQQMJDFiNDIyYTdjLTM3YjgtMTFlMi04Yzg5LTAwNTA1NjljNDQzYgQAMCYMEkFaTl9DUkVEX0lQX0ZBTUlMWTAQMA4CAQQMB0FGX0lORVQEADApDBBBWk5fQ1JFRF9NRUNIX0lEMBUwEwIBBAwMSVZfTERBUF9WMy4wBAAwMwwcQVpOX0NSRURfTkVUV09SS19BRERSRVNTX0JJTjATMBECAQQMCjB4MDkxNGVjODMEADA1DBxBWk5fQ1JFRF9ORVRXT1JLX0FERFJFU1NfU1RSMBUwEwIBBAwMOS4yMC4yMzYuMTMxBAAwLQwZQVpOX0NSRURfUFJJTkNJUEFMX0RPTUFJTjAQMA4CAQQMB1NTQVRBTTIEADAsDBdBWk5fQ1JFRF9QUklOQ0lQQUxfTkFNRTARMA8CAQQMCHdhc2FkbWluBAAwSAwXQVpOX0NSRURfUFJJTkNJUEFMX1VVSUQwLTArAgEEDCQyZGE2MjU3OC0zNTg5LTExZTItOGM4OS0wMDUwNTY5YzQ0M2IEADAsDBFBWk5fQ1JFRF9RT1BfSU5GTzAXMBUCAQQMDlNTSzogVExTVjE6IDM1BAAwRgwUQVpOX0NSRURfUkVHSVNUUllfSUQwLjAsAgEEDCVjbj13YXNhZG1pbixvdT1jb3JlLG89c2VydmljZXJlZ2lzdHJ5BAAwHwwSQVpOX0NSRURfVVNFUl9JTkZPMAkwBwIBBAwABAAwJwwQQVpOX0NSRURfVkVSU0lPTjATMBECAQQMCjB4MDAwMDA2MTEEADAtDBh0YWd2YWx1ZV9sb2dpbl91c2VyX25hbWUwETAPAgEEDAh3YXNhZG1pbgQAMEcMFnRhZ3ZhbHVlX3Nlc3Npb25faW5kZXgwLTArAgEEDCQ0MTJhYjc2Yy05MjNmLTExZTMtYjU4MC0wMDUwNTY5YzQ0M2IEAA==");
        httpURLConnection.setRequestProperty("iv_server_name", "WebSEAL4-webseald-srb79d");
        httpURLConnection.setRequestProperty("Refer", "https://srb79d.hursley.ibm.com:4790/ServiceRegistryDashboard/");
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:24.0) Gecko/20100101 Firefox/24.0");
        httpURLConnection.setRequestProperty("Via", "HTTP/1.1 srb79d.hursley.ibm.com:4790");
        // httpURLConnection.setRequestProperty("X-Shindig-ST",
        // "default:w6SQUbLywfGUEhsG76c_GxyhOUTwcwcRxxbLwQT-UMyeYDTQmS1vtZhv8by9H11lc0jM5xGF9MMOY5pc76X18yR8n10IxO7EPIzQRzTjkeDqTwJ6");

        if (httpURLConnection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(X509TrustManagerPassAllCersVerify.getSSLFactory());
            ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new TrustAnyHostnameVerifier());
        }

        httpURLConnection.connect();

        System.out.println(httpURLConnection.getResponseCode() + " = "
                + httpURLConnection.getResponseMessage());
        System.out.println(httpURLConnection.getHeaderFields());

        InputStream instream = (InputStream) httpURLConnection.getContent();
        BufferedInputStream buf = new BufferedInputStream(instream);
        InputStreamReader isr = new InputStreamReader(buf);
        BufferedReader br = new BufferedReader(isr);
        String readLine = null;

        while ((readLine = br.readLine()) != null) {
            System.out.println(readLine);
        }

        br.close();
        instream.close();
    }

    public void sendPut() throws Exception {
        this.enableFiddler(true);

        URL url = new URL(
                "https://9.110.71.40:9443/ServiceRegistryDashboard/rest/resources/users/dGVzdDE~");
        // URL url = new
        // URL("https://9.125.92.132/ServiceRegistryDashboard/rest/resources/users/dGVzdDE~");

        HttpsURLConnection httpURLConnection = (HttpsURLConnection) url
                .openConnection();
        httpURLConnection.setRequestMethod("PUT");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);

        // Common
        httpURLConnection.setRequestProperty("Accept",
                "application/javascript, application/json");
        httpURLConnection
                .setRequestProperty("Accept-Encoding", "gzip, deflate");
        httpURLConnection.setRequestProperty("Accept-Language",
                "en-US,en;q=0.5");
        httpURLConnection.setRequestProperty("Connection", "keep-alive");
        httpURLConnection.setRequestProperty("Content-Type",
                "application/json; charset=UTF-8");
        // Only for SSL
        httpURLConnection.setSSLSocketFactory(X509TrustManagerPassAllCersVerify
                .getSSLFactory());
        ((HttpsURLConnection) httpURLConnection)
                .setHostnameVerifier(new TrustAnyHostnameVerifier());
        // Need to specify
        httpURLConnection.setRequestProperty("Host", "9.110.71.40:9443");
        httpURLConnection.setRequestProperty("Referer",
                "https://9.110.71.40:9443/ServiceRegistryDashboard/");
        httpURLConnection
                .setRequestProperty("User-Agent",
                        "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:24.0) Gecko/20100101 Firefox/24.0");
        httpURLConnection.setRequestProperty("X-Requested-With",
                "XMLHttpRequest");
        httpURLConnection
                .setRequestProperty(
                        "Cookie",
                        "JSESSIONID=00007c8N63f_xNqZDxtvtHyVCk4:-1; Ltpatoken2=hpoCj/7QjVvfeKyGKrN6KcLMUbCQ08bmkDm/jByqrM2zz3pov817801B5W7PIRAfAZZf7TXdhoL0Opkxlulpq29xdL6VaB9NgxqGnQqiAUMRBYqB5k8EApb5WqLr49ZLm48AC1j0cnN7xTIMPTewly21O2Owqk7FzrG/ZKqHrjzG2YDl8CPNDtGax2WDVhNO+dYSlppsXWeA8vj+UMi2JKKSbhN+y1dfDKJSm3sKLjghq13+zCGElkFecU0KZn5wthJC3as8Ll/KM8+nP8zdVSHYGQpYCpDojWuJdJ+wXCPsaDyKDABXM/XOy7+2zP9jUgmlpS6lRYfEACwfqS8DXDRpwEIWLaIBet6wGhEb3POwYiNLt+gDj89kDJHN0M8ntieJnWE6ySzm7UVogtZ7YZR3jkBDYhZdH7a8gUxVc5H80meT2hrgcdha85qlXHjs5K+K6bxDEUBZpCbryOxV59yhn890t2s5oTfMnqQzYUcT6JkaGt3EuW0Ih0drhFO24T0jRvQwhA0Y3YR28z3oqR4EgeMRlvJwVx9tdZuBh/XLRN5LKSLg/taLkHcaZTdY1pqVY7hl9mGCcFHjmyv/+EznLo7jEFCuVmoigFG/5eNhIyVebtzSJQ1y0L3MTZDwhYOxByN65BMEjrzuop78oXWy7nKfVBxq8xjikPHG+Qa4xB9djFmE3yOI1Kjx1A+5");
        httpURLConnection
                .setRequestProperty(
                        "X-WSRR-CSRF-Prevent",
                        "c6167d2c9331fed70b204b3beb68bdfae734ba091a0148d54d8df1d9837f47439701c6cf46d4e9568efbf2317e5bead2bce2ddf6ecaba9c5fdeda6eccc1b39396bfe7f9797b8cf34db2daf74fd2ed7de0ac65ef3c4aa1f87d0d0b2ec44c7fb8f8543f94bf4d63fdfbb4cffdebf3315087f6da4a8f8a62b6589e221ce60ca60cb");

        httpURLConnection.connect();

        OutputStream out = httpURLConnection.getOutputStream();
        out.write("{\"name\":\"test1\",\"lastviewedview\":\"BusinessView\",\"lastviewedpage\":\"P55b2e63e-d9e8-4d26-881f-22560ba05cb8\"}"
                .getBytes());
        out.flush();
        out.close();

        System.out.println(httpURLConnection.getResponseCode() + " = "
                + httpURLConnection.getResponseMessage());

        InputStream instream = (InputStream) httpURLConnection.getContent();
        BufferedInputStream buf = new BufferedInputStream(instream);
        InputStreamReader isr = new InputStreamReader(buf);
        BufferedReader br = new BufferedReader(isr);
        String readLine = null;

        while ((readLine = br.readLine()) != null) {
            System.out.println(readLine);
        }

        br.close();
        instream.close();
    }

    public static void sendPost() throws Exception {
        URL url = new URL("http://9.110.71.29:9080/sampleweb/sampleservlet?abc=def");

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);

        if (httpURLConnection instanceof HttpsURLConnection) {
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(X509TrustManagerPassAllCersVerify.getSSLFactory());
            ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(new TrustAnyHostnameVerifier());
        }

        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
        httpURLConnection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        httpURLConnection.setRequestProperty("Cache-Control", "no-cache");
        httpURLConnection.setRequestProperty("Connection", "keep-alive");
        //httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        httpURLConnection.setRequestProperty("Content-Type", "text/plain");
        httpURLConnection.setRequestProperty("Host", "9.110.71.29:9080");
        httpURLConnection.setRequestProperty("Pragma", "WWW-Authenticate=XHR, no-cache");
        httpURLConnection.setRequestProperty("Referer", "http://connectionscbx.tcsbpo.net/communities/service/html/communitystart?communityUuid=f0031dca-20ad-4101-a3b7-eaeae46720bf");
        httpURLConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");

        httpURLConnection.setRequestProperty("$WSCS", "AES128-SHA");
        httpURLConnection.setRequestProperty("$WSIS", "true");
        httpURLConnection.setRequestProperty("$WSSC", "https");
        httpURLConnection.setRequestProperty("$WSPR", "HTTP/1.1");
        httpURLConnection.setRequestProperty("$WSRA", "59.163.27.11");
        httpURLConnection.setRequestProperty("$WSRH", "59.163.27.11");
        httpURLConnection.setRequestProperty("$WSSN", "connectionscbx.tcsbpo.net");
        httpURLConnection.setRequestProperty("$WSSP", "443");
        httpURLConnection.setRequestProperty("$WSSI", "ICYAAJ5mY/mnFNyrQeLoZjIE8BJYWFhYozIkVAAACpM=");
        httpURLConnection.setRequestProperty("Surrogate-Capability", "WS-ESI=\"ESI/1.0+\"");
        httpURLConnection.setRequestProperty("$WSFO", "TRUE");
        httpURLConnection.setRequestProperty("_WS_HAPRT_WLMVERSION", "-1");
        httpURLConnection.setRequestProperty("Expect", "100-Continue");

        httpURLConnection.connect();

        String a = "a&isUnlisted=22false&name=Review%20Panel%20ICS5&owner=senthilkumar.r%40tcsbpo.net&callData=&isRestricted=false&originType=communityWidget&password=p%40ssw0rd&originId=8320f93f-55c6-4d79-bb67-31f6c5628e36";


        OutputStream out = httpURLConnection.getOutputStream();
        out.write(a.getBytes());
        out.flush();
        out.close();

        System.out.println("Response: " + httpURLConnection.getResponseCode() + ", " + httpURLConnection.getResponseMessage());

        // InputStream instream = (InputStream) httpURLConnection.getContent();
        // BufferedInputStream buf = new BufferedInputStream(instream);
        // InputStreamReader isr = new InputStreamReader(buf);
        // BufferedReader br = new BufferedReader(isr);
        // String readLine = null;
        //
        // while ((readLine = br.readLine()) != null) {
        // System.out.println(readLine);
        // }
        // instream.close();
    }

    public static class TrustAnyHostnameVerifier implements HostnameVerifier {
        public boolean verify(String hostname, SSLSession session) {
            // 直接返回true
            return true;
        }
    }

    public void enableFiddler(boolean opt) {
        if (opt) {
            System.setProperty("http.proxyHost", "127.0.0.1");
            System.setProperty("http.proxyPort", "8888");
            System.setProperty("https.proxyHost", "127.0.0.1");
            System.setProperty("https.proxyPort", "8888");
            System.out.println(System.getProperty("http.proxyHost"));
            System.out.println(System.getProperty("http.proxyPort"));
        }
    }
}
