package frame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
public class CreateServerDialog extends JDialog implements ActionListener{
	MainFrame frame;
    JPanel panel1=new JPanel();
    JPanel panel2=new JPanel();
    JLabel port=new JLabel("Port");
    JTextField text=new JTextField(5);
    JButton ok=new JButton("OK");
    JButton cancel=new JButton("Cancel");
    public CreateServerDialog(MainFrame frm){
    	frame=frm;
    	setTitle("Create Server");
    	setLayout(new GridLayout(2,1));
    	panel1.add(port);
    	panel1.add(text);
    	panel2.add(ok);
    	panel2.add(cancel);
    	add(panel1);
    	add(panel2);
    	ok.addActionListener(this);
        cancel.addActionListener(this);
    	setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    	setResizable(false);
    	setLocation(500,300);
    	setSize(200,130);
    	setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
    	String str=e.getActionCommand();
        if(str.equals("OK")){
        	Server server=new Server(frame,Integer.parseInt(text.getText()));
        	setVisible(false);
		}
		else{
			setVisible(false);
		}
    }
    
}
