package frame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
public class ShapePro {
	public Shape shape;
	public BasicStroke stroke;
	public boolean lined;
	public Color lineColor;
	public int fillType;
	public Color fillColor;
	public GradientPaint gradientFillColor;
	public int shapeType;
	public boolean pointed;
	public boolean selected;
	public boolean rotated;
	public double rotatedAngle;
	public double rotatedVecX;
	public double rotatedVecY;
	public boolean scaled;
	public double scaledFactorX;
	public double scaledFactorY;
	public boolean sheared;
	public double shearedFactorX;
	public double shearedFactorY;
	
	public static Point2D.Double p1=new Point2D.Double();
	public static Point2D.Double p2=new Point2D.Double();;
	public static Point2D.Double p3=new Point2D.Double();;
	public static Point2D.Double p4=new Point2D.Double();;
	public static Ellipse2D.Double c1=new Ellipse2D.Double();
	public static Ellipse2D.Double c2=new Ellipse2D.Double();
	public static Ellipse2D.Double c3=new Ellipse2D.Double();
	public static Ellipse2D.Double c4=new Ellipse2D.Double();
	
	public ShapePro(){}
	public ShapePro(Shape s,BasicStroke stk,boolean lnd,Color lc, int fType, Color fc, GradientPaint gfc,int type,boolean ptd,boolean sel,boolean rot,double rAngle ,double rvx,double rvy,boolean sca,double sfx,double sfy,boolean she,double shfx,double shfy){
		shape=s;
		stroke=stk;
		lined=lnd;
		lineColor=lc;
		fillType=fType;
		fillColor=fc;
		gradientFillColor=gfc;
		shapeType=type;
		pointed=ptd;
		selected=sel;
		rotated=rot;
		rotatedAngle=rAngle;
		rotatedVecX=rvx;
		rotatedVecY=rvy;
		scaled=sca;
		scaledFactorX=sfx;
		scaledFactorY=sfy;
		sheared=she;
		shearedFactorX=shfx;
		shearedFactorY=shfy;
	}
	public boolean containsPro(double mouseX,double mouseY){
		
		
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
		
		
		//�˺�����������λ�õ���任
		switch(this.shapeType){
		case ShapeType.RECTANGLE:
			//�ж��Ƿ��ѷ�����ת��������ת�����������任
			if(rotated==true){
				AffineTransform t=new AffineTransform();
				//double vecX=(rotatedVecX*rotatedVecX)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
				//double vecY=-(rotatedVecX*rotatedVecY)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
				//t.setToRotation(rotatedVecX,rotatedVecY,getCenterPoint().x,getCenterPoint().y);
				t.setToRotation(rotatedAngle, getCenterPoint().x, getCenterPoint().y);
				try{
				t=t.createInverse();
				}catch(NoninvertibleTransformException e){}
				Point2D.Double p=new Point2D.Double(mouseX,mouseY);
				t.transform(p, p);//�任��������������p��
				mouseX=p.x;
				mouseY=p.y;
			}
			/*
			if(scaled==true){
				AffineTransform t=new AffineTransform();
				double factorX=1/scaledFactorX;
				double factorY=1/scaledFactorY;
				Point2D.Double p=new Point2D.Double(mouseX,mouseY);
				t.setToTranslation(getCenterPoint().x, getCenterPoint().y);
				try{
					t=t.createInverse();
					}catch(NoninvertibleTransformException e){}
				t.transform(p,p);
				t.setToScale(scaledFactorX, scaledFactorY);
				try{
				t=t.createInverse();
				}catch(NoninvertibleTransformException e){}
				t.transform(p, p);
				t.setToTranslation(-getCenterPoint().x, -getCenterPoint().y);
				try{
					t=t.createInverse();
					}catch(NoninvertibleTransformException e){}
				t.transform(p, p);
				mouseX=p.x;
				mouseY=p.y;
			}*/
			//�ж��Ƿ��ѷ������У����Ѽ��������������任
			if(sheared==true){
				AffineTransform t=new AffineTransform();
				Point2D.Double p=new Point2D.Double(mouseX,mouseY);
				t.setToTranslation(getCenterPoint().x, getCenterPoint().y);
				try{
					t=t.createInverse();
					}catch(NoninvertibleTransformException e){}
				t.transform(p,p);
				t.setToShear(shearedFactorX, shearedFactorY);
				try{
				t=t.createInverse();
				}catch(NoninvertibleTransformException e){}
				t.transform(p, p);
				t.setToTranslation(-getCenterPoint().x, -getCenterPoint().y);
				try{
					t=t.createInverse();
					}catch(NoninvertibleTransformException e){}
				t.transform(p,p);
				mouseX=p.x;
				mouseY=p.y;
			}
			return shape.contains(mouseX, mouseY);
		case ShapeType.ELLIPSE:
			//�ж��Ƿ��ѷ�����ת��������ת�����������任
			if(rotated==true){
				AffineTransform t=new AffineTransform();
				//double vecX=(rotatedVecX*rotatedVecX)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
				//double vecY=-(rotatedVecX*rotatedVecY)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
				//t.setToRotation(rotatedVecX,rotatedVecY,getCenterPoint().x,getCenterPoint().y);
				t.setToRotation(rotatedAngle, getCenterPoint().x, getCenterPoint().y);
				try{
				t=t.createInverse();
				}catch(NoninvertibleTransformException e){}
				Point2D.Double p=new Point2D.Double(mouseX,mouseY);
				t.transform(p, p);//�任��������������p��
				mouseX=p.x;
				mouseY=p.y;
			}
			/*
			if(scaled==true){
				AffineTransform t=new AffineTransform();
				double factorX=1/scaledFactorX;
				double factorY=1/scaledFactorY;
				Point2D.Double p=new Point2D.Double(mouseX,mouseY);
				t.setToTranslation(getCenterPoint().x, getCenterPoint().y);
				try{
					t=t.createInverse();
					}catch(NoninvertibleTransformException e){}
				t.transform(p,p);
				t.setToScale(scaledFactorX, scaledFactorY);
				try{
				t=t.createInverse();
				}catch(NoninvertibleTransformException e){}
				t.transform(p, p);
				t.setToTranslation(-getCenterPoint().x, -getCenterPoint().y);
				try{
					t=t.createInverse();
					}catch(NoninvertibleTransformException e){}
				t.transform(p, p);
				mouseX=p.x;
				mouseY=p.y;
			}*/
			//�ж��Ƿ��ѷ������У����Ѽ��������������任
			if(sheared==true){
				AffineTransform t=new AffineTransform();
				Point2D.Double p=new Point2D.Double(mouseX,mouseY);
				t.setToTranslation(getCenterPoint().x, getCenterPoint().y);
				try{
					t=t.createInverse();
					}catch(NoninvertibleTransformException e){}
				t.transform(p,p);
				t.setToShear(shearedFactorX, shearedFactorY);
				try{
				t=t.createInverse();
				}catch(NoninvertibleTransformException e){}
				t.transform(p, p);
				t.setToTranslation(-getCenterPoint().x, -getCenterPoint().y);
				try{
					t=t.createInverse();
					}catch(NoninvertibleTransformException e){}
				t.transform(p,p);
				mouseX=p.x;
				mouseY=p.y;
			}
			return shape.contains(mouseX, mouseY);
		case ShapeType.LINE:
			//�ж��Ƿ��ѷ�����ת��������ת�����������任
			if(rotated==true){
				AffineTransform t=new AffineTransform();
				//double vecX=(rotatedVecX*rotatedVecX)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
				//double vecY=-(rotatedVecX*rotatedVecY)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
				//t.setToRotation(rotatedVecX,rotatedVecY,getCenterPoint().x,getCenterPoint().y);
				t.setToRotation(rotatedAngle, getCenterPoint().x, getCenterPoint().y);
				try{
				t=t.createInverse();
				}catch(NoninvertibleTransformException e){}
				Point2D.Double p=new Point2D.Double(mouseX,mouseY);
				t.transform(p, p);//�任��������������p��
				mouseX=p.x;
				mouseY=p.y;
			}
			/*
			if(scaled==true){
				AffineTransform t=new AffineTransform();
				double factorX=1/scaledFactorX;
				double factorY=1/scaledFactorY;
				Point2D.Double p=new Point2D.Double(mouseX,mouseY);
				t.setToTranslation(getCenterPoint().x, getCenterPoint().y);
				try{
					t=t.createInverse();
					}catch(NoninvertibleTransformException e){}
				t.transform(p,p);
				t.setToScale(scaledFactorX, scaledFactorY);
				try{
				t=t.createInverse();
				}catch(NoninvertibleTransformException e){}
				t.transform(p, p);
				t.setToTranslation(-getCenterPoint().x, -getCenterPoint().y);
				try{
					t=t.createInverse();
					}catch(NoninvertibleTransformException e){}
				t.transform(p, p);
				mouseX=p.x;
				mouseY=p.y;
			}*/
			//�ж��Ƿ��ѷ������У����Ѽ��������������任
			if(sheared==true){
				AffineTransform t=new AffineTransform();
				Point2D.Double p=new Point2D.Double(mouseX,mouseY);
				t.setToTranslation(getCenterPoint().x, getCenterPoint().y);
				try{
					t=t.createInverse();
					}catch(NoninvertibleTransformException e){}
				t.transform(p,p);
				t.setToShear(shearedFactorX, shearedFactorY);
				try{
				t=t.createInverse();
				}catch(NoninvertibleTransformException e){}
				t.transform(p, p);
				t.setToTranslation(-getCenterPoint().x, -getCenterPoint().y);
				try{
					t=t.createInverse();
					}catch(NoninvertibleTransformException e){}
				t.transform(p,p);
				mouseX=p.x;
				mouseY=p.y;
			}
			Double x1=((Line2D.Double)shape).x1;
			Double y1=((Line2D.Double)shape).y1;
			Double x2=((Line2D.Double)shape).x2;
			Double y2=((Line2D.Double)shape).y2;
			Double A=y2-y1;
			Double B=x1-x2;
			Double C=x2*y1-x1*y2;
			Double d=(A*mouseX+B*mouseY+C)/(Math.sqrt(A*A+B*B));
			System.out.println(d);
			if(x1>x2){
				Double temp=x2;
				x2=x1;
				x1=temp;
			}
			if(y1>y2){
				Double temp=y2;
				y2=y1;
				y1=temp;
			}
			if((Math.abs(d)<9)&&
					((mouseX<x2+5)&&(mouseX>x1-5))&&
					((mouseY<y2+5)&&(mouseY>y1-5)))
			    return true;
			else 
				return false;
			default:
			    return false;
		}	
	}
	public Point2D.Double getCenterPoint(){
		double x,y;
		switch(shapeType){
		case ShapeType.RECTANGLE:
			x=((Rectangle2D.Double)shape).x+((Rectangle2D.Double)shape).width/2;
			y=((Rectangle2D.Double)shape).y+((Rectangle2D.Double)shape).height/2;
			return new Point2D.Double(x,y);
	    case ShapeType.ELLIPSE:
			x=((Ellipse2D.Double)shape).x+((Ellipse2D.Double)shape).width/2;
			y=((Ellipse2D.Double)shape).y+((Ellipse2D.Double)shape).height/2;
			return new Point2D.Double(x,y);
	    case ShapeType.LINE:
			x=(((Line2D.Double)shape).x1+((Line2D.Double)shape).x2)/2;
			y=(((Line2D.Double)shape).y1+((Line2D.Double)shape).y2)/2;
			return new Point2D.Double(x,y);
		default:
			return new Point2D.Double(0,0);
		}
	}
	public Point2D.Double getLeftTop(){
		double x,y;
		switch(shapeType){
		case ShapeType.RECTANGLE:
			x=((Rectangle2D.Double)shape).x;
			y=((Rectangle2D.Double)shape).y;
			return new Point2D.Double(x,y);
	    case ShapeType.ELLIPSE:
	    	x=((Ellipse2D.Double)shape).x;
			y=((Ellipse2D.Double)shape).y;
			return new Point2D.Double(x,y);
	    case ShapeType.LINE:
	    	return new Point2D.Double(((Line2D.Double)shape).x1,((Line2D.Double)shape).y1);
	   	default:
			return new Point2D.Double(0,0);
		}
	}
	public Point2D.Double getRightTop(){
		double x,y;
		switch(shapeType){
		case ShapeType.RECTANGLE:
			x=((Rectangle2D.Double)shape).x+((Rectangle2D.Double)shape).width;
			y=((Rectangle2D.Double)shape).y;
			return new Point2D.Double(x,y);
	    case ShapeType.ELLIPSE:
	    	x=((Ellipse2D.Double)shape).x+((Ellipse2D.Double)shape).width;
			y=((Ellipse2D.Double)shape).y;
			return new Point2D.Double(x,y);
	    case ShapeType.LINE:
	    	return new Point2D.Double(((Line2D.Double)shape).x1,((Line2D.Double)shape).y1);
	    default:
			return new Point2D.Double(0,0);
		}
	}
	public Point2D.Double getLeftBottom(){
		double x,y;
		switch(shapeType){
		case ShapeType.RECTANGLE:
			x=((Rectangle2D.Double)shape).x;
			y=((Rectangle2D.Double)shape).y+((Rectangle2D.Double)shape).height;
			return new Point2D.Double(x,y);
	    case ShapeType.ELLIPSE:
	    	x=((Ellipse2D.Double)shape).x;
			y=((Ellipse2D.Double)shape).y+((Ellipse2D.Double)shape).height;;
			return new Point2D.Double(x,y);
	    case ShapeType.LINE:
	    	return new Point2D.Double(((Line2D.Double)shape).x2,((Line2D.Double)shape).y2);
	    default:
			return new Point2D.Double(0,0);
		}
	}
	public Point2D.Double getRightBottom(){
		double x,y;
		switch(shapeType){
		case ShapeType.RECTANGLE:
			x=((Rectangle2D.Double)shape).x+((Rectangle2D.Double)shape).width;
			y=((Rectangle2D.Double)shape).y+((Rectangle2D.Double)shape).height;
			return new Point2D.Double(x,y);
	    case ShapeType.ELLIPSE:
	    	x=((Ellipse2D.Double)shape).x+((Ellipse2D.Double)shape).width;
			y=((Ellipse2D.Double)shape).y+((Ellipse2D.Double)shape).height;;
			return new Point2D.Double(x,y);
	    case ShapeType.LINE:
	    	return new Point2D.Double(((Line2D.Double)shape).x2,((Line2D.Double)shape).y2);
	    default:
			return new Point2D.Double(0,0);
		}
	}
	
}

