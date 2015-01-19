package frame;
import handler.DrawHandler;

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
		ShapePro demo;
	}
	public void paint(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		
		
		if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
			//下面根据当前绘图属性设置生成预览图像
			if(DrawHandler.currentSelectedShapePro.shapeType==ShapeType.ROUNDRECTANGLE)
				demo=new ShapePro(new RoundRectangle2D.Double(75, 30, 185, 63,((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).arcwidth,((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).archeight),DrawHandler.currentSelectedShapePro.stroke,DrawHandler.currentSelectedShapePro.lined,DrawHandler.currentSelectedShapePro.lineColor,DrawHandler.currentSelectedShapePro.fillType,DrawHandler.currentSelectedShapePro.fillColor,DrawHandler.currentSelectedShapePro.gradientFillColor,ShapeType.ROUNDRECTANGLE,false,false,false,0,0,0,false,1.0,1.0,false,0,0);
			else
				demo=new ShapePro(new RoundRectangle2D.Double(75, 30, 185, 63,currentPaintProperty.currentArcWidth,currentPaintProperty.currentArcHeight),DrawHandler.currentSelectedShapePro.stroke,DrawHandler.currentSelectedShapePro.lined,DrawHandler.currentSelectedShapePro.lineColor,DrawHandler.currentSelectedShapePro.fillType,DrawHandler.currentSelectedShapePro.fillColor,DrawHandler.currentSelectedShapePro.gradientFillColor,ShapeType.ROUNDRECTANGLE,false,false,false,0,0,0,false,1.0,1.0,false,0,0);
			//设置基本绘图属性
			g2.setStroke(new BasicStroke(DrawHandler.currentSelectedShapePro.stroke.width,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f,DrawHandler.currentSelectedShapePro.stroke.pattern,0));
			g2.setColor(DrawHandler.currentSelectedShapePro.lineColor);
			//若有边框则开始绘边框
			if(DrawHandler.currentSelectedShapePro.lined==true)
				g2.draw(demo.shape);
			//开始填充
			if(DrawHandler.currentSelectedShapePro.fillType==FillType.NONE){
				//Don't fill
			}
			if(DrawHandler.currentSelectedShapePro.fillType==FillType.SOLID){
			    g2.setColor(DrawHandler.currentSelectedShapePro.fillColor);
			    g2.fill(demo.shape);
			}
			else if(DrawHandler.currentSelectedShapePro.fillType==FillType.GRADIENT){
				g2.setPaint(new GradientPaint(DrawHandler.currentSelectedShapePro.gradientFillColor.point1,DrawHandler.currentSelectedShapePro.gradientFillColor.color1,DrawHandler.currentSelectedShapePro.gradientFillColor.point2,DrawHandler.currentSelectedShapePro.gradientFillColor.color2,true));
				g2.fill(demo.shape);
			}
		}
		else{
			//下面根据当前绘图属性设置生成预览图像
			demo=new ShapePro(new RoundRectangle2D.Double(75, 30, 185, 63,currentPaintProperty.currentArcWidth,currentPaintProperty.currentArcHeight),currentPaintProperty.currentStroke,currentPaintProperty.currentLined,currentPaintProperty.currentLineColor,currentPaintProperty.currentFillType,currentPaintProperty.currentFillColor,currentPaintProperty.currentGradientFillColor,ShapeType.ELLIPSE,false,false,false,0,0,0,false,1.0,1.0,false,0,0);
			//设置基本绘图属性
			g2.setStroke(new BasicStroke(currentPaintProperty.currentStroke.width,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f,currentPaintProperty.currentStroke.pattern,0));
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
				g2.setPaint(new GradientPaint(currentPaintProperty.currentGradientFillColor.point1,currentPaintProperty.currentGradientFillColor.color1,currentPaintProperty.currentGradientFillColor.point2,currentPaintProperty.currentGradientFillColor.color2,true));
				g2.fill(demo.shape);
			}
		}
		
	}
}
