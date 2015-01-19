package frame;

import java.awt.Dialog;
import java.awt.GridLayout;

import javax.swing.*;
import handler.*;

public class LineStyleOptionDialog extends JDialog {
		MainFrame frame;
		public final ButtonGroup group;
		public final JRadioButton style1;
		public final JRadioButton style2;
		public final JRadioButton style3;
		public final JRadioButton style4;
		public LineStyleOptionDialog(MainFrame frm){
			frame=frm;
			setTitle("Line Style Options");
			group=new ButtonGroup();
			style1=new JRadioButton("Continuum");
			style2=new JRadioButton("Dash");
			style3=new JRadioButton("Dash-Dot");
			style4=new JRadioButton("Dot");
			JButton ok=new JButton("OK");
			JButton cancel=new JButton("Cancel");
			group.add(style1);
			group.add(style2);
			group.add(style3);
			group.add(style4);
			JPanel panel=new JPanel();
			panel.setLayout(new GridLayout(3,2));
			panel.add(style1);
			panel.add(style2);
			panel.add(style3);
			panel.add(style4);
			panel.add(ok);
			panel.add(cancel);
			add(panel);  
			ok.addActionListener(new LineStyleOptionDialogHandler.Ok(this,frame));
			cancel.addActionListener(new LineStyleOptionDialogHandler.Cancel(this));
			setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
			setResizable(false);
			setLocation(500,300);
			setSize(190,190);
			setVisible(true);
		}
}

