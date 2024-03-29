package com.gx.code.utils.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.gx.code.utils.http.https.X509TrustManagerPassAllCersVerify;

public class HttpClientDemo {
	public static void main(String args[]) throws Exception {
		System.out.println(URLDecoder.decode(
		        "note=&soid&complaintType1Id=10&itemId=2100727532081&scenarioId=1&vouchers=%5B%7B%22code%22%3A%22textareaVoucher%22%2C%22userData%22%3A%7B%22value%22%3A%22adfafadfa%22%7D%7D%2C%7B%22code%22%3A%22uploadVoucher%22%2C%22userData%22%3A%7B%22value%22%3A%5B%7B%22name%22%3A%22complaintcenter%2Fa2a522e2-6149-455e-a338-bd4f0dfba726.jpg%22%2C%22url%22%3A%22http%3A%2F%2Fcomplaint-userdata.alicdn.com%2Fcomplaintcenter%2Fa2a522e2-6149-455e-a338-bd4f0dfba726.jpg%254095Q%257Cwatermark%253D2%2526text%253D5Li-5oql5Lit5b-D%2526type%253DZmFuZ3poZW5naGVpdGk%2526size%253D60%2526color%253DI0ZGRkZGRg%2526p%253D5%2526t%253D35%2526s%253D20%3FExpires%3D1449000701%26OSSAccessKeyId%3DtwjrtQ1f5qnFkzG5%26Signature%3D0iK9oYFFYqjxMehhnBveZwOjKNo%253D%22%2C%22thumbUrl%22%3A%22http%3A%2F%2Fcomplaint-userdata.alicdn.com%2Fcomplaintcenter%2Fa2a522e2-6149-455e-a338-bd4f0dfba726.jpg%254095Q_160w_1l%3FExpires%3D1449000701%26OSSAccessKeyId%3DtwjrtQ1f5qnFkzG5%26Signature%3DVoWKqQkv7SRzQ73QUbpP2FriNiw%253D%22%7D%2C%7B%22name%22%3A%22complaintcenter%2F1e8e0f89-8cb2-48ec-a11f-a80150284c72.jpg%22%2C%22url%22%3A%22http%3A%2F%2Fcomplaint-userdata.alicdn.com%2Fcomplaintcenter%2F1e8e0f89-8cb2-48ec-a11f-a80150284c72.jpg%254095Q%257Cwatermark%253D2%2526text%253D5Li-5oql5Lit5b-D%2526type%253DZmFuZ3poZW5naGVpdGk%2526size%253D60%2526color%253DI0ZGRkZGRg%2526p%253D5%2526t%253D35%2526s%253D20%3FExpires%3D1449000702%26OSSAccessKeyId%3DtwjrtQ1f5qnFkzG5%26Signature%3DKdipL4z0ydt9NULPoy8SImRwkbs%253D%22%2C%22thumbUrl%22%3A%22http%3A%2F%2Fcomplaint-userdata.alicdn.com%2Fcomplaintcenter%2F1e8e0f89-8cb2-48ec-a11f-a80150284c72.jpg%254095Q_160w_1l%3FExpires%3D1449000702%26OSSAccessKeyId%3DtwjrtQ1f5qnFkzG5%26Signature%3DweHtR%252B2en3gcn4MWYHLdc8OiMa4%253D%22%7D%5D%7D%7D%2C%7B%22code%22%3A%22inputlistVoucher%22%2C%22userData%22%3A%7B%22value%22%3A%5B%22333333333%22%2C%22444444444444%22%5D%7D%7D%5D",
		        "UTF-8"));
		
		HttpClientDemo httpClient = new HttpClientDemo();
		httpClient.testSubmitComplaint();
	}
	
