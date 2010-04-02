package GUI;

import java.awt.Dimension;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MainJApplet extends javax.swing.JApplet {

	private static JLabel jLabelTitle;
	private static ButtonGroup buttonGroupQuerySelection = new ButtonGroup();
	private static JRadioButton jRadioButtonQuery1;
	private static JRadioButton jRadioButtonQuery2;
	private static JButton jButtonPhase1Next;
	private static JSeparator jSeparator1;
	private static JLabel jLabelExplanationTitle;
	private static JButton jButtonPhase3Restart;
	private static JButton jButtonPhase3Back;
	private static JPanel jPanelPhase3;
	private static JButton jButtonPhase2Back;
	private static JButton jButtonCalcQuery;
	private static JButton jButtonNextPhase2;
	private static JTable jTableUserInput;
	private static JPanel jPanelPhase2;
	private static JLabel jLabelErrorMsgBox;
	private static JPanel jPanelPhase1;
	private static JButton jButtonInputXMLBrowse;
	private static JLabel jLabelInputXMLFile;
	private static JTextField jTextFieldInputCoeffmAmB;
	private static JLabel jLabelInputCoeffDelta;
	private static JTextField jTextFieldInputCoeffDelta;
	private static JLabel jLabelInputCoeffmAmB;
	private static JTextField jTextFieldInputFEuclideanNorm;
	private static JTextField jTextFieldInputFInfNorm;
	private static JTextField jTextFieldInputXMLFile;
	private static JLabel jLabelPhase3SFTOutput;
	private static JLabel jLabelInputFEuclideanNorm;
	private static JLabel jLabelInputFInfNorm;
	private static JTextField jTextFieldInputDelta;
	private static JTextField jTextFieldInputTau;
	private static JTextField jTextFieldInputN;
	private static JLabel jLabelInputDelta;
	private static JLabel jLabelInputTau;
	private static JLabel jLabelInputN;
	private static JLabel jLabelExplanation;
	private static DefaultTableModel jTableModelUserInput;

	
	/* ********************************
	 * 		GUI objects definitions
	 **********************************/
	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	* Auto-generated main method to display this 
	* JApplet inside a new JFrame.
	*/
	public static void main(String[] args) {
		try{
			SwingUtilities.invokeLater(new Runnable() {
			//SwingUtilities.invokeAndWait(new Runnable() {
				public void run() {
					JFrame frame = new JFrame();
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					MainJApplet inst = new MainJApplet();
					frame.getContentPane().add(inst);
					((JComponent)frame.getContentPane()).setPreferredSize(inst.getSize());
					frame.pack();
					frame.setVisible(true);
				}
			});
		}catch(Exception e){
			System.err.println("couldn't invoke and wait");
		}
	}
	
	/**
	 * default constructor
	 */
	public MainJApplet() {
		super();
		// initialize framework variables
		AppletListeners.initPhasesExplanation();
		// initialize GUI
		initGUI();
	}
	
	private void initGUI() {
		try {
			setSize(new Dimension(500, 400));
			getContentPane().setLayout(null);
			getContentPane().setBackground(new java.awt.Color(255,255,255));

			// Main title
			{
				jLabelTitle = new JLabel();
				getContentPane().add(jLabelTitle);
				jLabelTitle.setText("Learning and Coding Theory exercise - The SFT Algorithm");
				jLabelTitle.setBounds(12, 12, 476, 36);
				jLabelTitle.setFont(new java.awt.Font("Verdana",1,10));
				jLabelTitle.setHorizontalAlignment(JLabel.CENTER);
			}
			
			// text zone
			{
				jLabelExplanationTitle = new JLabel();
				getContentPane().add(jLabelExplanationTitle);
				jLabelExplanationTitle.setText(AppletListeners.phasesExplanationTitle.get("phase1"));
				jLabelExplanationTitle.setBounds(29, 54, 441, 21);
				jLabelExplanationTitle.setFont(new java.awt.Font("Verdana",1,14));
			}
			{
				jSeparator1 = new JSeparator();
				getContentPane().add(jSeparator1);
				jSeparator1.setBounds(29, 75, 441, 10);
			}
			{
				jLabelExplanation = new JLabel();
				getContentPane().add(jLabelExplanation);
				jLabelExplanation.setText(AppletListeners.phasesExplanation.get("phase1"));
				jLabelExplanation.setBounds(29, 86, 441, 117);
				jLabelExplanation.setVerticalAlignment(JLabel.TOP);
				jLabelExplanation.setFont(new java.awt.Font("Verdana",0,12));
			}
			{
				jLabelErrorMsgBox = new JLabel();
				getContentPane().add(jLabelErrorMsgBox);
				jLabelErrorMsgBox.setBounds(82, 379, 337, 18);
				jLabelErrorMsgBox.setText("");
				jLabelErrorMsgBox.setHorizontalAlignment(JLabel.CENTER);
			}
			
			// actions zone
			/* *************/
			
			// Phase #1
			{
				jPanelPhase1 = new JPanel();
				getContentPane().add(jPanelPhase1);
				jPanelPhase1.setBounds(3, 210, 495, 188);
				jPanelPhase1.setBackground(new java.awt.Color(255,255,255));
				jPanelPhase1.setLayout(null);
				{
					jButtonPhase1Next = new JButton();
					jPanelPhase1.add(jButtonPhase1Next);
					jButtonPhase1Next.setText("Next");
					jButtonPhase1Next.setBounds(378, 131, 80, 30);
					jButtonPhase1Next.setFont(AppletListeners.fontMainApplet);
				}
				{
					jLabelInputN = new JLabel();
					jPanelPhase1.add(jLabelInputN);
					jLabelInputN.setText("N:");
					jLabelInputN.setBounds(26, 13, 22, 22);
					jLabelInputN.setFont(AppletListeners.fontMainApplet);
				}
				{
					jLabelInputTau = new JLabel();
					jPanelPhase1.add(jLabelInputTau);
					jLabelInputTau.setText("<html>&tau;:</html>");
					jLabelInputTau.setBounds(26, 41, 22, 22);
					jLabelInputTau.setFont(AppletListeners.fontMainApplet);
				}
				{
					jLabelInputDelta = new JLabel();
					jPanelPhase1.add(jLabelInputDelta);
					jLabelInputDelta.setText("<html>&delta;:</html>");
					jLabelInputDelta.setBounds(26, 69, 22, 22);
					jLabelInputDelta.setFont(AppletListeners.fontMainApplet);
				}
				{
					jTextFieldInputN = new JTextField();
					jPanelPhase1.add(jTextFieldInputN);
					jTextFieldInputN.setBounds(48, 13, 71, 22);
					jTextFieldInputN.setFont(AppletListeners.fontMainApplet);
				}
				{
					jTextFieldInputTau = new JTextField();
					jPanelPhase1.add(jTextFieldInputTau);
					jTextFieldInputTau.setBounds(48, 41, 71, 22);
					jTextFieldInputTau.setFont(AppletListeners.fontMainApplet);
				}
				{
					jTextFieldInputDelta = new JTextField();
					jPanelPhase1.add(jTextFieldInputDelta);
					jTextFieldInputDelta.setBounds(48, 69, 71, 22);
					jTextFieldInputDelta.setFont(AppletListeners.fontMainApplet);
				}
				{
					jLabelInputFInfNorm = new JLabel();
					jPanelPhase1.add(jLabelInputFInfNorm);
					jLabelInputFInfNorm.setText("<html>||&fnof;||<sub>&infin;</sub>:</html>");
					jLabelInputFInfNorm.setBounds(152, 13, 49, 22);
					jLabelInputFInfNorm.setFont(AppletListeners.fontMainApplet);
				}
				{
					jTextFieldInputFInfNorm = new JTextField();
					jPanelPhase1.add(jTextFieldInputFInfNorm);
					jTextFieldInputFInfNorm.setBounds(201, 13, 71, 22);
					jTextFieldInputFInfNorm.setFont(AppletListeners.fontMainApplet);
				}
				{
					jLabelInputFEuclideanNorm = new JLabel();
					jPanelPhase1.add(jLabelInputFEuclideanNorm);
					jLabelInputFEuclideanNorm.setBounds(152, 41, 49, 22);
					jLabelInputFEuclideanNorm.setText("<html>||&fnof;||<sub>2</sub>:</html>");
					jLabelInputFEuclideanNorm.setFont(AppletListeners.fontMainApplet);
				}
				{
					jTextFieldInputFEuclideanNorm = new JTextField();
					jPanelPhase1.add(jTextFieldInputFEuclideanNorm);
					jTextFieldInputFEuclideanNorm.setBounds(201, 41, 71, 22);
					jTextFieldInputFEuclideanNorm.setFont(AppletListeners.fontMainApplet);
				}
				{
					jLabelInputCoeffDelta = new JLabel();
					jPanelPhase1.add(jLabelInputCoeffDelta);
					jLabelInputCoeffDelta.setText("<html>Coeff for &delta;:</html>");
					jLabelInputCoeffDelta.setBounds(287, 13, 92, 22);
					jLabelInputCoeffDelta.setFont(AppletListeners.fontMainApplet);
				}
				{
					jLabelInputCoeffmAmB = new JLabel();
					jPanelPhase1.add(jLabelInputCoeffmAmB);
					jLabelInputCoeffmAmB.setBounds(287, 41, 92, 22);
					jLabelInputCoeffmAmB.setText("<html>Coeff for m<sub>A</sub>, m<sub>B</sub>:</html>");
					jLabelInputCoeffmAmB.setFont(AppletListeners.fontMainApplet);
				}
				{
					jTextFieldInputCoeffDelta = new JTextField();
					jPanelPhase1.add(jTextFieldInputCoeffDelta);
					jTextFieldInputCoeffDelta.setBounds(387, 13, 71, 22);
					jTextFieldInputCoeffDelta.setFont(AppletListeners.fontMainApplet);
				}
				{
					jTextFieldInputCoeffmAmB = new JTextField();
					jPanelPhase1.add(jTextFieldInputCoeffmAmB);
					jTextFieldInputCoeffmAmB.setBounds(387, 41, 71, 22);
					jTextFieldInputCoeffmAmB.setFont(AppletListeners.fontMainApplet);
				}
			}
			
			// Phase #2
			{
				jPanelPhase2 = new JPanel();
				getContentPane().add(jPanelPhase2);
				jPanelPhase2.setBounds(3, 136, 495, 262);				
				jPanelPhase2.setBackground(new java.awt.Color(255,255,255));
				jPanelPhase2.setLayout(null);
				{
					jRadioButtonQuery1 = new JRadioButton();
					jPanelPhase2.add(jRadioButtonQuery1);
					buttonGroupQuerySelection.add(jRadioButtonQuery1);
					jRadioButtonQuery1.setBounds(26, 10, 339, 22);
					jRadioButtonQuery1.setText("<html>Interactive function query, user supplies &fnof; values on demand</html>");
					jRadioButtonQuery1.setBackground(new java.awt.Color(255,255,255));
					jRadioButtonQuery1.setFont(AppletListeners.fontMainApplet);
					jRadioButtonQuery1.setSelected(true);
					
				}
				{
					jRadioButtonQuery2 = new JRadioButton();
					jPanelPhase2.add(jRadioButtonQuery2);
					buttonGroupQuerySelection.add(jRadioButtonQuery2);
					jRadioButtonQuery2.setBounds(26, 36, 339, 24);
					jRadioButtonQuery2.setText("Supply XML with pairs of monoms and their coefficients");
					jRadioButtonQuery2.setBackground(new java.awt.Color(255,255,255));
					jRadioButtonQuery2.setFont(AppletListeners.fontMainApplet);
				}
				{
					jLabelInputXMLFile = new JLabel();
					jPanelPhase2.add(jLabelInputXMLFile);
					jLabelInputXMLFile.setText("XML File:");
					jLabelInputXMLFile.setBounds(26, 69, 49, 21);
					jLabelInputXMLFile.setFont(AppletListeners.fontMainApplet);
					jLabelInputXMLFile.setVisible(false);
				}
				{
					jTextFieldInputXMLFile = new JTextField();
					jPanelPhase2.add(jTextFieldInputXMLFile);
					jTextFieldInputXMLFile.setBounds(87, 69, 178, 21);
					jTextFieldInputXMLFile.setFont(AppletListeners.fontMainApplet);
					jTextFieldInputXMLFile.setVisible(false);
					//jTextFieldInputXMLFile.setEditable(false);
				}
				{
					jButtonInputXMLBrowse = new JButton();
					jPanelPhase2.add(jButtonInputXMLBrowse);
					jButtonInputXMLBrowse.setText("Browse");
					jButtonInputXMLBrowse.setBounds(277, 69, 71, 21);
					jButtonInputXMLBrowse.setFont(AppletListeners.fontMainApplet);
					jButtonInputXMLBrowse.setVisible(false);
				}
				{
					jButtonCalcQuery = new JButton();
					jPanelPhase2.add(jButtonCalcQuery);
					jButtonCalcQuery.setText("Calculate Values");
					jButtonCalcQuery.setBounds(354, 69, 90, 21);
					jButtonCalcQuery.setFont(AppletListeners.fontMainApplet);
					jButtonCalcQuery.setEnabled(false);
					jButtonCalcQuery.setVisible(false);
				}
				{
					jButtonPhase2Back = new JButton();
					jPanelPhase2.add(jButtonPhase2Back);
					jButtonPhase2Back.setText("Back");
					jButtonPhase2Back.setBounds(378, 171, 80, 28);
					jButtonPhase2Back.setFont(AppletListeners.fontMainApplet);
				}
				{
					jButtonNextPhase2 = new JButton();
					jPanelPhase2.add(jButtonNextPhase2);
					jButtonNextPhase2.setText("Next");
					jButtonNextPhase2.setBounds(378, 206, 80, 30);
					jButtonNextPhase2.setFont(AppletListeners.fontMainApplet);
				}
				{
					String[] columnNames = {"x","<html>&fnof;(x) real part</html>","<html>&fnof;(x) imaginary part</html>"};
					jTableModelUserInput = new DefaultTableModel(null,columnNames);
					jTableUserInput = new JTable();
					jTableUserInput.setModel(jTableModelUserInput);
					jTableUserInput.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
					JScrollPane pane = new JScrollPane(jTableUserInput);
					pane.setBounds(10, 102, 348, 134);
					jPanelPhase2.add(pane);
				}
				jPanelPhase2.setVisible(false);
			}
			
			// Phase #3
			{
				jPanelPhase3 = new JPanel();
				getContentPane().add(jPanelPhase3);
				jPanelPhase3.setBounds(3, 210, 495, 188);
				jPanelPhase3.setBackground(new java.awt.Color(255,255,255));
				jPanelPhase3.setLayout(null);
				{
					jButtonPhase3Back = new JButton();
					jPanelPhase3.add(jButtonPhase3Back);
					jButtonPhase3Back.setText("Back");
					jButtonPhase3Back.setBounds(378, 97, 80, 28);
					jButtonPhase3Back.setFont(AppletListeners.fontMainApplet);
				}
				{
					jButtonPhase3Restart = new JButton();
					jPanelPhase3.add(jButtonPhase3Restart);
					jButtonPhase3Restart.setText("Restart");
					jButtonPhase3Restart.setBounds(378, 131, 80, 29);
				}
				{
					jLabelPhase3SFTOutput = new JLabel();
					jPanelPhase3.add(jLabelPhase3SFTOutput);
					jLabelPhase3SFTOutput.setBounds(12, 12, 348, 148);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// initialize listeners
		AppletListeners.initAppletListeners();
	}
	
	// getters and setters

	public static JLabel getjLabelTitle() {
		return jLabelTitle;
	}

	public static ButtonGroup getButtonGroupQuerySelection() {
		return buttonGroupQuerySelection;
	}

	public static JRadioButton getjRadioButtonQuery1() {
		return jRadioButtonQuery1;
	}

	public static JButton getjButtonPhase1Next() {
		return jButtonPhase1Next;
	}

	public static JSeparator getjSeparator1() {
		return jSeparator1;
	}

	public static JLabel getjLabelExplanationTitle() {
		return jLabelExplanationTitle;
	}

	public static JButton getjButtonInputXMLBrowse() {
		return jButtonInputXMLBrowse;
	}

	public static JLabel getjLabelInputXMLFile() {
		return jLabelInputXMLFile;
	}

	public static JTextField getjTextFieldInputCoeffmAmB() {
		return jTextFieldInputCoeffmAmB;
	}

	public static JLabel getjLabelInputCoeffDelta() {
		return jLabelInputCoeffDelta;
	}

	public static JTextField getjTextFieldInputCoeffDelta() {
		return jTextFieldInputCoeffDelta;
	}

	public static JLabel getjLabelInputCoeffmAmB() {
		return jLabelInputCoeffmAmB;
	}

	public static JTextField getjTextFieldInputFEuclideanNorm() {
		return jTextFieldInputFEuclideanNorm;
	}

	public static JTextField getjTextFieldInputFInfNorm() {
		return jTextFieldInputFInfNorm;
	}

	public static JTextField getjTextFieldInputXMLFile() {
		return jTextFieldInputXMLFile;
	}

	public static JLabel getjLabelInputFEuclideanNorm() {
		return jLabelInputFEuclideanNorm;
	}

	public static JLabel getjLabelInputFInfNorm() {
		return jLabelInputFInfNorm;
	}

	public static JTextField getjTextFieldInputDelta() {
		return jTextFieldInputDelta;
	}

	public static JTextField getjTextFieldInputTau() {
		return jTextFieldInputTau;
	}

	public static JTextField getjTextFieldInputN() {
		return jTextFieldInputN;
	}

	public static JLabel getjLabelInputDelta() {
		return jLabelInputDelta;
	}

	public static JLabel getjLabelInputTau() {
		return jLabelInputTau;
	}

	public static JLabel getjLabelInputN() {
		return jLabelInputN;
	}

	public static JLabel getjLabelExplanation() {
		return jLabelExplanation;
	}

	public static JRadioButton getjRadioButtonQuery2() {
		return jRadioButtonQuery2;
	}

	public static JLabel getjLabelErrorMsgBox() {
		return jLabelErrorMsgBox;
	}

	public static JPanel getjPanelPhase1() {
		return jPanelPhase1;
	}

	public static void setjPanelPhase1(JPanel jPanelPhase1) {
		MainJApplet.jPanelPhase1 = jPanelPhase1;
	}

	public static JPanel getjPanelPhase2() {
		return jPanelPhase2;
	}

	public static void setjPanelPhase2(JPanel jPanelPhase2user) {
		MainJApplet.jPanelPhase2 = jPanelPhase2user;
	}

	public static JTable getjTableUserInput() {
		return jTableUserInput;
	}

	public static void setjTableUserInput(JTable jTableUserInput) {
		MainJApplet.jTableUserInput = jTableUserInput;
	}

	public static DefaultTableModel getjTableModelUserInput() {
		return jTableModelUserInput;
	}

	public static void setjTableModelUserInput(DefaultTableModel jTableUserInputModel) {
		MainJApplet.jTableModelUserInput = jTableUserInputModel;
	}

	public static JButton getjButtonCalcQuery() {
		return jButtonCalcQuery;
	}

	public static void setjButtonCalcQuery(JButton jButtonCalcQuery) {
		MainJApplet.jButtonCalcQuery = jButtonCalcQuery;
	}

	public static JButton getjButtonNextPhase2() {
		return jButtonNextPhase2;
	}

	public static void setjButtonNextPhase2(JButton jButtonNextPhase2) {
		MainJApplet.jButtonNextPhase2 = jButtonNextPhase2;
	}

	public static JButton getjButtonPhase2Back() {
		return jButtonPhase2Back;
	}

	public static void setjButtonPhase2Back(JButton jButtonPhase2Back) {
		MainJApplet.jButtonPhase2Back = jButtonPhase2Back;
	}

	public static JPanel getjPanelPhase3() {
		return jPanelPhase3;
	}

	public static void setjPanelPhase3(JPanel jPanelPhase3) {
		MainJApplet.jPanelPhase3 = jPanelPhase3;
	}

	public static JButton getjButtonPhase3Back() {
		return jButtonPhase3Back;
	}

	public static void setjButtonPhase3Back(JButton jButtonPhase3Back) {
		MainJApplet.jButtonPhase3Back = jButtonPhase3Back;
	}

	public static JButton getjButtonPhase3Restart() {
		return jButtonPhase3Restart;
	}

	public static void setjButtonPhase3Restart(JButton jButtonPhase3Restart) {
		MainJApplet.jButtonPhase3Restart = jButtonPhase3Restart;
	}

	public static JLabel getjLabelPhase3SFTOutput() {
		return jLabelPhase3SFTOutput;
	}

	public static void setjLabelPhase3SFTOutput(JLabel jLabelPhase3SFTOutput) {
		MainJApplet.jLabelPhase3SFTOutput = jLabelPhase3SFTOutput;
	}
}