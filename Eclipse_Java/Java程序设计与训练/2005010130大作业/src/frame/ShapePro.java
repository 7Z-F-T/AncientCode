package frame;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;
/**
 * 图形类。存储各式各样的图形。所有绘制的图形都存储为此类的对象。
 * @author 侯杰
 *
 */
public class ShapePro implements Serializable{
	/**
	 * 存储图形轮廓信息，为Shape接口
	 */
	public Shape shape;
	/**
	 * 存储图形所用画笔
	 */
	public BasStroke stroke;
	/**
	 * 存储图形是否有边线
	 */
	public boolean lined;
	/**
	 * 存储图形边线颜色
	 */
	public Color lineColor;
	/**
	 * 存储图形填充类型
	 */
	public int fillType;
	/**
	 * 存储图形填充颜色
	 */
	public Color fillColor;
	/**
	 * 存储图形梯度填充样式
	 */
	public GradPaint gradientFillColor;
	/**
	 * 存储图形类型
	 */
	public int shapeType;
	/**
	 * 存储图形当前是否被鼠标指向
	 */
	public boolean pointed;
	/**
	 * 存储图形当前是否被选中
	 */
	public boolean selected;
	/**
	 * 存储图形是否发生了旋转
	 */
	public boolean rotated;
	/**
	 * 存储图形旋转角度
	 */
	public double rotatedAngle;
	/**
	 * 存储图形旋转横向向量
	 */
	public double rotatedVecX;
	/**
	 * 存储图形旋转纵向向量
	 */
	public double rotatedVecY;
	/**
	 * 存储图形是否发生放缩
	 */
	public boolean scaled;
	/**
	 * 存储图形横向放缩比例
	 */
	public double scaledFactorX;
	/**
	 * 存储图形纵向放缩比例
	 */
	public double scaledFactorY;
	/**
	 * 存储图形是否发生拉扯
	 */
	public boolean sheared;
	/**
	 * 存储图形横向拉扯因子
	 */
	public double shearedFactorX;
	/**
	 * 存储图形纵向拉扯因子
	 */
	public double shearedFactorY;

	/**
	 * 控制点1
	 */
	public static Point2D.Double p1=new Point2D.Double();
	/**
	 * 控制点2
	 */
	public static Point2D.Double p2=new Point2D.Double();;
	/**
	 * 控制点3
	 */
	public static Point2D.Double p3=new Point2D.Double();;
	/**
	 * 控制点4
	 */
	public static Point2D.Double p4=new Point2D.Double();;
	/**
	 * 控制圆圈1
	 */
	public static Ellipse2D.Double c1=new Ellipse2D.Double();
	/**
	 * 控制圆圈2
	 */
	public static Ellipse2D.Double c2=new Ellipse2D.Double();
	/**
	 * 控制圆圈3
	 */
	public static Ellipse2D.Double c3=new Ellipse2D.Double();
	/**
	 * 控制圆圈4
	 */
	public static Ellipse2D.Double c4=new Ellipse2D.Double();
	
