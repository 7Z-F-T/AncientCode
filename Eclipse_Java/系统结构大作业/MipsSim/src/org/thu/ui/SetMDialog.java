package org.thu.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.thu.core.MipsSimEnv;


public class SetMDialog extends JDialog implements ActionListener{
	Mipsframe mipsframe;
	JLabel label,label2;
	JTextField field1,field2;
	JButton OK,cancel;
	public SetMDialog(Mipsframe mipsframe){
		this.mipsframe =mipsframe;
		this.setBounds(300,300,280,130);
		this.setResizable(false);
		this.setModal(true);
        this.setTitle("Set Memory");
        this.setLayout(null);
        label = new JLabel("Address:");
        label.setBounds(20,20,60,30);
        this.add(label);
        field1 = new JTextField();
        field1.setBounds(80,20,60,30);
        field1.setText("00000");
        this.add(field1);
        label2 = new JLabel("Value:");
        label2.setBounds(150,20,50,30);
        this.add(label2);
        field2 = new JTextField();
        field2.setBounds(200, 20, 60, 30);
        field2.setText("00");
        this.add(field2);
        OK = new JButton("OK");
        OK.setBounds(60,60,75,30);
        OK.addActionListener(this);
        this.add(OK);
        cancel = new JButton("cancel");
        cancel.setBounds(145,60,75,30);
        cancel.addActionListener(this);
        this.add(cancel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == OK){
			int address = 0,content  = 0;
			try{
				address = Integer.parseInt(field1.getText(),16);
				content = Integer.parseInt(field2.getText(), 16);
			}catch(Exception ee){}
			
			//////uncompleted!!!!!!!!!!!!!!!!!!!!!处理设置内存操作
			MipsSimEnv.dataMem.mem[address%0x10000] = (byte) (content%256);
			
			this.mipsframe.refreshDataMemory();
		}
		this.setVisible(false);
	}
}
