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

public class SFT {
	
	/* ***************************
	 * parameters used by the first part of the algorithm given by the user:
	 * - the infinity norm of f
	 * - the Euclidean norm of f
	 * - a constant coefficient for calculating delta
	 * - a constant coefficient for calculating m_A and m_B
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
	
	/* **************************
	 * parameters used by the second part of the algorithm
	 * - the sets of elements to get their f-values
	 * - an array of the sets A,B1,...,Bl
	 * - the query map of Q and its f-values
	 */
	private static Set<Elem> Q;
	private static Set<Elem>[] sets;
	private static Query query = null;
	
	// the variable to hold the result
	public static Set<Elem> L;
	
	/**
	 * Main SFT procedure (3.4)
	 * The main SFT is departed into two parts, where part one builds a set of elements to be
	 * f-valued, and part two continues its calculations using these query results
	 * @param N:		an integer value describing the group Z_N
	 * @param tau:		threshold on the weight of the Fourier coefficients we seek
	 * @param deltha_t:	confidence parameter
	 * @return			a short list L in Z_N of the tau-significant Fourier coefficients
	 * 					of f with probability at least 1-deltha_t
	 */
	public static void runMainSFTAlgorithm(long N, double tau, double delta_t){
		Debug.log("SFT -> runMainSFTAlgorithm - main algorithm part 1 started");
		
		// run generateQueries on N, gamma = tau/36, ||f||_infinity and delta = delta_t/O((||f||_2^2/tau)^1.5*logN)
		double gamma = tau/36;
		double fInfNorm = getfInfNorm();
		double delta = delta_t/( deltaCalculationConst * Math.pow(Math.pow(getfEuclideanNorm(),2)/tau,1.5) *
				(Math.log(N)/Math.log(2)) );
		Set<Elem>[] sets = generateQueries(N, gamma, fInfNorm, delta);
		Debug.log("\tgenerated sets A,B1,..,Bl");
		
		// Build set Q
		Set<Elem> Q = new HashSet<Elem>();
		Set<Elem> A = sets[0];
		
		for (int i=1; i<sets.length; i++){
			Set<Elem> Bl = sets[i];
			for(Elem e_a: A){
				for(Elem e_b: Bl){
					Elem elem = Elem.sub(e_a, e_b); // subtraction modulo N
					if (!Elem.contains(Q, elem))
						Q.add(elem);
				}
			}
		}
		
		String QValues = "";
		for (Iterator<Elem> j = Q.iterator(); j.hasNext();){
			QValues += j.next()+" ";
		}
		Debug.log("\tcreated Q = {x - y | x in A, y in union(B_i), i=1,...,log(N)}: "+QValues);
		
		// set up public variables with Q and sets
		// user will invoke the query calculation followed by the rest of the SFT algorithm execution
		// (the runMainSFTAlgorithmCont procedure)
		SFT.Q = Q;
		SFT.sets = sets;
		
		Debug.log("SFT -> runMainSFTAlgorithm - main algorithm part 1 completed");
	}
	
