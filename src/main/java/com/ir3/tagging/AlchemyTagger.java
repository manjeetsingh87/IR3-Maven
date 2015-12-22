package com.ir3.tagging;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import api.AlchemyAPI;
public class AlchemyTagger {

	private static AlchemyAPI alchemyObj = AlchemyAPI.GetInstanceFromString("860f2a92d7f2d7fa145705776f8d2741d5344fe2");

	public static List<String> getConceptfromDocument(String text) {
		List<String> conceptList= new ArrayList<String>();
		Document doc;
		try {
			doc = alchemyObj.TextGetRankedConcepts(text);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("concept");
			
			for(int i=0;i<nodeList.getLength();i++){
				Node nNode = nodeList.item(i);
				if (nNode.getNodeType() == Node.ELEMENT_NODE){
					Element eElement = (Element) nNode;
					conceptList.add(eElement.getElementsByTagName("text").item(0).getTextContent());
				}
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return conceptList;
	}

	public static double getSentimentfromDocument(String text) {
		double sentiment= 0.0;
		Document doc;
		StringBuilder querySB = new StringBuilder();
		String formattedQuery;

		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c == '\\' || c == '+' || c == '-' || c == '!'  || c == '(' || c == ')' || c == ':'
					|| c == '^' || c == '[' || c == ']' || c == '\"' || c == '{' || c == '}' || c == '~'
					|| c == '*' || c == '?' || c == '|' || c == '&'  || c == ';' || c == '/'
					|| Character.isWhitespace(c)) {
				querySB.append('\\');
			}
			querySB.append(c);
		}
		formattedQuery = querySB.toString();
				
		try {
			doc = alchemyObj.TextGetTextSentiment(formattedQuery);
			doc.getDocumentElement().normalize();
			NodeList nodeList = doc.getElementsByTagName("docSentiment");
			Node nNode = nodeList.item(0);
			if (nNode.getNodeType() == Node.ELEMENT_NODE){
				Element eElement = (Element) nNode;
				if(null != eElement.getElementsByTagName("score").item(0) && null != eElement.getElementsByTagName("score").item(0).getTextContent())
					sentiment = Double.parseDouble((eElement.getElementsByTagName("score").item(0).getTextContent()));
				else
					sentiment = 0.0;
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return sentiment;
	}
	/*// utility method
	private static String getStringFromDocument(Document doc) {
		try {
			DOMSource domSource = new DOMSource(doc);
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);

			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			transformer.transform(domSource, result);

			return writer.toString();
		} catch (TransformerException ex) {
			ex.printStackTrace();
			return null;
		}
	}*/
}