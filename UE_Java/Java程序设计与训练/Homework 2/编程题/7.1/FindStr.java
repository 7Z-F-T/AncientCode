public class FindStr{
	public static void main(String args[]){
		String text="解决 DCOM 的问题主要是解决程序配置和部署的问题。由于 DCOM 涉及到在多台计算机上运行的程序，所以潜在的问题比在单机上使用COM 时要大。其他可能需要解决的问题包括程序和网络协议之间的安全机制。因为在默认情况下 COM 安全是打开的，所以只要试图访问COM 对象的COM 程序或客户程序启动COM 对象，就会开始进行安全检查。";
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
    System.out.println("\"COM\"出现"+occurTime+"次");
  }
}
  		  
  	