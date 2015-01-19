package org.thu.ui;
import java.io.File;

public class Filter extends javax.swing.filechooser.FileFilter//ÎÄ¼þ¹ýÂËÆ÷
{
    String extent;
    Filter( String extent)
    {
        super();
        this.extent=extent;
    }
    public boolean accept(File dir)
    {
        if(dir.isDirectory()) return true;
        return dir.getName().endsWith("."+extent);
    }
    public String getDescription()
    {
        return "*."+extent;
    
    }
}