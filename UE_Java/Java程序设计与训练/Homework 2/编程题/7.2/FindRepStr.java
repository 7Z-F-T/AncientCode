public class FindRepStr{
	public static void main(String args[]){
		StringBuffer text=new StringBuffer("���DCOM��������Ҫ�ǽ���������úͲ�������⡣����DCOM�漰���ڶ�̨����������еĳ�������Ǳ�ڵ�������ڵ�����ʹ��COMʱҪ������������Ҫ���������������������Э��֮��İ�ȫ���ơ���Ϊ��Ĭ�������COM��ȫ�Ǵ򿪵ģ�����ֻҪ��ͼ����COM�����COM�����ͻ���������COM���󣬾ͻῪʼ���а�ȫ��顣");
    String obj="COM";
    String newObj="DCOM";
    int startIndex=0;
    int occurTime=0;
    int index=text.indexOf(obj,startIndex);
    while(index!=-1){
    	if(index==0){
  	    //occurTime++;
  	    text.replace(index,index+2,newObj);
  	  }
  	  else if(index>0)
  		  if(text.charAt(index-1)!='D'){
  		  	//occurTime++;
  		  	text.replace(index,index+3,newObj);
  		  }
    	startIndex=index+3;
    	index=text.indexOf(obj,startIndex);
    }
    System.out.println("�޸ĺ������Ϊ:");
    System.out.println(text);
  }
}

  		  
  	