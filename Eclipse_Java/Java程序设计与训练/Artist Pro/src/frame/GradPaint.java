package frame;
import java.awt.*;
import java.awt.geom.*;
import java.io.*;
/**
 * �ݶ�����ࡣ�������ݶ�������ʽ
 * @author ���
 *
 */
public class GradPaint implements Serializable {
	/**
	 * �ݶ�����1������
	 */
    public Point2D.Double point1;
    /**
     * �ݶ�����1����ɫ
     */
    public Color color1;
    /**
     * �ݶ�����2������
     */
    public Point2D.Double point2;
    /**
     * �ݶ�����2����ɫ
     */
    public Color color2;
    /**
     * �½�һ���ݶ�������
     * @param p1 ��1������
     * @param c1 ��1����ɫ
     * @param p2 ��2������
     * @param c2 ��2����ɫ
     */
    public GradPaint(Point2D.Double p1,Color c1,Point2D.Double p2,Color c2){
    	point1=p1;
    	point2=p2;
    	color1=c1;
    	color2=c2;
    }
}
