package com.gx.code.utils.html.listener;

import java.util.Enumeration;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTML.Attribute;
import javax.swing.text.html.HTMLEditorKit;

/**
 * 
 * HTML parsing proceeds by calling a callback for each and every piece of the HTML do*****ent. 
 * This simple callback class simply prints an indented structural listing of the HTML data.
 */

public class CustomizedHTMLParseListener extends HTMLEditorKit.ParserCallback {
	int indentSize = 0;
	

	protected void indent() {
		indentSize += 4;
	}

	protected void unIndent() {
		indentSize -= 4;
		if (indentSize < 0)
			indentSize = 0;
	}

	protected void pIndent() {
		for (int i = 0; i < indentSize; i++)
			System.out.print(" ");
	}

	public void handleText(char[] data, int pos) {
		pIndent();
		//System.out.println("Text(" + data.length + " chars)");
		System.out.println(new String(data));
	}

	public void handleComment(char[] data, int pos) {
		pIndent();
		//System.out.println("Comment(" + data.length + " chars)");
		System.out.println(new String(data));
	}

	public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos) {
		StringBuffer tag = new StringBuffer("");
		
		pIndent();
		tag.append("<").append(t.toString());
		Enumeration en = a.getAttributeNames();
		while (en.hasMoreElements()) {
			String attrName = null;
			String attrVal = null;
			
			Object elementName = en.nextElement();
			if (elementName instanceof Attribute) {
				attrName = ((Attribute) elementName).toString();
			} else if (elementName instanceof String)  {
				attrName = (String) elementName;
			} else if (elementName instanceof Boolean)  {
				attrName = ((Boolean) elementName).toString();
			} else {
				System.out.println("Name Go to die!!! > " + elementName.getClass());
			}
			
			Object elementVal = a.getAttribute(elementName);
			if (elementVal instanceof Attribute) {
				attrVal = ((Attribute) elementVal).toString();
			} else if (elementVal instanceof String)  {
				attrVal = (String) elementVal;
			} else if (elementVal instanceof Boolean)  {
				attrVal = ((Boolean) elementVal).toString();
			} else {
				System.out.println("Value Go to die!!! > " + elementVal.getClass());
			}
			tag.append(" ").append(attrName).append("=\"").append(attrVal).append("\"");
		}
		tag.append(">");
		System.out.println(tag);
		indent();
	}

	public void handleEndTag(HTML.Tag t, int pos) {
		unIndent();
		pIndent();
		System.out.println("</" + t.toString() + ">");
	}

	public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos) {
		StringBuffer tag = new StringBuffer("");
		
		pIndent();
		tag.append("<").append(t.toString());
		Enumeration<?> en = a.getAttributeNames();
		while (en.hasMoreElements()) {
			String attrName = null;
			String attrVal = null;
			
			Object elementName = en.nextElement();
			if (elementName instanceof Attribute) {
				attrName = ((Attribute) elementName).toString();
			} else if (elementName instanceof String)  {
				attrName = (String) elementName;
			} else if (elementName instanceof Boolean)  {
				attrName = ((Boolean) elementName).toString();
			} else {
				System.out.println("Name Go to die!!! > " + elementName.getClass());
			}
			
			Object elementVal = a.getAttribute(elementName);
			if (elementVal instanceof Attribute) {
				attrVal = ((Attribute) elementVal).toString();
			} else if (elementVal instanceof String)  {
				attrVal = (String) elementVal;
			} else if (elementVal instanceof Boolean)  {
				attrVal = ((Boolean) elementVal).toString();
			} else {
				System.out.println("Value Go to die!!! > " + elementVal.getClass());
			}
			tag.append(" ").append(attrName).append("=\"").append(attrVal).append("\"");
		}
		tag.append(">");
		System.out.println(tag);
	}

	public void handleError(String errorMsg, int pos) {
		//System.out.println("Parsing error: " + errorMsg + " at " + pos);
	}

}
