/**
 * 
 * Workshop Learning & Coding Theory Exercise
 * TAU, Spring Semester 2010
 * Elizabeth Firman and Ariel Stolerman
 * 
 * Filename: SFT.java
 * Description: code for class SFTRunner, implementation of Running the SFT algorithm using threads
 * 				for convenient interface with the gui (Applet)
 * 
 */

package SFT;

import GUI.AppletListeners;
import GUI.MainJApplet;
import Utils.Debug;
import Utils.Debug.DebugOutput;

public class SFTRunner {
	
	public class RunMainSFTAlgorithm implements Runnable{
		
		/**
		 * default constructor
		 */
		public RunMainSFTAlgorithm(){}
		
		/**
		 * main thread procedure
		 * runs the first part of the SFT algorithm - preparing the set of elements in Z_N
		 * for which f-values are to be set by the user
		 */
		public void run(){
			Debug.log("SFTRunner -> RunMainSFTAlgorithm thread started");
			
			// call main procedure (the first part of the SFT algorithm)
			SFT.runMainSFTAlgorithm(SFT.getN(), SFT.getTau(), SFT.getDelta());			

			// update gui: set table contents
			// initialize list of elements for which f-values are to be fetched)
			for (Elem elem: SFT.getQ()){
				String[] row = new String[]{elem.toString(),"",""};
				MainJApplet.getjTableModelUserInput().addRow(row);
			}
			
			Debug.log("SFTRunner -> RunMainSFTAlgorithm thread completed");
		}
	}
	
	public class RunMainSFTAlgorithmCont implements Runnable{
		
		/**
		 * default constructor
		 */
		public RunMainSFTAlgorithmCont(){}
		
		/**
		 * main thread procedure
		 * runs the second part of the SFT algorithm - using the query set by the user,
		 * calculates the significant Fourier coefficients of f
		 */
		public void run(){
			Debug.log("SFTRunner -> RunMainSFTAlgorithmCont thread started");
			
			// call main procedure (the second part of the SFT algorithm)
			SFT.runMainSFTAlgorithmCont(SFT.getSets(), SFT.getQuery());
			
			// display the output
			AppletListeners.setSFTOutputLabel();
			
			Debug.log("SFTRunner -> RunMainSFTAlgorithmCont thread completed");
		}
	}
}
