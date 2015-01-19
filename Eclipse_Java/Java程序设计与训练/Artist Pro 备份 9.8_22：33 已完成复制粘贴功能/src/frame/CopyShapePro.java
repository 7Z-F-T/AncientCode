package frame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.awt.geom.*;
public class CopyShapePro {
    public static ShapePro copy(ShapePro s){
    	switch(s.shapeType){
    	case ShapeType.RECTANGLE:
        Rectangle2D.Double src1=(Rectangle2D.Double)(s.shape);
    	Rectangle2D.Double dst1=new Rectangle2D.Double(300,300,src1.width,src1.height);
    	return new ShapePro(dst1,s.stroke,s.lined,s.lineColor,s.fillType,s.fillColor,s.gradientFillColor,s.shapeType,s.pointed,s.selected,s.rotated,s.rotatedAngle,s.rotatedVecX,s.rotatedVecY,s.scaled,s.scaledFactorX,s.scaledFactorY,s.sheared,s.scaledFactorX,s.scaledFactorY);

    	case ShapeType.ROUNDRECTANGLE:
        RoundRectangle2D.Double src2=(RoundRectangle2D.Double)(s.shape);
    	RoundRectangle2D.Double dst2=new RoundRectangle2D.Double(300,300,src2.width,src2.height,src2.arcwidth,src2.archeight);
    	return new ShapePro(dst2,s.stroke,s.lined,s.lineColor,s.fillType,s.fillColor,s.gradientFillColor,s.shapeType,s.pointed,s.selected,s.rotated,s.rotatedAngle,s.rotatedVecX,s.rotatedVecY,s.scaled,s.scaledFactorX,s.scaledFactorY,s.sheared,s.scaledFactorX,s.scaledFactorY);

    	case ShapeType.ELLIPSE:
        Ellipse2D.Double src3=(Ellipse2D.Double)(s.shape);
        Ellipse2D.Double dst3=new  Ellipse2D.Double(300,300,src3.width,src3.height);
    	return new ShapePro(dst3,s.stroke,s.lined,s.lineColor,s.fillType,s.fillColor,s.gradientFillColor,s.shapeType,s.pointed,s.selected,s.rotated,s.rotatedAngle,s.rotatedVecX,s.rotatedVecY,s.scaled,s.scaledFactorX,s.scaledFactorY,s.sheared,s.scaledFactorX,s.scaledFactorY);
    	
    	case ShapeType.LINE:
        Line2D.Double src4=(Line2D.Double)(s.shape);
        Line2D.Double dst4=new Line2D.Double(300,300,src4.x2+(300-src4.x1),src4.y2+(300-src4.y1));
    	return new ShapePro(dst4,s.stroke,s.lined,s.lineColor,s.fillType,s.fillColor,s.gradientFillColor,s.shapeType,s.pointed,s.selected,s.rotated,s.rotatedAngle,s.rotatedVecX,s.rotatedVecY,s.scaled,s.scaledFactorX,s.scaledFactorY,s.sheared,s.scaledFactorX,s.scaledFactorY);
        
    	case ShapeType.TEXT:
    	TextShapePro ts=(TextShapePro)s;
    	Point2D.Double newPosition=new Point2D.Double(300,300);
        return new TextShapePro(ts.text,ts.font,newPosition,ts.stroke,ts.lined,ts.lineColor,ts.fillType,ts.fillColor,ts.gradientFillColor,ts.shapeType,ts.pointed,ts.selected,ts.rotated,ts.rotatedAngle,ts.rotatedVecX,ts.rotatedVecY,ts.scaled,ts.scaledFactorX,ts.scaledFactorY,ts.sheared,ts.shearedFactorX,ts.scaledFactorY);
    	default:
        return new ShapePro();
    	}
    }
}
