package frame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import java.io.*;
public class BasStroke implements Serializable{
	public int width;
	public float[] pattern;
	public String patternName;
	public BasStroke(int w,float[] pat,String pName){
		width=w;
		pattern=pat;
		patternName=pName;
	}

}
