package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;

import org.xml.sax.SAXException;

import SFT.*;
import Utils.Debug;
import Utils.Polynomial;
import Utils.XMLParser;

import java.util.*;

public class AppletListeners {
	public static int tmp = 0;
	public static Map<String,String> phasesExplanation = new HashMap<String,String>();
	public static Map<String,String> phasesExplanationTitle = new HashMap<String,String>();
	public static java.awt.Font fontMainApplet = new Font("Tahoma", Font.PLAIN, 11);
	
	private static SFTRunner sftRunner = new SFTRunner();
	private static Thread sftThread = null;
	
	/* **************************
	 * 		initializers
	 ****************************/
	
	/**
	 * initialize the phases explanation used in the applet
	 * to explain each phase in the application run
	 */
	public static void initPhasesExplanation(){
		Debug.log("AppletListeners -> initPhasesExplanation started");
		
		// initialize explanations
		String phase1 =	"<html>" +
				"Insert an integer N representing the function's domain group, τ the <br>" +
				"threshold, δ the error probability, ||&fnof;||<sub>&infin;</sub> the infinity norm of &fnof;,<br>" +
				"||&fnof;||<sub>2</sub> the Euclidean norm of &fnof;, a constant coefficient for &delta; calculation<br>" +
				"and a constant coefficient for m<sub>A</sub> and m<sub>B</sub>.<br>" +
				"</html>";
		String phase2 = "<html>For each x below, please fill in its &fnof;-value or select an XML file.</html>";
		String phase3 = "<html>" +
			"The significant Fourier coefficients are now being calculated and will be displayed below." +
			"</html>";
		
		phasesExplanation.put("phase1",phase1);
		phasesExplanation.put("phase2",phase2);
		phasesExplanation.put("phase3",phase3);
		
		// initialize explanations titles 
		String phase1Title = "Phase #1";
		String phase2Title = "Phase #2";
		String phase3Title = "Phase #3";
		
		phasesExplanationTitle.put("phase1", phase1Title);
		phasesExplanationTitle.put("phase2", phase2Title);
		phasesExplanationTitle.put("phase3", phase3Title);
		
		Debug.log("AppletListeners -> initPhasesExplanation completed");
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
						Debug.log("AppletListeners -> phase #1 NEXT button clicked");
						
						// check inputs and set parameters
						if (phase1NextButtonValidateSetFields()){
							sftThread = (new Thread(sftRunner.new RunMainSFTAlgorithm()));
							sftThread.start();
							switchToPhase2();
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
						Debug.log("AppletListeners -> user input option selected");
						
						setPhase2XMLVisible(false);
					}
				}
		);
		
		// query option #2 selected
		MainJApplet.getjRadioButtonQuery2().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Debug.log("AppletListeners -> XML input option selected");
						setPhase2XMLVisible(true);
					}
				}
		);
		
		// browse XML button clicked
		MainJApplet.getjButtonInputXMLBrowse().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Debug.log("AppletListeners -> browse for XML file button clicked");
						
						openFileDialog();
					}
				}
		);
		
		// XML input text changed
		MainJApplet.getjTextFieldInputXMLFile().addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				Debug.log("AppletListeners -> XML input text changed");
				
				setCalcButtonOnTextChange();
			}
		});
				
		// calculate f-values via XML button clicked
		MainJApplet.getjButtonCalcQuery().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Debug.log("AppletListeners -> calculate via XML button clicked");
						
						calcValuesFromXML();
					}
				}
		);
		
		// phase #2 BACK button clicked
		MainJApplet.getjButtonPhase2Back().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Debug.log("AppletListeners -> phase #2 BACK button clicked");
						
						switchBackToPhase1();
					}
				}
		);
		
		// phase #2 NEXT button clicked
		MainJApplet.getjButtonNextPhase2().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Debug.log("AppletListeners -> phase #2 NEXT button clicked");
						
						if (validatePhase2Inputs()){
							sftThread = (new Thread(sftRunner.new RunMainSFTAlgorithmCont()));
							sftThread.start();
							switchToPhase3();
						}
					}
				}
		);
		
		// Phase #3 listeners
		/* **************** */
		
		// phase #3 BACK button clicked
		MainJApplet.getjButtonPhase3Back().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Debug.log("AppletListeners -> phase #3 BACK button clicked");
						
						switchBackToPhase2();
					}
				}
		);
		
		// phase #3 RESTART button clicked
		MainJApplet.getjButtonPhase3Restart().addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Debug.log("AppletListeners -> phase #3 RESTART button clicked");
						
						switchBackToPhase1();
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
		Debug.log("AppletListeners -> phase1NextButtonValidateSetFields started");
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
			
			Debug.log("AppletListeners -> phase1NextButtonValidateSetFields completed: bad N");
			
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
			
			Debug.log("AppletListeners -> phase1NextButtonValidateSetFields completed: bad tau");
			
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
			
			Debug.log("AppletListeners -> phase1NextButtonValidateSetFields completed: bad delta");
			
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
			
			Debug.log("AppletListeners -> phase1NextButtonValidateSetFields completed: bad ||f||_infinity");
			
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
			
			Debug.log("AppletListeners -> phase1NextButtonValidateSetFields completed: bad ||f||_2");
			
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
			
			Debug.log("AppletListeners -> phase1NextButtonValidateSetFields completed: bad delta coeff");
			
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
			
			Debug.log("AppletListeners -> phase1NextButtonValidateSetFields completed: bad mA,mB coeff");
			
			return false;
		}
		
		// all fields validated and set, may go on
		MainJApplet.getjLabelErrorMsgBox().setText("");
		Debug.log("\tAll input checks completed successfuly");
		Debug.log("AppletListeners -> phase1NextButtonValidateSetFields completed");
		
		return true;
	}
	
	/**
	 * switch view to phase #2 - user input or xml input
	 */
	public static void switchToPhase2(){
		Debug.log("AppletListeners -> switchToPhase2 started");
		
		// switch to phase2-user view
		MainJApplet.getjPanelPhase1().setVisible(false);
		MainJApplet.getjPanelPhase2().setVisible(true);
		// set explanation
		MainJApplet.getjLabelExplanationTitle().setText(phasesExplanationTitle.get("phase2"));
		MainJApplet.getjLabelExplanation().setText(phasesExplanation.get("phase2"));
		
		Debug.log("AppletListeners -> switchToPhase2 completed");
	}
	
	// phase #2 listeners actions
	/* *************************/
	
	/**
	 * sets the visibility of the XML input fields in phase 1
	 * @param visible:	visibility flag for XML input fields
	 */
	public static void setPhase2XMLVisible(boolean visible){
		Debug.log("AppletListeners -> setPhase2XMLVisible started");
		
		MainJApplet.getjLabelInputXMLFile().setVisible(visible);
		MainJApplet.getjTextFieldInputXMLFile().setVisible(visible);
		MainJApplet.getjButtonInputXMLBrowse().setVisible(visible);
		MainJApplet.getjButtonCalcQuery().setVisible(visible);
		MainJApplet.getjTableUserInput().setEnabled(!visible);
		
		Debug.log("AppletListeners -> setPhase2XMLVisible completed");
	}
	
	/**
	 * open file dialog box to browse for an XML file
	 */
	public static void openFileDialog(){
		Debug.log("AppletListeners -> openFileDialog started");
		
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
			Debug.log("\tXML file opened: "+Query.getXmlFile().getPath());
        } else {
            Debug.log("\tXML file open canceled");
        }
		
		Debug.log("AppletListeners -> openFileDialog completed");
	}
	
	/**
	 * enables the calculate button on text change
	 * disables it on empty text filed
	 */
	public static void setCalcButtonOnTextChange(){
		boolean set = MainJApplet.getjTextFieldInputXMLFile().getText().equals("");
		MainJApplet.getjButtonCalcQuery().setEnabled(!set);
	}
	
	/**
	 * calculates the f-values using according to the input XML file
	 * if the XML is invalid, notifies the user
	 */
	public static void calcValuesFromXML(){
		Debug.log("AppletListeners -> calcValuesFromXML started");
		
		try{
			// parse the XML file and create list of polynomials for f-calculation
			XMLParser parser = new XMLParser();
			parser.parseDocument();
			
			// remove all rows from the table
			MainJApplet.getjTableModelUserInput().setRowCount(0);
			Query query = new Query(SFT.getN());
			
			// case random - randomly choose a function from a list of function for each element in Q
			if (Query.getPolynomials().size() > 1){
				
				for(Elem elem: SFT.getQ()){
					int randIndex = (int)Math.ceil(Math.random()*Query.getPolynomials().size());
					
					// calculate the value
					Complex value = Query.getPolynomials().get(randIndex+"").getValue(elem);
					
					// update query
					query.addChangeValue(elem.getValue(), value);
					
					// update table
					String[] row = new String[]{elem.toString(),value.getRe()+"",value.getIm()+""};
					MainJApplet.getjTableModelUserInput().addRow(row);
				}
			}
			
			// otherwise - calculate using the polynomial whose id is selected
			else {
				for (Polynomial p: Query.getPolynomials().values()){ // will be only one
					for(Elem elem: SFT.getQ()){
						// calculate the value
						Complex value = p.getValue(elem);
						
						// update query
						query.addChangeValue(elem.getValue(), value);
						
						// update table
						String[] row = new String[]{elem.toString(),value.getRe()+"",value.getIm()+""};
						MainJApplet.getjTableModelUserInput().addRow(row);
					}
				}
			}
			
			// set query
			SFT.setQuery(query);
			
		} catch (Exception e){
			MainJApplet.getjLabelErrorMsgBox().setText(wrapRed(e.getMessage()));
		}
		
		Debug.log("AppletListeners -> calcValuesFromXML completed");
	}
	
	/**
	 * switch back to phase #1, invoked by clicking on phase #2 back button
	 * stops SFT thread if running switches the view
	 */
	public static void switchBackToPhase1(){
		Debug.log("AppletListeners -> switchBackToPhase1 started");
		
		// switch to phase1 - user input
		MainJApplet.getjPanelPhase1().setVisible(true);
		MainJApplet.getjPanelPhase2().setVisible(false);
		MainJApplet.getjPanelPhase3().setVisible(false);
		// set explanation
		MainJApplet.getjLabelExplanationTitle().setText(phasesExplanationTitle.get("phase1"));
		MainJApplet.getjLabelExplanation().setText(phasesExplanation.get("phase1"));
		// clear error label
		MainJApplet.getjLabelErrorMsgBox().setText("");
		
		// stop SFT thread if running
		if (sftThread != null){
			sftThread.stop();
		}
		sftThread = null;
		
		// clear SFT's query
		SFT.setQuery(null);
		
		// clear phase #2's table and phase #3's result label
		MainJApplet.getjTableModelUserInput().setRowCount(0);
		MainJApplet.getjLabelPhase3SFTOutput().setText("");
		
		Debug.log("AppletListeners -> switchBackToPhase1 completed");
	}

	// phase #3 listeners actions
	/* *************************/
	
	/**
	 * validates the f-values inputs for each element in the table in phase #2
	 * @return:	true iff the validation has completed successfuly
	 */
	public static boolean validatePhase2Inputs(){
		Debug.log("AppletListeners -> validatePhase2Inputs started");
		
		// if the user-input option is selected, verify that all values have input
		if (MainJApplet.getjRadioButtonQuery1().isSelected()){
			DefaultTableModel model = MainJApplet.getjTableModelUserInput();
			for (int i=0; i<model.getRowCount(); i++){
				String x = (String)model.getValueAt(i, 0);
				String re = (String)model.getValueAt(i, 1);
				String im = (String)model.getValueAt(i, 2);
				
				try{
					Long.parseLong(x);
					Double.parseDouble(re);
					Double.parseDouble(im);
				} catch (NumberFormatException nfe){
					MainJApplet.getjLabelErrorMsgBox().setText(wrapRed("Inputs incorrect, check all inputs"));
					return false;
				}
			}
		}
		// otherwise check that a calculation (based on an XML input) has been done
		else {
			if (SFT.getQuery() == null){
				MainJApplet.getjLabelErrorMsgBox().setText(wrapRed("No calculation is done. click the \"Calculate...\" button"));
				return false;
			}
		}
		
		// validation completed successfuly
		MainJApplet.getjLabelErrorMsgBox().setText("");
		Debug.log("AppletListeners -> validatePhase2Inputs completed");
		return true;
	}
	
	/**
	 * switches the view from phase #2 to phase #3
	 */
	public static void switchToPhase3(){
		Debug.log("AppletListeners -> switchToPhase3 started");
		
		// switch to phase2-user view
		MainJApplet.getjPanelPhase2().setVisible(false);
		MainJApplet.getjPanelPhase3().setVisible(true);
		// set explanation
		MainJApplet.getjLabelExplanationTitle().setText(phasesExplanationTitle.get("phase3"));
		MainJApplet.getjLabelExplanation().setText(phasesExplanation.get("phase3"));
		
		Debug.log("AppletListeners -> switchToPhase3 completed");
	}
	
	/**
	 * switches the view back from phase #3 to phase #2 and stops SFT calculation in the back 
	 */
	public static void switchBackToPhase2(){
		Debug.log("AppletListeners -> switchBackToPhase2 started");
		
		// switch to phase2-user view
		MainJApplet.getjPanelPhase2().setVisible(true);
		MainJApplet.getjPanelPhase3().setVisible(false);
		// set explanation
		MainJApplet.getjLabelExplanationTitle().setText(phasesExplanationTitle.get("phase2"));
		MainJApplet.getjLabelExplanation().setText(phasesExplanation.get("phase2"));
		
		// stop SFT thread if running
		if (sftThread != null){
			sftThread.stop();
		}
		sftThread = null;
		
		// clear phase #3's result label
		MainJApplet.getjLabelPhase3SFTOutput().setText("");
		
		Debug.log("AppletListeners -> switchBackToPhase2 completed");
	}
	
	/**
	 * sets the output label to display the SFT algorithm output - elements in L
	 */
	public static void setSFTOutputLabel(){
		String output = "The significant Fourier coefficients are:\n";
		Set<Elem> L = SFT.getL();
		for (Elem elem: L){
			output += elem.getValue()+"\n";
		}
		
		MainJApplet.getjLabelPhase3SFTOutput().setText(output);
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
