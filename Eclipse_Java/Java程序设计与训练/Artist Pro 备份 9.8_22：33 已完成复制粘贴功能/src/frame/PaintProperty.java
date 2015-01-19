package frame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
public class PaintProperty {
	public BasStroke currentStroke;
	public String currentStrokeName;
	public boolean currentLined;
	public Color currentLineColor;
	public int currentFillType;
	public Color currentFillColor;
	public GradPaint currentGradientFillColor;
	public boolean transformable;
	public double currentArcWidth;
	public double currentArcHeight;
	public PaintProperty(){
		float[] pattern={3000f};
		currentStroke=new BasStroke(4,pattern,"Continuum");
		currentStrokeName=new String("Continuum");
		currentLined=true;
		currentLineColor=Color.BLACK;
		currentFillType=FillType.SOLID;
		currentFillColor=Color.GREEN;
		currentGradientFillColor=new GradPaint(new Point2D.Double(0,0),Color.WHITE,new Point2D.Double(0,0),Color.WHITE);
		currentArcWidth=30.0;
		currentArcHeight=30.0;
		boolean transfromable=false;
	}
}
