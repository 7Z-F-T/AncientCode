import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Window1 {
	public static void main(String[] args) {
		JFrame frame=new JFrame("4.1");
		frame.setLayout(new BorderLayout());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(2,2));//FlowLayout为JPanel默认，可省略
		final JCheckBox check1=new JCheckBox("checkBox1");
		final JCheckBox check2=new JCheckBox("checkBox2");
		JRadioButton radio1=new JRadioButton("blue");
		JRadioButton radio2=new JRadioButton("red");
		ButtonGroup group=new ButtonGroup();
		group.add(radio1);
		group.add(radio2);
		panel.add(check1);
		panel.add(radio1);
		panel.add(check2);
		panel.add(radio2);
				
		final JTextArea text=new JTextArea("");
		
		JButton button=new JButton("OK");
		
		frame.getContentPane().add(panel,"North");
		frame.getContentPane().add(text,"Center");
		frame.getContentPane().add(button,"South");
		
		frame.setSize(200,250);
		frame.setVisible(true);
		
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				text.append("The OK button has been clicked.\r\n");
			}
			});
		check1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(check1.isSelected()==true)
					text.append("checkBox 1 has been selected.\r\n");
				else
					text.append("checkBox 1 has been unselected.\r\n");
			}
			});
		check2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(check2.isSelected()==true)
					text.append("checkBox 2 has been selected.\r\n");
				else
					text.append("checkBox 2 has been unselected.\r\n");
			}
			});
		
	    radio1.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	text.setForeground(Color.BLUE);
		    }
		    });
	    radio2.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		    	text.setForeground(Color.RED);
		    }
		    });
	}
}
	
			
