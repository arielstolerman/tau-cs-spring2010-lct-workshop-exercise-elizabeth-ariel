/**
 * 
 * Workshop Learning & Coding Theory Exercise
 * TAU, Spring Semester 2010
 * Elizabeth Firman and Ariel Stolerman
 * 
 * Filename: Polynomial.java
 * Description: code for a Fourier polynomial representation, used to represent functions
 * 				received as input from the user
 * 
 */

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
	public Complex getValue(Elem x){
		Complex ans = new Complex(0,0);
		
		for(Elem alpha: terms.keySet()){
			Complex coeff = terms.get(alpha);
			ans.addComplex(Complex.mulComplex(coeff,SFT.chi(alpha, x)));
		}
		
		return ans;
	}

	/**
	 * @param p:	a Fourier polynomial
	 * @param N:	group order
	 * @return:		||p||_infinity over Z_N (maximum norm of p)
	 */
	public static double getInfinityNorm(Polynomial p, long N){
		double ans = 0;
		
		for(long i=0; i<N; i++){
			Complex val = p.getValue(new Elem(i));
			double tmp = Math.pow(val.getRe(),2) + Math.pow(val.getIm(),2);
			if (tmp > ans) ans = tmp;
		}
		
		return Math.sqrt(ans);
	}
	
	/**
	 * @param p:	a Fourier polynomial
	 * @param N:	group order
	 * @return:		||p||_2 over Z_N (Euclidean norm of p)
	 */
	public static double getEuclideanNorm(Polynomial p, long N){
		double ans = 0;
		
		for(long i=0; i<N; i++){
			Complex val = p.getValue(new Elem(i));
			System.out.println(val);
			ans += (Math.pow(val.getRe(),2) + Math.pow(val.getIm(),2))/((double)N); // [f(x) * conjugate(f(x))] / N
			System.out.println(ans);
		}
		
		return Math.sqrt(ans);
	}
	
	// main for debugging
	public static void main(String[] args) {		
		SFT.setN(10000000);
		
		Polynomial p = new Polynomial("1");
		p.addUpdateTerm(new Elem(2031200), 10, 10);
		p.addUpdateTerm(new Elem(1492), 10, 10);
		p.addUpdateTerm(new Elem(542), 0.01, 0.01);
		p.addUpdateTerm(new Elem(29), 0.07, 0.09);
		/*p.addUpdateTerm(new Elem(4), 0.001, 0.001);
		p.addUpdateTerm(new Elem(5), 0.001, 0.001);*/
		
		System.out.println("inf: "+getInfinityNorm(p, SFT.getN())+", euclidean: "+getEuclideanNorm(p, SFT.getN()));
	}
}
