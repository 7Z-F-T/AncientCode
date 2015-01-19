import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Window2 {
	public static void main(String[] args) {
		//frame1 contents
		JFrame frame1=new JFrame("4.2");
		JButton dlgButton=new JButton("Dialog");
		final JTextArea text=new JTextArea("");
		JScrollPane scrPane=new JScrollPane(text,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);	
		frame1.getContentPane().add(dlgButton,"North");
		frame1.getContentPane().add(scrPane,"Center");
		frame1.setSize(200,200);
		frame1.setVisible(true);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame2 contents
		final JFrame frame2=new JFrame("Dialog");
		JButton OKButton=new JButton("OK");
		JLabel label1=new JLabel("        Name");
		JLabel label2=new JLabel("Password");
		final JTextArea text1=new JTextArea(1,15);
		final JTextArea text2=new JTextArea(1,15);
		JPanel panel=new JPanel();
		panel.setLayout(new FlowLayout());
		panel.add(label1);
		panel.add(text1);
		panel.add(label2);
		panel.add(text2);
		frame2.getContentPane().add(panel,"Center");
		frame2.getContentPane().add(OKButton,"South");
		frame2.setSize(250,130);
		//frame2.setVisible(true);
		//events
		dlgButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame2.setVisible(true);
			}
		});
		OKButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				text.append("Name:"+text1.getText()+"\r\n"+"Password:"+
						text2.getText()+"\r\n");
				frame2.setVisible(false);
			}
			
		});
		
		
		
		

	}

}
