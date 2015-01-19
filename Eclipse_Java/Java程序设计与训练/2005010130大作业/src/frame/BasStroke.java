package frame;
import java.io.*;

/**
 * 画笔类，可定制画笔风格
 * @author 侯杰
 *
 */
public class BasStroke implements Serializable{
	/**
	 * 画笔宽度
	 */
	public int width;
	/**
	 * 画笔虚实形式
	 * 采用float数组描述
	 */
	public float[] pattern;
	/**
	 * 画笔虚实形式的名称
	 */
	public String patternName;
	/**
	 * 创建一个画笔
	 * @param w 画笔宽度
	 * @param pat 画笔虚实形式
	 * @param pName 画笔虚实形式名称
	 */
	public BasStroke(int w,float[] pat,String pName){
		width=w;
		pattern=pat;
		patternName=pName;
	}

}
