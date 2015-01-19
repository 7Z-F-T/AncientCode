package frame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import java.io.*;
public class GradPaint implements Serializable {
    public Point2D.Double point1;
    public Color color1;
    public Point2D.Double point2;
    public Color color2;
    public GradPaint(Point2D.Double p1,Color c1,Point2D.Double p2,Color c2){
    	point1=p1;
    	point2=p2;
    	color1=c1;
    	color2=c2;
    }
}
