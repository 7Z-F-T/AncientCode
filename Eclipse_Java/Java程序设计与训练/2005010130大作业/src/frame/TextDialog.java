package frame;
import handler.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.*;
/**
 * 输入文字对话框
 * @author 侯杰
 *
 */
public class TextDialog extends JDialog implements ActionListener{
	MainFrame frame;
	TextShapePro ts;
	Point2D.Double currentPosition;
	JPanel panel1=new JPanel();
	JPanel panel2=new JPanel();
	JPanel panel3=new JPanel();
	JLabel textLabel=new JLabel("Text");
	JLabel fontLabel=new JLabel("Font");
	JLabel sizeLabel=new JLabel("Size");
	JLabel styleLabel=new JLabel("Style");
	JTextArea textArea=new JTextArea(2,45);
	JComboBox fontCombo,sizeCombo;
	JToggleButton boldButton=new JToggleButton("<html><b>B</b></html>");
	JToggleButton italicButton=new JToggleButton("<html><i>I</i></html>");
	JButton ok=new JButton("OK");
	JButton cancel=new JButton("Cancel");
	/**
	 * 新建一个输入文字对话框。此构造函数专为修改已有文字而设计，所以各参数的初始值
	 * 与所选择的已有的文字属性一致。
	 * @param frm 主程序框架
	 * @param point 对话框出现位置点
	 * @param t 即将修改的已有的TextShapePro
	 */
	public TextDialog(MainFrame frm, Point2D.Double point, TextShapePro t){
		frame=frm;
		currentPosition=point;
		ts=t;
		setTitle("Text");
		//先要得到系统内所有字体
		String[] fontNames=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		String[] sizeNumbers={"50","55","60","65","70","75","80","85","90","95","100","105","110","115","120"};
		fontCombo=new JComboBox(fontNames);
		fontCombo.setEditable(true);
		//设置对话框参数为当前所选的文字的样式
		fontCombo.setSelectedItem(ts.font.getName());
		sizeCombo=new JComboBox(sizeNumbers);
		sizeCombo.setEditable(true);
		sizeCombo.setSelectedItem(ts.font.getSize());
		int style=ts.font.getStyle();
		if(style==Font.BOLD)
			boldButton.setSelected(true);
		if(style==Font.ITALIC)
			italicButton.setSelected(true);
		if(style==Font.BOLD+Font.ITALIC){
			boldButton.setSelected(true);
			italicButton.setSelected(true);
		}
		textArea.setText(ts.text);
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
		panel3.add(ok);
		panel3.add(cancel);
		add(panel1);
		add(panel2);
		add(panel3);
		
		ok.addActionListener(this);
		cancel.addActionListener(this);
		setLocation((int)currentPosition.x+100,(int)currentPosition.y+100);
		setSize(600,160);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setVisible(true);
	}
	/**
	 * 新建一个输入文字对话框。此构造函数专为新建文字而设计，所以各参数的初始值
	 * 为最初默认值。
	 * @param frm 主程序框架
	 * @param point 对话框出现位置点
	 */
	public TextDialog(MainFrame frm, Point2D.Double point){
		frame=frm;
		currentPosition=point;
		setTitle("Text");
		//先要得到系统内所有字体
		String[] fontNames=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		String[] sizeNumbers={"50","55","60","65","70","75","80","85","90","95","100","105","110","115","120"};
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
		panel3.add(ok);
		panel3.add(cancel);
		add(panel1);
		add(panel2);
		add(panel3);
		
		ok.addActionListener(this);
		cancel.addActionListener(this);
		setLocation((int)currentPosition.x+100,(int)currentPosition.y+100);
		setSize(600,160);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setVisible(true);
		
		
	}

	public void actionPerformed(ActionEvent e){
		String str=e.getActionCommand();
        if(str.equals("OK")){
			int style=0;
			if(boldButton.isSelected())
				style|=Font.BOLD;
			if(italicButton.isSelected())
				style|=Font.ITALIC;
			String fontName=(String)fontCombo.getSelectedItem();
			int fontSize=Integer.parseInt(sizeCombo.getSelectedItem().toString());
			//根据对话框的输入内容，生成TextShapePro对象
			TextShapePro newText;
			frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
			//若是新建对象，则在shapeProArray中新增文字对象即可
			if(DrawHandler.currentSelectedShapePro==null||DrawHandler.currentSelectedShapePro.selected==false){
				newText=new TextShapePro(textArea.getText(),new Font(fontName,style,fontSize),currentPosition,frame.currentPaintProperty.currentStroke,frame.currentPaintProperty.currentLined,frame.currentPaintProperty.currentLineColor,frame.currentPaintProperty.currentFillType,frame.currentPaintProperty.currentFillColor,frame.currentPaintProperty.currentGradientFillColor,ShapeType.TEXT,false,false,false,0,0,0,false,1.0,1.0,false,0,0);
			    frame.shapeProArray.add(newText);
			    frame.canvas.repaint();
			}
			//若是修改已有对象，则应先在shapeProArray中将旧的删除，再新加入新的文字对象，同时注意这个新
			//的文字对象应保留原文字对象的其他属性（如颜色，变形特征等）
			else{
				newText=new TextShapePro(textArea.getText(),new Font(fontName,style,fontSize),ts.position,ts.stroke,ts.lined,ts.lineColor,ts.fillType,ts.fillColor,ts.gradientFillColor,ShapeType.TEXT,ts.pointed,ts.selected,ts.rotated,ts.rotatedAngle,ts.rotatedVecX,ts.rotatedVecX,ts.scaled,ts.scaledFactorX,ts.scaledFactorY,ts.sheared,ts.shearedFactorX,ts.shearedFactorY);
				frame.shapeProArray.remove(ts);
				frame.shapeProArray.add(newText);
				frame.canvas.repaint();
			}
			setVisible(false);
			frame.repaintFlip.setFlip();
		}
		else{
			setVisible(false);
		}
	}
	
}
