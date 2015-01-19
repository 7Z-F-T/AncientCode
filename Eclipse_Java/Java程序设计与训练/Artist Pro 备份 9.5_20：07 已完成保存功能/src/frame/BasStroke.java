package frame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.awt.geom.*;
import java.io.*;
public class BasStroke implements Serializable{
	public float width;
	public float[] pattern;
	public BasStroke(float w,float[] pat){
		width=w;
		pattern=pat;
	}

}
