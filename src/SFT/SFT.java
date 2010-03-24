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

import java.math.*;
import java.util.*;

import Utils.Debug;
import Utils.Debug.DebugOutput;

public class SFT {
	
	private static boolean DEBUG = true;
	
	/**
	 * Main SFT procedure (3.4)
	 * @param N:		an integer value describing the group Z_N
	 * @param tau:		threshold on the weight of the Fourier coefficients we seek
	 * @param deltha_t:	confidence parameter
	 * @return			a short list L in Z_N of the tau-significant Fourier coefficients
	 * 					of f with probability at least 1-deltha_t
	 */
	public static Set<Elem> runMainSFTAlgorithm(long N, double tau, double deltha_t){
		return new HashSet<Elem>(); //TODO
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
	public static Set<Elem>[] generateQueries(long N, double gamma, double fInfNorm, double deltha){
		Debug.log("SFT::generateQueries was called", DebugOutput.STDOUT);
		
		// compute m_A and m_B
		double tmp = 1.0/Math.pow(gamma, 2);
		int m_A = (int)Math.ceil(tmp*Math.log(1.0/deltha));
		int m_B = (int)Math.ceil(tmp*Math.log(1.0/(deltha*gamma)));
		
		Debug.log("m_A is: "+m_A+", m_B is: "+m_B, DebugOutput.STDOUT);
		
		// generate A,B1,...,Bl
		int logN = (int)Math.ceil(Math.log(N)/Math.log(2)); //TODO verify log basis and if l is floor/ceiling on log(N)
		Set<Elem>[] res = new HashSet[logN+1];
		
		// generate random subset A partial to Z_N with m_A elements
		//Set<Elem> A = generateRandomSubsetA(m_A, N); //TODO
		res[0] = generateRandomSubsetA(10, N);
		
		Debug.log("A:",DebugOutput.STDOUT);
		for (Iterator<Elem> j = res[0].iterator(); j.hasNext(); ){
			Debug.log("- "+j.next(),DebugOutput.STDOUT);
		}

		// generate logN random subsets B_l partial to {0,...,2^(l-1)-1} with min{m_B,2^(l-1)} elements
		// return an array of A,B1,...,Bl
		for(int l=1; l<=logN; l++){
			//res[l] = generateRandomSubsetBl(m_B,N,l);//TODO
			res[l] = generateRandomSubsetBl(10,N,l);
		}
		
		Debug.log("B's:",DebugOutput.STDOUT);
		for (int i=1; i<=logN; i++){
			Debug.log("B["+i+"]:",DebugOutput.STDOUT);
			for (Iterator<Elem> j = res[i].iterator(); j.hasNext(); ){
				Debug.log("- "+j.next(),DebugOutput.STDOUT);
			}
		}
		
		Debug.log("created A and B1,...,Bl",DebugOutput.STDOUT);
		
		return res;
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
	public static Set<Elem> getFixedQueriesSFT(long N, double tau, Set<Elem>[] querySets, Query query){		
		return new HashSet<Elem>(); //TODO
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
	public static boolean distinguish(Elem a, Elem b, double tau, Set<Elem> A, Set<Elem> B, Query query){
		return true; //TODO
	}
	
	/* *****************************
	 * private assistance methods
	 *******************************/
	
	/**
	 * @param m_A:	the size of the set
	 * @param N:	the order of Z_N
	 * @return:		a set of elements in Z_N, uniformly randomly selected
	 */
	private static Set<Elem> generateRandomSubsetA(int m_A, long N){
		Set<Elem> res = new HashSet<Elem>();
		
		// randomly choose elements
		for(int i=0; i< m_A; i++)
			if (!res.add(new Elem(getRandValue(N))))	// if add fails, another element is needed
				i--;
		
		return res;
	}
	
	private static Set<Elem> generateRandomSubsetBl(int m_B, long N, int l){
		// generate B_l
		// define number of elements by min{m_B, 2^(l-1)}
		long pow = (long)Math.pow(2, l-1);
		long numOfElems = Math.min((long)m_B, pow);

		// create B_l
		Set<Elem> res = new HashSet<Elem>();
		for(int i=0; i<numOfElems; i++){
			if (!res.add(new Elem(getRandValue(pow))))	// if add fails, another element is needed
				i--;
		}
		
		return res;
	}
	
	/**
	 * @param barrier
	 * @return:			a random integer value between 0 and barrier-1
	 */
	private static long getRandValue(long barrier){
		double value = Math.random()*(barrier-1);
		return (long)Math.floor(value);
	}
}
