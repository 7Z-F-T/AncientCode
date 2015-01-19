package frame;
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
public class faceChooseDialog extends JDialog {
	final MainFrame frame;
	JPanel panel1=new JPanel();
	JPanel panel2=new JPanel();
	JPanel panel3=new JPanel();
	JLabel faceChooseLabel=new JLabel("Select a Face You Like to Add");
	JButton ok=new JButton("OK");
	JButton smileButton;
	JButton cryButton;
	JButton byeButton;
	JButton omgButton;
	JButton bsButton;
	JButton angryButton;
	JButton hahaButton;
	JButton faintButton;
	
	public faceChooseDialog(MainFrame frm){
		frame=frm;
		setTitle("Add Face");
		JButton smileButton=new JButton(frame.smileIcon);
		JButton cryButton=new JButton(frame.cryIcon);
		JButton byeButton=new JButton(frame.byeIcon);
		JButton omgButton=new JButton(frame.omgIcon);
		JButton bsButton=new JButton(frame.bsIcon);
		JButton angryButton=new JButton(frame.angryIcon);
		JButton hahaButton=new JButton(frame.hahaIcon);
		JButton faintButton=new JButton(frame.faintIcon);
		setLayout(new GridLayout(3,1));
		panel1.add(faceChooseLabel);
		panel2.add(smileButton);
		panel2.add(cryButton);
		panel2.add(byeButton);
		panel2.add(omgButton);
		panel2.add(bsButton);
		panel2.add(angryButton);
		panel2.add(hahaButton);
		panel2.add(faintButton);
		panel3.add(ok);
		add(panel1);
		add(panel2);
		add(panel3);
		smileButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.privateWords.append(new String("/smile "));
			}
		});
		cryButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.privateWords.append(new String("/cry "));
			}
		});
		byeButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.privateWords.append(new String("/bye "));
			}
		});
		omgButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.privateWords.append(new String("/omg "));
			}
		});
		bsButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.privateWords.append(new String("/bs "));
			}
		});
		angryButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.privateWords.append(new String("/angry "));
			}
		});
		hahaButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.privateWords.append(new String("/haha "));
			}
		});
		faintButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.privateWords.append(new String("/faint "));
			}
		});
		ok.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				setVisible(false);
			}
		});
		
		
//		cryButton.addActionListener(this);
//		byeButton.addActionListener(this);
//		omgButton.addActionListener(this);
//		bsButton.addActionListener(this);
//		angryButton.addActionListener(this);
//		hahaButton.addActionListener(this);
//		faintButton.addActionListener(this);
		
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setSize(250,400);
		setResizable(false);
		setLocation(500,300);
		setVisible(true);
	}
}
