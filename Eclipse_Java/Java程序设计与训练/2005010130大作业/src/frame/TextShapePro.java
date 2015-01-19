package frame;
import java.awt.*;
import java.awt.geom.*;
/**
 * �洢������ͼ��
 * @author ���
 *
 */
public class TextShapePro extends ShapePro{
	/**
	 * ��������
	 */
    public String text=new String();
    /**
     * ��������
     */
    public Font font;
    /**
     * ���ֳ�ʼ��ʾ��λ��
     */
    public Point2D.Double position=new Point2D.Double(0,0);//��ָ���ֵ����½�
    /**
     * ���ָ߶�
     */
    public double textHeight=0;
    /**
     * ���ֿ��
     */
    public double textWidth=0;
    /**
     * �½�һ��TextShapePro����
     * @param txt ��������
     * @param fnt ��������
     * @param pos ��ʼ��ʾλ��
     * @param stk ��������
     * @param lnd �Ƿ��б���
     * @param lc ������ɫ
     * @param fType �������
     * @param fc �����ɫ
     * @param gfc �ݶ������ʽ
     * @param type ͼ������
     * @param ptd �Ƿ����ָ��
     * @param sel �Ƿ�ѡ��
     * @param rot �Ƿ�����ת
     * @param rAngle ��ת�Ƕ�
     * @param rvx ��ת��������
     * @param rvy ��ת��������
     * @param sca �Ƿ�������
     * @param sfx �����������
     * @param sfy �����������
     * @param she �Ƿ�������
     * @param shfx ������������
     * @param shfy ������������
     */
    public  TextShapePro(String txt, Font fnt, Point2D.Double pos, BasStroke stk,boolean lnd,Color lc, int fType, Color fc, GradPaint gfc,int type,boolean ptd,boolean sel,boolean rot,double rAngle ,double rvx,double rvy,boolean sca,double sfx,double sfy,boolean she,double shfx,double shfy){
        //shape=s
    	text=txt;
    	font=fnt;
    	position=pos;
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
    //��д����ĳЩ����
    /**
	 * �ж�ĳ���Ƿ���������
	 * @param mouseX ���жϵ��x����
	 * @param mouseY ���жϵ��y����
	 * @return ����true˵�����жϵ���������
	 */
    public boolean containsPro(double mouseX,double mouseY){
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
		//�ж��Ƿ��ѷ������ţ����Ѽ��������������任
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
    }
    /**
	 * �õ����ֵ����ĵ�����
	 * @return ���ĵ�
	 */
    public Point2D.Double getCenterPoint(){
		double x,y;
		x=position.x+textWidth/2;
		y=position.y-textHeight/2;
		return new Point2D.Double(x,y);
	}
    /**
	 * �õ����ֵ����Ͻǵ�����
	 * @return ���Ͻǵ�
	 */
	public Point2D.Double getLeftTop(){
		double x,y;
		x=position.x;
		y=position.y-textHeight;
		return new Point2D.Double(x,y);
	}
	/**
	 * �õ����ֵ����Ͻǵ�����
	 * @return ���Ͻǵ�
	 */
	public Point2D.Double getRightTop(){
		double x,y;
		x=position.x+textWidth+7;
		y=position.y-textHeight;
		return new Point2D.Double(x,y);
	}
	/**
	 * �õ����ֵ����½ǵ�����
	 * @return ���½ǵ�
	 */
	public Point2D.Double getLeftBottom(){
		double x,y;
		x=position.x;
		y=position.y+15;
		return new Point2D.Double(x,y);
	}
	/**
	 * �õ����ֵ����½ǵ�����
	 * @return ���½ǵ�
	 */
	public Point2D.Double getRightBottom(){
		double x,y;
		x=position.x+textWidth+7;
		y=position.y+15;
		return new Point2D.Double(x,y);
	}
}
