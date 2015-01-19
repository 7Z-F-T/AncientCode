package handler;
import frame.*;
import frame.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
public class DrawHandler implements MouseMotionListener,MouseListener{
	MainFrame frame;
	MyCanvas canvas;
	static ShapePro currentShapePro;
	public static ShapePro currentSelectedShapePro;
	static ShapePro currentPointedShapePro;
	static boolean startInCtrlPoint=false;
	ArrayList<ShapePro> shapeProArray;
	static double tempX=0;//鼠标落脚点
	static double tempY=0;//鼠标落脚点
	double dy;//计算用中间变量
	double dx;//计算用中间变量 
	static double lastX;//计算用中间变量
	static double lastY;//计算用中间变量
	static int currentCtrlPoint;
	double x;
	double y;
	Point2D.Double p1;
	Point2D.Double p2;
	Point2D.Double p3;
	public DrawHandler(MainFrame frm, MyCanvas cv,ArrayList<ShapePro> sharray){
		frame=frm;
		canvas=cv;
		shapeProArray=sharray;
	}
	
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
	
	
	//此函数完成修改图形坐标变量值，以及采集变换参数的工作
	public void mouseDragged(MouseEvent e){
		//System.out.println("tempX"+tempX);
		//System.out.println("tempY"+tempY);
		switch(frame.currentFunction){
		    case Function.RECTANGLE:
		    	dx=e.getX()-tempX;
		    	dy=e.getY()-tempY;
		    	if(dx>=0)
		    		((Rectangle2D.Double)(currentShapePro.shape)).width=dx;
		    	else{
		    		dx=0-dx;
		    		((Rectangle2D.Double)(currentShapePro.shape)).width=dx;
		    		((Rectangle2D.Double)(currentShapePro.shape)).x=tempX-dx;
		    		
		    	}
		    	if(dy>=0)
		    		((Rectangle2D.Double)(currentShapePro.shape)).height=dy;
		    	else{
		    		dy=0-dy;
		    		((Rectangle2D.Double)(currentShapePro.shape)).height=dy;
		    		((Rectangle2D.Double)(currentShapePro.shape)).y=tempY-dy;
		    	}
		    	canvas.repaint();
		    	
		    	break;
		    case Function.ROUNDRECTANGLE:
		    	dx=e.getX()-tempX;
		    	dy=e.getY()-tempY;
		    	if(dx>=0)
		    		((RoundRectangle2D.Double)(currentShapePro.shape)).width=dx;
		    	else{
		    		dx=0-dx;
		    		((RoundRectangle2D.Double)(currentShapePro.shape)).width=dx;
		    		((RoundRectangle2D.Double)(currentShapePro.shape)).x=tempX-dx;
		    		
		    	}
		    	if(dy>=0)
		    		((RoundRectangle2D.Double)(currentShapePro.shape)).height=dy;
		    	else{
		    		dy=0-dy;
		    		((RoundRectangle2D.Double)(currentShapePro.shape)).height=dy;
		    		((RoundRectangle2D.Double)(currentShapePro.shape)).y=tempY-dy;
		    	}
		    	canvas.repaint();
		    	
		    	break;
		    case Function.LINE:
		    	((Line2D.Double)(currentShapePro.shape)).x1=tempX;
		    	((Line2D.Double)(currentShapePro.shape)).y1=tempY;
		    	((Line2D.Double)(currentShapePro.shape)).x2=e.getX();
		    	((Line2D.Double)(currentShapePro.shape)).y2=e.getY();
		    	canvas.repaint();
		    	
		    	break;
		    case Function.ELLIPSE:
		    	dx=e.getX()-tempX;
		    	dy=e.getY()-tempY;
		    	if(dx>=0)
		    		((Ellipse2D.Double)(currentShapePro.shape)).width=dx;
		    	else{
		    		dx=0-dx;
		    		((Ellipse2D.Double)(currentShapePro.shape)).width=dx;
		    		((Ellipse2D.Double)(currentShapePro.shape)).x=tempX-dx;
		    		
		    	}
		    	if(dy>=0)
		    		((Ellipse2D.Double)(currentShapePro.shape)).height=dy;
		    	else{
		    		dy=0-dy;
		    		((Ellipse2D.Double)(currentShapePro.shape)).height=dy;
		    		((Ellipse2D.Double)(currentShapePro.shape)).y=tempY-dy;
		    	}
		    	canvas.repaint();
		    	break;
		    case Function.SELECT://对于drag，直接修改坐标变量值
		    	if(currentSelectedShapePro!=null&&currentSelectedShapePro.selected==true){
		    		//System.out.println("move");
		    		//System.out.println("tempX"+tempX);
		    		//System.out.println("tempY"+tempY);
		    		//System.out.println(e.getX()-tempX);
		    		switch(currentSelectedShapePro.shapeType){
		    		case ShapeType.RECTANGLE:{
		    			dx=e.getX()-lastX;
		    			dy=e.getY()-lastY;
		    			((Rectangle2D.Double)(currentSelectedShapePro.shape)).x+=dx;
		    			((Rectangle2D.Double)(currentSelectedShapePro.shape)).y+=dy;
		    			lastX=e.getX();
		    			lastY=e.getY();
		    			canvas.repaint();
		    			break;
		    		}
		    		case ShapeType.ROUNDRECTANGLE:{
		    			dx=e.getX()-lastX;
		    			dy=e.getY()-lastY;
		    			((RoundRectangle2D.Double)(currentSelectedShapePro.shape)).x+=dx;
		    			((RoundRectangle2D.Double)(currentSelectedShapePro.shape)).y+=dy;
		    			lastX=e.getX();
		    			lastY=e.getY();
		    			canvas.repaint();
		    			break;
		    		}
		    		case ShapeType.ELLIPSE:{
		    			dx=e.getX()-lastX;
		    			dy=e.getY()-lastY;
		    			((Ellipse2D.Double)(currentSelectedShapePro.shape)).x+=dx;
		    			((Ellipse2D.Double)(currentSelectedShapePro.shape)).y+=dy;
		    			lastX=e.getX();
		    			lastY=e.getY();
		    			canvas.repaint();
		    			break;
		    		}
		    		case ShapeType.LINE:{
		    			dx=e.getX()-lastX;
		    			dy=e.getY()-lastY;
		    			((Line2D.Double)(currentSelectedShapePro.shape)).x1+=dx;
		    			((Line2D.Double)(currentSelectedShapePro.shape)).x2+=dx;
		    			((Line2D.Double)(currentSelectedShapePro.shape)).y1+=dy;
		    			((Line2D.Double)(currentSelectedShapePro.shape)).y2+=dy;
		    			lastX=e.getX();
		    			lastY=e.getY();
		    			canvas.repaint();
		    			break;
		    		}
		    		case ShapeType.TEXT:{
		    			dx=e.getX()-lastX;
		    			dy=e.getY()-lastY;
		    			TextShapePro ts=(TextShapePro)currentSelectedShapePro;
		    			ts.position.x+=dx;
		    			ts.position.y+=dy;
		    			lastX=e.getX();
		    			lastY=e.getY();
		    			canvas.repaint();
		    		}
		    		}
		    	}
		    	break;
		    case Function.ROTATE://对于rotate,采集变换参数，待将shape正变换后输出
		    	if(currentSelectedShapePro!=null&&currentSelectedShapePro.selected==true&&startInCtrlPoint==true){
		    		currentSelectedShapePro.rotated=true;
		    		//System.out.println("tempX:"+tempX+" tempY:"+tempY+" eX:"+e.getX()+" eY:"+e.getY());
		    		x=currentSelectedShapePro.getCenterPoint().x;
		    		y=currentSelectedShapePro.getCenterPoint().y;
		    		System.out.println("x:"+x+" y:"+y);
		    		double x1=lastX-x;
		    		double y1=lastY-y;
		    		double x2=e.getX()-x;
		    		double y2=e.getY()-y;
		    		
		    		double alpha1=Math.atan2(x1, y1);
		    		double alpha2=Math.atan2(x2, y2);
		    		
		    		currentSelectedShapePro.rotatedAngle+=(alpha1-alpha2);
		    		
		    		//System.out.println("x1:"+x1+" y1:"+y1+" x2:"+x2+" y2:"+y2);
		    		//System.out.println("a1:"+alpha1/3.14*180+" a2:"+alpha2/3.14*180+" rotate:"+(alpha1-alpha2)/3.14*180+" angle:"+currentSelectedShapePro.rotatedAngle/3.14*180);
		    		//currentSelectedShapePro.rotatedVecX=dx;
		    		//currentSelectedShapePro.rotatedVecY=dy;
		    		//System.out.println(dx+" "+dy);
		    		canvas.repaint();
		    		lastX=e.getX();
		    		lastY=e.getY();
		    		break;
		    	}
		    	else{
		    		//直接完成drag操作
		    	   	if(currentSelectedShapePro!=null&&currentSelectedShapePro.selected==true){
			    		//System.out.println("move");
			    		//System.out.println("tempX"+tempX);
			    		//System.out.println("tempY"+tempY);
			    		//System.out.println(e.getX()-tempX);
			    		switch(currentSelectedShapePro.shapeType){
			    		case ShapeType.RECTANGLE:{
			    			dx=e.getX()-lastX;
			    			dy=e.getY()-lastY;
			    			((Rectangle2D.Double)(currentSelectedShapePro.shape)).x+=dx;
			    			((Rectangle2D.Double)(currentSelectedShapePro.shape)).y+=dy;
			    			lastX=e.getX();
			    			lastY=e.getY();
			    			canvas.repaint();
			    			break;
			    		}
			    		case ShapeType.ROUNDRECTANGLE:{
			    			dx=e.getX()-lastX;
			    			dy=e.getY()-lastY;
			    			((RoundRectangle2D.Double)(currentSelectedShapePro.shape)).x+=dx;
			    			((RoundRectangle2D.Double)(currentSelectedShapePro.shape)).y+=dy;
			    			lastX=e.getX();
			    			lastY=e.getY();
			    			canvas.repaint();
			    			break;
			    		}
			    		case ShapeType.ELLIPSE:{
			    			dx=e.getX()-lastX;
			    			dy=e.getY()-lastY;
			    			((Ellipse2D.Double)(currentSelectedShapePro.shape)).x+=dx;
			    			((Ellipse2D.Double)(currentSelectedShapePro.shape)).y+=dy;
			    			lastX=e.getX();
			    			lastY=e.getY();
			    			canvas.repaint();
			    			break;
			    		}
			    		case ShapeType.LINE:{
			    			dx=e.getX()-lastX;
			    			dy=e.getY()-lastY;
			    			((Line2D.Double)(currentSelectedShapePro.shape)).x1+=dx;
			    			((Line2D.Double)(currentSelectedShapePro.shape)).x2+=dx;
			    			((Line2D.Double)(currentSelectedShapePro.shape)).y1+=dy;
			    			((Line2D.Double)(currentSelectedShapePro.shape)).y2+=dy;
			    			lastX=e.getX();
			    			lastY=e.getY();
			    			canvas.repaint();
			    			break;
			    		}
			    		case ShapeType.TEXT:{
			    			dx=e.getX()-lastX;
			    			dy=e.getY()-lastY;
			    			TextShapePro ts=(TextShapePro)currentSelectedShapePro;
			    			ts.position.x+=dx;
			    			ts.position.y+=dy;
			    			lastX=e.getX();
			    			lastY=e.getY();
			    			canvas.repaint();
			    		}
			    		}
			    	}
			    	break;
		    	}
		    case Function.SCALE://对于scale,直接修改坐标变量值
		    	if(currentSelectedShapePro!=null&&currentSelectedShapePro.selected==true&&startInCtrlPoint==true){
		    		currentSelectedShapePro.scaled=true;
		    	switch(currentSelectedShapePro.shapeType){
		    	case ShapeType.RECTANGLE:
		    			p1=new Point2D.Double(e.getX(),e.getY());
	    				p2=new Point2D.Double(lastX,lastY);
	    				p3=new Point2D.Double(tempX,tempY);
		    			//System.out.println("dx:"+dx+" dy"+dy);
		    			//对鼠标当前坐标进行逆变换
	    				if(currentSelectedShapePro.rotated==false&&currentSelectedShapePro.sheared==false&&currentSelectedShapePro.scaled==false){
	    					dx=p1.x-p2.x;
		    				dy=p1.y-p2.y;
	    				}
		    			if(currentSelectedShapePro.rotated==true){
		    				AffineTransform t=new AffineTransform();
		    				//double vecX=(rotatedVecX*rotatedVecX)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
		    				//double vecY=-(rotatedVecX*rotatedVecY)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
		    				t.setToRotation(currentSelectedShapePro.rotatedAngle,currentSelectedShapePro.getCenterPoint().x,currentSelectedShapePro.getCenterPoint().y);
		    				try{
		    				t=t.createInverse();
		    				}catch(NoninvertibleTransformException ex){}
		    				t.transform(p1, p1);//变换完后的鼠标点击点存于p中
		    				t.transform(p2, p2);
		    				t.transform(p3, p3);
		    				dx=p1.x-p2.x;
		    				dy=p1.y-p2.y;
		    			}
		    			if(currentSelectedShapePro.sheared==true){
		    				AffineTransform t=new AffineTransform();
		    				t.setToTranslation(currentSelectedShapePro.getCenterPoint().x, currentSelectedShapePro.getCenterPoint().y);
		    				try{
		    					t=t.createInverse();
		    					}catch(NoninvertibleTransformException ex){}
		    				t.transform(p1,p1);
		    				t.transform(p2,p2);
		    				t.transform(p3,p3);
		    				t.setToShear(currentSelectedShapePro.shearedFactorX, currentSelectedShapePro.shearedFactorY);
		    				try{
		    				t=t.createInverse();
		    				}catch(NoninvertibleTransformException ex){}
		    				t.transform(p1,p1);
		    				t.transform(p2,p2);
		    				t.transform(p3,p3);
		    				t.setToTranslation(-currentSelectedShapePro.getCenterPoint().x, -currentSelectedShapePro.getCenterPoint().y);
		    				try{
		    					t=t.createInverse();
		    					}catch(NoninvertibleTransformException ex){}
		    				t.transform(p1,p1);
			    			t.transform(p2,p2);
			    			t.transform(p3,p3);
		    				dx=p1.x-p2.x;
		    				dy=p1.y-p2.y;
		    			}
		    			if(currentSelectedShapePro.scaled==true){
		    				AffineTransform t=new AffineTransform();
		    				t.setToTranslation(currentSelectedShapePro.getCenterPoint().x, currentSelectedShapePro.getCenterPoint().y);
		    				try{
		    					t=t.createInverse();
		    					}catch(NoninvertibleTransformException ex){}
		    				t.transform(p1,p1);
			    			t.transform(p2,p2);
			    			t.transform(p3,p3);
		    				t.setToScale(currentSelectedShapePro.scaledFactorX, currentSelectedShapePro.scaledFactorY);
		    				try{
		    				t=t.createInverse();
		    				}catch(NoninvertibleTransformException ex){}
		    				t.transform(p1,p1);
			    			t.transform(p2,p2);
			    			t.transform(p3,p3);
		    				t.setToTranslation(-currentSelectedShapePro.getCenterPoint().x, -currentSelectedShapePro.getCenterPoint().y);
		    				try{
		    					t=t.createInverse();
		    					}catch(NoninvertibleTransformException ex){}
		    				t.transform(p1,p1);
				    		t.transform(p2,p2);
				    		t.transform(p3,p3);
				    		dx=p1.x-p2.x;
		    				dy=p1.y-p2.y;
		    			}
		    			//开始处理RECTANGLE的scale操作
		    			x=currentSelectedShapePro.getCenterPoint().x;
		    			y=currentSelectedShapePro.getCenterPoint().y;
		    			dx*=currentSelectedShapePro.scaledFactorX;
		    			dy*=currentSelectedShapePro.scaledFactorY;
		    			if(p3.x>x){
		    				currentSelectedShapePro.scaledFactorX+=2*dx/((Rectangle2D.Double)(currentSelectedShapePro.shape)).width;
		    			}else{
		    				currentSelectedShapePro.scaledFactorX-=2*dx/((Rectangle2D.Double)(currentSelectedShapePro.shape)).width;
		    			}
		    			if(p3.y>y){
		    				currentSelectedShapePro.scaledFactorY+=2*dy/((Rectangle2D.Double)(currentSelectedShapePro.shape)).height;
		    			}else{
		    				currentSelectedShapePro.scaledFactorY-=2*dy/((Rectangle2D.Double)(currentSelectedShapePro.shape)).height;
		    			}
		    			lastX=e.getX();
		    			lastY=e.getY();
		    			canvas.repaint();
		    			break;
		    	case ShapeType.ROUNDRECTANGLE:
	    			p1=new Point2D.Double(e.getX(),e.getY());
    				p2=new Point2D.Double(lastX,lastY);
    				p3=new Point2D.Double(tempX,tempY);
	    			//System.out.println("dx:"+dx+" dy"+dy);
	    			//对鼠标当前坐标进行逆变换
    				if(currentSelectedShapePro.rotated==false&&currentSelectedShapePro.sheared==false&&currentSelectedShapePro.scaled==false){
    					dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
    				}
	    			if(currentSelectedShapePro.rotated==true){
	    				AffineTransform t=new AffineTransform();
	    				//double vecX=(rotatedVecX*rotatedVecX)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
	    				//double vecY=-(rotatedVecX*rotatedVecY)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
	    				t.setToRotation(currentSelectedShapePro.rotatedAngle,currentSelectedShapePro.getCenterPoint().x,currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    				t=t.createInverse();
	    				}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1, p1);//变换完后的鼠标点击点存于p中
	    				t.transform(p2, p2);
	    				t.transform(p3, p3);
	    				dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
	    			}
	    			if(currentSelectedShapePro.sheared==true){
	    				AffineTransform t=new AffineTransform();
	    				t.setToTranslation(currentSelectedShapePro.getCenterPoint().x, currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
	    				t.transform(p2,p2);
	    				t.transform(p3,p3);
	    				t.setToShear(currentSelectedShapePro.shearedFactorX, currentSelectedShapePro.shearedFactorY);
	    				try{
	    				t=t.createInverse();
	    				}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
	    				t.transform(p2,p2);
	    				t.transform(p3,p3);
	    				t.setToTranslation(-currentSelectedShapePro.getCenterPoint().x, -currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
		    			t.transform(p2,p2);
		    			t.transform(p3,p3);
	    				dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
	    			}
	    			if(currentSelectedShapePro.scaled==true){
	    				AffineTransform t=new AffineTransform();
	    				t.setToTranslation(currentSelectedShapePro.getCenterPoint().x, currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
		    			t.transform(p2,p2);
		    			t.transform(p3,p3);
	    				t.setToScale(currentSelectedShapePro.scaledFactorX, currentSelectedShapePro.scaledFactorY);
	    				try{
	    				t=t.createInverse();
	    				}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
		    			t.transform(p2,p2);
		    			t.transform(p3,p3);
	    				t.setToTranslation(-currentSelectedShapePro.getCenterPoint().x, -currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
			    		t.transform(p2,p2);
			    		t.transform(p3,p3);
			    		dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
	    			}
	    			//开始处理ROUNDRECTANGLE的scale操作
	    			x=currentSelectedShapePro.getCenterPoint().x;
	    			y=currentSelectedShapePro.getCenterPoint().y;
	    			dx*=currentSelectedShapePro.scaledFactorX;
	    			dy*=currentSelectedShapePro.scaledFactorY;
	    			if(p3.x>x){
	    				currentSelectedShapePro.scaledFactorX+=2*dx/((RoundRectangle2D.Double)(currentSelectedShapePro.shape)).width;
	    			}else{
	    				currentSelectedShapePro.scaledFactorX-=2*dx/((RoundRectangle2D.Double)(currentSelectedShapePro.shape)).width;
	    			}
	    			if(p3.y>y){
	    				currentSelectedShapePro.scaledFactorY+=2*dy/((RoundRectangle2D.Double)(currentSelectedShapePro.shape)).height;
	    			}else{
	    				currentSelectedShapePro.scaledFactorY-=2*dy/((RoundRectangle2D.Double)(currentSelectedShapePro.shape)).height;
	    			}
	    			lastX=e.getX();
	    			lastY=e.getY();
	    			canvas.repaint();
	    			break;
		    	case ShapeType.ELLIPSE:{
		    		p1=new Point2D.Double(e.getX(),e.getY());
    				p2=new Point2D.Double(lastX,lastY);
    				p3=new Point2D.Double(tempX,tempY);
	    			//System.out.println("dx:"+dx+" dy"+dy);
	    			//对鼠标当前坐标进行逆变换
    				if(currentSelectedShapePro.rotated==false&&currentSelectedShapePro.sheared==false){
    					dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
    				}
	    			if(currentSelectedShapePro.rotated==true){
	    				AffineTransform t=new AffineTransform();
	    				//double vecX=(rotatedVecX*rotatedVecX)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
	    				//double vecY=-(rotatedVecX*rotatedVecY)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
	    				t.setToRotation(currentSelectedShapePro.rotatedAngle,currentSelectedShapePro.getCenterPoint().x,currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    				t=t.createInverse();
	    				}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1, p1);//变换完后的鼠标点击点存于p中
	    				t.transform(p2, p2);
	    				t.transform(p3, p3);
	    				dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
	    			}
	    			
	    			if(currentSelectedShapePro.sheared==true){
	    				AffineTransform t=new AffineTransform();
	    				t.setToTranslation(currentSelectedShapePro.getCenterPoint().x, currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
	    				t.transform(p2,p2);
	    				t.transform(p3,p3);
	    				t.setToShear(currentSelectedShapePro.shearedFactorX, currentSelectedShapePro.shearedFactorY);
	    				try{
	    				t=t.createInverse();
	    				}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
	    				t.transform(p2,p2);
	    				t.transform(p3,p3);
	    				t.setToTranslation(-currentSelectedShapePro.getCenterPoint().x, -currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
		    			t.transform(p2,p2);
		    			t.transform(p3,p3);
	    				dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
	    			}
	    			if(currentSelectedShapePro.scaled==true){
	    				AffineTransform t=new AffineTransform();
	    				t.setToTranslation(currentSelectedShapePro.getCenterPoint().x, currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
		    			t.transform(p2,p2);
		    			t.transform(p3,p3);
	    				t.setToScale(currentSelectedShapePro.scaledFactorX, currentSelectedShapePro.scaledFactorY);
	    				try{
	    				t=t.createInverse();
	    				}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
		    			t.transform(p2,p2);
		    			t.transform(p3,p3);
	    				t.setToTranslation(-currentSelectedShapePro.getCenterPoint().x, -currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
			    		t.transform(p2,p2);
			    		t.transform(p3,p3);
			    		dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
	    			}
	    			
	    			    //开始处理ELLIPSE的scale操作
	    				x=currentSelectedShapePro.getCenterPoint().x;
	    				y=currentSelectedShapePro.getCenterPoint().y;
	    				dx*=currentSelectedShapePro.scaledFactorX;
	    				dy*=currentSelectedShapePro.scaledFactorY;
	    				if(p3.x>x){
		    					currentSelectedShapePro.scaledFactorX+=2*dx/((Ellipse2D.Double)(currentSelectedShapePro.shape)).width;
		    			}else{
		    				currentSelectedShapePro.scaledFactorX-=2*dx/((Ellipse2D.Double)(currentSelectedShapePro.shape)).width;
		    			}
		    			if(p3.y>y){
		    				currentSelectedShapePro.scaledFactorY+=2*dy/((Ellipse2D.Double)(currentSelectedShapePro.shape)).height;
		    			}else{
		    				currentSelectedShapePro.scaledFactorY-=2*dy/((Ellipse2D.Double)(currentSelectedShapePro.shape)).height;
		    			}
		    			lastX=e.getX();
		    			lastY=e.getY();
		    			canvas.repaint();
		    			break;
		    		    }
		    	case ShapeType.LINE:{
		    		p1=new Point2D.Double(e.getX(),e.getY());
    				p2=new Point2D.Double(lastX,lastY);
    				p3=new Point2D.Double(tempX,tempY);
	    			//System.out.println("dx:"+dx+" dy"+dy);
	    			//对鼠标当前坐标进行逆变换
    				if(currentSelectedShapePro.rotated==false&&currentSelectedShapePro.sheared==false){
    					dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
    				}
	    			if(currentSelectedShapePro.rotated==true){
	    				AffineTransform t=new AffineTransform();
	    				//double vecX=(rotatedVecX*rotatedVecX)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
	    				//double vecY=-(rotatedVecX*rotatedVecY)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
	    				t.setToRotation(currentSelectedShapePro.rotatedAngle,currentSelectedShapePro.getCenterPoint().x,currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    				t=t.createInverse();
	    				}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1, p1);//变换完后的鼠标点击点存于p中
	    				t.transform(p2, p2);
	    				t.transform(p3, p3);
	    				dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
	    			}
	    			
	    			if(currentSelectedShapePro.sheared==true){
	    				AffineTransform t=new AffineTransform();
	    				t.setToTranslation(currentSelectedShapePro.getCenterPoint().x, currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
	    				t.transform(p2,p2);
	    				t.transform(p3,p3);
	    				t.setToShear(currentSelectedShapePro.shearedFactorX, currentSelectedShapePro.shearedFactorY);
	    				try{
	    				t=t.createInverse();
	    				}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
	    				t.transform(p2,p2);
	    				t.transform(p3,p3);
	    				t.setToTranslation(-currentSelectedShapePro.getCenterPoint().x, -currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
		    			t.transform(p2,p2);
		    			t.transform(p3,p3);
	    				dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
	    			}
	    			if(currentSelectedShapePro.scaled==true){
	    				AffineTransform t=new AffineTransform();
	    				t.setToTranslation(currentSelectedShapePro.getCenterPoint().x, currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
		    			t.transform(p2,p2);
		    			t.transform(p3,p3);
	    				t.setToScale(currentSelectedShapePro.scaledFactorX, currentSelectedShapePro.scaledFactorY);
	    				try{
	    				t=t.createInverse();
	    				}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
		    			t.transform(p2,p2);
		    			t.transform(p3,p3);
	    				t.setToTranslation(-currentSelectedShapePro.getCenterPoint().x, -currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
			    		t.transform(p2,p2);
			    		t.transform(p3,p3);
			    		dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
	    			}
	    			    //开始处理LINE的scale操作
	    				x=currentSelectedShapePro.getCenterPoint().x;
		    			y=currentSelectedShapePro.getCenterPoint().y;
		    			dx*=currentSelectedShapePro.scaledFactorX;
	    				dy*=currentSelectedShapePro.scaledFactorY;
	    				
		    			if(p3.x>x){
		    				if(((Line2D.Double)(currentSelectedShapePro.shape)).x1<((Line2D.Double)(currentSelectedShapePro.shape)).x2){
		    					currentSelectedShapePro.scaledFactorX+=2*dx/(((Line2D.Double)(currentSelectedShapePro.shape)).x2-((Line2D.Double)(currentSelectedShapePro.shape)).x1);
		    				}
		    				else{
		    					currentSelectedShapePro.scaledFactorX+=2*dx/(((Line2D.Double)(currentSelectedShapePro.shape)).x1-((Line2D.Double)(currentSelectedShapePro.shape)).x2);
		    				}
		    			}else{
		    				if(((Line2D.Double)(currentSelectedShapePro.shape)).x1<((Line2D.Double)(currentSelectedShapePro.shape)).x2){
		    					currentSelectedShapePro.scaledFactorX-=2*dx/(((Line2D.Double)(currentSelectedShapePro.shape)).x2-((Line2D.Double)(currentSelectedShapePro.shape)).x1);
		    				}else{
		    					currentSelectedShapePro.scaledFactorX-=2*dx/(((Line2D.Double)(currentSelectedShapePro.shape)).x1-((Line2D.Double)(currentSelectedShapePro.shape)).x2);
		    				}
		    			}
		    			if(p3.y>y){
		    				if(((Line2D.Double)(currentSelectedShapePro.shape)).y1<((Line2D.Double)(currentSelectedShapePro.shape)).y2){
		    					currentSelectedShapePro.scaledFactorY+=2*dy/(((Line2D.Double)(currentSelectedShapePro.shape)).y2-((Line2D.Double)(currentSelectedShapePro.shape)).y1);
		    				}
		    				else{
		    					currentSelectedShapePro.scaledFactorY+=2*dy/(((Line2D.Double)(currentSelectedShapePro.shape)).y1-((Line2D.Double)(currentSelectedShapePro.shape)).y2);
		    				}
		    			}else{
		    				if(((Line2D.Double)(currentSelectedShapePro.shape)).y1<((Line2D.Double)(currentSelectedShapePro.shape)).y2){
		    					currentSelectedShapePro.scaledFactorY-=2*dy/(((Line2D.Double)(currentSelectedShapePro.shape)).y2-((Line2D.Double)(currentSelectedShapePro.shape)).y1);
		    				}else{
		    					currentSelectedShapePro.scaledFactorY-=2*dy/(((Line2D.Double)(currentSelectedShapePro.shape)).y1-((Line2D.Double)(currentSelectedShapePro.shape)).y2);
		    				}
		    			}
		    			lastX=e.getX();
		    			lastY=e.getY();
		    			canvas.repaint();
		    			break;
		    		}
		    	case ShapeType.TEXT:{
		    		p1=new Point2D.Double(e.getX(),e.getY());
    				p2=new Point2D.Double(lastX,lastY);
    				p3=new Point2D.Double(tempX,tempY);
	    			//System.out.println("dx:"+dx+" dy"+dy);
	    			//对鼠标当前坐标进行逆变换
    				if(currentSelectedShapePro.rotated==false&&currentSelectedShapePro.sheared==false){
    					dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
    				}
	    			if(currentSelectedShapePro.rotated==true){
	    				AffineTransform t=new AffineTransform();
	    				//double vecX=(rotatedVecX*rotatedVecX)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
	    				//double vecY=-(rotatedVecX*rotatedVecY)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
	    				t.setToRotation(currentSelectedShapePro.rotatedAngle,currentSelectedShapePro.getCenterPoint().x,currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    				t=t.createInverse();
	    				}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1, p1);//变换完后的鼠标点击点存于p中
	    				t.transform(p2, p2);
	    				t.transform(p3, p3);
	    				dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
	    			}
	    			
	    			if(currentSelectedShapePro.sheared==true){
	    				AffineTransform t=new AffineTransform();
	    				t.setToTranslation(currentSelectedShapePro.getCenterPoint().x, currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
	    				t.transform(p2,p2);
	    				t.transform(p3,p3);
	    				t.setToShear(currentSelectedShapePro.shearedFactorX, currentSelectedShapePro.shearedFactorY);
	    				try{
	    				t=t.createInverse();
	    				}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
	    				t.transform(p2,p2);
	    				t.transform(p3,p3);
	    				t.setToTranslation(-currentSelectedShapePro.getCenterPoint().x, -currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
		    			t.transform(p2,p2);
		    			t.transform(p3,p3);
	    				dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
	    			}
	    			if(currentSelectedShapePro.scaled==true){
	    				AffineTransform t=new AffineTransform();
	    				t.setToTranslation(currentSelectedShapePro.getCenterPoint().x, currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
		    			t.transform(p2,p2);
		    			t.transform(p3,p3);
	    				t.setToScale(currentSelectedShapePro.scaledFactorX, currentSelectedShapePro.scaledFactorY);
	    				try{
	    				t=t.createInverse();
	    				}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
		    			t.transform(p2,p2);
		    			t.transform(p3,p3);
	    				t.setToTranslation(-currentSelectedShapePro.getCenterPoint().x, -currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
			    		t.transform(p2,p2);
			    		t.transform(p3,p3);
			    		dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
	    			}
	    			    //开始处理TEXT的scale操作
	    			x=currentSelectedShapePro.getCenterPoint().x;
    				y=currentSelectedShapePro.getCenterPoint().y;
    				dx*=currentSelectedShapePro.scaledFactorX;
    				dy*=currentSelectedShapePro.scaledFactorY;
    				if(p3.x>x){
	    				currentSelectedShapePro.scaledFactorX+=2*dx/((TextShapePro)(currentSelectedShapePro)).textWidth;
	    			}else{
	    				currentSelectedShapePro.scaledFactorX-=2*dx/((TextShapePro)(currentSelectedShapePro)).textWidth;
	    			}
	    			if(p3.y>y){
	    				currentSelectedShapePro.scaledFactorY+=2*dy/((TextShapePro)(currentSelectedShapePro)).textHeight;
	    			}else{
	    				currentSelectedShapePro.scaledFactorY-=2*dy/((TextShapePro)(currentSelectedShapePro)).textHeight;
	    			}
	    			lastX=e.getX();
	    			lastY=e.getY();
	    			canvas.repaint();
		    	}
		    	
		    	}
		    	break;
		    }
		    	else{
		    		//直接完成drag操作
		    	   	if(currentSelectedShapePro!=null&&currentSelectedShapePro.selected==true){
			    		//System.out.println("move");
			    		//System.out.println("tempX"+tempX);
			    		//System.out.println("tempY"+tempY);
			    		//System.out.println(e.getX()-tempX);
			    		switch(currentSelectedShapePro.shapeType){
			    		case ShapeType.RECTANGLE:{
			    			dx=e.getX()-lastX;
			    			dy=e.getY()-lastY;
			    			((Rectangle2D.Double)(currentSelectedShapePro.shape)).x+=dx;
			    			((Rectangle2D.Double)(currentSelectedShapePro.shape)).y+=dy;
			    			lastX=e.getX();
			    			lastY=e.getY();
			    			canvas.repaint();
			    			break;
			    		}
			    		case ShapeType.ROUNDRECTANGLE:{
			    			dx=e.getX()-lastX;
			    			dy=e.getY()-lastY;
			    			((RoundRectangle2D.Double)(currentSelectedShapePro.shape)).x+=dx;
			    			((RoundRectangle2D.Double)(currentSelectedShapePro.shape)).y+=dy;
			    			lastX=e.getX();
			    			lastY=e.getY();
			    			canvas.repaint();
			    			break;
			    		}
			    		case ShapeType.ELLIPSE:{
			    			dx=e.getX()-lastX;
			    			dy=e.getY()-lastY;
			    			((Ellipse2D.Double)(currentSelectedShapePro.shape)).x+=dx;
			    			((Ellipse2D.Double)(currentSelectedShapePro.shape)).y+=dy;
			    			lastX=e.getX();
			    			lastY=e.getY();
			    			canvas.repaint();
			    			break;
			    		}
			    		case ShapeType.LINE:{
			    			dx=e.getX()-lastX;
			    			dy=e.getY()-lastY;
			    			((Line2D.Double)(currentSelectedShapePro.shape)).x1+=dx;
			    			((Line2D.Double)(currentSelectedShapePro.shape)).x2+=dx;
			    			((Line2D.Double)(currentSelectedShapePro.shape)).y1+=dy;
			    			((Line2D.Double)(currentSelectedShapePro.shape)).y2+=dy;
			    			lastX=e.getX();
			    			lastY=e.getY();
			    			canvas.repaint();
			    			break;
			    		}
			    		case ShapeType.TEXT:{
			    			dx=e.getX()-lastX;
			    			dy=e.getY()-lastY;
			    			TextShapePro ts=(TextShapePro)currentSelectedShapePro;
			    			ts.position.x+=dx;
			    			ts.position.y+=dy;
			    			lastX=e.getX();
			    			lastY=e.getY();
			    			canvas.repaint();
			    		}
			    		}
			    	}
		    	}
		    case Function.SHEAR://对于shear,采集变换参数，待将shape正变换后输出
		    	if(currentSelectedShapePro!=null&&currentSelectedShapePro.selected==true&&startInCtrlPoint==true){
		    		currentSelectedShapePro.sheared=true;
		    		p1=new Point2D.Double(e.getX(),e.getY());
    				p2=new Point2D.Double(lastX,lastY);
    				p3=new Point2D.Double(tempX,tempY);
	    			//System.out.println("dx:"+dx+" dy"+dy);
	    			//对鼠标当前坐标进行逆变换
    				if(currentSelectedShapePro.rotated==false&&currentSelectedShapePro.sheared==false){
    					dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
    				}
	    			if(currentSelectedShapePro.rotated==true){
	    				AffineTransform t=new AffineTransform();
	    				//double vecX=(rotatedVecX*rotatedVecX)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
	    				//double vecY=-(rotatedVecX*rotatedVecY)/Math.sqrt(rotatedVecX*rotatedVecX+rotatedVecY*rotatedVecY);
	    				t.setToRotation(currentSelectedShapePro.rotatedAngle,currentSelectedShapePro.getCenterPoint().x,currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    				t=t.createInverse();
	    				}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1, p1);//变换完后的鼠标点击点存于p中
	    				t.transform(p2, p2);
	    				t.transform(p3, p3);
	    				dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
	    			}
	    			
	    			if(currentSelectedShapePro.sheared==true){
	    				AffineTransform t=new AffineTransform();
	    				t.setToTranslation(currentSelectedShapePro.getCenterPoint().x, currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
	    				t.transform(p2,p2);
	    				t.transform(p3,p3);
	    				t.setToShear(currentSelectedShapePro.shearedFactorX, currentSelectedShapePro.shearedFactorY);
	    				try{
	    				t=t.createInverse();
	    				}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
	    				t.transform(p2,p2);
	    				t.transform(p3,p3);
	    				t.setToTranslation(-currentSelectedShapePro.getCenterPoint().x, -currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
		    			t.transform(p2,p2);
		    			t.transform(p3,p3);
	    				dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
	    			}
	    			if(currentSelectedShapePro.scaled==true){
	    				AffineTransform t=new AffineTransform();
	    				t.setToTranslation(currentSelectedShapePro.getCenterPoint().x, currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
		    			t.transform(p2,p2);
		    			t.transform(p3,p3);
	    				t.setToScale(currentSelectedShapePro.scaledFactorX, currentSelectedShapePro.scaledFactorY);
	    				try{
	    				t=t.createInverse();
	    				}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
		    			t.transform(p2,p2);
		    			t.transform(p3,p3);
	    				t.setToTranslation(-currentSelectedShapePro.getCenterPoint().x, -currentSelectedShapePro.getCenterPoint().y);
	    				try{
	    					t=t.createInverse();
	    					}catch(NoninvertibleTransformException ex){}
	    				t.transform(p1,p1);
			    		t.transform(p2,p2);
			    		t.transform(p3,p3);
			    		dx=p1.x-p2.x;
	    				dy=p1.y-p2.y;
	    			}
	    			//开始shear操作
		    		//dx=e.getX()-lastX;
		    		//dy=e.getY()-lastY;
		    		double x=e.getX()-currentSelectedShapePro.getCenterPoint().x;
		    		double y=e.getY()-currentSelectedShapePro.getCenterPoint().y;
		    		if(x>-80&&x<80){
		    			if(x<0)
		    				x=-80;
		    			else
		    				x=80;
		    		}
		    		if(y>-80&&y<80){
		    			if(y<0)
		    				y=-80;
		    			else
		    				y=80;
		    		}
		    		currentSelectedShapePro.shearedFactorX+=dx/y;
		    		currentSelectedShapePro.shearedFactorY+=dy/x;
		    		lastX=e.getX();
		    		lastY=e.getY();
		    		canvas.repaint();
		    		break;
		    	}
		    	else{
		    		//直接完成drag操作
		    	   	if(currentSelectedShapePro!=null&&currentSelectedShapePro.selected==true){
			    		//System.out.println("move");
			    		//System.out.println("tempX"+tempX);
			    		//System.out.println("tempY"+tempY);
			    		//System.out.println(e.getX()-tempX);
			    		switch(currentSelectedShapePro.shapeType){
			    		case ShapeType.RECTANGLE:{
			    			dx=e.getX()-lastX;
			    			dy=e.getY()-lastY;
			    			((Rectangle2D.Double)(currentSelectedShapePro.shape)).x+=dx;
			    			((Rectangle2D.Double)(currentSelectedShapePro.shape)).y+=dy;
			    			lastX=e.getX();
			    			lastY=e.getY();
			    			canvas.repaint();
			    			break;
			    		}
			    		case ShapeType.ROUNDRECTANGLE:{
			    			dx=e.getX()-lastX;
			    			dy=e.getY()-lastY;
			    			((RoundRectangle2D.Double)(currentSelectedShapePro.shape)).x+=dx;
			    			((RoundRectangle2D.Double)(currentSelectedShapePro.shape)).y+=dy;
			    			lastX=e.getX();
			    			lastY=e.getY();
			    			canvas.repaint();
			    			break;
			    		}
			    		case ShapeType.ELLIPSE:{
			    			dx=e.getX()-lastX;
			    			dy=e.getY()-lastY;
			    			((Ellipse2D.Double)(currentSelectedShapePro.shape)).x+=dx;
			    			((Ellipse2D.Double)(currentSelectedShapePro.shape)).y+=dy;
			    			lastX=e.getX();
			    			lastY=e.getY();
			    			canvas.repaint();
			    			break;
			    		}
			    		case ShapeType.LINE:{
			    			dx=e.getX()-lastX;
			    			dy=e.getY()-lastY;
			    			((Line2D.Double)(currentSelectedShapePro.shape)).x1+=dx;
			    			((Line2D.Double)(currentSelectedShapePro.shape)).x2+=dx;
			    			((Line2D.Double)(currentSelectedShapePro.shape)).y1+=dy;
			    			((Line2D.Double)(currentSelectedShapePro.shape)).y2+=dy;
			    			lastX=e.getX();
			    			lastY=e.getY();
			    			canvas.repaint();
			    			break;
			    		}
			    		case ShapeType.TEXT:{
			    			dx=e.getX()-lastX;
			    			dy=e.getY()-lastY;
			    			TextShapePro ts=(TextShapePro)currentSelectedShapePro;
			    			ts.position.x+=dx;
			    			ts.position.y+=dy;
			    			lastX=e.getX();
			    			lastY=e.getY();
			    			canvas.repaint();
			    		}
			    		}
			    	}
			    	break;
		    	}
		}
	}
	public void mouseMoved(MouseEvent e){
		if(frame.currentFunction==Function.SELECT||frame.currentFunction==Function.SCALE||frame.currentFunction==Function.ROTATE||frame.currentFunction==Function.SHEAR){
		switch(frame.currentFunction){
		case Function.RECTANGLE:break;
		case Function.ELLIPSE:break;
		case Function.LINE:break;
		default:
		tempX=e.getX();
		tempY=e.getY();
		lastX=tempX;
		lastY=tempY;
		//System.out.println(tempX);
		//System.out.println(tempY);
		ShapePro s=new ShapePro();
		boolean flag=false;//标志位，true:鼠标在图形区域内 false:否则
		for(int i=shapeProArray.size()-1;i>=0;i--){
			s=(ShapePro)shapeProArray.get(i);
			//System.out.println(s.containsPro(tempX,tempY));
			if(s.containsPro(tempX, tempY)){
				flag=true;//鼠标在图形区域内了！
				break;
			}
		}
		if(flag==false){//当前鼠标没有在任何图形区域内
			//System.out.println("Not Select");
			if(currentPointedShapePro!=null){
				//System.out.println("Deselect");
				currentPointedShapePro.pointed=false;//取消上次的选择
				canvas.repaint();
			}
		}
		else{//鼠标在图形s的区域内
			//System.out.println("Select!");
			if(currentPointedShapePro!=null)
				currentPointedShapePro.pointed=false;
			currentPointedShapePro=s;
			currentPointedShapePro.pointed=true;//设s为当前选中图形
			canvas.repaint();
		}
		}
		}
	}
	public void mousePressed(MouseEvent e){
		switch(frame.currentFunction){
			case Function.RECTANGLE:
				currentShapePro=new ShapePro(new Rectangle2D.Double(e.getX(),e.getY(),0,0),
					    frame.currentPaintProperty.currentStroke,
					    frame.currentPaintProperty.currentLined,
						frame.currentPaintProperty.currentLineColor,
						frame.currentPaintProperty.currentFillType,
						frame.currentPaintProperty.currentFillColor,
						frame.currentPaintProperty.currentGradientFillColor,
						ShapeType.RECTANGLE,
						false,
						false,
						false,
						0,
						0,0,
						false,
						1.0,1.0,
						false,
						0,0);
				shapeProArray.add(currentShapePro);
				tempX=e.getX();
				tempY=e.getY();
				
				break;
			case Function.ROUNDRECTANGLE:
				currentShapePro=new ShapePro(new RoundRectangle2D.Double(e.getX(),e.getY(),0,0,frame.currentPaintProperty.currentArcWidth,frame.currentPaintProperty.currentArcHeight),
					    frame.currentPaintProperty.currentStroke,
					    frame.currentPaintProperty.currentLined,
						frame.currentPaintProperty.currentLineColor,
						frame.currentPaintProperty.currentFillType,
						frame.currentPaintProperty.currentFillColor,
						frame.currentPaintProperty.currentGradientFillColor,
						ShapeType.ROUNDRECTANGLE,
						false,
						false,
						false,
						0,
						0,0,
						false,
						1.0,1.0,
						false,
						0,0);
				shapeProArray.add(currentShapePro);
				tempX=e.getX();
				tempY=e.getY();
				break;
			case Function.LINE:
				currentShapePro=new ShapePro(new Line2D.Double(e.getX(),e.getY(),e.getX(),e.getY()),
					    frame.currentPaintProperty.currentStroke,
					    frame.currentPaintProperty.currentLined,
						frame.currentPaintProperty.currentLineColor,
						FillType.SOLID,
						frame.currentPaintProperty.currentFillColor,
						frame.currentPaintProperty.currentGradientFillColor,
						ShapeType.LINE,
						false,
						false,
						false,
						0,
						0,0,
						false,
						1.0,1.0,
						false,
						0,0);
				shapeProArray.add(currentShapePro);
				tempX=e.getX();
				tempY=e.getY();
				break;
			case Function.ELLIPSE:
				currentShapePro=new ShapePro(new Ellipse2D.Double(e.getX(),e.getY(),e.getX(),e.getY()),
					    frame.currentPaintProperty.currentStroke,
					    frame.currentPaintProperty.currentLined,
						frame.currentPaintProperty.currentLineColor,
						frame.currentPaintProperty.currentFillType,
						frame.currentPaintProperty.currentFillColor,
						frame.currentPaintProperty.currentGradientFillColor,
						ShapeType.ELLIPSE,
						false,
						false,
						false,
						0,
						0,0,
						false,
						1.0,1.0,
						false,
						0,0);
				shapeProArray.add(currentShapePro);
				tempX=e.getX();
				tempY=e.getY();
				break;
			 //以上为绘制操作，以下情况均为选择，修改操作，在图形上摁下鼠标即可选中
			 default:
				tempX=e.getX();
				tempY=e.getY();
				lastX=tempX;
				lastY=tempY;
				System.out.println(tempX);
				System.out.println(tempY);
				ShapePro s=new ShapePro();
				boolean flag=false;//标志位，true:点击在图形区域内 false:否则
			
				for(int i=shapeProArray.size()-1;i>=0;i--){
					s=(ShapePro)shapeProArray.get(i);
					//System.out.println(s.containsPro(tempX,tempY));
					if(s.containsPro(tempX, tempY)){
						flag=true;//点击在图形区域内了！
						break;
					}
				}
				if(flag==true){//鼠标点击在了图形s的区域内
					System.out.println("Select!");
					startInCtrlPoint=false;
					if(currentSelectedShapePro!=null)
						currentSelectedShapePro.selected=false;
					currentSelectedShapePro=s;
					currentSelectedShapePro.selected=true;//设s为当前选中图形
					//更新右侧属性面板
					frame.lineColorIndicator.setBackground(currentSelectedShapePro.lineColor);
					frame.fillColorIndicator.setBackground(currentSelectedShapePro.fillColor);
					frame.lineWidthIndicator2.setText(new Integer(currentSelectedShapePro.stroke.width).toString());
					frame.lineStyleIndicator2.setText(currentSelectedShapePro.stroke.patternName);
					frame.lineWidthChooserSlider.setValue(currentSelectedShapePro.stroke.width);
					if(currentSelectedShapePro.shapeType==ShapeType.ROUNDRECTANGLE){
						frame.arcWidthIndicator.setText(new Integer((int)((RoundRectangle2D.Double)(currentSelectedShapePro.shape)).arcwidth).toString());
						frame.arcWidthSlider.setValue((int)((RoundRectangle2D.Double)(currentSelectedShapePro.shape)).arcwidth);
						frame.arcHeightIndicator.setText(new Integer((int)((RoundRectangle2D.Double)(currentSelectedShapePro.shape)).archeight).toString());
						frame.arcHeightSlider.setValue((int)((RoundRectangle2D.Double)(currentSelectedShapePro.shape)).archeight);
					}
					if(currentSelectedShapePro.lined==true&&currentSelectedShapePro.fillType!=FillType.NONE)
						frame.lineAndFillButton.setSelected(true);
					else if(currentSelectedShapePro.lined==true)
						frame.lineOnlyButton.setSelected(true);
					else
						frame.fillOnlyButton.setSelected(true);
					frame.previewCanvas.repaint();
					//frame.lineStyleIndicator2.set
					canvas.repaint();
				}
				else{//当前鼠标没有点击在任何图形区域内
					System.out.println("Not Select");
					if(currentSelectedShapePro!=null){
						//System.out.println("Deselect");
						if(inCtrlPoint(e.getX(),e.getY())==0){//同时也没有点到ctrlPoint内
						currentSelectedShapePro.selected=false;//取消上次的选择
						//更新右侧属性面板
						frame.lineColorIndicator.setBackground(frame.currentPaintProperty.currentLineColor);
						frame.fillColorIndicator.setBackground(frame.currentPaintProperty.currentFillColor);
						frame.lineWidthIndicator2.setText(new Integer(frame.currentPaintProperty.currentStroke.width).toString());
						frame.lineStyleIndicator2.setText(frame.currentPaintProperty.currentStroke.patternName);
						frame.lineWidthChooserSlider.setValue(frame.currentPaintProperty.currentStroke.width);
						frame.arcWidthIndicator.setText(new Integer((int)(frame.currentPaintProperty.currentArcWidth)).toString());
						frame.arcWidthSlider.setValue((int)(frame.currentPaintProperty.currentArcWidth));
						frame.arcHeightIndicator.setText(new Integer((int)(frame.currentPaintProperty.currentArcHeight)).toString());
						frame.arcHeightSlider.setValue((int)(frame.currentPaintProperty.currentArcHeight));
						if(frame.currentPaintProperty.currentLined==true&&frame.currentPaintProperty.currentFillType!=FillType.NONE)
							frame.lineAndFillButton.setSelected(true);
						else if(frame.currentPaintProperty.currentLined==true)
							frame.lineOnlyButton.setSelected(true);
						else
							frame.fillOnlyButton.setSelected(true);
						frame.previewCanvas.repaint();
						canvas.repaint();
						}
					}
					if(frame.currentFunction==Function.TEXT)
						new TextDialog(frame,new Point2D.Double(tempX,tempY));
				}
				if(inCtrlPoint(e.getX(),e.getY())!=0)
					startInCtrlPoint=true;
				
				System.out.println(inCtrlPoint(e.getX(),e.getY()));
				break;
				
		}
	}
	public void mouseReleased(MouseEvent e){
		frame.repaintFlip.setFlip();
	}
	public void mouseEntered(MouseEvent e){
	}
	public void mouseExited(MouseEvent e){
	}
	public void mouseClicked(MouseEvent e){
	}
    public int inCtrlPoint(double mouseX, double mouseY){
    	Point2D.Double p=new Point2D.Double(mouseX,mouseY);
    	if(ShapePro.c1.contains(p))
    		return 1;
    	else if(ShapePro.c2.contains(p))
    		return 2;
    	else if(ShapePro.c3.contains(p))
    		return 3;
    	else if(ShapePro.c4.contains(p))
    		return 4;
    	else
    		return 0;
     }
}
