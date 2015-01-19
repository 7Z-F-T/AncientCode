package handler;
import frame.*;

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.util.*;
import java.io.*;
import javax.imageio.*;
import java.awt.geom.*;
public abstract class MenuHandler /*implements ActionListener*/{
	public static class Exit implements ActionListener{
		MainFrame frame;
		public Exit(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			System.exit(0);
		}
	}
	public static class Mac implements ActionListener{
		MainFrame frame;
		public Mac(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			try{
				UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
			}catch(Exception excpt){
				System.err.println("Error changing look-and-feel: "+excpt.getMessage());
			}
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}
	public static class Metal implements ActionListener{
		MainFrame frame;
		public Metal(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			try{
				UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			}catch(Exception excpt){
				System.err.println("Error changing look-and-feel: "+excpt.getMessage());
			}
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}
	public static class Motif implements ActionListener{
		MainFrame frame;
		public Motif(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			try{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			}catch(Exception excpt){
				System.err.println("Error changing look-and-feel: "+excpt.getMessage());
			}
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}
	
	public static class Windows implements ActionListener{
		MainFrame frame;
		public Windows(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			try{
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}catch(Exception excpt){
				System.err.println("Error changing look-and-feel: "+excpt.getMessage());
			}
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}
	public static class Save implements ActionListener{
		MainFrame frame;
		public Save(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			JFileChooser fc=new JFileChooser(".");
			if(fc.showSaveDialog(frame)==JFileChooser.APPROVE_OPTION){
				File newFile=fc.getSelectedFile();
				newFile=new File(newFile.toString()+".ap");
				try{
				FileOutputStream fo=new FileOutputStream(newFile);
				ObjectOutputStream oo=new ObjectOutputStream(fo);
				oo.writeObject(frame.shapeProArray);
				}catch(Exception ex){System.out.print(ex);}
			}
			
		}
	}
	public static class Open implements ActionListener{
		MainFrame frame;
		public Open(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			JFileChooser fc=new JFileChooser(".");
			if(fc.showOpenDialog(frame)==JFileChooser.APPROVE_OPTION){
				File newFile=fc.getSelectedFile();
				try{
				FileInputStream fi=new FileInputStream(newFile);
				ObjectInputStream oi=new ObjectInputStream(fi);
				System.out.println(newFile);
				ArrayList<ShapePro> newShapeProArray=(ArrayList<ShapePro>)(oi.readObject());
				System.out.println(frame.shapeProArray.size());
				frame.shapeProArray.clear();
				frame.shapeProArray.ensureCapacity(newShapeProArray.size());
				for(int i=0;i<newShapeProArray.size();i++){
					frame.shapeProArray.add(newShapeProArray.get(i));
					System.out.println("outputint");
				}
				frame.canvas.repaint();
				frame.repaintFlip.setFlip();
				}catch(Exception ex){System.out.println(ex);}
			}
			
		}
	}
	public static class Create implements ActionListener{
		MainFrame frame;
		public Create(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.shapeProArray.clear();
			frame.backShapeProArrayArray.clear();
			frame.frontShapeProArrayArray.clear();
			frame.canvas.repaint();
			frame.repaintFlip.setFlip();
		}
	}
	public static class CreateServer implements ActionListener{
		MainFrame frame;
		public CreateServer(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			CreateServerDialog createServerDialog=new CreateServerDialog(frame);
		}
	}
	public static class ConnectToServer implements ActionListener{
		MainFrame frame;
		public ConnectToServer(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			ConnectToServerDialog connectToServerDialog=new ConnectToServerDialog(frame);
		}
	}
	public static class Cut implements ActionListener{
		MainFrame frame;
		public Cut(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
				frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
				for(int i=0;i<frame.shapeProArray.size();i++){
					if(frame.shapeProArray.get(i).equals(DrawHandler.currentSelectedShapePro)){
						frame.clipboardShapePro=CopyShapePro.copy1(DrawHandler.currentSelectedShapePro);
						DrawHandler.currentSelectedShapePro.selected=false;				
						frame.shapeProArray.remove(i);
						frame.canvas.repaint();
						frame.repaintFlip.setFlip();
						break;
					}
				}
			}
		}
	}
	public static class Copy implements ActionListener{
		MainFrame frame;
		public Copy(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
				//DrawHandler.currentSelectedShapePro.selected=false;
				//frame.clipboardShapePro=new ShapePro(DrawHandler.currentSelectedShapePro.shape,DrawHandler.currentSelectedShapePro.stroke,DrawHandler.currentSelectedShapePro.lined,DrawHandler.currentSelectedShapePro.lineColor,DrawHandler.currentSelectedShapePro.fillType,DrawHandler.currentSelectedShapePro.fillColor,DrawHandler.currentSelectedShapePro.gradientFillColor,DrawHandler.currentSelectedShapePro.shapeType,DrawHandler.currentSelectedShapePro.pointed,false,DrawHandler.currentSelectedShapePro.rotated,DrawHandler.currentSelectedShapePro.rotatedAngle,DrawHandler.currentSelectedShapePro.rotatedVecX,DrawHandler.currentSelectedShapePro.rotatedVecY,DrawHandler.currentSelectedShapePro.scaled,DrawHandler.currentSelectedShapePro.scaledFactorX*2,DrawHandler.currentSelectedShapePro.scaledFactorY*2,DrawHandler.currentSelectedShapePro.sheared,DrawHandler.currentSelectedShapePro.shearedFactorX,DrawHandler.currentSelectedShapePro.shearedFactorY);
				frame.clipboardShapePro=CopyShapePro.copy1(DrawHandler.currentSelectedShapePro);
				DrawHandler.currentSelectedShapePro.selected=false;
				//DrawHandler.currentSelectedShapePro=null;
				//System.out.println(frame.clipboardShapePro==DrawHandler.currentSelectedShapePro);
				
			}
		}
	}
	public static class Paste implements ActionListener{
		MainFrame frame;
		public Paste(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
			//switch(frame.clipboardShapePro.shape){
			//case ShapeType
			//}
			ShapePro a=CopyShapePro.copy1(frame.clipboardShapePro);
//			frame.shapeProArray.add(new ShapePro(frame.clipboardShapePro.shape,frame.clipboardShapePro.stroke,frame.clipboardShapePro.lined,frame.clipboardShapePro.lineColor,frame.clipboardShapePro.fillType,frame.clipboardShapePro.fillColor,frame.clipboardShapePro.gradientFillColor,frame.clipboardShapePro.shapeType,frame.clipboardShapePro.pointed,false,frame.clipboardShapePro.rotated,frame.clipboardShapePro.rotatedAngle,frame.clipboardShapePro.rotatedVecX,frame.clipboardShapePro.rotatedVecY,frame.clipboardShapePro.scaled,frame.clipboardShapePro.scaledFactorX,frame.clipboardShapePro.scaledFactorY,frame.clipboardShapePro.sheared,frame.clipboardShapePro.shearedFactorX,frame.clipboardShapePro.shearedFactorY));
			//System.out.println(a==frame.clipboardShapePro);
			frame.shapeProArray.add(a);
			DrawHandler.currentSelectedShapePro.selected=false;
			frame.canvas.repaint();
			frame.repaintFlip.setFlip();
			System.out.println(frame.shapeProArray.size());
			//System.out.println(frame.shapeProArray.get(1)==frame.shapeProArray.get(0));
		}
	}
	public static class Delete implements ActionListener{
		MainFrame frame;
		public Delete(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			if(DrawHandler.currentSelectedShapePro!=null&&DrawHandler.currentSelectedShapePro.selected==true){
				frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
				for(int i=0;i<frame.shapeProArray.size();i++){
					if(frame.shapeProArray.get(i).equals(DrawHandler.currentSelectedShapePro)){
						DrawHandler.currentSelectedShapePro.selected=false;
						frame.shapeProArray.remove(i);
						frame.canvas.repaint();
						frame.repaintFlip.setFlip();
						break;
					}
				}
			}
		}
	}
	public static class ToJPEG implements ActionListener{
		MainFrame frame;
		public ToJPEG(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			Rectangle canvasBound=new Rectangle(0,0,frame.canvas.getWidth(),frame.canvas.getHeight());
			BufferedImage image_export=new BufferedImage(canvasBound.width,canvasBound.height,BufferedImage.TYPE_INT_RGB);
			Graphics2D g2_export=image_export.createGraphics();
			Shape transformedShape;
			//���׵�
			g2_export.setColor(Color.WHITE);
			g2_export.fill(canvasBound);
			
			for(int i=0;i<frame.shapeProArray.size();i++){
				//ShapePro s=(ShapePro)it.next();
				ShapePro s=frame.shapeProArray.get(i);
				//��������ֶ�����Ҫ����shape
				if(s.shapeType==ShapeType.TEXT){
					    FontRenderContext context=g2_export.getFontRenderContext();
						TextShapePro ts=(TextShapePro)(s);//ts��sָ��ͬһ����ֻ�������������õ����Ͳ�ͬ
						TextLayout layout=new TextLayout(ts.text,ts.font,context);
						AffineTransform transform=new AffineTransform();
						transform.setToTranslation(ts.position.x, ts.position.y);
						Shape outline=layout.getOutline(transform);
						ts.shape=outline;
						Rectangle2D boundsRect=ts.shape.getBounds2D();
						ts.textHeight=boundsRect.getHeight();
						ts.textWidth=boundsRect.getWidth();
				}
				//��ʼ��transformedShape
				transformedShape=s.shape;
				//��ʼ��ͼ�ν��ϵĿ��Ƶ�
			    ShapePro.p1=s.getLeftTop();
				ShapePro.p2=s.getRightTop();
				ShapePro.p3=s.getLeftBottom();
				ShapePro.p4=s.getRightBottom();
				//���û�����ͼ����
				g2_export.setStroke(new BasicStroke(s.stroke.width,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f,s.stroke.pattern,0));
				g2_export.setColor(s.lineColor);
				//oldTransform=g2_export.getTransform();
				
				
				//*****************************************************************
				//�任˵��
				//����drag�������ɸ�����������ֱ���޸�ͼ�ε��������ֵ���ɣ���ʼ�㡢���ֵ��
				//����scale,rotate��shear�������޷�ͨ���޸��������ʵ�ֱ任�����������������������
				//����任�����µ�Shape����(transformedShape)������������µĶ�����л�ͼ�����
				//��������µ�Shape���󲢲��洢���ڴ��д�Ļ��Ƿ���仯ǰ��Shape��
				//���������������ʱ���û���Ҫ���ж�������Ƿ����µ�Shape�����С���������µ�Shape
				//��δ�洢�����Խ���������ж�ʱ��Ҫ�����λ�ý��з��任�����ڴ��еı任ǰ��
				//Shape����������ϵ�����жϡ�
				//ע��˳���ҵı任˳������
				//���任��scale,shear,rotate
				//��任��rotate,shear,scale
				//ע�⵽˳������෴����������ʹһ��ֵ�����任�پ���任���ܻص�ԭ����״̬
				//*****************************************************************
				
				
				//�˺��������ԭʼShape�����任
				
				
				//�ж��Ƿ������ţ������������任
				if(s.scaled==true){
					AffineTransform t=new AffineTransform();
					AffineTransform t2=new AffineTransform();
					t.setToTranslation(s.getCenterPoint().x,s.getCenterPoint().y);
					t2.concatenate(t);
					//g2_export.transform(t);
					t.setToScale(s.scaledFactorX,s.scaledFactorY);
					t2.concatenate(t);
					//transformedShape=t.createTransformedShape(transformedShape);
					//g2_export.transform(t);
					t.setToTranslation(-s.getCenterPoint().x,-s.getCenterPoint().y);
					t2.concatenate(t);
					transformedShape=t2.createTransformedShape(transformedShape);
					//g2_export.transform(t);
					t2.transform(ShapePro.p1, ShapePro.p1);
					t2.transform(ShapePro.p2, ShapePro.p2);
					t2.transform(ShapePro.p3, ShapePro.p3);
					t2.transform(ShapePro.p4, ShapePro.p4);
				}
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
				    //g2_export.transform(t);
				}
				
				
				//���б߿���ʼ��߿�
				if(s.lined==true)
					g2_export.draw(transformedShape);
				//��ʼ���
				if(s.fillType==FillType.NONE){
					//Don't fill
				}
				if(s.fillType==FillType.SOLID){
				    g2_export.setColor(s.fillColor);
				    g2_export.fill(transformedShape);
				}
				else if(s.fillType==FillType.GRADIENT){
					g2_export.setPaint(new GradientPaint(s.gradientFillColor.point1,s.gradientFillColor.color1,s.gradientFillColor.point2,s.gradientFillColor.color2,true));
					g2_export.fill(transformedShape);
				}
			}
			JFileChooser fc=new JFileChooser(".");
			if(fc.showSaveDialog(frame)==JFileChooser.APPROVE_OPTION){
				File file=fc.getSelectedFile();
				file=new File(file.toString()+".jpg");
				try{
					FileOutputStream os=new FileOutputStream(file);
					ImageIO.write(image_export, "JPEG", os);
					}catch(Exception ex){
						System.out.print(ex);
					}
			}
			
		}
	}
	public static class ToPNG implements ActionListener{
		MainFrame frame;
		public ToPNG(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			Rectangle canvasBound=new Rectangle(0,0,frame.canvas.getWidth(),frame.canvas.getHeight());
			BufferedImage image_export=new BufferedImage(canvasBound.width,canvasBound.height,BufferedImage.TYPE_INT_RGB);
			Graphics2D g2_export=image_export.createGraphics();
			Shape transformedShape;
			//���׵�
			g2_export.setColor(Color.WHITE);
			g2_export.fill(canvasBound);
			
			for(int i=0;i<frame.shapeProArray.size();i++){
				//ShapePro s=(ShapePro)it.next();
				ShapePro s=frame.shapeProArray.get(i);
				//��������ֶ�����Ҫ����shape
				if(s.shapeType==ShapeType.TEXT){
					    FontRenderContext context=g2_export.getFontRenderContext();
						TextShapePro ts=(TextShapePro)(s);//ts��sָ��ͬһ����ֻ�������������õ����Ͳ�ͬ
						TextLayout layout=new TextLayout(ts.text,ts.font,context);
						AffineTransform transform=new AffineTransform();
						transform.setToTranslation(ts.position.x, ts.position.y);
						Shape outline=layout.getOutline(transform);
						ts.shape=outline;
						Rectangle2D boundsRect=ts.shape.getBounds2D();
						ts.textHeight=boundsRect.getHeight();
						ts.textWidth=boundsRect.getWidth();
				}
				//��ʼ��transformedShape
				transformedShape=s.shape;
				//��ʼ��ͼ�ν��ϵĿ��Ƶ�
			    ShapePro.p1=s.getLeftTop();
				ShapePro.p2=s.getRightTop();
				ShapePro.p3=s.getLeftBottom();
				ShapePro.p4=s.getRightBottom();
				//���û�����ͼ����
				g2_export.setStroke(new BasicStroke(s.stroke.width,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f,s.stroke.pattern,0));
				g2_export.setColor(s.lineColor);
				//oldTransform=g2_export.getTransform();
				
				
				//*****************************************************************
				//�任˵��
				//����drag�������ɸ�����������ֱ���޸�ͼ�ε��������ֵ���ɣ���ʼ�㡢���ֵ��
				//����scale,rotate��shear�������޷�ͨ���޸��������ʵ�ֱ任�����������������������
				//����任�����µ�Shape����(transformedShape)������������µĶ�����л�ͼ�����
				//��������µ�Shape���󲢲��洢���ڴ��д�Ļ��Ƿ���仯ǰ��Shape��
				//���������������ʱ���û���Ҫ���ж�������Ƿ����µ�Shape�����С���������µ�Shape
				//��δ�洢�����Խ���������ж�ʱ��Ҫ�����λ�ý��з��任�����ڴ��еı任ǰ��
				//Shape����������ϵ�����жϡ�
				//ע��˳���ҵı任˳������
				//���任��scale,shear,rotate
				//��任��rotate,shear,scale
				//ע�⵽˳������෴����������ʹһ��ֵ�����任�پ���任���ܻص�ԭ����״̬
				//*****************************************************************
				
				
				//�˺��������ԭʼShape�����任
				
				
				//�ж��Ƿ������ţ������������任
				if(s.scaled==true){
					AffineTransform t=new AffineTransform();
					AffineTransform t2=new AffineTransform();
					t.setToTranslation(s.getCenterPoint().x,s.getCenterPoint().y);
					t2.concatenate(t);
					//g2_export.transform(t);
					t.setToScale(s.scaledFactorX,s.scaledFactorY);
					t2.concatenate(t);
					//transformedShape=t.createTransformedShape(transformedShape);
					//g2_export.transform(t);
					t.setToTranslation(-s.getCenterPoint().x,-s.getCenterPoint().y);
					t2.concatenate(t);
					transformedShape=t2.createTransformedShape(transformedShape);
					//g2_export.transform(t);
					t2.transform(ShapePro.p1, ShapePro.p1);
					t2.transform(ShapePro.p2, ShapePro.p2);
					t2.transform(ShapePro.p3, ShapePro.p3);
					t2.transform(ShapePro.p4, ShapePro.p4);
				}
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
				    //g2_export.transform(t);
				}
				
				
				//���б߿���ʼ��߿�
				if(s.lined==true)
					g2_export.draw(transformedShape);
				//��ʼ���
				if(s.fillType==FillType.NONE){
					//Don't fill
				}
				if(s.fillType==FillType.SOLID){
				    g2_export.setColor(s.fillColor);
				    g2_export.fill(transformedShape);
				}
				else if(s.fillType==FillType.GRADIENT){
					g2_export.setPaint(new GradientPaint(s.gradientFillColor.point1,s.gradientFillColor.color1,s.gradientFillColor.point2,s.gradientFillColor.color2,true));
					g2_export.fill(transformedShape);
				}
			}
			JFileChooser fc=new JFileChooser(".");
			if(fc.showSaveDialog(frame)==JFileChooser.APPROVE_OPTION){
				File file=fc.getSelectedFile();
				file=new File(file.toString()+".png");
				try{
					FileOutputStream os=new FileOutputStream(file);
					ImageIO.write(image_export, "PNG", os);
					}catch(Exception ex){
						System.out.print(ex);
					}
			}
			
		}
	}
	public static class ToBMP implements ActionListener{
		MainFrame frame;
		public ToBMP(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			Rectangle canvasBound=new Rectangle(0,0,frame.canvas.getWidth(),frame.canvas.getHeight());
			BufferedImage image_export=new BufferedImage(canvasBound.width,canvasBound.height,BufferedImage.TYPE_INT_RGB);
			Graphics2D g2_export=image_export.createGraphics();
			Shape transformedShape;
			//���׵�
			g2_export.setColor(Color.WHITE);
			g2_export.fill(canvasBound);
			
			for(int i=0;i<frame.shapeProArray.size();i++){
				//ShapePro s=(ShapePro)it.next();
				ShapePro s=frame.shapeProArray.get(i);
				//��������ֶ�����Ҫ����shape
				if(s.shapeType==ShapeType.TEXT){
					    FontRenderContext context=g2_export.getFontRenderContext();
						TextShapePro ts=(TextShapePro)(s);//ts��sָ��ͬһ����ֻ�������������õ����Ͳ�ͬ
						TextLayout layout=new TextLayout(ts.text,ts.font,context);
						AffineTransform transform=new AffineTransform();
						transform.setToTranslation(ts.position.x, ts.position.y);
						Shape outline=layout.getOutline(transform);
						ts.shape=outline;
						Rectangle2D boundsRect=ts.shape.getBounds2D();
						ts.textHeight=boundsRect.getHeight();
						ts.textWidth=boundsRect.getWidth();
				}
				//��ʼ��transformedShape
				transformedShape=s.shape;
				//��ʼ��ͼ�ν��ϵĿ��Ƶ�
			    ShapePro.p1=s.getLeftTop();
				ShapePro.p2=s.getRightTop();
				ShapePro.p3=s.getLeftBottom();
				ShapePro.p4=s.getRightBottom();
				//���û�����ͼ����
				g2_export.setStroke(new BasicStroke(s.stroke.width,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER,10.0f,s.stroke.pattern,0));
				g2_export.setColor(s.lineColor);
				//oldTransform=g2_export.getTransform();
				
				
				//*****************************************************************
				//�任˵��
				//����drag�������ɸ�����������ֱ���޸�ͼ�ε��������ֵ���ɣ���ʼ�㡢���ֵ��
				//����scale,rotate��shear�������޷�ͨ���޸��������ʵ�ֱ任�����������������������
				//����任�����µ�Shape����(transformedShape)������������µĶ�����л�ͼ�����
				//��������µ�Shape���󲢲��洢���ڴ��д�Ļ��Ƿ���仯ǰ��Shape��
				//���������������ʱ���û���Ҫ���ж�������Ƿ����µ�Shape�����С���������µ�Shape
				//��δ�洢�����Խ���������ж�ʱ��Ҫ�����λ�ý��з��任�����ڴ��еı任ǰ��
				//Shape����������ϵ�����жϡ�
				//ע��˳���ҵı任˳������
				//���任��scale,shear,rotate
				//��任��rotate,shear,scale
				//ע�⵽˳������෴����������ʹһ��ֵ�����任�پ���任���ܻص�ԭ����״̬
				//*****************************************************************
				
				
				//�˺��������ԭʼShape�����任
				
				
				//�ж��Ƿ������ţ������������任
				if(s.scaled==true){
					AffineTransform t=new AffineTransform();
					AffineTransform t2=new AffineTransform();
					t.setToTranslation(s.getCenterPoint().x,s.getCenterPoint().y);
					t2.concatenate(t);
					//g2_export.transform(t);
					t.setToScale(s.scaledFactorX,s.scaledFactorY);
					t2.concatenate(t);
					//transformedShape=t.createTransformedShape(transformedShape);
					//g2_export.transform(t);
					t.setToTranslation(-s.getCenterPoint().x,-s.getCenterPoint().y);
					t2.concatenate(t);
					transformedShape=t2.createTransformedShape(transformedShape);
					//g2_export.transform(t);
					t2.transform(ShapePro.p1, ShapePro.p1);
					t2.transform(ShapePro.p2, ShapePro.p2);
					t2.transform(ShapePro.p3, ShapePro.p3);
					t2.transform(ShapePro.p4, ShapePro.p4);
				}
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
				    //g2_export.transform(t);
				}
				
				
				//���б߿���ʼ��߿�
				if(s.lined==true)
					g2_export.draw(transformedShape);
				//��ʼ���
				if(s.fillType==FillType.NONE){
					//Don't fill
				}
				if(s.fillType==FillType.SOLID){
				    g2_export.setColor(s.fillColor);
				    g2_export.fill(transformedShape);
				}
				else if(s.fillType==FillType.GRADIENT){
					g2_export.setPaint(new GradientPaint(s.gradientFillColor.point1,s.gradientFillColor.color1,s.gradientFillColor.point2,s.gradientFillColor.color2,true));
					g2_export.fill(transformedShape);
				}
			}
			JFileChooser fc=new JFileChooser(".");
			if(fc.showSaveDialog(frame)==JFileChooser.APPROVE_OPTION){
				File file=fc.getSelectedFile();
				file=new File(file.toString()+".bmp");
				try{
					FileOutputStream os=new FileOutputStream(file);
					ImageIO.write(image_export, "BMP", os);
					}catch(Exception ex){
						System.out.print(ex);
					}
			}
			
		}
	}
	public static class About implements ActionListener{
		MainFrame frame;
		public About(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			AboutDialog about=new AboutDialog();
		}
	}
	public static class Undo implements ActionListener{
		MainFrame frame;
		public Undo(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			if(frame.backShapeProArrayArray.size()!=0){//���Գ���
				ShapePro s;
				//shapeProArray��һ�ݸ�frontShapeProArrayArray,�Ա�redo
				frame.frontShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
				//��ǰshapeProArray�ĳɾ�ֵ(��backShapeProArrayArray��ȡ�þ�ֵ)
				frame.shapeProArray.clear();
				ArrayList<ShapePro> a=frame.backShapeProArrayArray.get(frame.backShapeProArrayArray.size()-1);
				for(int i=0;i<a.size();i++){
					s=a.get(i);
					if(s.selected==true)
						DrawHandler.currentSelectedShapePro=s;
					frame.shapeProArray.add(s);
				}
				//�����Ҳ��������
				if(DrawHandler.currentSelectedShapePro!=null){
					frame.lineColorIndicator.setBackground(DrawHandler.currentSelectedShapePro.lineColor);
					frame.fillColorIndicator.setBackground(DrawHandler.currentSelectedShapePro.fillColor);
					frame.fillColorIndicator2.setBackground(DrawHandler.currentSelectedShapePro.gradientFillColor.color2);
					if(DrawHandler.currentSelectedShapePro.fillType!=FillType.NONE){
						frame.gradientFillCheck.setEnabled(true);
						if(DrawHandler.currentSelectedShapePro.fillType==FillType.GRADIENT){
							frame.gradientFillCheck.setSelected(true);
							frame.gradientFillButton.setEnabled(true);
						}
						else{
							frame.gradientFillCheck.setSelected(false);
							frame.gradientFillButton.setEnabled(false);
						}
					}
					else{
						frame.gradientFillCheck.setSelected(false);
						frame.gradientFillCheck.setEnabled(false);
						frame.gradientFillButton.setEnabled(false);
					}
					frame.lineWidthIndicator2.setText(new Integer(DrawHandler.currentSelectedShapePro.stroke.width).toString());
					frame.lineStyleIndicator2.setText(DrawHandler.currentSelectedShapePro.stroke.patternName);
					frame.lineWidthChooserSlider.setValue(DrawHandler.currentSelectedShapePro.stroke.width);
					if(DrawHandler.currentSelectedShapePro.shapeType==ShapeType.ROUNDRECTANGLE){
						frame.arcWidthIndicator.setText(new Integer((int)((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).arcwidth).toString());
						frame.arcWidthSlider.setValue((int)((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).arcwidth);
						frame.arcHeightIndicator.setText(new Integer((int)((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).archeight).toString());
						frame.arcHeightSlider.setValue((int)((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).archeight);
					}
					if(DrawHandler.currentSelectedShapePro.lined==true&&DrawHandler.currentSelectedShapePro.fillType!=FillType.NONE)
						frame.lineAndFillButton.setSelected(true);
					else if(DrawHandler.currentSelectedShapePro.lined==true)
						frame.lineOnlyButton.setSelected(true);
					else
						frame.fillOnlyButton.setSelected(true);
					frame.previewCanvas.repaint();
				}
				//backShapeProArrayArray�д�����һ�ε�shapeProArray�ľ�ֵ�Ѿ��ָ���shapeProArray
				//���Խ���ɾ����¶����ǰһ�ε�shapeProArray��ֵ
				frame.backShapeProArrayArray.remove(frame.backShapeProArrayArray.size()-1);
				frame.canvas.repaint();
				frame.repaintFlip.setFlip();
			}
		}
	}
	public static class Redo implements ActionListener{
		MainFrame frame;
		public Redo(MainFrame frm){
			frame=frm;
		}
		public void actionPerformed(ActionEvent e){
			if(frame.frontShapeProArrayArray.size()!=0){//��������
				ShapePro s;
				//shapeProArray��һ�ݸ�backShapeProArrayArray,�Ա�undo
				frame.backShapeProArrayArray.add(CopyShapeProArray.copy(frame.shapeProArray));
				//��ǰshapeProArray�ĳ���ֵ(��frontShapeProArrayArray��ȡ����ֵ)
				frame.shapeProArray.clear();
				ArrayList<ShapePro> a=frame.frontShapeProArrayArray.get(frame.frontShapeProArrayArray.size()-1);
				for(int i=0;i<a.size();i++){
					s=a.get(i);
					if(s.selected==true)
						DrawHandler.currentSelectedShapePro=s;
					frame.shapeProArray.add(s);
				}
				//�����Ҳ��������
				if(DrawHandler.currentSelectedShapePro!=null){
					frame.lineColorIndicator.setBackground(DrawHandler.currentSelectedShapePro.lineColor);
					frame.fillColorIndicator.setBackground(DrawHandler.currentSelectedShapePro.fillColor);
					frame.fillColorIndicator2.setBackground(DrawHandler.currentSelectedShapePro.gradientFillColor.color2);
					if(DrawHandler.currentSelectedShapePro.fillType!=FillType.NONE){
						frame.gradientFillCheck.setEnabled(true);
						if(DrawHandler.currentSelectedShapePro.fillType==FillType.GRADIENT){
							frame.gradientFillCheck.setSelected(true);
							frame.gradientFillButton.setEnabled(true);
						}
						else{
							frame.gradientFillCheck.setSelected(false);
							frame.gradientFillButton.setEnabled(false);
						}
					}
					else{
						frame.gradientFillCheck.setSelected(false);
						frame.gradientFillCheck.setEnabled(false);
						frame.gradientFillButton.setEnabled(false);
					}
					frame.lineWidthIndicator2.setText(new Integer(DrawHandler.currentSelectedShapePro.stroke.width).toString());
					frame.lineStyleIndicator2.setText(DrawHandler.currentSelectedShapePro.stroke.patternName);
					frame.lineWidthChooserSlider.setValue(DrawHandler.currentSelectedShapePro.stroke.width);
					if(DrawHandler.currentSelectedShapePro.shapeType==ShapeType.ROUNDRECTANGLE){
						frame.arcWidthIndicator.setText(new Integer((int)((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).arcwidth).toString());
						frame.arcWidthSlider.setValue((int)((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).arcwidth);
						frame.arcHeightIndicator.setText(new Integer((int)((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).archeight).toString());
						frame.arcHeightSlider.setValue((int)((RoundRectangle2D.Double)(DrawHandler.currentSelectedShapePro.shape)).archeight);
					}
					if(DrawHandler.currentSelectedShapePro.lined==true&&DrawHandler.currentSelectedShapePro.fillType!=FillType.NONE)
						frame.lineAndFillButton.setSelected(true);
					else if(DrawHandler.currentSelectedShapePro.lined==true)
						frame.lineOnlyButton.setSelected(true);
					else
						frame.fillOnlyButton.setSelected(true);
					frame.previewCanvas.repaint();
				}
				//frontShapeProArrayArray�д�����һ�ε�shapeProArray����ֵ�Ѿ��ָ���shapeProArray
				//���Խ���ɾ����¶������һ�ε�shapeProArray��ֵ
				frame.frontShapeProArrayArray.remove(frame.frontShapeProArrayArray.size()-1);
				frame.canvas.repaint();
				frame.repaintFlip.setFlip();
			}
		}
	}
}
