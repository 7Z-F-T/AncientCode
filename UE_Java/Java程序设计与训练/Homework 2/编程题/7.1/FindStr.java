public class FindStr{
	public static void main(String args[]){
		String text="��� DCOM ��������Ҫ�ǽ���������úͲ�������⡣���� DCOM �漰���ڶ�̨����������еĳ�������Ǳ�ڵ�������ڵ�����ʹ��COM ʱҪ������������Ҫ���������������������Э��֮��İ�ȫ���ơ���Ϊ��Ĭ������� COM ��ȫ�Ǵ򿪵ģ�����ֻҪ��ͼ����COM �����COM �����ͻ���������COM ���󣬾ͻῪʼ���а�ȫ��顣";
    String obj="COM";
    int startIndex=0;
    int occurTime=0;
    int index=text.indexOf(obj,startIndex);
    while(index!=-1){
    	if(index==0)
  	    occurTime++;
  	  else if(index>0)
  		  if(text.charAt(index-1)!='D') occurTime++;
    	startIndex=index+3;
    	index=text.indexOf(obj,startIndex);
    }
    System.out.println("\"COM\"����"+occurTime+"��");
  }
}
  		  
  	