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
		FUNCTIONS,FUNCTION,TERM,ALPHA,RECOEFF,IMCOEFF,END;
	}

	private Tag currTag;
	private String runId;
	private String funcId = null;
	private double recoeff, imcoeff;
	private Elem alpha;
	
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
			// check if valid, else throw exception
			try{
				Integer.parseInt(runId);
			} catch (NumberFormatException nfe){
				if (!runId.equalsIgnoreCase("random"))
					throw new SAXException("functions opening tag has invalid runid (not \"random\" or an integer): "+runId);
			}
			
			// initialize polynomials map
			Query.setPolynomials(new HashMap<String,Polynomial>());
			// set current tag
			currTag = Tag.FUNCTIONS;
			
			Debug.log("<functions runid=\""+runId+"\">");
		}
		
		else if(qName.equalsIgnoreCase("function")){
			String funcId = attributes.getValue(0);
			// check if valid, else throw exception
			try{
				Integer.parseInt(funcId);
			} catch (NumberFormatException nfe){
				throw new SAXException("function opening tag has invalid id (not an integer): "+funcId);
			}
			
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
		
		else if(qName.equalsIgnoreCase("term")){
			// do nothing
			// set current tag
			currTag = Tag.TERM;
			
			Debug.log("\t\t<term>");
		}
		
		else if(qName.equalsIgnoreCase("alpha")){
			// set current tag
			currTag = Tag.ALPHA;
			
			Debug.log("\t\t\t<alpha>");
		}
		
		else if(qName.equalsIgnoreCase("reCoeff")){
			// set current tag
			currTag = Tag.RECOEFF;
			
			Debug.log("\t\t\t<reCoeff>");
		}
		
		else if(qName.equalsIgnoreCase("imCoeff")){
			// set current tag
			currTag = Tag.IMCOEFF;
			
			Debug.log("\t\t\t<imCoeff>");
		}
		
		// otherwise
		else {
			throw new SAXException("unrecognized opening tag: "+qName);
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
		
		else if(qName.equalsIgnoreCase("function")){
			funcId = null;
			// set current tag
			currTag = Tag.FUNCTIONS;
			
			Debug.log("\t</function>");
		}
		
		else if(qName.equalsIgnoreCase("term")){
			// add a new term to the current polynomial (only if needed)
			Polynomial p = Query.getPolynomials().get(funcId);
			if (p != null) p.addUpdateTerm(alpha, recoeff, imcoeff);
			
			// set current tag
			currTag = Tag.FUNCTION;
			
			Debug.log("\t\t</term>");
		}
		
		else if(qName.equalsIgnoreCase("alpha")){
			// set current tag
			currTag = Tag.TERM;
			
			Debug.log("\t\t\t</alpha>");
		}
		
		else if(qName.equalsIgnoreCase("reCoeff")){
			// set current tag
			currTag = Tag.TERM;
			
			Debug.log("\t\t\t</reCoeff>");
		}
		
		else if(qName.equalsIgnoreCase("imCoeff")){
			// set current tag
			currTag = Tag.TERM;
			
			Debug.log("\t\t\t</imCoeff>");
		}
		
		// otherwise
		else {
			throw new SAXException("unrecognized closing tag: "+qName);
		}
	}
	
	
	/**
	 * handler for text between open and close tags
	 */
	public void characters(char ch[], int start, int length) throws SAXException {
		String str = new String(ch, start, length);
		
		switch (currTag){
		case FUNCTIONS:
			// do nothing
			break;
		case FUNCTION:
			// do nothing
			break;
		case TERM:
			// do nothing
			break;
		case ALPHA:
			try{
				Long value = Long.parseLong(str);
				alpha = new Elem(value);
				Debug.log("\t\t\t\t"+str);
				if (alpha.getValue() < 0 || alpha.getValue() >= SFT.getN())
					throw new SAXException("alpha not in range [0,1,...,N-1]: "+(alpha==null?"null":alpha.getValue()));
			} catch (NumberFormatException nfe){
				throw new SAXException("alpha must be a number in range [0,1,...,N-1]: "+(alpha==null?"null":alpha.getValue()));
			}
			break;
		case RECOEFF:
			try{
				recoeff = Double.parseDouble(str);
				Debug.log("\t\t\t\t"+str);
			} catch (NumberFormatException nfe){
				throw new SAXException("reCoeff not a double");
			}
			break;
		case IMCOEFF:
			try{
				imcoeff = Double.parseDouble(str);
				Debug.log("\t\t\t\t"+str);
			} catch (NumberFormatException nfe){
				throw new SAXException("imCoeff not a double");
			}
			break;
		case END:
			throw new SAXException("XML parsing error");
		default:
			// do nothing
		}
	}


	
	/**
	 * main for debugging
	 * @param args
	 */
	public static void main(String[] args) {
		Query.setXmlFile(new File("d:\\tmp\\tmp.xml"));
		SFT.setN(10);
		XMLParser parser = new XMLParser();
		try{
			parser.parseDocument();
			for(Polynomial poly: Query.getPolynomials().values()){
				System.out.println(poly);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
