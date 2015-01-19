package frame;
import java.awt.*;
import java.awt.geom.*;
/**
 * 绘图属性。用来存储绘图的默认属性，在新绘图形时应用属性到图形。
 * @author 侯杰
 *
 */
public class PaintProperty {
	/**
	 * 画笔属性
	 */
	public BasStroke currentStroke;
	/**
	 * 画笔名称
	 */
	public String currentStrokeName;
	/**
	 * 是否有边线。true为有边线
	 */
	public boolean currentLined;
	/**
	 * 边线颜色
	 */
	public Color currentLineColor;
	/**
	 * 填充类型
	 */
	public int currentFillType;
	/**
	 * 填充颜色
	 */
	public Color currentFillColor;
	/**
	 * 梯度填充样式
	 */
	public GradPaint currentGradientFillColor;
	/**
	 * 是否可对画布图形进行变形操作。true为可变形
	 */
	public boolean transformable;
	/**
	 * 圆角矩形弧高
	 */
	public double currentArcWidth;
	/**
	 * 圆角矩形弧宽
	 */
	public double currentArcHeight;
	/**
	 * 新建一个绘图属性对象
	 */
	public PaintProperty(){
		float[] pattern={3000f};
		currentStroke=new BasStroke(4,pattern,"Continuum");
		currentStrokeName=new String("Continuum");
		currentLined=true;
		currentLineColor=Color.BLACK;
		currentFillType=FillType.SOLID;
		currentFillColor=new Color(150,252,24);
		currentGradientFillColor=new GradPaint(new Point2D.Double(0,0),Color.WHITE,new Point2D.Double(1000,1000),Color.WHITE);
		currentArcWidth=30.0;
		currentArcHeight=30.0;
		boolean transfromable=false;
	}
}
