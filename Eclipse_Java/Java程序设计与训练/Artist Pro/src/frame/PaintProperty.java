package frame;
import java.awt.*;
import java.awt.geom.*;
/**
 * ��ͼ���ԡ������洢��ͼ��Ĭ�����ԣ����»�ͼ��ʱӦ�����Ե�ͼ�Ρ�
 * @author ���
 *
 */
public class PaintProperty {
	/**
	 * ��������
	 */
	public BasStroke currentStroke;
	/**
	 * ��������
	 */
	public String currentStrokeName;
	/**
	 * �Ƿ��б��ߡ�trueΪ�б���
	 */
	public boolean currentLined;
	/**
	 * ������ɫ
	 */
	public Color currentLineColor;
	/**
	 * �������
	 */
	public int currentFillType;
	/**
	 * �����ɫ
	 */
	public Color currentFillColor;
	/**
	 * �ݶ������ʽ
	 */
	public GradPaint currentGradientFillColor;
	/**
	 * �Ƿ�ɶԻ���ͼ�ν��б��β�����trueΪ�ɱ���
	 */
	public boolean transformable;
	/**
	 * Բ�Ǿ��λ���
	 */
	public double currentArcWidth;
	/**
	 * Բ�Ǿ��λ���
	 */
	public double currentArcHeight;
	/**
	 * �½�һ����ͼ���Զ���
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
