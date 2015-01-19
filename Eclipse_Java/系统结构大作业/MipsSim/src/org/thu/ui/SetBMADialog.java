package org.thu.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;


public class SetBMADialog extends JDialog implements ActionListener{
	Mipsframe mipsframe;
	JLabel label,label2;
	JTextField textfield;
	JButton OK,cancel;
	public SetBMADialog(Mipsframe mipsframe){
		this.mipsframe =mipsframe;
		this.setBounds(300,300,300,130);
		this.setResizable(false);
		this.setModal(true);
        this.setTitle("Set Base Memory Address");
        this.setLayout(null);
        label = new JLabel("Set Base Memory Address:   0x");
        label.setBounds(20,20,180,30);
        this.add(label);
        textfield = new JTextField(Integer.toHexString(mipsframe.base_memory_address));
        textfield.setBounds(200,20,40,30);
        this.add(textfield);
        label2 = new JLabel("00");
        label2.setBounds(240,20,100,30);
        this.add(label2);
        OK = new JButton("OK");
        OK.setBounds(50,60,75,30);
        OK.addActionListener(this);
        this.add(OK);
        cancel = new JButton("cancel");
        cancel.setBounds(175,60,75,30);
        cancel.addActionListener(this);
        this.add(cancel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == OK){
			try{
				this.mipsframe.base_memory_address = (Integer.parseInt(textfield.getText(),16)%0x100)*256;
			}catch(Exception ee){}
			this.mipsframe.refreshDataMemory();
			for(int i = 0;i<17;i++)
				for(int j = 0;j<16;j++)
					Mipsframe.memory_changed[i][j] = false;
			this.mipsframe.dataMemory.repaint();
		}
		this.setVisible(false);
	}
}