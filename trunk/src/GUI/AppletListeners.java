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
				"threshold, δ the error probability, ||f||<sub>&infin;</sub> the infinity norm of f,<br>" +
				"||f||<sub>2</sub> the Euclidean norm of f, a constant coefficient for &delta; calculation<br>" +
				"and a constant coefficient for m<sub>A</sub> and m<sub>B</sub>.<br>" +
				"Then select the method of the function query." +
				"</html>";
		
		phasesExplanation.put("phase1",phase1);
	}
}
