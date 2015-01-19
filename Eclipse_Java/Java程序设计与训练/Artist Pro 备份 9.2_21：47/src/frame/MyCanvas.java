package frame;
import handler.*;
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.*;


public class MyCanvas extends JPanel{
	ArrayList<ShapePro> shapeProArray;
	PaintProperty currentPaintProperty;
	float[] dashPattern={5,5,5,5,5,5,5};
	BufferedImage texture=new BufferedImage(4,4,BufferedImage.TYPE_INT_ARGB);
	
	
	
	public MyCanvas(ArrayList<ShapePro> array,PaintProperty property){
		shapeProArray=array;
		currentPaintProperty=property;
		this.setBackground(Color.GREEN);
	}
	public void paint(Graphics g){
		Graphics2D g2=(Graphics2D)g;
		Shape transformedShape;
		//AffineTransform oldTransform=g2.getTransform();
		//��Ⱦ��������
		RenderingHints rh = new RenderingHints(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(rh);
		//��ʼ�������ͼ��
		Iterator it=shapeProArray.iterator();
		while(it.hasNext()){
			ShapePro s=(ShapePro)it.next();
			transformedShape=s.shape;//��ʼ��transformedShape
			//���û�����ͼ����
			g2.setStroke(s.stroke);
			g2.setColor(s.lineColor);
			//oldTransform=g2.getTransform();
			//�ж��Ƿ�����ת������ת�����任���
			if(s.rotated==true){
				AffineTransform t=new AffineTransform();
				AffineTransform t2=new AffineTransform();
			    t.setToRotation(s.rotatedVecX,s.rotatedVecY,s.getCenterPoint().x,s.getCenterPoint().y);
			    t2.concatenate(t);
			    transformedShape=t2.createTransformedShape(transformedShape);
			    //g2.transform(t);
			}
			//�ж��Ƿ������ţ������������任���
			if(s.scaled==true){
				AffineTransform t=new AffineTransform();
				AffineTransform t2=new AffineTransform();
				t.setToTranslation(s.getCenterPoint().x,s.getCenterPoint().y);
				t2.concatenate(t);
				//g2.transform(t);
				t.setToScale(s.scaledFactorX,s.scaledFactorY);
				t2.concatenate(t);
				//transformedShape=t.createTransformedShape(transformedShape);
				//g2.transform(t);
				t.setToTranslation(-s.getCenterPoint().x,-s.getCenterPoint().y);
				t2.concatenate(t);
				transformedShape=t2.createTransformedShape(transformedShape);
				//g2.transform(t);
			}
			//�ж��Ƿ������У������������任���
			if(s.sheared==true){
				AffineTransform t=new AffineTransform();
				AffineTransform t2=new AffineTransform();
				t.setToTranslation(s.getCenterPoint().x,s.getCenterPoint().y);
				t2.concatenate(t);
				t.setToShear(s.shearedFactorX,s.shearedFactorY);
				t2.concatenate(t);
				t.setToTranslation(-s.getCenterPoint().x,-s.getCenterPoint().y);
				t2.concatenate(t);
				transformedShape=t2.createTransformedShape(transformedShape);
			}
			//���б߿���ʼ��߿�
			if(s.lined==true)
				g2.draw(transformedShape);
			//��ʼ���
			if(s.fillType==FillType.NONE){
				//Don't fill
			}
			if(s.fillType==FillType.SOLID){
			    g2.setColor(s.fillColor);
			    g2.fill(transformedShape);
			}
			else if(s.fillType==FillType.GRADIENT){
				g2.setPaint(s.gradientFillColor);
				g2.fill(transformedShape);
			}
			//�ж��Ƿ�ָ�������ָ����������
			if(s.pointed==true){
				Graphics2D g2_texture=texture.createGraphics();
				Rectangle2D anchorRectangle=new Rectangle2D.Double(0.0,0.0,2.0,2.0);
				switch(s.shapeType){
				case ShapeType.RECTANGLE:
					g2_texture.setColor(Color.WHITE);
					g2_texture.fillRect(0,0,1,1);
					g2.setPaint(new TexturePaint(texture,anchorRectangle));
					g2.fill(transformedShape);
					break;
				case ShapeType.ELLIPSE:
					g2_texture.setColor(Color.WHITE);
					g2_texture.fillRect(0,0,1,1);
					g2.setPaint(new TexturePaint(texture,anchorRectangle));
					g2.fill(transformedShape);
					break;
				case ShapeType.LINE:
					g2_texture.setColor(Color.WHITE);
					g2_texture.fillRect(0,0,1,1);
					g2.setPaint(new TexturePaint(texture,anchorRectangle));
					g2.draw(transformedShape);
					
				}
			}
			//�ж��Ƿ�ѡ�У���ѡ�����ϱ߿�
			if(s.selected==true){
				switch(s.shapeType){
				case ShapeType.RECTANGLE:
					g2.setStroke(new BasicStroke(4.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0f,dashPattern,0));
					g2.setColor(new Color(141,186,245));
					g2.draw(transformedShape);
					break;
				case ShapeType.ELLIPSE:
					g2.setStroke(new BasicStroke(4.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0f,dashPattern,0));
					g2.setColor(new Color(141,186,245));
					g2.draw(transformedShape);
					break;
				case ShapeType.LINE:
					g2.setStroke(new BasicStroke(4.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0f,dashPattern,0));
					g2.setColor(new Color(141,186,245));
					g2.draw(transformedShape);
					break;
				}
			}
			//�ָ�Ĭ�ϻ�ͼ����
			//g2.setTransform(oldTransform);
			
			
			
		}
			
	}
}
