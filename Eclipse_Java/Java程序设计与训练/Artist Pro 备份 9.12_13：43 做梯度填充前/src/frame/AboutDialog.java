package frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class AboutDialog extends JDialog implements ActionListener{
	JLabel softNameLabel=new JLabel("                                          Artist Pro");
	JLabel versionLabel=new JLabel("                          Version 1.0 Build 0001 2008.9.14");
	JLabel authorLabel=new JLabel("                            Developed by SoulCircus @9#");
	JLabel copyrightLabel=new JLabel("               Copyright SoulCircus 2008. All Rights Reserved");
	JButton ok=new JButton("...srO    Orz...");
	public AboutDialog(){
		setTitle("About");
		setLayout(new GridLayout(5,1));
		add(softNameLabel);
		add(versionLabel);
		add(authorLabel);
		add(copyrightLabel);
		add(ok);
		ok.addActionListener(this);
		setSize(400,250);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    	setResizable(false);
    	setLocation(500,300);
    	setVisible(true);
	}
	public void actionPerformed(ActionEvent e){
		setVisible(false);
	}
	
}
