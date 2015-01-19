#include <iostream.h>

int main()
{
	int man[5];
	int i,j,k,l,m;
	int n;
	int count=0;
	for(i=1;i<=5;i++)
	{
		man[0]=i;
		for(j=1;j<=5;j++)
		{
			if(j==man[0]) continue;
			man[1]=j;
			for(k=1;k<=5;k++)
			{
				
				if(k==man[0]||k==man[1]) continue;
				man[2]=k;
				for(l=1;l<=5;l++)
				{
					
					if(l==man[0]||l==man[1]||l==man[2]) continue;
					man[3]=l;
					for(m=1;m<=5;m++)
					{
						
						if(m==man[0]||m==man[1]||m==man[2]||m==man[3]) continue;
						man[4]=m;
						if((man[1]==2||man[0]==3)+(man[1]==2&&man[0]==3)) count++;
						if((man[1]==2||man[4]==4)+(man[1]==2&&man[4]==4)) count++;
						if((man[2]==1||man[3]==2)+(man[2]==1&&man[3]==2)) count++;
						if((man[2]==5||man[3]==3)+(man[4]==5&&man[3]==3)) count++;
						if((man[4]==4||man[0]==1)+(man[4]==4&&man[0]==1)) count++;
                        if(count==5)
						{
							cout<<"ABCDE order is"<<endl;
                            for(n=0;n<=4;n++) cout<<man[n];
							return 0;
						}
						count=0;
					}
				}
			}
		}
    
	}
return 0;
}