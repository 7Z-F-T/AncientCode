package com.libin.chapter19.exec1;
 
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.text.DateFormat;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;
 
import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;
 
public class MyViewer extends JComponent implements Observer {
 
    MyModel model = new MyModel();
 
    public MyViewer() {
       MyMouserHandler handler=new MyMouserHandler();
       this.addMouseListener(handler);
 
    }
    
    
    public void paint(Graphics g) {
        Graphics2D g2D = (Graphics2D)g;                // Get a Java 2D device context
 
        Iterator elements = model.getIterator();
        Line2D.Double element;                                    // Stores an element
 
        while(elements.hasNext()) {                         // Go through the list
          element = (Line2D.Double)elements.next();               // Get the next element
          g2D.setPaint(Color.red);                 // Set the element color
          //绘制最终的形状
          g2D.draw(element);
          // Draw its shape
        }
     }
    
    
 
    public void update(Observable o, Object obj) {
       
       if((Line2D.Double)obj==null){
           repaint();
       }
 
    }
 
    public class MyMouserHandler extends MouseInputAdapter {
 
       private Point start; // Stores cursor position on press
 
       private Point last; // Stores cursor position on drag
 
       private Line2D.Double tempElement; // Stores a temporary element
 
       private booleanbutton1Down = false; // Flag for button 1 state
 
       private Graphics2D g2D = null;
 
       public void mousePressed(MouseEvent e) {
           start = e.getPoint(); // Save the cursor position in start
           if (button1Down = (e.getButton() == MouseEvent.BUTTON1)) {
              System.out.println("鼠标左键被按下");
              g2D = (Graphics2D) getGraphics();
              g2D.setPaint(Color.blue);
 
           }
       }
 
       public void mouseReleased(MouseEvent e) {
           
           last = e.getPoint();
           tempElement = createElement(start, last);
           g2D.draw(tempElement); 
           
           if (button1Down = (e.getButton() == MouseEvent.BUTTON1)) {
              button1Down = false; // Reset the button 1 flag
              System.out.println("鼠标左键已经被释放");
              if (tempElement != null) {
 
                  model.add(tempElement); // Add element to the model
                  tempElement = null; // No temporary now stored
              }
              // 释放绘图对象，清理绘图环境
              if (g2D != null) { // If there's a graphics context
                  g2D.dispose(); // ...release the resource
                  g2D = null; // Set field to null
              }
              start = last = null; // Remove the points
           }
       }
 
       private Line2D.Double createElement(Point start, Point end) {
 
           returnnew Line2D.Double(start, end);
           // We should never get to here
 
       }
 
    }
 
}