/**
 * 
 * Workshop Learning & Coding Theory Exercise
 * TAU, Spring Semester 2010
 * Elizabeth Firman and Ariel Stolerman
 * 
 * Filename: Elem.java
 * Description: code for class Elem, representation of element in Z_N
 * 
 */

package SFT;

public class Elem {
	// static members
	private static long N = 2;
	
	// dynamic members
	public long value;
	
	/**
	 * default constructor
	 */
	public Elem(long value){
		this.value = value;
	}

	// getters
	
	/**
	 * @return:		the order N
	 */
	public long getOrder(){
		return N;
	}
	
	// setters
	
	/**
	 * @param N:	new order for Z_N
	 */
	public void setOrder(long N){
		Elem.N= N;
	}
	
	// static functions on elements
	// mathematical functions modulo the group order
	
	/**
	 * addition modulo N
	 * @param elem1:		first element for addition
	 * @param elem2:		second element for addition
	 * @return:				(elem1 + elem2) mod N
	 */
	public static Elem add(Elem elem1, Elem elem2){
		return new Elem((elem1.value + elem2.value)%N);
	}
	
	/**
	 * subtraction modulo N
	 * @param elem1:		first element for sub
	 * @param elem2:		second element for sub
	 * @return:				(elem1 - elem2) mod N
	 */
	public static Elem sub(Elem elem1, Elem elem2){
		long value = (elem1.value - elem2.value)%N;
		if (value < 0)
			value += N;
		return new Elem(value);
	}
	
	/**
	 * multiplication modulo N
	 * @param elem1:		first element for mul
	 * @param elem2:		second element for mul
	 * @return:				(elem1 * elem2) mod N
	 */
	public static Elem mul(Elem elem1, Elem elem2){
		return new Elem((elem1.value * elem2.value)%N);
	}
	
	/**
	 * division modulo N
	 * @param elem1:		first element for div
	 * @param elem2:		second element for div
	 * @return:				(elem1 * elem2^-1) mod N
	 */
	public static Elem div(Elem elem1, Elem elem2){
		//TODO
		return new Elem((elem1.value * elem2.value)%N);
	}
}
