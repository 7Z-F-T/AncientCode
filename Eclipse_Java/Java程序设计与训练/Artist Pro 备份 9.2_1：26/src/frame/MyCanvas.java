package frame;
import handler.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
public class MyCanvas extends JPanel{
	ArrayList<ShapePro> shapeProArray;
	PaintProperty currentPaintProperty;
	float[] dashPattern={7,7,7,7,7,7,7,7,7,7,7,7,7};
	
	public MyCanvas(ArrayList<ShapePro> array,PaintProperty property){
		shapeProArray=array;
		currentPaintProperty=property;
	}
	public void paint(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		AffineTransform oldTransform=g2.getTransform();
		//渲染质量设置
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		//输出所有图形
		Iterator it=shapeProArray.iterator();
		while(it.hasNext()){
			ShapePro s=(ShapePro)it.next();
			//设置基本绘图属性
			g2.setStroke(s.stroke);
			g2.setColor(s.lineColor);
			oldTransform=g2.getTransform();
			//判断是否发生旋转，若旋转则仿射变换输出
			if(s.rotated==true){
				AffineTransform t=new AffineTransform();
			    t.setToRotation(s.rotatedVecX,s.rotatedVecY,s.getCenterPoint().x,s.getCenterPoint().y);
			    g2.transform(t);
			}
			//判断是否发生缩放，若缩放则仿射变换输出
			if(s.scaled==true){
				AffineTransform t=new AffineTransform();
				t.setToTranslation(s.getCenterPoint().x,s.getCenterPoint().y);
				g2.transform(t);
				t.setToScale(s.scaledFactorX,s.scaledFactorY);
				g2.transform(t);
				t.setToTranslation(-s.getCenterPoint().x,-s.getCenterPoint().y);
				g2.transform(t);
			}
			//开始绘图
			g2.draw(s.shape);
			//开始填充
			if(s.fillType==FillType.NONE){
				//Don't fill
			}
			if(s.fillType==FillType.SOLID){
			    g2.setColor(s.fillColor);
			    g2.fill(s.shape);
			}
			else if(s.fillType==FillType.GRADIENT){
				g2.setPaint(s.gradientFillColor);
				g2.fill(s.shape);
			}
			//判断是否被选中，如果选中就画一个矩形框
			if(s.selected==true){
				Rectangle2D bound;
				switch(s.shapeType){
				case ShapeType.RECTANGLE:
					bound=((Rectangle2D.Double)(s.shape).getBounds2D());
					g2.setStroke(new BasicStroke(4.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0f,dashPattern,0));
					g2.setColor(new Color(92,107,239));
					g2.draw(bound);
					break;
				case ShapeType.ELLIPSE:
					bound=((Ellipse2D.Double)(s.shape)).getBounds2D();
					g2.setStroke(new BasicStroke(4.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0f,dashPattern,0));
					g2.setColor(new Color(92,107,239));
					g2.draw(bound);
					break;
				case ShapeType.LINE:
					bound=((Line2D.Double)(s.shape)).getBounds2D();
					g2.setStroke(new BasicStroke(4.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0f,dashPattern,0));
					g2.setColor(new Color(92,107,239));
					g2.draw(bound);
					break;
				}
			}
			//恢复默认绘图属性
			g2.setTransform(oldTransform);
			
			
			
		}
			
	}
}
