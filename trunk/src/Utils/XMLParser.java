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
	public void parseDocument() throws Exception {
		
		Debug.log("XMLParser -> parseDocument started");

		//get a factory
		SAXParserFactory spf = SAXParserFactory.newInstance();

		//get a new instance of parser
		SAXParser sp = spf.newSAXParser();

		//parse the file and also register this class for call backs
		sp.parse(Query.getXmlFile(), this);
		
		/*try {

		}catch(SAXException se) {
			se.printStackTrace();
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch (IOException ie) {
			ie.printStackTrace();
		}*/
		
		Debug.log("XMLParser -> parseDocument completed");
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
			
			Debug.log("<functions runid=\""+runId+"\">");
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
			
			Debug.log("\t<function id=\""+funcId+"\">");
		}
		
		if(qName.equalsIgnoreCase("term")){
			// do nothing
			// set current tag
			currTag = Tag.TERM;
			
			Debug.log("\t\t<term>");
		}
		
		if(qName.equalsIgnoreCase("rcoeff")){
			// set current tag
			currTag = Tag.RCOEFF;
			
			Debug.log("\t\t\t<rcoeff>");
		}
		
		if(qName.equalsIgnoreCase("icoeff")){
			// set current tag
			currTag = Tag.ICOEFF;
			
			Debug.log("\t\t\t<icoeff>");
		}
		
		if(qName.equalsIgnoreCase("exp")){
			// set current tag
			currTag = Tag.EXP;
			
			Debug.log("\t\t\t<exp>");
		}
	}
	
	/**
	 * handlers for end tags
	 */
	public void endElement(String uri, String localName,String qName) throws SAXException {
		
		if(qName.equalsIgnoreCase("functions")) {
			// set current tag
			currTag = Tag.END;
			
			Debug.log("</functions>");
		}
		
		if(qName.equalsIgnoreCase("function")){
			funcId = null;
			// set current tag
			currTag = Tag.FUNCTIONS;
			
			Debug.log("\t</function>");
		}
		
		if(qName.equalsIgnoreCase("term")){
			// add a new term to the current polynomial (only if needed)
			Polynomial p = Query.getPolynomials().get(funcId);
			if (p != null) p.addUpdateTerm(exp, rcoeff, icoeff);
			
			// set current tag
			currTag = Tag.FUNCTION;
			
			Debug.log("\t\t</term>");
		}
		
		if(qName.equalsIgnoreCase("rcoeff")){
			// set current tag
			currTag = Tag.TERM;
			
			Debug.log("\t\t\t</rcoeff>");
		}
		
		if(qName.equalsIgnoreCase("icoeff")){
			// set current tag
			currTag = Tag.TERM;
			
			Debug.log("\t\t\t</icoeff>");
		}
		
		if(qName.equalsIgnoreCase("exp")){
			// set current tag
			currTag = Tag.TERM;
			
			Debug.log("\t\t\t</exp>");
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
				Debug.log("\t\t\t\t"+str);
			} catch (NumberFormatException nfe){
				//TODO add error message
			}
			break;
		case ICOEFF:
			try{
				icoeff = Double.parseDouble(str);
				Debug.log("\t\t\t\t"+str);
			} catch (NumberFormatException nfe){
				//TODO add error message
			}
			break;
		case EXP:
			try{
				exp = Integer.parseInt(str);
				Debug.log("\t\t\t\t"+str);
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
		try{
			parser.parseDocument();
			for(Polynomial poly: Query.getPolynomials().values()){
				System.out.println(poly);
			}
		} catch (Exception e) {
			System.err.println("parsing error");
		}
	}
}
