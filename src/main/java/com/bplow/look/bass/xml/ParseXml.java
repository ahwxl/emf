package com.bplow.look.bass.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 
 * @author hongliang.dinghl SAX文档解析 解析xml文件
 */
public class ParseXml {
	public void createXml(String fileName) {
		System.out.println("<<" + fileName + ">>");
	}

	public void parserXml(String fileName) {
		SAXParserFactory saxfac = SAXParserFactory.newInstance();
		try {
			SAXParser saxparser = saxfac.newSAXParser();
			InputStream is = new FileInputStream(fileName);
			saxparser.parse(is, new MySAXHandler());
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void parserToDoc() throws ParserConfigurationException,
			SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(new File(
				"D:\\program\\tomcat-6.0.13_9191\\conf\\web.xml"));

		Element root = doc.getDocumentElement();
		System.out.println(root.getAttribute("version"));
		System.out.println(root.getNodeName());
	}

	public static void main(String[] args) {
		ParseXml pxml = new ParseXml();
		// pxml.parserXml("D:\\program\\tomcat-6.0.13_9191\\conf\\web.xml");

		try {
			pxml.parserToDoc();

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

class MySAXHandler extends DefaultHandler {
	boolean hasAttribute = false;
	Attributes attributes = null;

	public void startDocument() throws SAXException {
		System.out.println("开始");
	}

	public void endDocument() throws SAXException {
		System.out.println("结束");
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if (qName.equals("employees")) {
			return;
		}
		if (qName.equals("employee")) {
			System.out.println(qName);
		}
		if (attributes.getLength() > 0) {
			this.attributes = attributes;
			this.hasAttribute = true;
		}
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		if (hasAttribute && (attributes != null)) {
			for (int i = 0; i < attributes.getLength(); i++) {
				System.out.println(attributes.getQName(0)
						+ attributes.getValue(0));
			}
		}
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		System.out.println(new String(ch, start, length));
	}
}
