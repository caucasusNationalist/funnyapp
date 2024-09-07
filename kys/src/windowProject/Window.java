package windowProject;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.File;
//import java.awt.event.WindowEvent;
//import java.io.IOException;
//import java.awt.event.WindowListener;
//import java.net.URISyntaxException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import javax.swing.JButton;

public class Window implements ActionListener, Runnable {
	JFrame frame;
	JLabel label;
	JPanel panel;
	JButton button;
	GridBagConstraints buttonConstr;
	GridBagConstraints labelConstr;
	int dialogueIterator = 1;
	int size = 300;
	int restartCount = 0;

	String[] text0 = { "hey", "I lied so like", "it is actually a virus",
			"you've got like 10 minutes to figure this out" };
	String[] text1 = { "vro it's borked I'm sorry", "the app is fucked" };
	String[] text2 = { "the timer does not reset even if you try real hard", "" };
	String[] text3 = { "" };
	String[][] text = { text0, text1, text2, text3 };

	public Window(int size, int arg) {
		this.size = size;
		restartCount = arg;

	}

	public void run() {
		frame = new JFrame(":)");
		System.out.println("window open!");
//		frame.addWindowListener(this);
		panel = new JPanel();
		label = new JLabel(text[restartCount][0]);
		button = new JButton("next");
		button.addActionListener(this);

		buttonConstr = new GridBagConstraints();
		buttonConstr.gridx = 2;
		buttonConstr.gridy = 3;

		labelConstr = new GridBagConstraints();
		labelConstr.gridx = 2;
		labelConstr.gridy = 2;

		panel.setLayout(new GridBagLayout());
		button.setSize(50, 50);
		label.setSize(200, 100);

		panel.add(label, labelConstr);
		panel.add(button, buttonConstr);
		frame.add(panel);
		panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		frame.setSize(size, size);
		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		frame.setVisible(true);
//        Runtime.getRuntime().addShutdownHook(new Thread(){
//        	public void run() {
//        		try {
//					Process p  = Runtime.getRuntime().exec("java -jar" + getRunningLocation());
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//        	}
//        });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (dialogueIterator != text[restartCount].length) {
			label.setText(text[restartCount][dialogueIterator]);
			dialogueIterator++;
			return;
		}
//		 try {
//		 shutdown(600);
//		 } catch (IOException e1) {
//		 e1.printStackTrace();
//		 }
	}

}