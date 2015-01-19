package frame;
import java.awt.*;
import java.awt.geom.*;
/**
 * 存储文字类图形
 * @author 侯杰
 *
 */
public class TextShapePro extends ShapePro{
	/**
	 * 文字内容
	 */
    public String text=new String();
    /**
     * 字体类型
     */
    public Font font;
    /**
     * 文字初始显示定位点
     */
    public Point2D.Double position=new Point2D.Double(0,0);//所指文字的左下角
    /**
     * 文字高度
     */
    public double textHeight=0;
    /**
     * 文字宽度
     */
    public double textWidth=0;
    /**
     * 新建一个TextShapePro对象
     * @param txt 文字内容
     * @param fnt 字体类型
     * @param pos 初始显示位置
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
    //重写父类某些函数
    /**
	 * 判断某点是否在文字内
	 * @param mouseX 待判断点的x坐标
	 * @param mouseY 待判断点的y坐标
	 * @return 返回true说明待判断点在文字内
	 */
    public boolean containsPro(double mouseX,double mouseY){
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
    }
    /**
	 * 得到文字的中心点坐标
	 * @return 中心点
	 */
    public Point2D.Double getCenterPoint(){
		double x,y;
		x=position.x+textWidth/2;
		y=position.y-textHeight/2;
		return new Point2D.Double(x,y);
	}
    /**
	 * 得到文字的左上角点坐标
	 * @return 左上角点
	 */
	public Point2D.Double getLeftTop(){
		double x,y;
		x=position.x;
		y=position.y-textHeight;
		return new Point2D.Double(x,y);
	}
	/**
	 * 得到文字的右上角点坐标
	 * @return 右上角点
	 */
	public Point2D.Double getRightTop(){
		double x,y;
		x=position.x+textWidth+7;
		y=position.y-textHeight;
		return new Point2D.Double(x,y);
	}
	/**
	 * 得到文字的左下角点坐标
	 * @return 左下角点
	 */
	public Point2D.Double getLeftBottom(){
		double x,y;
		x=position.x;
		y=position.y+15;
		return new Point2D.Double(x,y);
	}
	/**
	 * 得到文字的右下角点坐标
	 * @return 右下角点
	 */
	public Point2D.Double getRightBottom(){
		double x,y;
		x=position.x+textWidth+7;
		y=position.y+15;
		return new Point2D.Double(x,y);
	}
}
