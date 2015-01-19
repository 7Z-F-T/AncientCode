import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window4 extends JFrame{
	void go(){
		//design UI
		JMenuBar mb=new JMenuBar();
		JMenu manage=new JMenu("Manage");
		JMenu help=new JMenu("Help");
		JMenu color=new JMenu("color");
		final JCheckBoxMenuItem check=new JCheckBoxMenuItem("check");
		JMenuItem exit=new JMenuItem("exit");
		JMenuItem red=new JMenuItem("red");
		JMenuItem blue=new JMenuItem("blue");
		JMenuItem helpInfo=new JMenuItem("help info");
		
		final JTextArea text=new JTextArea("You can begin your operation\r\n");
		
		add(mb,"North");
		add(text,"Center");
		mb.add(manage);mb.add(help);
		manage.add(color);manage.addSeparator();manage.add(check);manage.add(exit);
		help.add(helpInfo);
		color.add(red);color.add(blue);
		
		setTitle("4.4");
		setSize(300,300);
		setVisible(true);
		//deal with events
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		KeyStroke key_exit=KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.CTRL_MASK|InputEvent.SHIFT_MASK);
		KeyStroke key_red=KeyStroke.getKeyStroke(KeyEvent.VK_R,InputEvent.CTRL_MASK|InputEvent.SHIFT_MASK);
		KeyStroke key_blue=KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK|InputEvent.SHIFT_MASK);
		exit.setAccelerator(key_exit);
		red.setAccelerator(key_red);
		blue.setAccelerator(key_blue);
		
		helpInfo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				text.append("Here's some help infomation.\r\n");
			}
		});
		blue.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				text.setForeground(Color.blue);
			}
		});
		red.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				text.setForeground(Color.red);
			}
		});
		check.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
			    if(check.isSelected()==true)
			    	text.append("checked!\r\n");
			    else
			    	text.append("unchecked!\r\n");
			}
		});
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		
	}
	
	public static void main (String[] args) {
		Window4 window=new Window4();
		window.go();
	}

}
