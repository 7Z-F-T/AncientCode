package frame;
import java.io.*;

/**
 * �����࣬�ɶ��ƻ��ʷ��
 * @author ���
 *
 */
public class BasStroke implements Serializable{
	/**
	 * ���ʿ��
	 */
	public int width;
	/**
	 * ������ʵ��ʽ
	 * ����float��������
	 */
	public float[] pattern;
	/**
	 * ������ʵ��ʽ������
	 */
	public String patternName;
	/**
	 * ����һ������
	 * @param w ���ʿ��
	 * @param pat ������ʵ��ʽ
	 * @param pName ������ʵ��ʽ����
	 */
	public BasStroke(int w,float[] pat,String pName){
		width=w;
		pattern=pat;
		patternName=pName;
	}

}
