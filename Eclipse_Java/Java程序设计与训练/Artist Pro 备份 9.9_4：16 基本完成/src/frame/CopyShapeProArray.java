package frame;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.awt.geom.*;
public class CopyShapeProArray {
	public static ArrayList<ShapePro> copy(ArrayList<ShapePro> src){
		ArrayList<ShapePro> dst=new ArrayList<ShapePro>();
		for(int i=0;i<src.size();i++){
			ShapePro s=CopyShapePro.copy2(src.get(i));
			dst.add(s);
		}
		return dst;
	}
}
