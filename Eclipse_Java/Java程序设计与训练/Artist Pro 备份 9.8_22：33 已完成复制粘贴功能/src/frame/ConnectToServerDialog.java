package frame;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
public class ConnectToServerDialog extends JDialog implements ActionListener{
	MainFrame frame;
    JPanel panel1=new JPanel();
    JPanel panel2=new JPanel();
    JLabel ip=new JLabel("IP Address");
    JTextField text1=new JTextField(16);
    JLabel port=new JLabel("Port");
    JTextField text2=new JTextField(5);
    JButton ok=new JButton("OK");
    JButton cancel=new JButton("Cancel");
    JPanel panel3=new JPanel();
    JLabel nickNameLabel=new JLabel("Name");
    JTextField nickNameText=new JTextField(5);
    public ConnectToServerDialog(MainFrame frm){
    	frame=frm;
    	setTitle("Connect To Server");
    	setLayout(new GridLayout(3,1));
    	panel1.add(ip);
    	panel1.add(text1);
    	panel1.add(port);
    	panel1.add(text2);
    	panel2.add(ok);
    	panel2.add(cancel);
    	panel3.add(nickNameLabel);
    	panel3.add(nickNameText);
    	add(panel1);
    	add(panel3);
    	add(panel2);
    	ok.addActionListener(this);
        cancel.addActionListener(this);
    	setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    	setResizable(false);
    	setLocation(400,300);
    	setSize(480,130);
    	setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
    	String str=e.getActionCommand();
        if(str.equals("OK")){
        	Client client=new Client(frame,text1.getText(),Integer.parseInt(text2.getText()));
        	frame.nickName=nickNameText.getText();
        	setVisible(false);
		}
		else{
			setVisible(false);
		}
    }
    
}

