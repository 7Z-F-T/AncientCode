package frame;
import java.util.*;
/**
 * 完成ArrayList<ShapePro>的复制操作
 * @author 侯杰
 *
 */
public class CopyShapeProArray {
	/**
	 * 接收一个ArrayList<ShapePro>对象，返回一个复制后的ArrayList<ShapePro>对象。
	 * @param src 源ArrayList<ShapePro>对象
	 * @return 复制后的新的ArrayList<ShapePro>对象
	 */
	public static ArrayList<ShapePro> copy(ArrayList<ShapePro> src){
		ArrayList<ShapePro> dst=new ArrayList<ShapePro>();
		for(int i=0;i<src.size();i++){
			ShapePro s=CopyShapePro.copy2(src.get(i));
			dst.add(s);
		}
		return dst;
	}
}
