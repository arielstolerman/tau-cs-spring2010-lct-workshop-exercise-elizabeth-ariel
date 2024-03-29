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

import java.io.File;
import java.util.*;
import Utils.Polynomial;

public class Query {
	private Map<Long,Complex> query;
	private int numOfElements;
	private final long N;
	
	private static File xmlFile = null;
	private static Map<String,Polynomial> polynomials = null;
	
	/**
	 * default constructor for a query object
	 * @param N:	the size of the domain Z_N
	 */
	public Query(long N){
		this.query = new HashMap<Long,Complex>();
		this.numOfElements = 0;
		this.N = N;
	}
	
	/**
	 * constructor for query with initial values
	 * @param query:	initial query values
	 * @param N:	the size of the domain Z_N
	 */
	public Query(long N, HashMap<Long,Complex> query){
		this.query = query;
		this.numOfElements = query.size();
		this.N = N;
	}
	
	// getters
	
	/**
	 * @return:		the query map
	 */
	public Map<Long,Complex> getQuery(){
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
	public Complex getValue(Long key){
		return query.get(key);
	}
	
	/**
	 * @param key:	an integer in Z_N
	 * @return:		true iff the query hold a pair <key,value>
	 */
	public boolean containsKey(Long key){
		return query.containsKey(key);
	}
	
	// setters and adders
	
	/**
	 * adder for a new query pair <key,value>
	 * if the key already exits, the value will be replaced with the new one
	 * @param key:		key to insert into the query
	 * @param value:	value for this key (a complex number)
	 */
	public void addChangeValue(Long key, Complex value){
		if (!containsKey(key))
			this.numOfElements++;
		query.put(key, value);
	}

	public static File getXmlFile() {
		return xmlFile;
	}

	public static void setXmlFile(File xmlFile) {
		Query.xmlFile = xmlFile;
	}

	public static void setPolynomials(Map<String,Polynomial> polynomials) {
		Query.polynomials = polynomials;
	}

	public static Map<String,Polynomial> getPolynomials() {
		return polynomials;
	}
}
