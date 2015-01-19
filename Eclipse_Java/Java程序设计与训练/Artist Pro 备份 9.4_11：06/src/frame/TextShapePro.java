package frame;
import handler.*;
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;

public class TextShapePro extends ShapePro{
    String text=new String();
    Font font;
    Point2D.Double position=new Point2D.Double(0,0);
    public  TextShapePro(String txt, Font fnt, Point2D.Double pos, BasicStroke stk,boolean lnd,Color lc, int fType, Color fc, GradientPaint gfc,int type,boolean ptd,boolean sel,boolean rot,double rAngle ,double rvx,double rvy,boolean sca,double sfx,double sfy,boolean she,double shfx,double shfy){
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
}
