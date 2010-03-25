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

import java.util.*;
import Utils.Debug;
import Utils.Debug.DebugOutput;

public class SFT {
	
	/* ***************************
	 * parameters used by the algorithm given by the user:
	 * - the infinity norm of f
	 * - the Euclidean norm of f
	 * - a constant coefficient for calculating delta
	 * - a constant coefficient for calculating m_A nad m_B
	 * - N the order of Z_N
	 * - tau the threshold
	 * - delta the confidence parameter
	 * 
	 * these parameters will be set by the user input, and the SFT algorithm will
	 * run using these parameters
	 */
	private static double fInfNorm;
	private static double fEuclideanNorm;
	private static double deltaCalculationConst = 1;
	private static double m_A_m_B_CalculationConst = 1;
	private static long N;
	private static double tau;
	private static double delta;
	
	/**
	 * Main SFT procedure (3.4)
	 * @param N:		an integer value describing the group Z_N
	 * @param tau:		threshold on the weight of the Fourier coefficients we seek
	 * @param deltha_t:	confidence parameter
	 * @return			a short list L in Z_N of the tau-significant Fourier coefficients
	 * 					of f with probability at least 1-deltha_t
	 */
	public static Set<Elem> runMainSFTAlgorithm(long N, double tau, double delta_t){
		Debug.log("SFT::runMainSFTAlgorithm started", DebugOutput.STDOUT);
		
		// run generateQueries on N, gamma = tau/36, ||f||_infinity and delta = delta_t/O((||f||_2^2/tau)^1.5*logN)
		double gamma = tau/36;
		double fInfNorm = getfInfNorm();
		double delta = delta_t/( deltaCalculationConst * Math.pow(Math.pow(getfEuclideanNorm(),2)/tau,1.5) *
				(Math.log(N)/Math.log(2)) );
		Set<Elem>[] sets = generateQueries(N, gamma, fInfNorm, delta);
		
		Debug.log("generated sets A,B1,..,Bl",DebugOutput.STDOUT);
		
		// Build set Q
		Set<Elem> BUnified = new HashSet<Elem>();
		for (int i=1; i<sets.length; i++){
			BUnified.addAll(sets[i]);
		}
		Set<Elem> Q = sets[0];
		Q.removeAll(BUnified);
		
		Debug.log("created Q from A - union(B_i), i=1,...,log(N)",DebugOutput.STDOUT);
		
		// query f to find values f(q) for all q in Q
		Query query = Query.getQueryFromQ(Q, N); //TODO needs implementation in class Query
		
		Debug.log("fetched query for all elements in Q",DebugOutput.STDOUT);
		
		// run getFixedQueriesSFT and return its output, L
		Set<Elem> L = getFixedQueriesSFT(N,tau,sets,query); 
		Debug.log("finished calculating L, the list of significant Fourier coefficients for f. L elements:",DebugOutput.STDOUT);
		for (Elem e: L){
			Debug.log(String.valueOf(e.getValue())+" ",DebugOutput.STDOUT);
		}
		
		Debug.log("SFT::runMainSFTAlgorithm finished", DebugOutput.STDOUT);
		
		return L;
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
	public static Set<Elem>[] generateQueries(long N, double gamma, double fInfNorm, double delta){
		Debug.log("SFT::generateQueries started", DebugOutput.STDOUT);
		
		// compute m_A and m_B
		double tmp = 1.0/Math.pow(gamma, 2);
		int m_A = (int) (m_A_m_B_CalculationConst * Math.ceil(tmp*Math.log(1.0/delta)));
		int m_B = (int) (m_A_m_B_CalculationConst * Math.ceil(tmp*Math.log(1.0/(delta*gamma))));
		
		Debug.log("m_A is: "+m_A+", m_B is: "+m_B, DebugOutput.STDOUT);
		
		// generate A,B1,...,Bl
		int logN = calcLogN(N);
		Set<Elem>[] res = new HashSet[logN+1];
		
		// generate random subset A partial to Z_N with m_A elements
		Set<Elem> A = generateRandomSubsetA(m_A, N);
		
		Debug.log("A:",DebugOutput.STDOUT);
		for (Iterator<Elem> j = res[0].iterator(); j.hasNext(); ){
			Debug.log("- "+j.next(),DebugOutput.STDOUT);
		}

		// generate logN random subsets B_l partial to {0,...,2^(l-1)-1} with min{m_B,2^(l-1)} elements
		// return an array of A,B1,...,Bl
		for(int l=1; l<=logN; l++){
			res[l] = generateRandomSubsetBl(m_B,N,l);
		}
		
		Debug.log("B's:",DebugOutput.STDOUT);
		for (int i=1; i<=logN; i++){
			Debug.log("B["+i+"]:",DebugOutput.STDOUT);
			for (Iterator<Elem> j = res[i].iterator(); j.hasNext(); ){
				Debug.log("- "+j.next(),DebugOutput.STDOUT);
			}
		}
		
		Debug.log("created A and B1,...,Bl",DebugOutput.STDOUT);
		Debug.log("SFT::generateQueries finished",DebugOutput.STDOUT);
		
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
		Debug.log("SFT::getFixedQueriesSFT started",DebugOutput.STDOUT);
		
		// initialize candidate (candidate_0)
		Elem[] initInterval = new Elem[2];
		initInterval[0].setValue(0);
		initInterval[1].setValue(N);
		Candidate candidate = new Candidate(initInterval);
		
		// run iterations over l = 0,...,log_2(N)-1
		int logN = calcLogN(N);
		Set<Elem> A = querySets[0];
		
		for(int l=0; l<logN; l++){
			Candidate tmpCandidate = new Candidate();
			for (Elem[] interval: candidate.getSet()){
				// create two sub intervals
				Elem a = interval[0];
				Elem b = interval[1];
				
				Elem middle = new Elem(Math.floor((a.getValue()+b.getValue())/2));
				Elem[] subInterval1 = {new Elem(a.getValue()), new Elem(middle.getValue())};
				Elem[] subInterval2 = {new Elem(middle.getValue()+1), new Elem(b.getValue())};
				
				// check that the intervals size difference is no greater that 1
				assert(Math.abs((subInterval1[1].getValue()-subInterval1[0].getValue()) - 
						(subInterval2[1].getValue()-subInterval2[0].getValue())) <= 1);
				
				// for each sub interval check if it is "heavy"
				Set<Elem> B_lplus1 = querySets[l+1];
				if (distinguish(subInterval1, tau, A, B_lplus1, query))
					tmpCandidate.addInterval(subInterval1);
				if (distinguish(subInterval2, tau, A, B_lplus1, query))
					tmpCandidate.addInterval(subInterval2);
			}
			candidate = tmpCandidate; // update candidate_i to candidate_(i+1)
		}
		
		Debug.log("candidate iterations finished",DebugOutput.STDOUT);
		
		// build L and return it
		Set<Elem> L = new HashSet<Elem>();
		for (Elem[] interval: candidate.getSet()){
			if (interval[0].getValue() == interval[1].getValue()){
				Elem elem = new Elem(interval[0].getValue());
				L.add(elem);
			}
		}
		
		Debug.log("Done creating L",DebugOutput.STDOUT);
		Debug.log("SFT::getFixedQueriesSFT finished",DebugOutput.STDOUT);
		
		return L;
	}
	
	/**
	 * Distinguishing algorithm (3.7)
	 * @param interval:	the interval to be checked for "heaviness"
	 * @param tau:		threshold
	 * @param A:		
	 * @param B:		
	 * @param query:	
	 * @return:			decides whether to keep or discard the interval {a,b} 
	 */
	public static boolean distinguish(Elem[] interval, double tau, Set<Elem> A, Set<Elem> B, Query query){
		Debug.log("SFT::distinguish started",DebugOutput.STDOUT);
		
		double est = 0;
		Elem v = new Elem(Math.floor((interval[0].getValue()+interval[1].getValue())/2));
		
		// calculate est(a,b)
		for (Elem x: A){
			double tmpBSum = 0;
			for (Elem y: B){
				//TODO calculate tmpBSum
			}
			tmpBSum *= tmpBSum;
			est += tmpBSum;
		}
		
		est /= A.size()*B.size()*B.size();
		
		Debug.log("calculated est: "+est,DebugOutput.STDOUT);
		
		// compare to threshold and return result
		double threshold = 5*tau/36;
		
		Debug.log("SFT::distinguish finished",DebugOutput.STDOUT);
		
		return est >= threshold;
	}
	
	/* *****************************
	 * private assistance methods
	 *******************************/
	
	/**
	 * @param N:	the order of Z_N
	 * @return:		log_2(N), rounded up
	 */
	private static int calcLogN(long N){
		return (int)Math.ceil(Math.log(N)/Math.log(2));
	}
	
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
	
	/**
	 * @param m_B:	potential size of the set
	 * @param N:	the order of Z_N
	 * @param l		a value between 1 and log(N)
	 * @return		a set of elements in {0,...,2^(l-1)-1}
	 */
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
	 * @param barrier:	upper limit for the random generator
	 * @return:			a random integer value between 0 and barrier-1
	 */
	private static long getRandValue(long barrier){
		double value = Math.random()*(barrier-1);
		return (long)Math.floor(value);
	}
	
	/**
	 * calculate Chi
	 * @param v:		floor( (a+b)/2 )
	 * @param y:		input for the chi function
	 * @return:			chi_(floor[(a+b)/2]) (y)
	 */
	private static Complex chi(Elem v, Elem y){
		return new Complex(1.0, 1.0); //TODO
	}
	
	/* ********************
	 * 	Main for Debugging
	 **********************/
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Debug.log("Started",DebugOutput.STDOUT);
	}
	
