/**
 * 
 * Workshop Learning & Coding Theory Exercise
 * TAU, Spring Semester 2010
 * Elizabeth Firman and Ariel Stolerman
 * 
 * Filename: Query.java
 * Description: code for class Query, object that holds query values for a function f, given by the user
 * 				in various methods (on-the-fly build, coefficients of a polynomial or randomly generated)
 * 
 */

package SFT;

import java.util.*;

public class Query {
	private Map<Elem,Complex> query;
	private int numOfElements;
	private final long N;
	
	/**
	 * default constructor for a query object
	 * @param N:	the size of the domain Z_N
	 */
	public Query(long N){
		this.query = new HashMap<Elem,Complex>();
		this.numOfElements = 0;
		this.N = N;
	}
	
	/**
	 * constructor for query with initial values
	 * @param query:	initial query values
	 * @param N:	the size of the domain Z_N
	 */
	public Query(long N, HashMap<Elem,Complex> query){
		this.query = query;
		this.numOfElements = query.size();
		this.N = N;
	}
	
	// getters
	
	/**
	 * @return:		the query map
	 */
	public Map<Elem,Complex> getQuery(){
		return this.query;
	}
	
	/**
	 * @return:		the number of elements in the query
	 */
	public int getNumOfElements(){
		return this.numOfElements;
	}
	
	/**
	 * @return:		the size of the domain group Z_N
	 */
	public long getSizeOfDomain(){
		return this.N;
	}
	
	/**
	 * getter for a specific value
	 * @param n:	input for the function the query represents
	 * @return:		the output of the function the query represents for the given input (a complex number)
	 */
	public Complex getValue(Elem key){
		return query.get(key);
	}
	
	/**
	 * @param key:	an integer in Z_N
	 * @return:		true iff the query hold a pair <key,value>
	 */
	public boolean containsKey(Elem key){
		return query.containsKey(key);
	}
	
	// setters and adders
	
	/**
	 * adder for a new query pair <key,value>
	 * if the key already exits, the value will be replaced with the new one
	 * @param key:		key to insert into the query
	 * @param value:	value for this key (a complex number)
	 */
	public void addChangeValue(Elem key, Complex value){
		if (!containsKey(key))
			this.numOfElements++;
		query.put(key, value);
	}
}
