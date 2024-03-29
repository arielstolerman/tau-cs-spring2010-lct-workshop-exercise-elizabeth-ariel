/**
 * 
 * Workshop Learning & Coding Theory Exercise
 * TAU, Spring Semester 2010
 * Elizabeth Firman and Ariel Stolerman
 * 
 * Filename: Complex.java
 * Description: code for class Complex, representation of complex numbers
 * 
 */

package SFT;

public class Complex {
	// class members
	private double real, imaginary;
	
	/**
	 * Constructor
	 * @param real:			real coordinate
	 * @param imaginary:	imaginary coordinate
	 */
	public Complex(double real, double imaginary){
		this.real = real;
		this.imaginary = imaginary;
	}
	
	// getters
	
	/**
	 * @return:		the real coordinate
	 */
	public double getRe(){
		return this.real;
	}
	
	/**
	 * @return:		the imaginary coordinate
	 */
	public double getIm(){
		return this.imaginary;
	}
	
	/**
	 * @return:		a double array with the real coordinate as the first element
	 * 				and the imaginary coordinate as the second element
	 */
	public double[] getComplex(){
		double[] res = new double[2];
		res[0] = this.real;
		res[1] = this.imaginary;
		
		return res;
	}
	
	/**
	 * string representation of the complex number
	 */
	public String toString(){
		return real+(imaginary<0?"":"+")+imaginary+"i";
	}
	
	/**
	 * @return:		the Euclidean norm, that is |a+bi| = (a+bi)*(a-bi) = a^2 + b^2
	 */
	public double getNorm(){
		return Math.pow(real, 2)+Math.pow(imaginary, 2);
	}
	
	// setters
	
	/**
	 * setter for the complex number
	 * @param real:			new real value
	 * @param imaginary:	new imaginary value
	 */
	public void setComplex(double real, double imaginary){
		this.real = real;
		this.imaginary = imaginary;
	}
	
	/**
	 * adds the given complex number
	 * @param real:			real value to add
	 * @param imaginary:	imaginary value to add
	 */
	public void addComplex(double real, double imaginary){
		this.real = this.real+real;
		this.imaginary = this.imaginary+imaginary;
	}
	
	public void addComplex(Complex complex){
		this.addComplex(complex.getRe(), complex.getIm());
	}
	
	// static math functions
	
	/**
	 * @param complex1:		first argument
	 * @param complex2:		second argument
	 * @return:				multiplies the two complex numbers
	 */
	public static Complex mulComplex(Complex complex1, Complex complex2){
		// (a + bi)(c + di) = (ac - bd) + (bc + ad)i
		return new Complex(
				complex1.getRe()*complex2.getRe() - complex1.getIm()*complex2.getIm(),
				complex1.getIm()*complex2.getRe() + complex1.getRe()*complex2.getIm()
				); 
	}
}
