package frame;
import java.util.*;
/**
 * ���ArrayList<ShapePro>�ĸ��Ʋ���
 * @author ���
 *
 */
public class CopyShapeProArray {
	/**
	 * ����һ��ArrayList<ShapePro>���󣬷���һ�����ƺ��ArrayList<ShapePro>����
	 * @param src ԴArrayList<ShapePro>����
	 * @return ���ƺ���µ�ArrayList<ShapePro>����
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
