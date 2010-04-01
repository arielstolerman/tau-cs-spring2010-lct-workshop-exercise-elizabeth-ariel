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

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import SFT.*;

public class XMLParser extends DefaultHandler{
	
	/**
	 * enum to indicate the current scope we are in
	 */
	public enum Tag{
		FUNCTIONS,FUNCTION,TERM,RCOEFF,ICOEFF,EXP,END;
	}

	private Tag currTag;
	private String runId;
	private String funcId = null;
	private double rcoeff, icoeff;
	private int exp;
	
	/**
	 * Main parsing procedure
	 * parses the input XML file
	 */
	public void parseDocument() {

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
	
	/**
	 * handlers for start tags
	 */
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if(qName.equalsIgnoreCase("functions")) {
			// set runid
			runId = attributes.getValue(0);
			// initialize polynomials map
			Query.setPolynomials(new HashMap<String,Polynomial>());
			// set current tag
			currTag = Tag.FUNCTIONS;
		}
		
		if(qName.equalsIgnoreCase("function")){
			String funcId = attributes.getValue(0);
			// if this function is the one we're looking for, or we're in random mode, get it
			if (runId.equalsIgnoreCase("random") || runId.equals(funcId)){
				Polynomial poly = new Polynomial(funcId);
				// add to the polynomials map
				Query.getPolynomials().put(funcId, poly);
				// set the current function that is parsed to this one
				this.funcId = funcId;
			}
			// set current tag
			currTag = Tag.FUNCTION;
		}
		
		if(qName.equalsIgnoreCase("term")){
			// do nothing
			// set current tag
			currTag = Tag.TERM;
		}
		
		if(qName.equalsIgnoreCase("rcoeff")){
			// set current tag
			currTag = Tag.RCOEFF;
		}
		
		if(qName.equalsIgnoreCase("icoeff")){
			// set current tag
			currTag = Tag.ICOEFF;
		}
		
		if(qName.equalsIgnoreCase("exp")){
			// set current tag
			currTag = Tag.EXP;
		}
	}
	
	/**
	 * handlers for end tags
	 */
	public void endElement(String uri, String localName,String qName) throws SAXException {
		if(qName.equalsIgnoreCase("functions")) {
			// set current tag
			currTag = Tag.END;
		}
		
		if(qName.equalsIgnoreCase("function")){
			funcId = null;
			// set current tag
			currTag = Tag.FUNCTIONS;
		}
		
		if(qName.equalsIgnoreCase("term")){
			// add a new term to the current polynomial
			Query.getPolynomials().get(funcId).addUpdateTerm(exp, rcoeff, icoeff);
			
			// set current tag
			currTag = Tag.FUNCTION;
		}
		
		if(qName.equalsIgnoreCase("rcoeff")){
			// set current tag
			currTag = Tag.TERM;
		}
		
		if(qName.equalsIgnoreCase("icoeff")){
			// set current tag
			currTag = Tag.TERM;
		}
		
		if(qName.equalsIgnoreCase("exp")){
			// set current tag
			currTag = Tag.TERM;
		}
	}
	
	
	/**
	 * handler for text between open and close tags
	 */
	public void characters(char ch[], int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		
		switch (currTag){
		case FUNCTIONS:
			//TODO add error message
			break;
		case FUNCTION:
			//TODO add error message
			break;
		case TERM:
			//TODO add error message
			break;
		case RCOEFF:
			try{
				rcoeff = Double.parseDouble(str);
			} catch (NumberFormatException nfe){
				//TODO add error message
			}
			break;
		case ICOEFF:
			try{
				icoeff = Double.parseDouble(str);
			} catch (NumberFormatException nfe){
				//TODO add error message
			}
			break;
		case EXP:
			try{
				exp = Integer.parseInt(str);
			} catch (NumberFormatException nfe){
				//TODO add error message
			}
			break;
		case END:
			//TODO add error message
			break;
		default:
			//TODO add error message
		}
	}


	
	/**
	 * main for debugging
	 * @param args
	 */
	public static void main(String[] args) {
		Query.setXmlFile(new File("d:\\tmp\\tmp.xml"));
		XMLParser parser = new XMLParser();
		parser.parseDocument();
		for(Polynomial poly: Query.getPolynomials().values()){
			System.out.println(poly);
		}
	}
}
