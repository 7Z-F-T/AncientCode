public class Append_Case{
	public static void main(String args[]){
		StringBuffer text=new StringBuffer("���DCOM��������Ҫ�ǽ���������úͲ�������⡣����DCOM�漰���ڶ�̨����������еĳ�������Ǳ�ڵ�������ڵ�����ʹ��COMʱҪ������������Ҫ���������������������Э��֮��İ�ȫ���ơ�");
    StringBuffer append=new StringBuffer("��Ϊ��Ĭ�������COM��ȫ�Ǵ򿪵ģ�����ֻҪ��ͼ����COM�����COM�����ͻ���������COM���󣬾ͻῪʼ���а�ȫ��顣");
    
    System.out.println("��ʼ����Ϊ:");
    System.out.println(text);
    
    text.append(append);
    System.out.println("׷����ɺ������Ϊ:");
    System.out.println(text);
    
    System.out.println("��ĸ�ĳ�Сд�������Ϊ:");
    System.out.println(text.toString().toLowerCase());
  }
}
  		  
  	