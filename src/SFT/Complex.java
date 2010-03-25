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
}
