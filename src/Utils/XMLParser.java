/**
 * 
 * Workshop Learning & Coding Theory Exercise
 * TAU, Spring Semester 2010
 * Elizabeth Firman and Ariel Stolerman
 * 
 * Filename: XMLParser.java
 * Description: code for class XMLParser, a parser for an XML file input describing a function
 * 				for the SFT algorithm
 * 
 */

package Utils;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import SFT.*;

public class XMLParser extends DefaultHandler{

	/**
	 * Main parsing procedure
	 * parses the input XML file
	 */
	private void parseDocument() {

		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();
		try {

			//get a new instance of parser
			SAXParser sp = spf.newSAXParser();

			//parse the file and also register this class for call backs
			sp.parse(Query.getXmlFile(), this);

		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	
	// Event handlers
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		//TODO
		if(qName.equalsIgnoreCase("functions")) {
			// set runid
			Query.setRunId(attributes.getValue(0));
			// initialize polynomials map
			Query.setPolynomials(new HashMap<String,Polynomial>());
		}
		
		if(qName.equalsIgnoreCase("function")){
			// if this function is the one we're looking for, or we're in random mode, get it
			if (Query.getRunId().equals("random") || Query.getRunId().equals(attributes.getValue(0))){
				Polynomial poly = new Polynomial()
			}
		}
		
		if(qName.equalsIgnoreCase("term")){
			
		}
		
		if(qName.equalsIgnoreCase("rcoeff")){
			
		}
		
		if(qName.equalsIgnoreCase("icoeff")){
			
		}
		
		if(qName.equalsIgnoreCase("exp")){
			
		}
	}

	
	/**
	 * main for debugging
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