	/**
	 * Main SFT Algorithm - continuation of the main procedure (3.4)
	 * Called after the f-values of the set Q of elements in Z_N constructed on the first part
	 * (runMainSFTAlgorithm) were calculated
	 */
	public static void runMainSFTAlgorithmCont(Set<Elem>[] sets, Query query){
		Debug.log("SFT -> runMainSFTAlgorithmCont - main algorithm part 2 started");
		
		// run getFixedQueriesSFT and return its output, L
		Set<Elem> L = getFixedQueriesSFT(N,tau,sets,query);
		SFT.L = L;
		
		String LValues = "";
		for (Elem e: L){
			LValues += String.valueOf(e.getValue())+" ";
		}
		Debug.log("\tfinished calculating L, the list of significant Fourier coefficients for f: "+LValues);
		
		Debug.log("SFT -> runMainSFTAlgorithmCont  - main algorithm part 2 completed");
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
	@SuppressWarnings("unchecked")
	public static Set<Elem>[] generateQueries(long N, double gamma, double fInfNorm, double delta){
		Debug.log("SFT -> generateQueries started");
		
		// compute m_A and m_B
		double tmp = 1.0/Math.pow(gamma, 2);
		int m_A = (int) (m_A_m_B_CalculationConst * Math.ceil(tmp*Math.log(1.0/delta)));
		int m_B = (int) (m_A_m_B_CalculationConst * Math.ceil(tmp*Math.log(1.0/(delta*gamma))));
		
		Debug.log("\tm_A is: "+m_A+", m_B is: "+m_B);
		
		// generate A,B1,...,Bl
		int logN = calcLogN(N);
		Set<Elem>[] res = new HashSet[logN+1];
		
		// generate random subset A partial to Z_N with m_A elements
		res[0] = generateRandomSubsetA(m_A, N);
		
		String AValues = "";
		for (Iterator<Elem> j = res[0].iterator(); j.hasNext(); ){
			AValues += j.next()+" ";
		}
		Debug.log("\tA: "+AValues);

		// generate logN random subsets B_l partial to {0,...,2^(l-1)-1} with min{m_B,2^(l-1)} elements
		// return an array of A,B1,...,Bl
		for(int l=1; l<=logN; l++){
			res[l] = generateRandomSubsetBl(m_B,N,l);
		}
		
		Debug.log("\tB's:");
		for (int i=1; i<=logN; i++){
			String BlValues = "size: "+res[i].size()+"; elements: ";
			for (Iterator<Elem> j = res[i].iterator(); j.hasNext(); ){
				BlValues += j.next()+" ";
			}
			Debug.log("\tB["+i+"]: "+BlValues);
		}
		
		Debug.log("\tcreated A and B1,...,Bl");
		Debug.log("SFT -> generateQueries completed");
		
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
		Debug.log("SFT -> getFixedQueriesSFT started");
		
		// initialize candidate (candidate_0)
		Elem[] initInterval = new Elem[2];
		initInterval[0] = new Elem(0);
		initInterval[1] = new Elem(N);
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
		
		Debug.log("\tcandidate iterations finished");
		
		// build L and return it
		Set<Elem> L = new HashSet<Elem>();
		for (Elem[] interval: candidate.getSet()){
			if (interval[0].getValue() == interval[1].getValue()){
				Elem elem = new Elem(interval[0].getValue());
				L.add(elem);
			}
		}
		
		Debug.log("\tDone creating L");
		Debug.log("SFT -> getFixedQueriesSFT completed");
		
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
		Debug.log("SFT -> distinguish started");
		
		double est = 0;
		Elem v = new Elem(Math.floor((interval[0].getValue()+interval[1].getValue())/2));
		
		// calculate est(a,b)
		for (Elem x: A){
			double tmpBSum = 0;
			for (Elem y: B){
				long x_sub_y = (Elem.sub(x, y)).getValue();
				tmpBSum += innerProduct(chi(v,y),query.getValue(x_sub_y));
			}
			tmpBSum /= B.size();
			tmpBSum *= tmpBSum;
			est += tmpBSum;
		}
		
		est /= A.size();
		
		Debug.log("\tcalculated est: "+est);
		
		// compare to threshold and return result
		double threshold = 5*tau/36;
		
		Debug.log("SFT -> distinguish completed");
		
		return est >= threshold;
	}
	
	/* *******************
	 * assistance methods
	 *********************/
	
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
		for(int i=0; i< m_A; i++){
			// create and add new element with value different from the elements already contained in a
			res.add(genNewElem(res,N));
		}
		
		return res;
	}
	
	/**
	 * @param m_B:	potential size of the set
	 * @param N:	the order of Z_N
	 * @param l		a value between 1 and log(N)
	 * @return		a set of elements in {0,...,2^(l-1)-1}
	 */
	private static Set<Elem> generateRandomSubsetBl(int m_B, long N, int l){
		Set<Elem> res = new HashSet<Elem>();
		
		// if 2^(l-1) < m_B, no need to randomly choose elements for be, take all 0,...,2^(l-1)-1
		long pow = (long)Math.pow(2, l-1);
		if (pow <= m_B){
			// take all elements in {0,...,2^(l-1)-1} to B_l
			for (long i=0; i<pow; i++){
				res.add(new Elem(i));
			}
		}
		// otherwise, choose randomly m_B elements from 0,...,2^(l-1)-1
		else {
			for (long i=0; i<m_B; i++){
				res.add(genNewElem(res, pow));
			}
		}
		
		return res;
	}
	
	/**
	 * @param set:		a set of elements
	 * @param randBar:	a random barrier to be used
	 * @return			a new element with value different from any element's value in the given set,
	 * 					with value generated randomly between 0 and randBar
	 */
	private static Elem genNewElem(Set<Elem> set, long randBar){
		long value;
		boolean doAgain;
		do{
			value = getRandValue(randBar);
			doAgain = false;
			for (Elem e: set){
				if (e.getValue() == value){ //: fix the problem with generating a group with barrier 1 - always generates the element 0
					doAgain = true;
					break;
				}
			}
		} while (doAgain);
		
		return new Elem(value);
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
	public static Complex chi(Elem v, Elem y){
		// chi_v (y) = e^(i2pi * v/N * y) = cos(2pi * v/N * y) + i*sin(2pi * v/N * y)
		double arg = 2 * Math.PI * (((double)v.getValue())/N) * ((double)y.getValue());
		double re = Math.cos(arg);
		double im = Math.sin(arg);
		
		return new Complex(re, im);
	}
	
	/**
	 * calculate the inner product over C
	 * @param x:	first element in the inner product
	 * @param y:	first element in the inner product
	 * @return:		the inner product <x,y> = sum[x_i * y_i] (i = 1,2)
	 */
	public static double innerProduct(Complex x, Complex y){
		return x.getRe()*y.getRe()+x.getIm()*y.getIm();
	}
	
	
	// AUTO GENERATED GETTERS AND SETTER

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

	public static Set<Elem>[] getSets() {
		return sets;
	}

	public static void setSets(Set<Elem>[] sets) {
		SFT.sets = sets;
	}

	public static Query getQuery() {
		return query;
	}

	public static void setQuery(Query query) {
		SFT.query = query;
	}

	public static Set<Elem> getL() {
		return L;
	}

	public static void setL(Set<Elem> l) {
		L = l;
	}

	public static Set<Elem> getQ() {
		return Q;
	}

	public static void setQ(Set<Elem> q) {
		Q = q;
	}
}
