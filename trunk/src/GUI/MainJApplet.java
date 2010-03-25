package GUI;

import java.awt.Dimension;
import javax.swing.*;


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

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticXPLookAndFeel");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private JLabel jLabelTitle;
	private ButtonGroup buttonGroupQuerySelection = new ButtonGroup();
	private JRadioButton jRadioButtonQuery1;
	private JButton jButtonPhaseNext;
	private JSeparator jSeparator1;
	private JLabel jLabelExplanationTitle;
	private JButton jButtonInputXMLBrowse;
	private JLabel jLabelInputXMLFile;
	private JTextField jTextFieldInputCoeffmAmB;
	private JLabel jLabelInputCoeffDelta;
	private JTextField jTextFieldInputCoeffDelta;
	private JLabel jLabelInputCoeffmAmB;
	private JTextField jTextFieldInputFEuclideanNorm;
	private JTextField jTextFieldInputFInfNorm;
	private JTextField jTextFieldInputXMLFile;
	private JLabel jLabelInputFEuclideanNorm;
	private JLabel jLabelInputFInfNorm;
	private JTextField jTextFieldInputDelta;
	private JTextField jTextFieldInputTau;
	private JTextField jTextFieldInputN;
	private JLabel jLabelInputDelta;
	private JLabel jLabelInputTau;
	private JLabel jLabelInputN;
	private JLabel jLabelExplanation;
	private JRadioButton jRadioButtonQuery2;

	/**
	* Auto-generated main method to display this 
	* JApplet inside a new JFrame.
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
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

	}
	
	public MainJApplet() {
		super();
		// initialize framework variables
		AppletListeners.initAll();
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
				jLabelExplanationTitle.setText("Phase #1");
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
				jLabelExplanation.setBounds(29, 86, 441, 131);
				jLabelExplanation.setVerticalAlignment(JLabel.TOP);
				jLabelExplanation.setFont(new java.awt.Font("Verdana",0,12));
			}
			
			// actions zone
			{
				jRadioButtonQuery1 = new JRadioButton();
				buttonGroupQuerySelection.add(jRadioButtonQuery1);
				getContentPane().add(jRadioButtonQuery1);
				jRadioButtonQuery1.setBounds(29, 314, 337, 28);
				jRadioButtonQuery1.setText("Interactive function query, user supplies f values on demand");
				jRadioButtonQuery1.setBackground(new java.awt.Color(255,255,255));
				
			}
			{
				jRadioButtonQuery2 = new JRadioButton();
				buttonGroupQuerySelection.add(jRadioButtonQuery2);
				getContentPane().add(jRadioButtonQuery2);
				jRadioButtonQuery2.setBounds(29, 343, 337, 28);
				jRadioButtonQuery2.setText("Supply XML with pairs of monoms and their coefficients");
				jRadioButtonQuery2.setBackground(new java.awt.Color(255,255,255));
			}
			{
				jButtonPhaseNext = new JButton();
				getContentPane().add(getJButtonPhase1Next());
				jButtonPhaseNext.setText("Next");
				jButtonPhaseNext.setBounds(381, 341, 80, 30);
			}
			{
				jLabelInputN = new JLabel();
				getContentPane().add(jLabelInputN);
				jLabelInputN.setText("N:");
				jLabelInputN.setBounds(29, 223, 22, 22);
			}
			{
				jLabelInputTau = new JLabel();
				getContentPane().add(jLabelInputTau);
				jLabelInputTau.setText("<html>&tau;:</html>");
				jLabelInputTau.setBounds(29, 251, 22, 22);
			}
			{
				jLabelInputDelta = new JLabel();
				getContentPane().add(jLabelInputDelta);
				jLabelInputDelta.setText("<html>&delta;:</html>");
				jLabelInputDelta.setBounds(29, 279, 22, 22);
			}
			{
				jTextFieldInputN = new JTextField();
				getContentPane().add(jTextFieldInputN);
				jTextFieldInputN.setBounds(51, 223, 71, 22);
			}
			{
				jTextFieldInputTau = new JTextField();
				getContentPane().add(jTextFieldInputTau);
				jTextFieldInputTau.setBounds(51, 251, 71, 22);
			}
			{
				jTextFieldInputDelta = new JTextField();
				getContentPane().add(jTextFieldInputDelta);
				jTextFieldInputDelta.setBounds(51, 279, 71, 22);
			}
			{
				jLabelInputFInfNorm = new JLabel();
				getContentPane().add(jLabelInputFInfNorm);
				jLabelInputFInfNorm.setText("<html>||f||<sub>&infin;</sub>:</html>");
				jLabelInputFInfNorm.setBounds(155, 223, 49, 22);
			}
			{
				jTextFieldInputFInfNorm = new JTextField();
				getContentPane().add(jTextFieldInputFInfNorm);
				jTextFieldInputFInfNorm.setBounds(204, 223, 71, 22);
			}
			{
				jLabelInputFEuclideanNorm = new JLabel();
				getContentPane().add(jLabelInputFEuclideanNorm);
				jLabelInputFEuclideanNorm.setBounds(155, 251, 49, 22);
				jLabelInputFEuclideanNorm.setText("<html>||f||<sub>2</sub>:</html>");
			}
			{
				jTextFieldInputFEuclideanNorm = new JTextField();
				getContentPane().add(jTextFieldInputFEuclideanNorm);
				jTextFieldInputFEuclideanNorm.setBounds(204, 251, 71, 22);
			}
			{
				jLabelInputCoeffDelta = new JLabel();
				getContentPane().add(jLabelInputCoeffDelta);
				jLabelInputCoeffDelta.setText("<html>Coeff for &delta;:</html>");
				jLabelInputCoeffDelta.setBounds(290, 223, 92, 22);
			}
			{
				jLabelInputCoeffmAmB = new JLabel();
				getContentPane().add(jLabelInputCoeffmAmB);
				jLabelInputCoeffmAmB.setBounds(290, 251, 92, 22);
				jLabelInputCoeffmAmB.setText("<html>Coeff for m<sub>A</sub>, m<sub>B</sub>:</html>");
			}
			{
				jTextFieldInputCoeffDelta = new JTextField();
				getContentPane().add(jTextFieldInputCoeffDelta);
				jTextFieldInputCoeffDelta.setBounds(390, 223, 71, 22);
			}
			{
				jTextFieldInputCoeffmAmB = new JTextField();
				getContentPane().add(jTextFieldInputCoeffmAmB);
				jTextFieldInputCoeffmAmB.setBounds(390, 251, 71, 22);
			}
			{
				jLabelInputXMLFile = new JLabel();
				getContentPane().add(jLabelInputXMLFile);
				jLabelInputXMLFile.setText("XML File:");
				jLabelInputXMLFile.setBounds(155, 279, 49, 22);
			}
			{
				jTextFieldInputXMLFile = new JTextField();
				getContentPane().add(jTextFieldInputXMLFile);
				jTextFieldInputXMLFile.setBounds(204, 280, 178, 21);
			}
			{
				jButtonInputXMLBrowse = new JButton();
				getContentPane().add(jButtonInputXMLBrowse);
				jButtonInputXMLBrowse.setText("Browse");
				jButtonInputXMLBrowse.setBounds(390, 280, 71, 22);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// getters and setters
	
	public JButton getJButtonPhase1Next() {
		return jButtonPhaseNext;
	}

	public JLabel getjLabelTitle() {
		return jLabelTitle;
	}

	public ButtonGroup getButtonGroupQuerySelection() {
		return buttonGroupQuerySelection;
	}

	public JRadioButton getjRadioButtonQuery1() {
		return jRadioButtonQuery1;
	}

	public JButton getjButtonPhaseNext() {
		return jButtonPhaseNext;
	}

	public JSeparator getjSeparator1() {
		return jSeparator1;
	}

	public JLabel getjLabelExplanationTitle() {
		return jLabelExplanationTitle;
	}

	public JButton getjButtonInputXMLBrowse() {
		return jButtonInputXMLBrowse;
	}

	public JLabel getjLabelInputXMLFile() {
		return jLabelInputXMLFile;
	}

	public JTextField getjTextFieldInputCoeffmAmB() {
		return jTextFieldInputCoeffmAmB;
	}

	public JLabel getjLabelInputCoeffDelta() {
		return jLabelInputCoeffDelta;
	}

	public JTextField getjTextFieldInputCoeffDelta() {
		return jTextFieldInputCoeffDelta;
	}

	public JLabel getjLabelInputCoeffmAmB() {
		return jLabelInputCoeffmAmB;
	}

	public JTextField getjTextFieldInputFEuclideanNorm() {
		return jTextFieldInputFEuclideanNorm;
	}

	public JTextField getjTextFieldInputFInfNorm() {
		return jTextFieldInputFInfNorm;
	}

	public JTextField getjTextFieldInputXMLFile() {
		return jTextFieldInputXMLFile;
	}

	public JLabel getjLabelInputFEuclideanNorm() {
		return jLabelInputFEuclideanNorm;
	}

	public JLabel getjLabelInputFInfNorm() {
		return jLabelInputFInfNorm;
	}

	public JTextField getjTextFieldInputDelta() {
		return jTextFieldInputDelta;
	}

	public JTextField getjTextFieldInputTau() {
		return jTextFieldInputTau;
	}

	public JTextField getjTextFieldInputN() {
		return jTextFieldInputN;
	}

	public JLabel getjLabelInputDelta() {
		return jLabelInputDelta;
	}

	public JLabel getjLabelInputTau() {
		return jLabelInputTau;
	}

	public JLabel getjLabelInputN() {
		return jLabelInputN;
	}

	public JLabel getjLabelExplanation() {
		return jLabelExplanation;
	}

	public JRadioButton getjRadioButtonQuery2() {
		return jRadioButtonQuery2;
	}
}
