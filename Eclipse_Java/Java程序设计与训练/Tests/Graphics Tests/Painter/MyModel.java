package com.libin.chapter19.exec1;
 
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Observable;
 
import com.libin.chapter19.demo3.Element;
import java.awt.geom.*;
public class MyModel extends Observable{
    
    public boolean remove(Line2D.Double line) {
        boolean removed = elementList.remove(line);
        if(removed) {
          setChanged();
          notifyObservers(line.getBounds());
        }
 
        return removed;
     }
     
     public void add(Line2D.Double line) {
        elementList.add(line);
        setChanged();
        notifyObservers(line.getBounds());
     }
 
     public Iterator getIterator() {
        returnelementList.listIterator(); 
     }
     //基于链表结构实现的集合类
     protected LinkedList elementList = new LinkedList();
 
    
    
}