	/**
	 * 新建一个默认的ShapePro对象
	 */
	public ShapePro(){}
	/**
	 * 新建一个有指定属性的ShapePro对象
	 * @param s 轮廓（Shape接口）
	 * @param stk 画笔类型
	 * @param lnd 是否有边线
	 * @param lc 边线颜色
	 * @param fType 填充类型
	 * @param fc 填充颜色
	 * @param gfc 梯度填充样式
	 * @param type 图形类型
	 * @param ptd 是否被鼠标指向
	 * @param sel 是否被选中
	 * @param rot 是否发生旋转
	 * @param rAngle 旋转角度
	 * @param rvx 旋转横向向量
	 * @param rvy 旋转纵向向量
	 * @param sca 是否发生放缩
	 * @param sfx 横向放缩比例
	 * @param sfy 纵向放缩比例
	 * @param she 是否发生拉扯
	 * @param shfx 横向拉扯因子
	 * @param shfy 纵向拉扯因子
	 */
	public ShapePro(Shape s,BasStroke stk,boolean lnd,Color lc, int fType, Color fc, GradPaint gfc,int type,boolean ptd,boolean sel,boolean rot,double rAngle ,double rvx,double rvy,boolean sca,double sfx,double sfy,boolean she,double shfx,double shfy){
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
	/**
	 * 判断某点是否在图形内
	 * @param mouseX 待判断点的x坐标
	 * @param mouseY 待判断点的y坐标
	 * @return 返回true说明待判断点在图形内
	 */
	public boolean containsPro(double mouseX,double mouseY){
		
		
		//*****************************************************************
		//变换说明
		//对于drag操作，可根据鼠标操作，直接修改图形的坐标变量值即可（起始点、宽高值）
		//对于scale,rotate和shear操作，无法通过修改坐标变量实现变换。所以需根据鼠标操作，采用
		//仿射变换出的新的Shape对象(transformedShape)，并利用这个新的对象进行绘图输出。
		//但是这个新的Shape对象并不存储，内存中存的还是仿射变化前的Shape。
		//而进行鼠标点击测试时，用户需要的判断是鼠标是否在新的Shape区域中。由于这个新的Shape
		//并未存储，所以进行鼠标点击判断时需要将鼠标位置进行反变换后，与内存中的变换前的
		//Shape对象的区域关系进行判断。
		//注意顺序！我的变换顺序如下
		//正变换：scale,shear,rotate
		//逆变换：rotate,shear,scale
		//注意到顺序必须相反，这样才能使一个值经正变换再经逆变换后，能回到原来的状态
		//*****************************************************************
		
		
		//此函数内完成鼠标位置的逆变换
		switch(this.shapeType){
		case ShapeType.RECTANGLE:
			//判断是否已发生旋转，若已旋转则将鼠标坐标逆变换
			if(rotated==true){
				AffineTransform t=new AffineTransform();
				t.setToRotation(rotatedAngle, getCenterPoint().x, getCenterPoint().y);
				try{
				t=t.createInverse();
				}catch(NoninvertibleTransformException e){}
				Point2D.Double p=new Point2D.Double(mouseX,mouseY);
				t.transform(p, p);//变换完后的鼠标点击点存于p中
				mouseX=p.x;
				mouseY=p.y;
			}
			
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
			//判断是否已发生缩放，若已剪切则将鼠标坐标逆变换
			if(scaled==true){
				AffineTransform t=new AffineTransform();
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
			}
			return shape.contains(mouseX, mouseY);
		case ShapeType.ROUNDRECTANGLE:
			//判断是否已发生旋转，若已旋转则将鼠标坐标逆变换
			if(rotated==true){
				AffineTransform t=new AffineTransform();
				t.setToRotation(rotatedAngle, getCenterPoint().x, getCenterPoint().y);
				try{
				t=t.createInverse();
				}catch(NoninvertibleTransformException e){}
				Point2D.Double p=new Point2D.Double(mouseX,mouseY);
				t.transform(p, p);//变换完后的鼠标点击点存于p中
				mouseX=p.x;
				mouseY=p.y;
			}
			
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
			//判断是否已发生缩放，若已剪切则将鼠标坐标逆变换
			if(scaled==true){
				AffineTransform t=new AffineTransform();
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
			}
			return shape.contains(mouseX, mouseY);
		case ShapeType.ELLIPSE:
			//判断是否已发生旋转，若已旋转则将鼠标坐标逆变换
			if(rotated==true){
				AffineTransform t=new AffineTransform();
				t.setToRotation(rotatedAngle, getCenterPoint().x, getCenterPoint().y);
				try{
				t=t.createInverse();
				}catch(NoninvertibleTransformException e){}
				Point2D.Double p=new Point2D.Double(mouseX,mouseY);
				t.transform(p, p);//变换完后的鼠标点击点存于p中
				mouseX=p.x;
				mouseY=p.y;
			}
			
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
			//判断是否已发生缩放，若已剪切则将鼠标坐标逆变换
			if(scaled==true){
				AffineTransform t=new AffineTransform();
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
			}
			return shape.contains(mouseX, mouseY);
		case ShapeType.LINE:
			//判断是否已发生旋转，若已旋转则将鼠标坐标逆变换
			if(rotated==true){
				AffineTransform t=new AffineTransform();
				t.setToRotation(rotatedAngle, getCenterPoint().x, getCenterPoint().y);
				try{
				t=t.createInverse();
				}catch(NoninvertibleTransformException e){}
				Point2D.Double p=new Point2D.Double(mouseX,mouseY);
				t.transform(p, p);//变换完后的鼠标点击点存于p中
				mouseX=p.x;
				mouseY=p.y;
			}
			
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
			//判断是否已发生缩放，若已剪切则将鼠标坐标逆变换
			if(scaled==true){
				AffineTransform t=new AffineTransform();
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
	/**
	 * 得到图形的中心点坐标
	 * @return 中心点
	 */
	public Point2D.Double getCenterPoint(){
		double x,y;
		switch(shapeType){
		case ShapeType.RECTANGLE:
			x=((Rectangle2D.Double)shape).x+((Rectangle2D.Double)shape).width/2;
			y=((Rectangle2D.Double)shape).y+((Rectangle2D.Double)shape).height/2;
			return new Point2D.Double(x,y);
		case ShapeType.ROUNDRECTANGLE:
			x=((RoundRectangle2D.Double)shape).x+((RoundRectangle2D.Double)shape).width/2;
			y=((RoundRectangle2D.Double)shape).y+((RoundRectangle2D.Double)shape).height/2;
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
	/**
	 * 得到图形的左上角点坐标
	 * @return 左上角点
	 */
	public Point2D.Double getLeftTop(){
		double x,y;
		switch(shapeType){
		case ShapeType.RECTANGLE:
			x=((Rectangle2D.Double)shape).x;
			y=((Rectangle2D.Double)shape).y;
			return new Point2D.Double(x,y);
		case ShapeType.ROUNDRECTANGLE:
			x=((RoundRectangle2D.Double)shape).x;
			y=((RoundRectangle2D.Double)shape).y;
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
	/**
	 * 得到图形的右上角点坐标
	 * @return 右上角点
	 */
	public Point2D.Double getRightTop(){
		double x,y;
		switch(shapeType){
		case ShapeType.RECTANGLE:
			x=((Rectangle2D.Double)shape).x+((Rectangle2D.Double)shape).width;
			y=((Rectangle2D.Double)shape).y;
			return new Point2D.Double(x,y);
		case ShapeType.ROUNDRECTANGLE:
			x=((RoundRectangle2D.Double)shape).x+((RoundRectangle2D.Double)shape).width;
			y=((RoundRectangle2D.Double)shape).y;
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
	/**
	 * 得到图形的左下角点坐标
	 * @return 左下角点
	 */
	public Point2D.Double getLeftBottom(){
		double x,y;
		switch(shapeType){
		case ShapeType.RECTANGLE:
			x=((Rectangle2D.Double)shape).x;
			y=((Rectangle2D.Double)shape).y+((Rectangle2D.Double)shape).height;
			return new Point2D.Double(x,y);
		case ShapeType.ROUNDRECTANGLE:
			x=((RoundRectangle2D.Double)shape).x;
			y=((RoundRectangle2D.Double)shape).y+((RoundRectangle2D.Double)shape).height;
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
	/**
	 * 得到图形的右下角点坐标
	 * @return 右下角点
	 */
	public Point2D.Double getRightBottom(){
		double x,y;
		switch(shapeType){
		case ShapeType.RECTANGLE:
			x=((Rectangle2D.Double)shape).x+((Rectangle2D.Double)shape).width;
			y=((Rectangle2D.Double)shape).y+((Rectangle2D.Double)shape).height;
			return new Point2D.Double(x,y);
		case ShapeType.ROUNDRECTANGLE:
			x=((RoundRectangle2D.Double)shape).x+((RoundRectangle2D.Double)shape).width;
			y=((RoundRectangle2D.Double)shape).y+((RoundRectangle2D.Double)shape).height;
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

