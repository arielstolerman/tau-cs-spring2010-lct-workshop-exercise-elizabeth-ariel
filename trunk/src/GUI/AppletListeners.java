package GUI;

import java.util.*;

public class AppletListeners {
	public static Map<String,String> phasesExplanation = new HashMap<String,String>();
	
	/**
	 * initialize all static parameters and values
	 */
	public static void initAll(){
		initPhasesExplanation();
	}
	
	/**
	 * initialize all the applet listeners
	 */
	public static void initAllListeners(){
		//TODO
	}
	
	/**
	 * initialize the phases explanation used in the applet
	 * to explain each phase in the application run
	 */
	public static void initPhasesExplanation(){
		String phase1 =	"<html>" +
				"Insert an integer N representing the function's domain group, τ the <br>" +
				"threshold and δ the error probability. Then select the method of the function query." +
				"</html>";
		
		phasesExplanation.put("phase1",phase1);
	}
}
