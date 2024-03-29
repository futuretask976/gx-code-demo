package com.gx.code.utils.html;

import com.gx.code.utils.html.listener.CustomizedHTMLParseListener;

import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.*;
import javax.swing.text.html.*;
import javax.swing.text.html.parser.*;

/**
 * This small demo program shows how to use the
 * HTMLEditorKit.Parser and its implementing class
 * ParserDelegator in the Swing system.
 * 
 * Refer: http://www.oschina.net/code/snippet_12_226
 */

public class HtmlParseDemo {

	public static void main(String[] args) throws Exception {
		Reader reader = null;
//		if (args.length == 0) {
//			System.err.println("Usage: java HTMLParseDemo [url | file]");
//			System.exit(0);
//		}

		String spec = "http://www.sina.com.cn";//args[0];
		try {
			if (spec.indexOf("://") > 0) {
				URL url = new URL(spec);
				Object content = url.getContent();
				if (content instanceof InputStream) {
					reader = new InputStreamReader((InputStream) content, "GBK");
				} else if (content instanceof Reader) {
					reader = (Reader) content;
				} else {
					throw new Exception("Bad URL content type.");
				}
			} else {
				reader = new FileReader(spec);
			}
			HTMLEditorKit.Parser parser = new ParserDelegator();
			parser.parse(reader, new CustomizedHTMLParseListener(), true);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
	}

}
