package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import SFT.*;
import Utils.Debug;
import Utils.Debug.DebugOutput;

import java.util.*;

public class AppletListeners {
	public static Map<String,String> phasesExplanation = new HashMap<String,String>();
	public static Map<String,String> phasesExplanationTitle = new HashMap<String,String>();
	public static java.awt.Font fontMainApplet = new Font("Tahoma", Font.PLAIN, 11);
	
	private static SFTRunner sftRunner = new SFTRunner();
	
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
		// initialize explanations
		String phase1 =	"<html>" +
				"Insert an integer N representing the function's domain group, τ the <br>" +
				"threshold, δ the error probability, ||&fnof;||<sub>&infin;</sub> the infinity norm of &fnof;,<br>" +
				"||&fnof;||<sub>2</sub> the Euclidean norm of &fnof;, a constant coefficient for &delta; calculation<br>" +
				"and a constant coefficient for m<sub>A</sub> and m<sub>B</sub>.<br>" +
				"</html>";
		String phase2 = "<html>For each x below, please fill in its &fnof;-value or select an XML file.</html>";
		
		phasesExplanation.put("phase1",phase1);
		phasesExplanation.put("phase2",phase2);
		
		// initialize explanations titles 
		String phase1Title = "Phase #1";
		String phase2Title = "Phase #2";
		
