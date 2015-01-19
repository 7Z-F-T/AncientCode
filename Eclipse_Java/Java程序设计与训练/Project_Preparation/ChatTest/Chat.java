package ChatTest;
import javax.swing.*;
import java.awt.*;
public class Chat extends JFrame {
	public Chat(){
        JLabel label1=new JLabel();
        label1.setText("<html><i>I am hungry</i></html>");
        //JEditorPane=new JEditorPane();
		add(label1);
		setSize(200,200);
		setVisible(true);
		
		
	}
	public static void main(String[] args) {
		Chat chat=new Chat();

	}

}
