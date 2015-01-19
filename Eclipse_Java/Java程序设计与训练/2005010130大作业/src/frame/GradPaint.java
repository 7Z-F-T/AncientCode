package frame;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;
/**
 * 梯度填充类。可设置梯度填充的形式
 * @author 侯杰
 *
 */
public class GradPaint implements Serializable {
	/**
	 * 梯度填充第1点坐标
	 */
    public Point2D.Double point1;
    /**
     * 梯度填充第1点颜色
     */
    public Color color1;
    /**
     * 梯度填充第2点坐标
     */
    public Point2D.Double point2;
    /**
     * 梯度填充第2点颜色
     */
    public Color color2;
    /**
     * 新建一个梯度填充对象
     * @param p1 第1点坐标
     * @param c1 第1点颜色
     * @param p2 第2点坐标
     * @param c2 第2点颜色
     */
    public GradPaint(Point2D.Double p1,Color c1,Point2D.Double p2,Color c2){
    	point1=p1;
    	point2=p2;
    	color1=c1;
    	color2=c2;
    }
}
