package frame;
/**
 * 跳转开关。当主程序有操作需要进行网络传递时，拨动此开关，这时监视此开关
 * 的收发线程即明白要进行数据传输。
 * @author 侯杰
 *
 */
public class Flip {
	/**
	 * 开关状态值。正常情况下只有1和-1两种取值
	 */
    public int status;
    /**
     * 新建一个跳转开关
     * @param val 开关初始值。请在1和-1之间选择。
     */
    public Flip(int val){
    	status = val;
    }
    /**
     * 拨动开关，使其值发生变化
     */
    public void setFlip(){
    	status=-status;
    }
}

