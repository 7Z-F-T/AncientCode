package frame;
/**
 * ��ת���ء����������в�����Ҫ�������紫��ʱ�������˿��أ���ʱ���Ӵ˿���
 * ���շ��̼߳�����Ҫ�������ݴ��䡣
 * @author ���
 *
 */
public class Flip {
	/**
	 * ����״ֵ̬�����������ֻ��1��-1����ȡֵ
	 */
    public int status;
    /**
     * �½�һ����ת����
     * @param val ���س�ʼֵ������1��-1֮��ѡ��
     */
    public Flip(int val){
    	status = val;
    }
    /**
     * �������أ�ʹ��ֵ�����仯
     */
    public void setFlip(){
    	status=-status;
    }
}

