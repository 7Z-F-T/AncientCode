package frame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * “建立服务器”对话框
 * @author 侯杰
 *
 */
public class CreateServerDialog extends JDialog implements ActionListener{
	MainFrame frame;
    JPanel panel1=new JPanel();
    JPanel panel2=new JPanel();
    JPanel panel3=new JPanel();
    JLabel port=new JLabel("Port  ");
    JTextField text=new JTextField(5);
    JButton ok=new JButton("OK");
    JButton cancel=new JButton("Cancel");
    JLabel nickNameLabel=new JLabel("Name");
    JTextField nickNameText=new JTextField(5);
    public CreateServerDialog(MainFrame frm){
    	frame=frm;
    	setTitle("Create Server");
    	setLayout(new GridLayout(3,1));
    	panel1.add(port);
    	panel1.add(text);
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
    	setLocation(500,300);
    	//pack();
        setSize(200,170);
    	setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
    	String str=e.getActionCommand();
        if(str.equals("OK")){
        	Server server=new Server(frame,Integer.parseInt(text.getText()));//新开服务器端
        	frame.nickName=nickNameText.getText();//设置服务器端昵称
        	setVisible(false);
		}
		else{
			setVisible(false);
		}
    }
    
}