	public void testSubmitComplaint() throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://jubao.daily.taobao.net/ajax/complaintHttpService/SubmitComplaint.do");
        HttpEntity requestEntity = new StringEntity(
                "note=&soid&complaintType1Id=10&itemId=2100727532081&scenarioId=1&vouchers=[{\"code\":\"textareaVoucher\",\"userData\":{\"value\":\"adfafadfa\"}},{\"code\":\"uploadVoucher\",\"userData\":{\"value\":[{\"name\":\"complaintcenter/a2a522e2-6149-455e-a338-bd4f0dfba726.jpg\",\"url\":\"http://complaint-userdata.alicdn.com/complaintcenter/a2a522e2-6149-455e-a338-bd4f0dfba726.jpg%4095Q%7Cwatermark%3D2%26text%3D5Li-5oql5Lit5b-D%26type%3DZmFuZ3poZW5naGVpdGk%26size%3D60%26color%3DI0ZGRkZGRg%26p%3D5%26t%3D35%26s%3D20?Expires=1449000701&OSSAccessKeyId=twjrtQ1f5qnFkzG5&Signature=0iK9oYFFYqjxMehhnBveZwOjKNo%3D\",\"thumbUrl\":\"http://complaint-userdata.alicdn.com/complaintcenter/a2a522e2-6149-455e-a338-bd4f0dfba726.jpg%4095Q_160w_1l?Expires=1449000701&OSSAccessKeyId=twjrtQ1f5qnFkzG5&Signature=VoWKqQkv7SRzQ73QUbpP2FriNiw%3D\"},{\"name\":\"complaintcenter/1e8e0f89-8cb2-48ec-a11f-a80150284c72.jpg\",\"url\":\"http://complaint-userdata.alicdn.com/complaintcenter/1e8e0f89-8cb2-48ec-a11f-a80150284c72.jpg%4095Q%7Cwatermark%3D2%26text%3D5Li-5oql5Lit5b-D%26type%3DZmFuZ3poZW5naGVpdGk%26size%3D60%26color%3DI0ZGRkZGRg%26p%3D5%26t%3D35%26s%3D20?Expires=1449000702&OSSAccessKeyId=twjrtQ1f5qnFkzG5&Signature=KdipL4z0ydt9NULPoy8SImRwkbs%3D\",\"thumbUrl\":\"http://complaint-userdata.alicdn.com/complaintcenter/1e8e0f89-8cb2-48ec-a11f-a80150284c72.jpg%4095Q_160w_1l?Expires=1449000702&OSSAccessKeyId=twjrtQ1f5qnFkzG5&Signature=weHtR%2B2en3gcn4MWYHLdc8OiMa4%3D\"}]}},{\"code\":\"inputlistVoucher\",\"userData\":{\"value\":[\"333333333\",\"444444444444\"]}}]");
        httpPost.setEntity(requestEntity);
        httpPost.getParams().setIntParameter("complaintType1Id", 10);
        httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
        httpPost.setHeader("Cookie", "v=0; _tb_token_=dHT9norOjDVX39; hid=2065658231; uc3=nk2=gOozdgTB7IS8o88%3D&id2=3HRdGAFgXHPI6A%3D%3D&vt3=mtZrlw1ZlqYc7Nu20S4%3D&lg2=3g62r0GuoFsD%2Bw%3D%3D; lastgetwwmsg=MTQ0ODk1NzEwMw%3D%3D; lgc=newshop0056; tracknick=newshop0056; sg=611; _cc_=3CUF6DqJPug%3D; tg=0; _l_g_=3w%3D%3D; uc1=cookie14=36wM6X2246L%2Fwg%3D%3D&cookie16=3CUF6DqJcCN6%2BA7pPvM%3D&existShop=false&cookie21=2LBjEixMtzI59MARPci53A%3D%3D&tag=0&cookie15=3czDlEdPVGcyXA%3D%3D&pas=0; cookie2=1cfe844e69cee4ffb080829f8bcaa80a; cookie1=2OjJZW6vCxm87hgFGyLNfNdP1bgx%2BuVIVHZGmqyPmEA%3D; mt=ci=0_1; unb=2065658231; t=fa4955c6b802dc00f228dc0cf35789b5; _nk_=newshop0056; cookie17=3HRdGAFgXHPI6A%3D%3D; thw=cn; isg=23364EFAC1EB9CAFB0110DC1648F7D4C; l=And3HA3JPcrR5z/CuVRVK8vsh2XARkuU");

        HttpResponse response = httpclient.execute(httpPost);
        int status = response.getStatusLine().getStatusCode();
        System.out.println("status: " + status);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream instream = entity.getContent();
            BufferedInputStream buf = new BufferedInputStream(instream);
            InputStreamReader isr = new InputStreamReader(buf);
            BufferedReader br = new BufferedReader(isr);
            String readLine = null;

