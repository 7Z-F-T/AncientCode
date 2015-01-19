import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Window3 {
	public static void main(String[] args) {
		final JFrame frame=new JFrame("4.3");
		JPanel panel=new JPanel();
		JButton openButton=new JButton("open");
		JButton saveButton=new JButton("save");
		panel.add(openButton);
		panel.add(saveButton);
		final JTextArea text=new JTextArea();
		
		frame.getContentPane().add(panel,"North");
		frame.getContentPane().add(new JScrollPane(text,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200,230);
		frame.setVisible(true);

		ActionListener openListen=new ActionListener(){
			public void actionPerformed(ActionEvent e){
			    JFileChooser c=new JFileChooser();
			    int rVal=c.showOpenDialog(frame);
			    if(rVal==JFileChooser.APPROVE_OPTION)
				    text.setText(c.getSelectedFile().toString());
		    }
		};
		
		openButton.addActionListener(openListen);
	}

}
