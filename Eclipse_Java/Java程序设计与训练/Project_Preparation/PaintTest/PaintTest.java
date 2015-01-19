package PaintTest;
import javax.swing.*;
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.geom.AffineTransform;
import java.util.*;
import java.awt.geom.AffineTransform;

public class PaintTest extends JFrame{
	MyCanvas c;
	ArrayList<ShapePro> shapeArray=new ArrayList();
	public PaintTest(){
	}
	public static void main(String[] args) {
		PaintTest p=new PaintTest();
		p.initialize();
		p.startPaint();
		p.setVisible(true);
	}
	public void initialize(){
		setTitle("Paint");
		c=new MyCanvas(shapeArray);
		getContentPane().add(c);
		setSize(800,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void startPaint(){
		//以后这里构造函数的一切参数值都从鼠标操作和当前选中的样式中get得到，而不是随便new一个参数
		shapeArray.add(new ShapePro(new Rectangle2D.Double(300,300,150,150),new BasicStroke(10.0f),Color.RED,"Gradient",Color.GREEN,new GradientPaint(100,100,Color.ORANGE,240,260,Color.WHITE)));
		shapeArray.add(new ShapePro(new Ellipse2D.Double(400, 200, 100, 50),new BasicStroke(10.0f),Color.BLUE,"Gradient",Color.GREEN,new GradientPaint(10,10,Color.PINK,100,50,Color.WHITE)));
		//shapes.add(new RoundRectangle2D.Double(200,200,100,100,50,50));
		//c.repaint();
		
	}
}
class ShapePro {
	Shape shape;
	BasicStroke stroke;
	Color lineColor;
	String fillType;
	Color fillColor;
	GradientPaint gradientFillColor;
	ShapePro(Shape s,BasicStroke stk,Color lc, String fType, Color fc, GradientPaint gfc){
		shape=s;
		stroke=stk;
		lineColor=lc;
		fillType=fType;
		fillColor=fc;
		gradientFillColor=gfc;
	}
}
class MyCanvas  extends Canvas {
	ArrayList<ShapePro> shapeArray;
	AffineTransform t;
	public MyCanvas(ArrayList<ShapePro> array){
		shapeArray=array;
	}
	public void paint(Graphics g){
		Graphics2D g2=(Graphics2D)g;
	        
		    g2.setStroke(new BasicStroke(3.0f));
			g2.setColor(Color.red);
			g2.draw(new RoundRectangle2D.Double(100,100,200,150,30,30));
			AffineTransform old=g2.getTransform();
			t=new AffineTransform();
			
			/*
			t.setToTranslation(300,300);
			g2.transform(t);
			t.setToShear(1,1);
			AffineTransform t2=new AffineTransform();
			try{
			t2=t.createInverse();
			}catch(NoninvertibleTransformException e){}
			g2.transform(t);
			t.setToTranslation(-300,-300);
			g2.transform(t);
			g2.draw(s.shape);
			//g2.setTransform(old);
			
			
			
			//old=g2.getTransform();
			t=new AffineTransform();
			t.setToTranslation(300,300);
			g2.transform(t);
			//t.setToShear(-2,-2);
			g2.transform(t2);
			t.setToTranslation(-300,-300);
			g2.transform(t);
			g2.setColor(Color.GREEN);
			g2.draw(s.shape);*/
			
			
			
			Font font=new Font("Georgia",Font.BOLD,104);
			FontRenderContext context=g2.getFontRenderContext();
			TextLayout layout=new TextLayout("Hello",font,context);
			t.setToTranslation(350,350);
			Shape outline=layout.getOutline(t);
			g2.setTransform(old);
			
			g2.draw(outline);
			
			g2.setColor(Color.YELLOW);
			g2.fill(outline);
			
			
			
			
			/*if(s.fillType.equals("Solid")){
			    g2.setColor(s.fillColor);
			    g2.fill(s.shape);
			}
			else if(s.fillType.equals("Gradient")){
				g2.setPaint(s.gradientFillColor);
				g2.fill(s.shape);
			}*/
			
			
		
			
	}
}
/*class MyRectangle2D extends Rectan{//要新增一些属性项
	double height;
	double width;
	double x;
	double y;
	public MyRectangle2D(double h, double w, double xx, double yy,  BasicStroke stk, Color lColor, String fType, Color fColor, GradientPaint gfColor){
		height=h;//类独有的特性
		width=w;
		x=xx;
		y=yy;
		
		stroke=stk;//以下顺序各类要一致
		lineColor=lColor;
		fillType=fType;
		fillColor=fColor;
		gradientFillColor=gfColor;
		
	}
}
class MyRoundRectangle2D extends RoundRectangle2D.Double{//要新增一些属性项
	Color lineColor;
	Color fillColor;
	String fillType;
	GradientPaint gradientFillColor;
	BasicStroke stroke;
	public MyRoundRectangle2D(Color lColor, Color fColor, String fType, GradientPaint gfColor, BasicStroke stk){
		lineColor=lColor;
		fillColor=fColor;
		fillType=fType;
		gradientFillColor=gfColor;
		stroke=stk;
	}
}*/