		phasesExplanationTitle.put("phase1", phase1Title);
		phasesExplanationTitle.put("phase2", phase2Title);
	}
	
	/* ***************************************************************
	 * 		methods called by the SFT once calculations are done
	 * 		to invoke the GUI
	 *****************************************************************/
	
	public static void invokedByPhase1Next(){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				// switch to phase #2 view
				switchToPhase2();
			}
		});
	}
	
	public static void invokedByPhase2NextUserInteractive(){
		//TODO
	}
	
	public static void invokedByPhase2NextXML(){
		//TODO
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
		
		// phase #1 NEXT button clicked
		MainJApplet.getjButtonPhase1Next().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// check inputs and set parameters
						if (phase1NextButtonValidateSetFields()){
							//SFT.runMainSFTAlgorithm(SFT.getN(), SFT.getTau(), SFT.getDelta());
							(new Thread(sftRunner.new RunMainSFTAlgorithm())).start();
						}
					}
				}
		);
		
		// Phase #2 listeners
		/* **************** */
		
		// query option #1 selected
		MainJApplet.getjRadioButtonQuery1().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						setPhase2XMLVisible(false);
					}
				}
		);
		
		// query option #2 selected
		MainJApplet.getjRadioButtonQuery2().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						setPhase2XMLVisible(true);
					}
				}
		);
		
		// browse XML button clicked
		MainJApplet.getjButtonInputXMLBrowse().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						openFileDialog();
					}
				}
		);
		
		// calculate f-values via XML button clicked
		MainJApplet.getjButtonCalcQuery().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						//TODO
					}
				}
		);
		
		// phase #2 NEXT button clicked
		MainJApplet.getjButtonNextPhase2().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						//TODO
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
	
	/**
	 * validate all input fields and set the corresponding inputs
	 * @return:		true iff all inputs are inserted correctly
	 */
	public static boolean phase1NextButtonValidateSetFields(){
		Debug.log("AppletListeners::phase1NextButtonValidateSetFields was called",DebugOutput.STDOUT);
		// validate all user inputs and set SFT fields accordingly
		
		// validate that N is a positive integer (long) greater than 0
		try{
			long N = Long.parseLong(MainJApplet.getjTextFieldInputN().getText());
			if (N <= 0) throw new NumberFormatException();
			// N is ok, set it
			else SFT.setN(N);
		} catch (NumberFormatException nfe){
			// N is not a number
			MainJApplet.getjLabelErrorMsgBox().setText(wrapRed("N must be a positive integer"));
			return false;
		}
		
		// validate that tau is a positive double //TODO between 0 and 1?
		try{
			double tau = Double.parseDouble(MainJApplet.getjTextFieldInputTau().getText());
			if (tau <= 0) throw new NumberFormatException();
			// tau is ok, set it
			else SFT.setTau(tau);
		} catch (NumberFormatException nfe){
			MainJApplet.getjLabelErrorMsgBox().setText(wrapRed("&tau; must be a positive double")); //TODO correct?
			return false;
		}
		
		// validate that delta is in (0,1)
		try{
			double delta = Double.parseDouble(MainJApplet.getjTextFieldInputDelta().getText());
			if (delta <= 0 || delta >= 1) throw new NumberFormatException();
			// delta is ok, set it
			else SFT.setDelta(delta);
		} catch (NumberFormatException nfe){
			MainJApplet.getjLabelErrorMsgBox().setText(wrapRed("&delta; must be a double in (0,1)"));
			return false;
		}
		
		// validate that ||f||_infinity is a positive double
		try{
			double fInfNorm = Double.parseDouble(MainJApplet.getjTextFieldInputFInfNorm().getText());
			if (fInfNorm <= 0) throw new NumberFormatException();
			// fInfNorm is ok, set it
			else SFT.setfInfNorm(fInfNorm);
		} catch (NumberFormatException nfe){
			MainJApplet.getjLabelErrorMsgBox().setText(wrapRed("||f||<sub>&infin;</sub> must be a positive double"));
			return false;
		}
		
		// validate that ||f||_2 is a positive double
		try{
			double fEuclideanNorm = Double.parseDouble(MainJApplet.getjTextFieldInputFEuclideanNorm().getText());
			if (fEuclideanNorm <= 0) throw new NumberFormatException();
			// fEuclideanNorm is ok, set it
			else SFT.setfEuclideanNorm(fEuclideanNorm);
		} catch (NumberFormatException nfe){
			MainJApplet.getjLabelErrorMsgBox().setText(wrapRed("||f||<sub>2</sub> must be a positive double"));
			return false;
		}
		
		// validate that delta calculation coefficient is a positive double
		try{
			double deltaCalculationConst = Double.parseDouble(MainJApplet.getjTextFieldInputCoeffDelta().getText());
			if (deltaCalculationConst <= 0) throw new NumberFormatException();
			// deltaCalculationConst is ok, set it
			else SFT.setDeltaCalculationConst(deltaCalculationConst);
		} catch (NumberFormatException nfe){
			MainJApplet.getjLabelErrorMsgBox().setText(wrapRed("&delta;'s coefficient must be a positive double"));
			return false;
		}
		
		// validate that delta calculation coefficient is a positive double
		try{
			double m_A_m_B_CalculationConst = Double.parseDouble(MainJApplet.getjTextFieldInputCoeffmAmB().getText());
			if (m_A_m_B_CalculationConst <= 0) throw new NumberFormatException();
			// m_A_m_B_CalculationConst is ok, set it
			else SFT.setM_A_m_B_CalculationConst(m_A_m_B_CalculationConst);
		} catch (NumberFormatException nfe){
			MainJApplet.getjLabelErrorMsgBox().setText(wrapRed("m<sub>A</sub> and m<sub>B</sub> coefficient must be a positive double"));
			return false;
		}
		
		// all fields validated and set, may go on
		MainJApplet.getjLabelErrorMsgBox().setText("");
		Debug.log("All input checks completed successfuly",DebugOutput.STDOUT);
		return true;
	}
	
	/**
	 * switch view to phase #2 - user input or xml input
	 */
	public static void switchToPhase2(){
		Debug.log("AppletListeners::switchToPhase2 was called",DebugOutput.STDOUT);
		
		// switch to phase2-user view
		MainJApplet.getjPanelPhase1().setVisible(false);
		MainJApplet.getjPanelPhase2().setVisible(true);
		// set explanation
		MainJApplet.getjLabelExplanationTitle().setText(phasesExplanationTitle.get("phase2"));
		MainJApplet.getjLabelExplanation().setText(phasesExplanation.get("phase2"));
		// set table contents
		

		{
			// initialize list of elements for which f-values are to be fetched
			String[] columnNames = {"x","<html>&fnof;</html>(x) real part","<html>&fnof;</html>(x) imaginary part"};
			String[][] tableContent = new String[SFT.getQ().size()][3];
			int i=0;
			for (Elem elem: SFT.getQ()){
				tableContent[i][0] = elem.toString();
				tableContent[i][1] = "";
				tableContent[i][2] = "";
				i++;
			}
			
			MainJApplet.setjTableUserInputModel(new DefaultTableModel(tableContent,columnNames));
			MainJApplet.setjTableUserInput(new JTable());
			MainJApplet.getjPanelPhase2().add(MainJApplet.getjTableUserInput());
			MainJApplet.getjTableUserInput().setModel(MainJApplet.getjTableUserInputModel());
			MainJApplet.getjTableUserInput().setBounds(10, 102, 348, 152);
			//TODO set header for table
		}
	}
	
	// phase #2 listeners actions
	/* *************************/
	
	/**
	 * sets the visibility of the XML input fields in phase 1
	 * @param visible:	visibility flag for XML input fields
	 */
	public static void setPhase2XMLVisible(boolean visible){
		Debug.log("AppletListeners::setPhase2XMLVisible was called",DebugOutput.STDOUT);
		MainJApplet.getjLabelInputXMLFile().setVisible(visible);
		MainJApplet.getjTextFieldInputXMLFile().setVisible(visible);
		MainJApplet.getjButtonInputXMLBrowse().setVisible(visible);
		MainJApplet.getjButtonCalcQuery().setVisible(visible);
	}
	
	/**
	 * open file dialog box to browse for an XML file
	 */
	public static void openFileDialog(){
		Debug.log("AppletListeners::openFileDialog was called",DebugOutput.STDOUT);
		
		JFileChooser xmlFileChoose = new JFileChooser();
		javax.swing.filechooser.FileFilter filter = new javax.swing.filechooser.FileFilter() {
			
			@Override
			public String getDescription() {
				return "XML files";
			}
			
			@Override
			public boolean accept(File f) {
				 return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml");
			}
		};
		xmlFileChoose.setFileFilter(filter);
		int returnVal = xmlFileChoose.showOpenDialog(MainJApplet.getjPanelPhase1());
		
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			Query.setXmlFile(xmlFileChoose.getSelectedFile());
			MainJApplet.getjTextFieldInputXMLFile().setText(Query.getXmlFile().getPath());
			MainJApplet.getjLabelErrorMsgBox().setText("");
			MainJApplet.getjButtonCalcQuery().setEnabled(true);
			Debug.log("XML file openned: "+Query.getXmlFile().getPath(),DebugOutput.STDOUT);
        } else {
            Debug.log("XML file open canceled",DebugOutput.STDOUT);
        }
	}

	/**
	 * validate all input fields and set the corresponding inputs
	 * @return:		true iff all inputs are inserted correctly
	 */
	public static boolean phase2NextButtonValidateSetFields(){
		// if xml input is selected, check that an input is given
		if (MainJApplet.getjRadioButtonQuery2().isSelected()){
			if (Query.getXmlFile() == null){
				MainJApplet.getjLabelErrorMsgBox().setText(wrapRed("Must enter an XML file"));
				return false;
			}
		}
		
		// set Query is user interactive flag
		Query.setUserInteractive(MainJApplet.getjRadioButtonQuery1().isSelected());
		return true; //TODO
	}
	
	// getters and setters
	/* ******************/
	
	public static Map<String, String> getPhasesExplanation() {
		return phasesExplanation;
	}

	public static void setPhasesExplanation(Map<String, String> phasesExplanation) {
		AppletListeners.phasesExplanation = phasesExplanation;
	}

	public static Map<String, String> getPhasesExplanationTitle() {
		return phasesExplanationTitle;
	}

	public static void setPhasesExplanationTitle(
			Map<String, String> phasesExplanationTitle) {
		AppletListeners.phasesExplanationTitle = phasesExplanationTitle;
	}
}
