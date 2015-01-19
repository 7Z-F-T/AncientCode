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
	static double tempX=0;//�����ŵ�
	static double tempY=0;//�����ŵ�
	double dy;//�������м����
	double dx;//�������м���� 
	static double lastX;//�������м����
	static double lastY;//�������м����
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
	
	
	//�˺�������޸�ͼ���������ֵ���Լ��ɼ��任�����Ĺ���
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
		    case Function.SELECT://����drag��ֱ���޸��������ֵ
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
		    case Function.ROTATE://����rotate,�ɼ��任����������shape���任�����
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
		    		//ֱ�����drag����
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
		    case Function.SCALE://����scale,ֱ���޸��������ֵ
		    	if(currentSelectedShapePro!=null&&currentSelectedShapePro.selected==true&&startInCtrlPoint==true){
		    		currentSelectedShapePro.scaled=true;
		    	switch(currentSelectedShapePro.shapeType){
		    	case ShapeType.RECTANGLE:
		    			p1=new Point2D.Double(e.getX(),e.getY());
	    				p2=new Point2D.Double(lastX,lastY);
	    				p3=new Point2D.Double(tempX,tempY);
		    			//System.out.println("dx:"+dx+" dy"+dy);
		    			//����굱ǰ���������任
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
		    				t.transform(p1, p1);//�任��������������p��
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
		    			//��ʼ����RECTANGLE��scale����
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
	    			//����굱ǰ���������任
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
	    				t.transform(p1, p1);//�任��������������p��
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
	    			//��ʼ����ROUNDRECTANGLE��scale����
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
	    			//����굱ǰ���������任
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
	    				t.transform(p1, p1);//�任��������������p��
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
	    			
	    			    //��ʼ����ELLIPSE��scale����
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
	    			//����굱ǰ���������任
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
	    				t.transform(p1, p1);//�任��������������p��
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
	    			    //��ʼ����LINE��scale����
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
	    			//����굱ǰ���������任
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
	    				t.transform(p1, p1);//�任��������������p��
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
	    			    //��ʼ����TEXT��scale����
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
		    		//ֱ�����drag����
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
		    case Function.SHEAR://����shear,�ɼ��任����������shape���任�����
		    	if(currentSelectedShapePro!=null&&currentSelectedShapePro.selected==true&&startInCtrlPoint==true){
		    		currentSelectedShapePro.sheared=true;
		    		p1=new Point2D.Double(e.getX(),e.getY());
    				p2=new Point2D.Double(lastX,lastY);
    				p3=new Point2D.Double(tempX,tempY);
	    			//System.out.println("dx:"+dx+" dy"+dy);
	    			//����굱ǰ���������任
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
	    				t.transform(p1, p1);//�任��������������p��
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
	    			//��ʼshear����
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
		    		//ֱ�����drag����
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
		boolean flag=false;//��־λ��true:�����ͼ�������� false:����
		for(int i=shapeProArray.size()-1;i>=0;i--){
			s=(ShapePro)shapeProArray.get(i);
			//System.out.println(s.containsPro(tempX,tempY));
			if(s.containsPro(tempX, tempY)){
				flag=true;//�����ͼ���������ˣ�
				break;
			}
		}
		if(flag==false){//��ǰ���û�����κ�ͼ��������
			//System.out.println("Not Select");
			if(currentPointedShapePro!=null){
				//System.out.println("Deselect");
				currentPointedShapePro.pointed=false;//ȡ���ϴε�ѡ��
				canvas.repaint();
			}
		}
		else{//�����ͼ��s��������
			//System.out.println("Select!");
			if(currentPointedShapePro!=null)
				currentPointedShapePro.pointed=false;
			currentPointedShapePro=s;
			currentPointedShapePro.pointed=true;//��sΪ��ǰѡ��ͼ��
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
			 //����Ϊ���Ʋ��������������Ϊѡ���޸Ĳ�������ͼ����������꼴��ѡ��
			 default:
				tempX=e.getX();
				tempY=e.getY();
				lastX=tempX;
				lastY=tempY;
				System.out.println(tempX);
				System.out.println(tempY);
				ShapePro s=new ShapePro();
				boolean flag=false;//��־λ��true:�����ͼ�������� false:����
			
				for(int i=shapeProArray.size()-1;i>=0;i--){
					s=(ShapePro)shapeProArray.get(i);
					//System.out.println(s.containsPro(tempX,tempY));
					if(s.containsPro(tempX, tempY)){
						flag=true;//�����ͼ���������ˣ�
						break;
					}
				}
				if(flag==true){//���������ͼ��s��������
					System.out.println("Select!");
					startInCtrlPoint=false;
					if(currentSelectedShapePro!=null)
						currentSelectedShapePro.selected=false;
					currentSelectedShapePro=s;
					currentSelectedShapePro.selected=true;//��sΪ��ǰѡ��ͼ��
					//�����Ҳ��������
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
				else{//��ǰ���û�е�����κ�ͼ��������
					System.out.println("Not Select");
					if(currentSelectedShapePro!=null){
						//System.out.println("Deselect");
						if(inCtrlPoint(e.getX(),e.getY())==0){//ͬʱҲû�е㵽ctrlPoint��
						currentSelectedShapePro.selected=false;//ȡ���ϴε�ѡ��
						//�����Ҳ��������
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
