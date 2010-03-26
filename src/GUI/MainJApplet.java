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

	private static JLabel jLabelTitle;
	private static ButtonGroup buttonGroupQuerySelection = new ButtonGroup();
	private static JRadioButton jRadioButtonQuery1;
	private static JButton jButtonPhase1Next;
	private static JSeparator jSeparator1;
	private static JLabel jLabelExplanationTitle;
	private static JTabbedPane jTabbedPaneMain;
	private static JLabel jLabelPhase1ErrorMsgBox;
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
	private static JLabel jLabelInputFEuclideanNorm;
	private static JLabel jLabelInputFInfNorm;
	private static JTextField jTextFieldInputDelta;
	private static JTextField jTextFieldInputTau;
	private static JTextField jTextFieldInputN;
	private static JLabel jLabelInputDelta;
	private static JLabel jLabelInputTau;
	private static JLabel jLabelInputN;
	private static JLabel jLabelExplanation;
	private static JRadioButton jRadioButtonQuery2;

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
					jRadioButtonQuery1.setBounds(26, 110, 337, 23);
					jRadioButtonQuery1.setText("<html>Interactive function query, user supplies &fnof; values on demand</html>");
					jRadioButtonQuery1.setBackground(new java.awt.Color(255,255,255));
					jRadioButtonQuery1.setFont(AppletListeners.fontMainApplet);
					jRadioButtonQuery1.setSelected(true);
					
				}
				{
					jRadioButtonQuery2 = new JRadioButton();
					jPanelPhase1.add(jRadioButtonQuery2);
					buttonGroupQuerySelection.add(jRadioButtonQuery2);
					jRadioButtonQuery2.setBounds(26, 134, 337, 23);
					jRadioButtonQuery2.setText("Supply XML with pairs of monoms and their coefficients");
					jRadioButtonQuery2.setBackground(new java.awt.Color(255,255,255));
					jRadioButtonQuery2.setFont(AppletListeners.fontMainApplet);
				}
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
				{
					jLabelInputXMLFile = new JLabel();
					jPanelPhase1.add(jLabelInputXMLFile);
					jLabelInputXMLFile.setText("XML File:");
					jLabelInputXMLFile.setBounds(152, 69, 49, 22);
					jLabelInputXMLFile.setFont(AppletListeners.fontMainApplet);
					jLabelInputXMLFile.setVisible(false);
				}
				{
					jTextFieldInputXMLFile = new JTextField();
					jPanelPhase1.add(jTextFieldInputXMLFile);
					jTextFieldInputXMLFile.setBounds(201, 70, 178, 21);
					jTextFieldInputXMLFile.setFont(AppletListeners.fontMainApplet);
					jTextFieldInputXMLFile.setVisible(false);
				}
				{
					jButtonInputXMLBrowse = new JButton();
					jPanelPhase1.add(jButtonInputXMLBrowse);
					jButtonInputXMLBrowse.setText("Browse");
					jButtonInputXMLBrowse.setBounds(387, 70, 71, 22);
					jButtonInputXMLBrowse.setFont(AppletListeners.fontMainApplet);
					jButtonInputXMLBrowse.setVisible(false);
				}
				{
					jLabelPhase1ErrorMsgBox = new JLabel();
					jPanelPhase1.add(jLabelPhase1ErrorMsgBox);
					jLabelPhase1ErrorMsgBox.setBounds(79, 169, 337, 18);
					jLabelPhase1ErrorMsgBox.setText("");
					jLabelPhase1ErrorMsgBox.setHorizontalAlignment(JLabel.CENTER);
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

	public static JLabel getjLabelPhase1ErrorMsgBox() {
		return jLabelPhase1ErrorMsgBox;
	}

	public static JPanel getjPanelPhase1() {
		return jPanelPhase1;
	}

	public static void setjPanelPhase1(JPanel jPanelPhase1) {
		MainJApplet.jPanelPhase1 = jPanelPhase1;
	}
}
