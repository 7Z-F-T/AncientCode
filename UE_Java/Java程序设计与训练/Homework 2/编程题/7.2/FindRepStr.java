public class FindRepStr{
	public static void main(String args[]){
		StringBuffer text=new StringBuffer("解决DCOM的问题主要是解决程序配置和部署的问题。由于DCOM涉及到在多台计算机上运行的程序，所以潜在的问题比在单机上使用COM时要大。其他可能需要解决的问题包括程序和网络协议之间的安全机制。因为在默认情况下COM安全是打开的，所以只要试图访问COM对象的COM程序或客户程序启动COM对象，就会开始进行安全检查。");
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
    System.out.println("修改后的文字为:");
    System.out.println(text);
  }
}

  		  
  	