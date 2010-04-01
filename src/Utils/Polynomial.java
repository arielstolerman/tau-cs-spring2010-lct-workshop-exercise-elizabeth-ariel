

package Utils;

import java.util.*;
import SFT.Complex;
import SFT.Elem;

public class Polynomial {
	private Map<Integer,Complex> terms;
	private String id;
	
	/**
	 * default constructor
	 * @param id
	 */
	public Polynomial(String id){
		this.id = id;
		terms = new HashMap<Integer,Complex>();
	}
	
	// getters
	
	/**
	 * @param exp:	the exponent for which the coefficient is fetched
	 * @return:		the coefficient
	 */
	public Complex getCoeff(int exp){
		return terms.get(exp);
	}
	
	public String getId() {
		return id;
	}
	
	/**
	 * get the string representation of the polynomial
	 */
	public String toString(){
		String str = "";
		
		for (int exp: terms.keySet()){
			Complex coeff = terms.get(exp);
			str += "("+coeff.toString()+")*x^"+exp+" + ";
		}
		str = str.substring(0,str.length()-3);
		
		return str;
	}
	
	/**
	 * 
	 * @return:		the string representation in html code of the polynomial
	 */
	public String toHTMLString(){
String str = "<html>";
		
		for (int exp: terms.keySet()){
			Complex coeff = terms.get(exp);
			str += "("+coeff.toString()+")*x<sup>"+exp+"</sup> + ";
		}
		str = str.substring(0,str.length()-3) + "</html>";
		
		return str;
	}
	
	// setters
	
	/**
	 * adds the term to the polynomial
	 * if already exist, adds the coefficients of the exponent
	 * @param exp:	exponent
	 * @param re:	real part
	 * @param im:	imaginary part
	 */
	public void addUpdateTerm(int exp, double re, double im){
		Complex coeff = terms.get(exp);
		if (coeff == null){
			// create new entry
			coeff = new Complex(re,im);
			terms.put(exp,coeff);
		} else {
			// add to existing coefficient
			coeff.addComplex(re, im);
		}
	}
	
	// calculations
	
	/**
	 * @param x:	input for the polynomial p
	 * @return:		the complex value of p(x)
	 */
	public Complex getValue(Elem x){
		Complex ans = new Complex(0,0);
		double expValue;
		for(int exp: terms.keySet()){
			Complex coeff = terms.get(exp);
			expValue = Math.pow(x.getValue(), exp);
			ans.addComplex(coeff.getRe()*expValue, coeff.getIm()*expValue);
		}
		
		return ans;
	}

}
