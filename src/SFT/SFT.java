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
	 * Main SFT procedure (3.4)
	 * @param N:		an integer value describing the group Z_N
	 * @param tau:		threshold on the weight of the Fourier coefficients we seek
	 * @param deltha_t:	confidence parameter
	 * @return			a short list L in Z_N of the tau-significant Fourier coefficients
	 * 					of f with probability at least 1-deltha_t
	 */
	public static Elem[] runMainSFTAlgorithm(long N, double tau, double deltha_t){
		return new Elem[1]; //TODO
	}
	
	/**
	 * Generate Queries algorithm (3.5)
	 * @param N:		an integer value describing the group Z_N
	 * @param gamma:	a value in R+
	 * @param fInfNorm:	||f||_(infinity)
	 * @param deltha:	confidence parameter
	 * @return:			sets of elements in Z_N from which the main procedure will
	 * 					create the set of x's to ask their f-value
	 */
	public static Elem[][] generateQueries(long N, double gamma, double fInfNorm, double deltha){
		return new Elem[1][1]; //TODO
	}
	
	/**
	 * Fixed Queries SFT algorithm (3.6)
	 * @param N:			an integer value describing the group Z_N
	 * @param tau:			threshold on the weight of the Fourier coefficients we seek
	 * @param querySets:	the output of the generateQueries function
	 * @param query:		a set {q,f(q)}
	 * @return:				a short list L in Z_N of the tau-significant Fourier coefficients
	 * 						of f with probability at least 1-deltha_t
	 */
	public static Elem[] getFixedQueriesSFT(long N, double tau, Elem[][] querySets, Query query){
		return new Elem[1]; //TODO
	}
	
	/**
	 * Distinguishing algorithm (3.7)
	 * @param a:		
	 * @param b:		
	 * @param tau:		
	 * @param A:		
	 * @param B:		
	 * @param query:	
	 * @return:			decides whether to keep or discard the interval {a,b} 
	 */
	public static boolean distinguish(Elem a, Elem b, double tau, Elem[] A, Elem[] B, Query query){
		return true; //TODO
	}
}
