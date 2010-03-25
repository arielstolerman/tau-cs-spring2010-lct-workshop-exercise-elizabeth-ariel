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
	private JTabbedPane jTabbedPaneMain;
	private JPanel jPanelPhase1;
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
				jLabelExplanation.setBounds(29, 86, 441, 117);
				jLabelExplanation.setVerticalAlignment(JLabel.TOP);
				jLabelExplanation.setFont(new java.awt.Font("Verdana",0,12));
			}
			
			// actions zone
			{
				jPanelPhase1 = new JPanel();
				getContentPane().add(jPanelPhase1);
				jPanelPhase1.setBounds(3, 210, 495, 188);
				jPanelPhase1.setBackground(new java.awt.Color(255,255,255));
				jPanelPhase1.setLayout(null);
				{
					jRadioButtonQuery1 = new JRadioButton();
					jPanelPhase1.add(jRadioButtonQuery1);
					buttonGroupQuerySelection.add(jRadioButtonQuery1);
					jRadioButtonQuery1.setBounds(26, 104, 337, 28);
					jRadioButtonQuery1.setText("Interactive function query, user supplies f values on demand");
					jRadioButtonQuery1.setBackground(new java.awt.Color(255,255,255));
					jRadioButtonQuery1.setFont(AppletListeners.fontMainApplet);
					
				}
				{
					jRadioButtonQuery2 = new JRadioButton();
					jPanelPhase1.add(jRadioButtonQuery2);
					buttonGroupQuerySelection.add(jRadioButtonQuery2);
					jRadioButtonQuery2.setBounds(26, 133, 337, 28);
					jRadioButtonQuery2.setText("Supply XML with pairs of monoms and their coefficients");
					jRadioButtonQuery2.setBackground(new java.awt.Color(255,255,255));
					jRadioButtonQuery2.setFont(AppletListeners.fontMainApplet);
				}
				{
					jButtonPhaseNext = new JButton();
					jPanelPhase1.add(jButtonPhaseNext);
					jButtonPhaseNext.setText("Next");
					jButtonPhaseNext.setBounds(378, 131, 80, 30);
					jButtonPhaseNext.setFont(AppletListeners.fontMainApplet);
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
					jLabelInputFInfNorm.setText("<html>||f||<sub>&infin;</sub>:</html>");
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
					jLabelInputFEuclideanNorm.setText("<html>||f||<sub>2</sub>:</html>");
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
				{
					jLabelInputXMLFile = new JLabel();
					jPanelPhase1.add(jLabelInputXMLFile);
					jLabelInputXMLFile.setText("XML File:");
					jLabelInputXMLFile.setBounds(152, 69, 49, 22);
					jLabelInputXMLFile.setFont(AppletListeners.fontMainApplet);
				}
				{
					jTextFieldInputXMLFile = new JTextField();
					jPanelPhase1.add(jTextFieldInputXMLFile);
					jTextFieldInputXMLFile.setBounds(201, 70, 178, 21);
					jTextFieldInputXMLFile.setFont(AppletListeners.fontMainApplet);
				}
				{
					jButtonInputXMLBrowse = new JButton();
					jPanelPhase1.add(jButtonInputXMLBrowse);
					jButtonInputXMLBrowse.setText("Browse");
					jButtonInputXMLBrowse.setBounds(387, 70, 71, 22);
					jButtonInputXMLBrowse.setFont(AppletListeners.fontMainApplet);
				}
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
