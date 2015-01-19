package frame;
import java.awt.geom.*;
/**
 * ���ShapePro�����ĸ��Ʋ���
 * @author ���
 *
 */
public class CopyShapePro {
	/**
	 * ����һ��ShapePro���󣬷���һ�����ƺ��ShapePro����Ϊ����ճ��������ƣ��������ͼ��λ�����룬�����û������µ�ͼ�γ���λ�á�
	 * @param s ԴShapePro����
	 * @return ���ƺ���µ�ShapePro����
	 */
    public static ShapePro copy1(ShapePro s){//Ϊ����ճ��������ƣ��������ͼ��λ�����룬�����û������µ�ͼ�γ���λ��
    	switch(s.shapeType){
    	case ShapeType.RECTANGLE:
        Rectangle2D.Double src1=(Rectangle2D.Double)(s.shape);
    	Rectangle2D.Double dst1=new Rectangle2D.Double(300,300,src1.width,src1.height);
    	return new ShapePro(dst1,s.stroke,s.lined,s.lineColor,s.fillType,s.fillColor,s.gradientFillColor,s.shapeType,s.pointed,s.selected,s.rotated,s.rotatedAngle,s.rotatedVecX,s.rotatedVecY,s.scaled,s.scaledFactorX,s.scaledFactorY,s.sheared,s.shearedFactorX,s.shearedFactorY);

    	case ShapeType.ROUNDRECTANGLE:
        RoundRectangle2D.Double src2=(RoundRectangle2D.Double)(s.shape);
    	RoundRectangle2D.Double dst2=new RoundRectangle2D.Double(300,300,src2.width,src2.height,src2.arcwidth,src2.archeight);
    	return new ShapePro(dst2,s.stroke,s.lined,s.lineColor,s.fillType,s.fillColor,s.gradientFillColor,s.shapeType,s.pointed,s.selected,s.rotated,s.rotatedAngle,s.rotatedVecX,s.rotatedVecY,s.scaled,s.scaledFactorX,s.scaledFactorY,s.sheared,s.shearedFactorX,s.shearedFactorY);

    	case ShapeType.ELLIPSE:
        Ellipse2D.Double src3=(Ellipse2D.Double)(s.shape);
        Ellipse2D.Double dst3=new  Ellipse2D.Double(300,300,src3.width,src3.height);
    	return new ShapePro(dst3,s.stroke,s.lined,s.lineColor,s.fillType,s.fillColor,s.gradientFillColor,s.shapeType,s.pointed,s.selected,s.rotated,s.rotatedAngle,s.rotatedVecX,s.rotatedVecY,s.scaled,s.scaledFactorX,s.scaledFactorY,s.sheared,s.shearedFactorX,s.shearedFactorY);
    	
    	case ShapeType.LINE:
        Line2D.Double src4=(Line2D.Double)(s.shape);
        Line2D.Double dst4=new Line2D.Double(300,300,src4.x2+(300-src4.x1),src4.y2+(300-src4.y1));
    	return new ShapePro(dst4,s.stroke,s.lined,s.lineColor,s.fillType,s.fillColor,s.gradientFillColor,s.shapeType,s.pointed,s.selected,s.rotated,s.rotatedAngle,s.rotatedVecX,s.rotatedVecY,s.scaled,s.scaledFactorX,s.scaledFactorY,s.sheared,s.shearedFactorX,s.shearedFactorY);
        
    	case ShapeType.TEXT:
    	TextShapePro ts=(TextShapePro)s;
    	Point2D.Double newPosition=new Point2D.Double(300,300);
        return new TextShapePro(ts.text,ts.font,newPosition,ts.stroke,ts.lined,ts.lineColor,ts.fillType,ts.fillColor,ts.gradientFillColor,ts.shapeType,ts.pointed,ts.selected,ts.rotated,ts.rotatedAngle,ts.rotatedVecX,ts.rotatedVecY,ts.scaled,ts.scaledFactorX,ts.scaledFactorY,ts.sheared,ts.shearedFactorX,ts.shearedFactorY);
    	default:
        return new ShapePro();
    	}
    }
    /**
     * ����һ��ShapePro���󣬷���һ�����ƺ��ShapePro����Ϊ��������������ƣ��������ͼ��λ��ԭ��λ�ã������û��ָ�ԭʼ״̬��
     * @param s ����ԴShapePro����
     * @return ���ƺ���µ�ShapePro����
     */
    public static ShapePro copy2(ShapePro s){//Ϊ��������������ƣ��������ͼ��λ��ԭ��λ�ã������û��ָ�ԭʼ״̬
    	switch(s.shapeType){
    	case ShapeType.RECTANGLE:
        Rectangle2D.Double src1=(Rectangle2D.Double)(s.shape);
    	Rectangle2D.Double dst1=new Rectangle2D.Double(src1.x,src1.y,src1.width,src1.height);
    	return new ShapePro(dst1,s.stroke,s.lined,s.lineColor,s.fillType,s.fillColor,s.gradientFillColor,s.shapeType,s.pointed,s.selected,s.rotated,s.rotatedAngle,s.rotatedVecX,s.rotatedVecY,s.scaled,s.scaledFactorX,s.scaledFactorY,s.sheared,s.shearedFactorX,s.shearedFactorY);

    	case ShapeType.ROUNDRECTANGLE:
        RoundRectangle2D.Double src2=(RoundRectangle2D.Double)(s.shape);
    	RoundRectangle2D.Double dst2=new RoundRectangle2D.Double(src2.x,src2.y,src2.width,src2.height,src2.arcwidth,src2.archeight);
    	return new ShapePro(dst2,s.stroke,s.lined,s.lineColor,s.fillType,s.fillColor,s.gradientFillColor,s.shapeType,s.pointed,s.selected,s.rotated,s.rotatedAngle,s.rotatedVecX,s.rotatedVecY,s.scaled,s.scaledFactorX,s.scaledFactorY,s.sheared,s.shearedFactorX,s.shearedFactorY);

    	case ShapeType.ELLIPSE:
        Ellipse2D.Double src3=(Ellipse2D.Double)(s.shape);
        Ellipse2D.Double dst3=new Ellipse2D.Double(src3.x,src3.y,src3.width,src3.height);
    	return new ShapePro(dst3,s.stroke,s.lined,s.lineColor,s.fillType,s.fillColor,s.gradientFillColor,s.shapeType,s.pointed,s.selected,s.rotated,s.rotatedAngle,s.rotatedVecX,s.rotatedVecY,s.scaled,s.scaledFactorX,s.scaledFactorY,s.sheared,s.shearedFactorX,s.shearedFactorY);
    	
    	case ShapeType.LINE:
        Line2D.Double src4=(Line2D.Double)(s.shape);
        Line2D.Double dst4=new Line2D.Double(src4.x1,src4.y1,src4.x2,src4.y2);
    	return new ShapePro(dst4,s.stroke,s.lined,s.lineColor,s.fillType,s.fillColor,s.gradientFillColor,s.shapeType,s.pointed,s.selected,s.rotated,s.rotatedAngle,s.rotatedVecX,s.rotatedVecY,s.scaled,s.scaledFactorX,s.scaledFactorY,s.sheared,s.shearedFactorX,s.shearedFactorY);
        
    	case ShapeType.TEXT:
    	TextShapePro ts=(TextShapePro)s;
        return new TextShapePro(ts.text,ts.font,ts.position,ts.stroke,ts.lined,ts.lineColor,ts.fillType,ts.fillColor,ts.gradientFillColor,ts.shapeType,ts.pointed,ts.selected,ts.rotated,ts.rotatedAngle,ts.rotatedVecX,ts.rotatedVecY,ts.scaled,ts.scaledFactorX,ts.scaledFactorY,ts.sheared,ts.shearedFactorX,ts.shearedFactorY);
    	default:
        return new ShapePro();
    	}
    }
}
