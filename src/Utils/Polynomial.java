

package Utils;

import java.util.*;
import SFT.*;

public class Polynomial {
	private Map<Elem,Complex> terms;
	private String id;
	
	/**
	 * default constructor
	 * @param id
	 */
	public Polynomial(String id){
		this.id = id;
		terms = new HashMap<Elem,Complex>();
	}
	
	// getters
	
	/**
	 * @param alpha:	the element for which the coefficient is fetched
	 * @return:			the coefficient or null if doesn't exist
	 */
	public Complex getCoeff(Elem alpha){
		for(Elem elem: terms.keySet()){
			if (elem.getValue() == alpha.getValue())
				return terms.get(elem); // found it, return the coeff
		}
		return null;
	}
	
	public String getId() {
		return id;
	}
	
	/**
	 * get the string representation of the polynomial
	 */
	public String toString(){
		String str = "";
		
		for (Elem elem: terms.keySet()){
			Complex coeff = terms.get(elem);
			str += "("+coeff.toString()+")*chi_("+elem.getValue()+")[x] + ";
		}
		str = str.substring(0,str.length()-3);
		
		return str;
	}
	
	/**
	 * 
	 * @return:		the string representation in html code of the polynomial
	 */
	public String toHTMLString(){
		return "<html>"+this.toString()+"</html>";
	}
	
	// setters
	
	/**
	 * adds the term to the polynomial
	 * if already exist, adds the coefficients
	 * @param alpha:	the element
	 * @param re:		real part
	 * @param im:		imaginary part
	 */
	public void addUpdateTerm(Elem alpha, double re, double im){
		Complex coeff = this.getCoeff(alpha);
		if (coeff == null){
			// create new entry
			coeff = new Complex(re,im);
			terms.put(alpha,coeff);
		} else {
			// add to existing coefficient
			coeff.addComplex(re, im);
		}
	}
	
	// calculations
	
	/**
	 * @param x:	input for the polynomial p
	 * @return:		the complex value of p(x) which is SUM_(alpha in Z_N) [coeff_alpha * chi_alpha(x)]
	 */
	//TODO: fix calculation
	public Complex getValue(Elem x){
		Complex ans = new Complex(0,0);
		
		for(Elem alpha: terms.keySet()){
			Complex coeff = terms.get(alpha);
			double val = SFT.innerProduct(coeff, SFT.chi(alpha, x));
			ans.addComplex(val, 0);
		}
		
		return ans;
	}

}
