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
			// call main procedure (the first part of the SFT algorithm)
			SFT.runMainSFTAlgorithm(SFT.getN(), SFT.getTau(), SFT.getDelta());
			// call gui updater
			AppletListeners.invokedByPhase1Next();
			Debug.log("invoked gui phase1 next",DebugOutput.STDOUT);
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
			// call main procedure (the second part of the SFT algorithm)
			SFT.runMainSFTAlgorithmCont(SFT.getSets(), SFT.getQuery());
		}
	}
}