            BufferedWriter bw = null;
            bw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream("D:\\response.txt", false), "UTF-8"));
            while ((readLine = br.readLine()) != null) {
                bw.write(readLine);
                bw.write("\n");
            }
            bw.flush();
            bw.close();
        }
    }
	
	public void testHTTPS() throws Exception {
		ClientConnectionManager connManager = this.getConnManager4SSL();
		HttpClient httpclient = new DefaultHttpClient(connManager);
		HttpGet httpGet = new HttpGet("https://www.verisign.com/");
		
		try {
			HttpResponse response = httpclient.execute(httpGet);
			int status = response.getStatusLine().getStatusCode();
			System.out.println("status: " + status);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				BufferedInputStream buf = new BufferedInputStream(instream);
				InputStreamReader isr = new InputStreamReader(buf);
				BufferedReader br = new BufferedReader(isr);
				String readLine = null;

				BufferedWriter bw = null;
				bw = new BufferedWriter(new OutputStreamWriter(
				        new FileOutputStream("e:\\response.html", false), "UTF-8"));
				while ((readLine = br.readLine()) != null) {
					bw.write(readLine);
					bw.write("\n");
				}
				bw.flush();
				bw.close();
			}
		} finally {
			httpGet.releaseConnection();
		}
	}

	public void testIFR() throws Exception {
		HttpClient httpclient = new DefaultHttpClient();

		HttpGet httpGet = new HttpGet(
		        "http://localhost:8080/cre/gadgets/ifr?url=http%3A%2F%2Flocalhost%3A8080%2Fproductserver%2Fgadgets%2Fembedded-experience%2FEEHelloWorld.xml&container=default&view=embedded&lang=en&country=US&debug=0&nocache=0&sanitize=0&v=062f9205bfe19d20a52b466de18ba1d0&st=default%3As7pRNcrcgmK8dcAfS5Ffa_ieeAyNo-He-n_rPdpY7sNIDuN9gWOAGVcp7RRwC-L9AxktCLmSYN-H-M_dVRhLCIf7oAYRve6g2W1uvxAl2McnuzjUjThI9wAhmj1bK3ivqpwGaMiW5BV3Iek3J5zXBEtSeSP0aDQ4JOxsEVrm_K9YkS751SEOvl6ZlKySKiF6N86MvMKdiB25u6Dsri4pTPfI0dHJr9PBYtS8TdJhtM5Zi_FpgmzQImD1gPgfP-61t1FoOAVaAebFJRLyaYzZGC55pm0&testmode=0&parent=http%3A%2F%2Flocalhost%3A8080&mid=0#up_pref1=value1&up_pref2=value2&view-params=%7B%22viewkey1%22%3A%22viewvalue1%22%7D&embeddedlevel=0");
		httpGet.setHeader("Content-Type", "application/json;charset=UTF-8");

		HttpResponse response = httpclient.execute(httpGet);
		int status = response.getStatusLine().getStatusCode();
		System.out.println("status: " + status);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instream = entity.getContent();
			BufferedInputStream buf = new BufferedInputStream(instream);
			InputStreamReader isr = new InputStreamReader(buf);
			BufferedReader br = new BufferedReader(isr);
			String readLine = null;

			BufferedWriter bw = null;
			bw = new BufferedWriter(new OutputStreamWriter(
			        new FileOutputStream("e:\\response.txt", false), "UTF-8"));
			while ((readLine = br.readLine()) != null) {
				bw.write(readLine);
				bw.write("\n");
			}
			bw.flush();
			bw.close();
		}
	}

	public void testRPC() throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		// HttpGet httpget = new
		// HttpGet("http://localhost:8080/cre/gadgets/js/cre.iruntime:cre.iwidget:cre.iwidget.itemset:cre.util.json:cre.wire:cre.iwidget.event:cre.osgadget.js?debug=1&c=1");
		// cre.wire
		HttpPost httpPost = new HttpPost("http://localhost:8080/cre/rpc");
		HttpEntity requestEntity = new StringEntity(
		        "[{\"method\":\"gadgets.metadata\",\"id\":\"gadgets.metadata\",\"params\":{\"container\":\"default\",\"ids\":[\"http://localhost:8080/productserver/gadgets/embedded-experience/EEHelloWorld.xml\"],\"fields\":[\"iframeUrls\",\"modulePrefs.*\",\"needsTokenRefresh\",\"userPrefs.*\",\"views.preferredHeight\",\"views.preferredWidth\",\"expireTimeMs\",\"responseTimeMs\",\"rpcServiceIds\",\"tokenTTL\"],\"language\":\"en\",\"country\":\"US\",\"userId\":\"@viewer\",\"groupId\":\"@self\"}}]");
		httpPost.setEntity(requestEntity);
		httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");

		HttpResponse response = httpclient.execute(httpPost);
		int status = response.getStatusLine().getStatusCode();
		System.out.println("status: " + status);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instream = entity.getContent();
			BufferedInputStream buf = new BufferedInputStream(instream);
			InputStreamReader isr = new InputStreamReader(buf);
			BufferedReader br = new BufferedReader(isr);
			String readLine = null;

			BufferedWriter bw = null;
			bw = new BufferedWriter(new OutputStreamWriter(
			        new FileOutputStream("e:\\response.txt", false), "UTF-8"));
			while ((readLine = br.readLine()) != null) {
				bw.write(readLine);
				bw.write("\n");
			}
			bw.flush();
			bw.close();
		}
	}

	public void test2() throws Exception {
		HttpClient httpclient = new DefaultHttpClient();
		// HttpGet httpget = new
		// HttpGet("http://localhost:8080/cre/gadgets/js/cre.iruntime:cre.iwidget:cre.iwidget.itemset:cre.util.json:cre.wire:cre.iwidget.event:cre.osgadget.js?debug=1&c=1");
		// cre.wire
		HttpGet httpget = new HttpGet(
		        "http://localhost:8080/cre/gadgets/js/cre.iruntime:cre.iwidget:cre.iwidget.itemset:cre.util.json:cre.wire:cre.iwidget.event:cre.osgadget.js?debug=1&c=1");

		HttpResponse response = httpclient.execute(httpget);
		int status = response.getStatusLine().getStatusCode();
		System.out.println("status: " + status);
		HttpEntity entity = response.getEntity();
		if (entity != null) {
			InputStream instream = entity.getContent();
			BufferedInputStream buf = new BufferedInputStream(instream);
			InputStreamReader isr = new InputStreamReader(buf);
			BufferedReader br = new BufferedReader(isr);
			String readLine = null;

			BufferedWriter bw = null;
			bw = new BufferedWriter(new OutputStreamWriter(
			        new FileOutputStream("e:\\badJs.txt", false), "UTF-8"));
			while ((readLine = br.readLine()) != null) {
				bw.write(readLine);
				bw.write("\n");
			}
			bw.flush();
			bw.close();
		}
	}

	public void test1() throws Exception {
		// scanFolder();
		List<String> compositions = getJsCompositions(readJsNames());

		// compositions.clear();
		// compositions.add("cre.commoncontainer:cre.config:cre.constants:cre.constants.services:cre.cookie:cre.endpoint:cre.globals:cre.iruntime:cre.iwidget:cre.iwidget.event:cre.iwidget.itemset:cre.localehelper:cre.messages.ar:cre.messages.ca:cre.messages.cs:cre.messages.da:cre.messages.de:cre.messages.el:cre.messages.en:cre.messages.es:cre.messages.fi:cre.messages.fr:cre.messages.hu:cre.messages.it:cre.messages.iw:cre.messages.ja:cre.messages.kk:cre.messages.ko:cre.messages.nl:cre.messages.no:cre.messages.pl:cre.messages.pt:cre.messages.pt-br:cre.messages.ru:cre.messages.sl:cre.messages.sv:cre.messages.th:cre.messages.zh-cn");

		int sucReqCount = 0;
		int compositionSize = compositions.size();
		System.out.println("" + compositions.size());
		for (int i = 0; i < compositionSize; i++) {
			StringBuffer targetUrl = new StringBuffer();

			targetUrl.append("http://localhost:8080/cre/gadgets/js/")
			        .append(compositions.get(i))
			        .append(".js?debug=0&c=1&container=default");
			if (targetUrl.length() > 2048) {
				continue;
			}
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(targetUrl.toString());
			// HttpGet httpget = new
			// HttpGet("http://localhost/cre/gadgets/js/abc:def:ghi.js?debug=0&c=1&container=default");

			// Test.pr("Begin Request.");
			HttpResponse response = httpclient.execute(httpget);
			int status = response.getStatusLine().getStatusCode();
			if (status == 200) {
				sucReqCount++;
			} else {
				System.out.println("seq " + i + " : " + response.getStatusLine() + ", "
				        + compositions.get(i));
				break;
			}
			System.out.println("seq " + i + " : " + response.getStatusLine() + ", "
			        + compositions.get(i));
			// Test.pr("End Request.");

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				BufferedInputStream buf = new BufferedInputStream(instream);
				InputStreamReader isr = new InputStreamReader(buf);
				BufferedReader br = new BufferedReader(isr);
				String readLine = null;
				while ((readLine = br.readLine()) != null) {
					// Test.pr(readLine);
				}
			}
		}
		System.out.println("sucReqCount " + sucReqCount);
	}

	public List<String> getJsCompositions(List<String> jsNames)
	        throws Exception {
		List<String> jsCompositions = new ArrayList<String>();

		int maxCount = 10000;
		int totalCount = 0;
		int jsNamesSize = jsNames.size();
		for (int i = 0; i < jsNamesSize; i++) {
			String jsNameOuter = jsNames.get(i);
			if (jsNameOuter.startsWith("cre.messages")) {
				continue;
			}
			StringBuffer compo = new StringBuffer(jsNameOuter);
			jsCompositions.add(compo.toString());
			totalCount++;
			if (totalCount > maxCount) {
				break;
			}

			int j = i + 1;
			while (j < jsNamesSize) {
				String jsNameInner = jsNames.get(j);
				if (jsNameInner.startsWith("cre.messages")) {
					j++;
					continue;
				}
				compo.append(":").append(jsNameInner);
				jsCompositions.add(compo.toString());
				j++;
				totalCount++;
				if (totalCount > maxCount) {
					break;
				}
			}
		}

		return jsCompositions;
	}

	public static List<String> readJsNames() throws Exception {
		List<String> jsNames = new ArrayList<String>();

		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
			        "C:\\Kevin\\featureXml.txt"), "UTF-8"));

			String line = null;
			while ((line = br.readLine()) != null) {
				if (!line.trim().equals("")) {
					jsNames.add(line);
				}
			}
		} catch (IOException e) {
			if (br != null) {
				br.close();
			}
		}
		return jsNames;
	}

	public static void scanFolder() throws Exception {
		List<String> featureXmlList = new ArrayList<String>();
		findFiles("C:\\Kevin\\VulcanWorkspace\\CRE12_Build", featureXmlList);
		System.out.println("Scan finished.");

		BufferedWriter bw = null;
		try {
			File file = new File("C:\\Kevin\\featureXml.txt");
			if (!file.exists()) {
				file.createNewFile();
			}

			bw = new BufferedWriter(new OutputStreamWriter(
			        new FileOutputStream("C:\\Kevin\\featureXml.txt", true),
			        "UTF-8"));

			for (int i = 0; i < featureXmlList.size(); i++) {
				String jsName = parseFeatureXml(featureXmlList.get(i));
				if (jsName != null) {
					bw.write(jsName);
					bw.write("\n");
				}
			}
			bw.flush();
		} catch (IOException e) {
			if (bw != null) {
				bw.close();
			}
		}
	}

	public static void findFiles(String rootPath, List<String> fileList) {
		File root = new File(rootPath);
		File[] subFiles = root.listFiles();
		for (int i = 0; i < subFiles.length; i++) {
			File subFile = subFiles[i];
			if (subFile.isDirectory()) {
				String subFileName = subFile.getName();
				if (!subFileName.equals("CRE Proxy")
				        && !subFileName.equals(".jazzShed")) {
					findFiles(subFile.getAbsolutePath(), fileList);
				}
			} else {
				if (subFile.getName().equals("feature.xml")) {
					fileList.add(subFile.getAbsolutePath());
				}
			}
		}
	}

	public static String parseFeatureXml(String xmlFilePath) throws Exception {
		DocumentBuilderFactory domfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder dombuilder = domfac.newDocumentBuilder();
		InputStream is = new FileInputStream(xmlFilePath);
		Document doc = dombuilder.parse(is);

		Element root = doc.getDocumentElement();
		NodeList subNodes = root.getChildNodes();

		if (subNodes != null) {
			for (int i = 0; i < subNodes.getLength(); i++) {
				Node subNode = subNodes.item(i);

				if (subNode.getNodeType() == Node.ELEMENT_NODE
				        && subNode.getNodeName().equals("name")) {
					return subNode.getChildNodes().item(0).getTextContent();
				}
			}
		}
		return null;
	}
	
	public static ClientConnectionManager getConnManager4SSL() throws Exception {
		X509TrustManager tm = new X509TrustManagerPassAllCersVerify();
    	
        SSLContext sslLContext = SSLContext.getInstance("TLS");
        sslLContext.init(null, new TrustManager[] { tm }, null);
        SSLSocketFactory ssf = new SSLSocketFactory(sslLContext, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("https", 443, ssf));
        ClientConnectionManager mgr = new ThreadSafeClientConnManager(registry);
        
        return mgr;
    }	
}
