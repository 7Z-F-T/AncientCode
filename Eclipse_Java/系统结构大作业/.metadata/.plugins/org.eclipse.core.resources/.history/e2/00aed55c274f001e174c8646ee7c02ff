package org.thu.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.thu.core.MipsSimEnv;


public class SetRDialog extends JDialog implements ActionListener{
	Mipsframe mipsframe;
	JLabel label,label2;
	JTextField field1,field2;
	JButton OK,cancel;
	public SetRDialog(Mipsframe mipsframe){
		this.mipsframe =mipsframe;
		this.setBounds(300,300,280,130);
		this.setResizable(false);
		this.setModal(true);
        this.setTitle("Set Register");
        this.setLayout(null);
        label = new JLabel("Register No.:");
        label.setBounds(5,20,75,30);
        this.add(label);
        field1 = new JTextField();
        field1.setText("0");
        field1.setBounds(80,20,60,30);
        this.add(field1);
        label2 = new JLabel("Value:");
        label2.setBounds(150,20,50,30);
        this.add(label2);
        field2 = new JTextField();
        field2.setBounds(200, 20, 60, 30);
        field2.setText("0");
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
			int registerNo = 0,content  = 0;
			try{
				registerNo = Integer.parseInt(field1.getText(),10);
				content = Integer.parseInt(field2.getText(), 16);
			}catch(Exception ee){}
			
			//////uncompleted!!!!!!!!!!!!!!!!!!!!!处理设置寄存器操作
			MipsSimEnv.reg.regs[registerNo%32] = content;
			
			this.mipsframe.refreshRegister();
		}
		this.setVisible(false);
	}
}
