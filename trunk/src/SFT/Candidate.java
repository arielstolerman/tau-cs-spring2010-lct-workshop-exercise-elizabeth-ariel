/**
 * 
 * Workshop Learning & Coding Theory Exercise
 * TAU, Spring Semester 2010
 * Elizabeth Firman and Ariel Stolerman
 * 
 * Filename: Candidate.java
 * Description: code for class Candidate, representation of a set of intervals, candidates for
 * 				the Fourier significant coefficients
 * 
 */

package SFT;

import java.util.*;

public class Candidate {
	
	private Set<Elem[]> candidates;
	
	/**
	 * default constructor
	 */
	public Candidate(){
		this.candidates = new HashSet<Elem[]>();
	}
	
	/**
	 * constructor with initial interval
	 * @param interval:		the initial interval to be added to the candidates group
	 */
	public Candidate(Elem[] interval){
		this.candidates = new HashSet<Elem[]>();
		this.addInterval(interval);
	}
	
	// getters
	
	/**
	 * @return:		the set of intervals for this candidate
	 */
	public Set<Elem[]> getSet(){
		return this.candidates;
	}
	
	/**
	 * @param interval:		an interval {a,b} (a,b in 0,...,N)
	 * @return:				returns true iff the interval is contained in this candidate
	 */
	public boolean belongsTo(Elem[] interval){
		for (Elem[] elem: candidates){
			if (elem[0].getValue() == interval[0].getValue())
				if (elem[1].getValue() == interval[1].getValue())
					return true;
		}
		return false;
	}
	
	// adders and setters
	/**
	 * @param interval:		interval to be added to the candidates group
	 * Assumption:			the interval is not already contained in the candidates group
	 */
	public void addInterval(Elem[] interval){
		candidates.add(interval);
	}

}
