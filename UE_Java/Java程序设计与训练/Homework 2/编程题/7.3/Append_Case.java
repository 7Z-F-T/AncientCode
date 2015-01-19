public class Append_Case{
	public static void main(String args[]){
		StringBuffer text=new StringBuffer("解决DCOM的问题主要是解决程序配置和部署的问题。由于DCOM涉及到在多台计算机上运行的程序，所以潜在的问题比在单机上使用COM时要大。其他可能需要解决的问题包括程序和网络协议之间的安全机制。");
    StringBuffer append=new StringBuffer("因为在默认情况下COM安全是打开的，所以只要试图访问COM对象的COM程序或客户程序启动COM对象，就会开始进行安全检查。");
    
    System.out.println("初始文字为:");
    System.out.println(text);
    
    text.append(append);
    System.out.println("追加完成后的文字为:");
    System.out.println(text);
    
    System.out.println("字母改成小写后的文字为:");
    System.out.println(text.toString().toLowerCase());
  }
}
  		  
  	