package com.gx.code.utils.html.encoder;

public class HtmlEncoderUtils {
	public static String htmlEncode(String content) {
		if (content == null)
			return "";

		String encodedContent = content;
		encodedContent = encodedContent.replace("&", "&amp;");
		encodedContent = encodedContent.replace("\"", "&quot;");
		encodedContent = encodedContent.replace("'", "&apos;");
		encodedContent = encodedContent.replace(" ", "&nbsp");
		encodedContent = encodedContent.replace("<", "&lt;");
		encodedContent = encodedContent.replace(">", "&gt;");

		return encodedContent;
	}
}
