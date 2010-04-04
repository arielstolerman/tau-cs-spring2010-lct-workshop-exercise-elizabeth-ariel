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

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Elem {
	public long value;
	
	/**
	 * default constructors
	 */
	public Elem(long value){
		this.value = value;
	}
	public Elem(double value){
		this.value = (long)value;
	}

	// getters
	
	/**
	 * @return:		the value
	 */
	public long getValue(){
		return value;
	}
	
	public String toString(){
		return String.valueOf(value);
	}
	
	// setters
	
	/**
	 * setter for the value
	 * @param value:	new value
	 */
	public void setValue(long value){
		this.value = value;
	}
	
	// static functions on elements
	// mathematical functions modulo the group order
	// Set<Elem> operations
	
	// Math operations:
	
	/**
	 * addition modulo N
	 * @param elem1:		first element for addition
	 * @param elem2:		second element for addition
	 * @return:				(elem1 + elem2) mod N
	 */
	public static Elem add(Elem elem1, Elem elem2){
		return new Elem((elem1.value + elem2.value)%SFT.getN());
	}
	
	/**
	 * subtraction modulo N
	 * @param elem1:		first element for sub
	 * @param elem2:		second element for sub
	 * @return:				(elem1 - elem2) mod N
	 */
	public static Elem sub(Elem elem1, Elem elem2){
		long value = elem1.value - elem2.value;
		if (value < 0) value += SFT.getN();
		return new Elem(value);
	}
	
	/**
	 * multiplication modulo N
	 * @param elem1:		first element for mul
	 * @param elem2:		second element for mul
	 * @return:				(elem1 * elem2) mod N
	 */
	public static Elem mul(Elem elem1, Elem elem2){
		return new Elem((elem1.value * elem2.value)%SFT.getN());
	}
		
	// Set<Elem> operations:
	
	/**
	 * @param set:	set of Elem
	 * @param elem:	element to check if contained in the set
	 * @return:		true iff elem is already ontained in the set
	 */
	public static boolean contains(Set<Elem> set, Elem elem){
		for(Elem e: set){
			if (e.getValue() == elem.getValue())
				return true;
		}
		
		return false;
	}
	
	/**
	 * @param source:	source set of elements
	 * @return:			a deep copy of the source set
	 */
	public static Set<Elem> copyElemSet(Set<Elem> source){
		Set<Elem> dest = new HashSet<Elem>();
		for (Iterator<Elem> j = source.iterator(); j.hasNext();){
			dest.add(new Elem(j.next().getValue()));
		}
		return dest;
	}
	
	/**
	 * removes all elements in source that have the same value as some element in toRemove
	 * @param source:		source set of elements to be changed
	 * @param toRemove:		set of elements to be removed from source
	 */
	public static void removeElemsFromSet(Set<Elem> source, Set<Elem> toRemove){
		for (Elem elemToRemove: toRemove){
			for (Elem elem: source){
				if (elem.getValue() == elemToRemove.getValue()){
					source.remove(elem);
					break; // assuming source set has exactly one element with that value
				}
			}
		}
	}
}