	// AUTO GENERATED GETTERS AND SETTERS

	public static double getfInfNorm() {
		return fInfNorm;
	}

	public static void setfInfNorm(double fInfNorm) {
		SFT.fInfNorm = fInfNorm;
	}

	public static double getfEuclideanNorm() {
		return fEuclideanNorm;
	}

	public static void setfEuclideanNorm(double fEuclideanNorm) {
		SFT.fEuclideanNorm = fEuclideanNorm;
	}

	public static double getDeltaCalculationConst() {
		return deltaCalculationConst;
	}

	public static void setDeltaCalculationConst(double deltaCalculationConst) {
		SFT.deltaCalculationConst = deltaCalculationConst;
	}

	public static double getM_A_m_B_CalculationConst() {
		return m_A_m_B_CalculationConst;
	}

	public static void setM_A_m_B_CalculationConst(double mAMBCalculationConst) {
		m_A_m_B_CalculationConst = mAMBCalculationConst;
	}

	public static long getN() {
		return N;
	}

	public static void setN(long n) {
		N = n;
	}

	public static double getTau() {
		return tau;
	}

	public static void setTau(double tau) {
		SFT.tau = tau;
	}

	public static double getDelta() {
		return delta;
	}

	public static void setDelta(double delta) {
		SFT.delta = delta;
	}
}
