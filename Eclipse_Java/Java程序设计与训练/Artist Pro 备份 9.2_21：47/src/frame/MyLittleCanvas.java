package frame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;
public class MyLittleCanvas extends JPanel{
	PaintProperty currentPaintProperty;
	Color color1;
	Color color2;
	ShapePro demo;
	
	public MyLittleCanvas(PaintProperty property){
		currentPaintProperty=property;
		demo=new ShapePro(new Rectangle2D.Double(60, 15, 110, 60),currentPaintProperty.currentStroke,currentPaintProperty.currentLined,currentPaintProperty.currentLineColor,currentPaintProperty.currentFillType,currentPaintProperty.currentFillColor,currentPaintProperty.currentGradientFillColor,ShapeType.ELLIPSE,false,false,false,0,0,false,1.0,1.0,false,0,0);
	}
	public void paint(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		//下面根据当前绘图属性设置生成预览图像
		
		//设置基本绘图属性
		g2.setStroke(currentPaintProperty.currentStroke);
		g2.setColor(currentPaintProperty.currentLineColor);
		//若有边框则开始绘边框
		if(currentPaintProperty.currentLined==true)
			g2.draw(demo.shape);
		//开始填充
		if(currentPaintProperty.currentFillType==FillType.NONE){
			//Don't fill
		}
		if(currentPaintProperty.currentFillType==FillType.SOLID){
		    g2.setColor(currentPaintProperty.currentFillColor);
		    g2.fill(demo.shape);
		}
		else if(currentPaintProperty.currentFillType==FillType.GRADIENT){
			g2.setPaint(currentPaintProperty.currentGradientFillColor);
			g2.fill(demo.shape);
		}
		
	}
}
