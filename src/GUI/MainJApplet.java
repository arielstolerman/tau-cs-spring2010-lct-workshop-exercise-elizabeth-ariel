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
	private JRadioButton[] jRadioButtonQuerySelect;
	private JRadioButton jRadioButtonQuery1;
	private JButton jButtonPhaseNext;
	private JSeparator jSeparator1;
	private JLabel jLabelExplanationTitle;
	private JLabel jLabelExplanation;
	private JRadioButton jRadioButtonQuery2;
	private JRadioButton jRadioButtonQuery3;

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
				jRadioButtonQuery1.setBounds(24, 257, 366, 28);
				jRadioButtonQuery1.setText("Interactive function query, user supplies f values on demand");
				jRadioButtonQuery1.setBackground(new java.awt.Color(255,255,255));
			}
			{
				jRadioButtonQuery2 = new JRadioButton();
				buttonGroupQuerySelection.add(jRadioButtonQuery2);
				getContentPane().add(jRadioButtonQuery2);
				jRadioButtonQuery2.setBounds(24, 286, 366, 28);
				jRadioButtonQuery2.setText("Supply XML with pairs of monoms and thier coefficients");
				jRadioButtonQuery2.setBackground(new java.awt.Color(255,255,255));
			}
			{
				jRadioButtonQuery3 = new JRadioButton();
				buttonGroupQuerySelection.add(jRadioButtonQuery3);
				getContentPane().add(jRadioButtonQuery3);
				jRadioButtonQuery3.setBounds(24, 315, 366, 28);
				jRadioButtonQuery3.setText("Randomly select f values");
				jRadioButtonQuery3.setBackground(new java.awt.Color(255,255,255));
			}
			{
				jButtonPhaseNext = new JButton();
				getContentPane().add(getJButtonPhase1Next());
				jButtonPhaseNext.setText("Next");
				jButtonPhaseNext.setBounds(390, 343, 80, 30);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public JButton getJButtonPhase1Next() {
		return jButtonPhaseNext;
	}
}
