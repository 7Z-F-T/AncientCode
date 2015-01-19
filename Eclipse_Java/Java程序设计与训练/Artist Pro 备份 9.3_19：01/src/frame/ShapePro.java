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
		//变换说明
		//对于drag和scale操作，可根据鼠标操作，直接修改图形的坐标变量值即可（起始点、宽高值）
		//对于rotate和shear操作，无法通过修改坐标变量实现变换。所以需根据鼠标操作，采用
		//仿射变换出的新的Shape对象(transformedShape)，并利用这个新的对象进行绘图输出。
		//但是这个新的Shape对象并不存储，内存中存的还是仿射变化前的Shape。
		//而进行鼠标点击测试时，用户需要的判断是鼠标是否在新的Shape区域中。由于这个新的Shape
		//并未存储，所以进行鼠标点击判断时需要将鼠标位置进行反变换后，与内存中的变换前的
		//Shape对象的区域关系进行判断。
		//注意顺序！我的变换顺序如下
		//正变换：shear,rotate
		//逆变换：rotate,shear
		//注意到顺序必须相反，这样才能使一个值经正变换再经逆变换后，能回到原来的状态
		//*****************************************************************
		
		
		//此函数内完成鼠标位置的逆变换
		switch(this.shapeType){
		case ShapeType.RECTANGLE:
			//判断是否已发生旋转，若已旋转则将鼠标坐标逆变换
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
				t.transform(p, p);//变换完后的鼠标点击点存于p中
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
			//判断是否已发生剪切，若已剪切则将鼠标坐标逆变换
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
			//判断是否已发生旋转，若已旋转则将鼠标坐标逆变换
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
				t.transform(p, p);//变换完后的鼠标点击点存于p中
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
			//判断是否已发生剪切，若已剪切则将鼠标坐标逆变换
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
			//判断是否已发生旋转，若已旋转则将鼠标坐标逆变换
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
				t.transform(p, p);//变换完后的鼠标点击点存于p中
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
			//判断是否已发生剪切，若已剪切则将鼠标坐标逆变换
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

