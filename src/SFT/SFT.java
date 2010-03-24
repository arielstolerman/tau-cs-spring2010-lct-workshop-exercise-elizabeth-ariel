/**
 * 
 * Workshop Learning & Coding Theory Exercise
 * TAU, Spring Semester 2010
 * Elizabeth Firman and Ariel Stolerman
 * 
 * Filename: SFT.java
 * Description: code for class SFT, implementation of the SFT algorithm
 * 
 */

package SFT;

public class SFT {
	
	/**
	 * Main SFT procedure
	 * @param N:		an integer value describing the group Z_N
	 * @param tau:		threshold on the weight of the Fourier coefficients we seek
	 * @param deltha_t:	confidence parameter
	 * @return			a short list L in Z_N of the tau-significant Fourier coefficients
	 * 					of f with probability at least 1-deltha_t
	 */
	public static Elem[] runMainSFTAlgorithm(long N, double tau, double deltha_t, Query query){
		return new Elem[1]; //TODO
	}
	
	/**
	 * Generate Queries algorithm
	 * @param N:		an integer value describing the group Z_N
	 * @param gamma:	a value in R+
	 * @param fInfNorm:	||f||_(infinity)
	 * @param deltha:	confidence parameter
	 * @return:			a set of elements in Z_N for which the main procedure will ask
	 * 					its f values
	 */
	public static Elem[] generateQueries(long N, double gamma, double fInfNorm, double deltha){
		return new Elem[1]; //TODO
	}
	
	
}
