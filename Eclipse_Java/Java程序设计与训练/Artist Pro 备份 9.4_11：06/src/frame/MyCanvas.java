package frame;
import handler.*;
import java.awt.*;
import java.awt.font.*;
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
			//��������ֶ�����Ҫ����shape
			if(s.shapeType==ShapeType.TEXT){
				FontRenderContext context=g2.getFontRenderContext();
				TextShapePro ts=(TextShapePro)(s);
				TextLayout layout=new TextLayout(ts.text,ts.font,context);
				AffineTransform transform=AffineTransform.getTranslateInstance(ts.position.x, ts.position.y);
				Shape outline=layout.getOutline(transform);
				s.shape=outline;
			}
			//��ʼ��transformedShape
			transformedShape=s.shape;
			//��ʼ��ͼ�ν��ϵĿ��Ƶ�
			ShapePro.p1=s.getLeftTop();
			ShapePro.p2=s.getRightTop();
			ShapePro.p3=s.getLeftBottom();
			ShapePro.p4=s.getRightBottom();
			//���û�����ͼ����
			g2.setStroke(s.stroke);
			g2.setColor(s.lineColor);
			//oldTransform=g2.getTransform();
			
			
			//*****************************************************************
			//�任˵��
			//����drag��scale�������ɸ�����������ֱ���޸�ͼ�ε��������ֵ���ɣ���ʼ�㡢���ֵ��
			//����rotate��shear�������޷�ͨ���޸��������ʵ�ֱ任�����������������������
			//����任�����µ�Shape����(transformedShape)������������µĶ�����л�ͼ�����
			//��������µ�Shape���󲢲��洢���ڴ��д�Ļ��Ƿ���仯ǰ��Shape��
			//���������������ʱ���û���Ҫ���ж�������Ƿ����µ�Shape�����С���������µ�Shape
			//��δ�洢�����Խ���������ж�ʱ��Ҫ�����λ�ý��з��任�����ڴ��еı任ǰ��
			//Shape����������ϵ�����жϡ�
			//ע��˳���ҵı任˳������
			//���任��shear,rotate
			//��任��rotate,shear
			//ע�⵽˳������෴����������ʹһ��ֵ�����任�پ���任���ܻص�ԭ����״̬
			//*****************************************************************
			
			
			//�˺��������ԭʼShape�����任
			
			//�ж��Ƿ������У���������shape���任
			if(s.sheared==true){
				AffineTransform t=new AffineTransform();
				AffineTransform t2=new AffineTransform();
				//
				t.setToTranslation(s.getCenterPoint().x,s.getCenterPoint().y);
				t2.concatenate(t);
				t.setToShear(s.shearedFactorX,s.shearedFactorY);
				t2.concatenate(t);
				t.setToTranslation(-s.getCenterPoint().x,-s.getCenterPoint().y);
				t2.concatenate(t);
				transformedShape=t2.createTransformedShape(transformedShape);
				t2.transform(ShapePro.p1, ShapePro.p1);
				t2.transform(ShapePro.p2, ShapePro.p2);
				t2.transform(ShapePro.p3, ShapePro.p3);
				t2.transform(ShapePro.p4, ShapePro.p4);
			}
			/*//�ж��Ƿ������ţ������������任
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
			}*/
			//�ж��Ƿ�����ת������ת��shape���任
			if(s.rotated==true){
				AffineTransform t=new AffineTransform();
				AffineTransform t2=new AffineTransform();
			    //t.setToRotation(s.rotatedVecX,s.rotatedVecY,s.getCenterPoint().x,s.getCenterPoint().y);
				t.setToRotation(s.rotatedAngle, s.getCenterPoint().x, s.getCenterPoint().y);
			    t2.concatenate(t);
			    transformedShape=t2.createTransformedShape(transformedShape);
			    t2.transform(ShapePro.p1, ShapePro.p1);
				t2.transform(ShapePro.p2, ShapePro.p2);
				t2.transform(ShapePro.p3, ShapePro.p3);
				t2.transform(ShapePro.p4, ShapePro.p4);
			    //g2.transform(t);
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
					//g2.draw(transformedShape);
					g2.draw(new Line2D.Double(ShapePro.p2.x,ShapePro.p2.y,ShapePro.p1.x,ShapePro.p1.y));
					g2.draw(new Line2D.Double(ShapePro.p3.x,ShapePro.p3.y,ShapePro.p1.x,ShapePro.p1.y));
					g2.draw(new Line2D.Double(ShapePro.p2.x,ShapePro.p2.y,ShapePro.p4.x,ShapePro.p4.y));
					g2.draw(new Line2D.Double(ShapePro.p3.x,ShapePro.p3.y,ShapePro.p4.x,ShapePro.p4.y));
					break;
				case ShapeType.LINE:
					g2.setStroke(new BasicStroke(4.0f,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,10.0f,dashPattern,0));
					g2.setColor(new Color(141,186,245));
					g2.draw(transformedShape);
					break;
				}
				//����ǰ����Ϊrotate,shear,scale,������ѡ����ͼ�ν��ϻ���СԲȦ
				if(currentPaintProperty.transformable==true){
						ShapePro.c1.x=ShapePro.p1.x-6.0;
						ShapePro.c1.y=ShapePro.p1.y-6.0;
						ShapePro.c2.x=ShapePro.p2.x-6.0;
						ShapePro.c2.y=ShapePro.p2.y-6.0;
						ShapePro.c3.x=ShapePro.p3.x-6.0;
						ShapePro.c3.y=ShapePro.p3.y-6.0;
						ShapePro.c4.x=ShapePro.p4.x-6.0;
						ShapePro.c4.y=ShapePro.p4.y-6.0;
						ShapePro.c1.height=ShapePro.c1.width=12;
						ShapePro.c2.height=ShapePro.c2.width=12;
						ShapePro.c3.height=ShapePro.c3.width=12;
						ShapePro.c4.height=ShapePro.c4.width=12;
						g2.setStroke(new BasicStroke(3.0f));
						g2.setColor(Color.BLACK);
						g2.draw(ShapePro.c1);
						g2.draw(ShapePro.c2);
						g2.draw(ShapePro.c3);
						g2.draw(ShapePro.c4);
						g2.setColor(Color.GREEN);
						g2.fill(ShapePro.c1);
						g2.fill(ShapePro.c2);
						g2.fill(ShapePro.c3);
						g2.fill(ShapePro.c4);	
				}
				
			}
			//�ָ�Ĭ�ϻ�ͼ����
			//g2.setTransform(oldTransform);
			
			
			
		}
			
	}
}
