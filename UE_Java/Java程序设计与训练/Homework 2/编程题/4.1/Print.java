public class Print{
	public static void main(String args[]){
		char ch;
		System.out.println("All lowercase letters:");
		for(ch='a';ch<='z';ch++){
			System.out.print(ch);
		}
		System.out.println();
		
		System.out.println("All letters:");
		for(ch='A';ch<='Z';ch++){
			System.out.print(ch);
		}
		for(ch='a';ch<='z';ch++){
			System.out.print(ch);
		}
		System.out.println();
		
		System.out.println("All numbers and letters:");
		for(ch='0';ch<='9';ch++){
			System.out.print(ch);
		}
		for(ch='A';ch<='Z';ch++){
			System.out.print(ch);
		}
		for(ch='a';ch<='z';ch++){
			System.out.print(ch);
		}
		System.out.println();
		
		System.out.println("All characters that can appear on identifiers:");
		System.out.print("_$");
		for(ch='0';ch<='9';ch++){
			System.out.print(ch);
		}
		for(ch='A';ch<='Z';ch++){
			System.out.print(ch);
		}
		for(ch='a';ch<='z';ch++){
			System.out.print(ch);
		}
		System.out.println();
		
		System.out.println("All visiable characters:");
		for(ch='!';ch<='~';ch++){
			System.out.print(ch);
		}
		
		
	}
}