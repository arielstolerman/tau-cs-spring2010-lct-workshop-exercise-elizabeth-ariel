package SFT;

import java.awt.Dimension;

import javax.swing.JApplet;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


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
	private JLabel jLabelTitle;
	private JLabel jLabelExplanation;

	/**
	* Auto-generated main method to display this 
	* JApplet inside a new JFrame.
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
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
		initGUI();
	}
	
	private void initGUI() {
		try {
			setSize(new Dimension(500, 400));
			getContentPane().setLayout(null);
			{
				jLabelTitle = new JLabel();
				getContentPane().add(jLabelTitle);
				jLabelTitle.setText("Learning and Coding Theory exercise - The SFT Algorithm");
				jLabelTitle.setBounds(12, 12, 476, 36);
				jLabelTitle.setFont(new java.awt.Font("Tahoma",1,14));
			}
			{
				jLabelExplanation = new JLabel();
				getContentPane().add(jLabelExplanation);
				jLabelExplanation.setBounds(12, 60, 476, 89);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
