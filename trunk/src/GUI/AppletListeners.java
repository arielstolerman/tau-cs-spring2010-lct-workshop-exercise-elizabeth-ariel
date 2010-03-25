package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.*;
import org.eclipse.swt.widgets.*;

import SFT.*;

import java.util.*;

public class AppletListeners {
	public static Map<String,String> phasesExplanation = new HashMap<String,String>();
	public static java.awt.Font fontMainApplet = new Font("Tahoma", Font.PLAIN, 11);
	
	/**
	 * initialize all static parameters and values
	 */
	public static void initAll(){
		initPhasesExplanation();
	}
	
	/**
	 * initialize the phases explanation used in the applet
	 * to explain each phase in the application run
	 */
	public static void initPhasesExplanation(){
		String phase1 =	"<html>" +
				"Insert an integer N representing the function's domain group, τ the <br>" +
				"threshold, δ the error probability, ||&fnof;||<sub>&infin;</sub> the infinity norm of &fnof;,<br>" +
				"||&fnof;||<sub>2</sub> the Euclidean norm of &fnof;, a constant coefficient for &delta; calculation<br>" +
				"and a constant coefficient for m<sub>A</sub> and m<sub>B</sub>.<br>" +
				"Then select the method of the function query." +
				"</html>";
		
		phasesExplanation.put("phase1",phase1);
	}
	
	/* ***********************
	 * 		Listeners
	 *************************/
	
	/**
	 * initialize all listeners in the applet
	 */
	public static void initAppletListeners(){
		// Phase #1 listeners
		/* **************** */
		
		// query option #1 selected
		MainJApplet.getjRadioButtonQuery1().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						setPhase1XMLVisible(false);						
					}
				}
		);
		
		// query option #2 selected
		MainJApplet.getjRadioButtonQuery2().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						setPhase1XMLVisible(true);						
					}
				}
		);
		
		// phase #1 NEXT button clicked
		MainJApplet.getjButtonPhase1Next().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// check inputs and set parameters //TODO
						phase1NextButtonValidateSetFields();
					}
				}
		);
		
		// browse XML button clicked
		MainJApplet.getjButtonInputXMLBrowse().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
					}
				}
		);
	}
	
	/* ****************************
	 * 		Listeners actions
	 ******************************/
	
	// phase #1 listeners actions
	/* *********************** */
	
	/**
	 * @param text:	text to wrap
	 * @return:		wrap text with html tags to make it red
	 */
	public static String wrapRed(String text){
		return "<html><font color=\"red\">"+text+"</font></html>";
	}
	
	public static boolean phase1NextButtonValidateSetFields(){
		// validate all user inputs and set SFT fields accordingly
		
		// validate that N is a positive integer (long) greater than 0
		try{
			long N = Long.parseLong(MainJApplet.getjTextFieldInputN().getText());
			if (N <= 0) throw new NumberFormatException();
			// N is ok, set it
			else SFT.setN(N);
		} catch (NumberFormatException nfe){
			// N is not a number
			MainJApplet.getjLabelPhase1ErrorMsgBox().setText(wrapRed("N must be a positive integer"));
			return false;
		}
		
		// validate that tau is a positive double //TODO between 0 and 1?
		try{
			double tau = Double.parseDouble(MainJApplet.getjTextFieldInputTau().getText());
			if (tau <= 0) throw new NumberFormatException();
			// tau is ok, set it
			else SFT.setTau(tau);
		} catch (NumberFormatException nfe){
			MainJApplet.getjLabelPhase1ErrorMsgBox().setText(wrapRed("&tau; must be a positive double")); //TODO correct?
			return false;
		}
		
		// validate that delta is in (0,1)
		try{
			double delta = Double.parseDouble(MainJApplet.getjTextFieldInputDelta().getText());
			if (delta <= 0 || delta >= 1) throw new NumberFormatException();
			// delta is ok, set it
			else SFT.setDelta(delta);
		} catch (NumberFormatException nfe){
			MainJApplet.getjLabelPhase1ErrorMsgBox().setText(wrapRed("&delta; must be a double in (0,1)"));
			return false;
		}
		
		// validate that ||f||_infinity is a positive double
		try{
			double fInfNorm = Double.parseDouble(MainJApplet.getjTextFieldInputFInfNorm().getText());
			if (fInfNorm <= 0) throw new NumberFormatException();
			// fInfNorm is ok, set it
			else SFT.setfInfNorm(fInfNorm);
		} catch (NumberFormatException nfe){
			MainJApplet.getjLabelPhase1ErrorMsgBox().setText(wrapRed("||f||<sub>&infin;</sub> must be a positive double"));
			return false;
		}
		
		// validate that ||f||_2 is a positive double
		try{
			double fEuclideanNorm = Double.parseDouble(MainJApplet.getjTextFieldInputFEuclideanNorm().getText());
			if (fEuclideanNorm <= 0) throw new NumberFormatException();
			// fEuclideanNorm is ok, set it
			else SFT.setfEuclideanNorm(fEuclideanNorm);
		} catch (NumberFormatException nfe){
			MainJApplet.getjLabelPhase1ErrorMsgBox().setText(wrapRed("||f||<sub>2</sub> must be a positive double"));
			return false;
		}
		
		// validate that delta calculation coefficient is a positive double
		try{
			double deltaCalculationConst = Double.parseDouble(MainJApplet.getjTextFieldInputCoeffDelta().getText());
			if (deltaCalculationConst <= 0) throw new NumberFormatException();
			// deltaCalculationConst is ok, set it
			else SFT.setDeltaCalculationConst(deltaCalculationConst);
		} catch (NumberFormatException nfe){
			MainJApplet.getjLabelPhase1ErrorMsgBox().setText(wrapRed("&delta;'s coefficient must be a positive double"));
			return false;
		}
		
		// validate that delta calculation coefficient is a positive double
		try{
			double m_A_m_B_CalculationConst = Double.parseDouble(MainJApplet.getjTextFieldInputCoeffmAmB().getText());
			if (m_A_m_B_CalculationConst <= 0) throw new NumberFormatException();
			// m_A_m_B_CalculationConst is ok, set it
			else SFT.setM_A_m_B_CalculationConst(m_A_m_B_CalculationConst);
		} catch (NumberFormatException nfe){
			MainJApplet.getjLabelPhase1ErrorMsgBox().setText(wrapRed("m<sub>A</sub> and m<sub>B</sub> coefficient must be a positive double"));
			return false;
		}
		
		// all fields validated and set, may go on
		MainJApplet.getjLabelPhase1ErrorMsgBox().setText("");
		return true;
	}
	
	/**
	 * sets the visibility of the XML input fields in phase 1
	 * @param visible:	visibility flag for XML input fields
	 */
	public static void setPhase1XMLVisible(boolean visible){
		MainJApplet.getjLabelInputXMLFile().setVisible(visible);
		MainJApplet.getjTextFieldInputXMLFile().setVisible(visible);
		MainJApplet.getjButtonInputXMLBrowse().setVisible(visible);
	}
	
}
