package frame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
public class TextDialog extends JFrame implements ActionListener{
	MainFrame frame;
	Point2D.Double currentPosition;
	JPanel panel1=new JPanel();
	JPanel panel2=new JPanel();
	JPanel panel3=new JPanel();
	JLabel textLabel=new JLabel("Text");
	JLabel fontLabel=new JLabel("Font");
	JLabel sizeLabel=new JLabel("Size");
	JLabel styleLabel=new JLabel("Style");
	JTextArea textArea=new JTextArea(2,50);
	JComboBox fontCombo,sizeCombo;
	JToggleButton boldButton=new JToggleButton("<html><b>B</b></html>");
	JToggleButton italicButton=new JToggleButton("<html><i>I</i></html>");
	JToggleButton underLineButton=new JToggleButton("<html><u>U</u></html>");
	JButton ok=new JButton("OK");
	JButton cancel=new JButton("Cancel");
	public TextDialog(MainFrame frm, Point2D.Double point){
		frame=frm;
		currentPosition=point;
		setTitle("Text");
		String[] fontNames=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		String[] sizeNumbers={"5","10","15","20","25","30","35","40","45","50","55","60","65","70","75","80","85","90","95","100","105","110","115","120"};
		fontCombo=new JComboBox(fontNames);
		fontCombo.setEditable(true);
		sizeCombo=new JComboBox(sizeNumbers);
		sizeCombo.setEditable(true);
		setLayout(new GridLayout(3,1));
		panel1.add(textLabel);
		panel1.add(textArea);
		panel2.add(fontLabel);
		panel2.add(fontCombo);
		panel2.add(sizeLabel);
		panel2.add(sizeCombo);
		panel2.add(styleLabel);
		panel2.add(boldButton);
		panel2.add(italicButton);
		panel2.add(underLineButton);
		panel3.add(ok);
		panel3.add(cancel);
		add(panel1);
		add(panel2);
		add(panel3);
		
		ok.addActionListener(this);
		//cancel.addActionListener(this);
		setLocation((int)currentPosition.x,(int)currentPosition.y);
		setSize(630,160);
		setVisible(true);
		
		
	}
	//public static void main(String[] args){
	//	new TextDialog();
	//}
	public void actionPerformed(ActionEvent e){
		String str=e.getActionCommand();
        
		if(str.equals("OK")){
			int style=0;
			if(boldButton.isSelected())
				style|=Font.BOLD;
			if(italicButton.isSelected())
				style|=Font.ITALIC;
			if(underLineButton.isSelected())
				style|=Font.ROMAN_BASELINE;
			String fontName=(String)fontCombo.getSelectedItem();
			int fontSize=Integer.parseInt((String)sizeCombo.getSelectedItem());
			TextShapePro newText=new TextShapePro(textArea.getText(),new Font(fontName,style,fontSize),currentPosition,frame.currentPaintProperty.currentStroke,frame.currentPaintProperty.currentLined,frame.currentPaintProperty.currentLineColor,frame.currentPaintProperty.currentFillType,frame.currentPaintProperty.currentFillColor,frame.currentPaintProperty.currentGradientFillColor,ShapeType.TEXT,false,false,false,0,0,0,false,0,0,false,0,0);
			frame.shapeProArray.add(newText);
			frame.canvas.repaint();
		}
	}
	
}
