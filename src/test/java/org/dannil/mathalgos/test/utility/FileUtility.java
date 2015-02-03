package org.dannil.mathalgos.test.utility;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

public final class FileUtility {

	public static final String getPiXmlValue(final String filename, final String expression) {
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document doc = builder.parse(Thread.currentThread().getContextClassLoader().getResource("").getPath() + filename);

			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpath = xPathfactory.newXPath();
			XPathExpression expr = xpath.compile(expression);
			return (String) expr.evaluate(doc, XPathConstants.STRING);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
