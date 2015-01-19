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
		//��Ⱦ��������
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		//�������ͼ��
		Iterator it=shapeProArray.iterator();
		while(it.hasNext()){
			ShapePro s=(ShapePro)it.next();
			//���û�����ͼ����
			g2.setStroke(s.stroke);
			g2.setColor(s.lineColor);
			oldTransform=g2.getTransform();
			//�ж��Ƿ�����ת������ת�����任���
			if(s.rotated==true){
				AffineTransform t=new AffineTransform();
			    t.setToRotation(s.rotatedVecX,s.rotatedVecY,s.getCenterPoint().x,s.getCenterPoint().y);
			    g2.transform(t);
			}
			//�ж��Ƿ������ţ������������任���
			if(s.scaled==true){
				AffineTransform t=new AffineTransform();
				t.setToTranslation(s.getCenterPoint().x,s.getCenterPoint().y);
				g2.transform(t);
				t.setToScale(s.scaledFactorX,s.scaledFactorY);
				g2.transform(t);
				t.setToTranslation(-s.getCenterPoint().x,-s.getCenterPoint().y);
				g2.transform(t);
			}
			//��ʼ��ͼ
			g2.draw(s.shape);
			//��ʼ���
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
			//�ж��Ƿ�ѡ�У����ѡ�оͻ�һ�����ο�
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
			//�ָ�Ĭ�ϻ�ͼ����
			g2.setTransform(oldTransform);
			
			
			
		}
			
	}
}
