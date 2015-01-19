#include <iostream.h>
long start(int i,int s);
int number[999];//////////////////////////////////////////////////////////////////////////
long sumMAX=1;//number[]和sumMAX千万记得用全局变量！否则函数一换变量也就跟着换了


int main()
{

	int x;
	cout<<"请输入要操作的整数"<<endl;
	cin>>x;
	start(x,1);
	cout<<"拆成整数和后这些整数的乘积最大值为"<<sumMAX<<endl;;
	return 0;
}

long start(int i,int s)//i代表还剩下多大数字，j代表此步分解出数字的大小，s代表这是分解的第几步
{
	int j,k;
	long sum=1;//sum表示分解出数字的乘积
   

	
	
    for(j=i;j>=1;j--)//分解数j从最大取值i到最小取值1挨个试
	{
		if(i<j)//剩的数比分解出的数小，无法分解，所以不操作
		{
		}
		else if(i==j)//剩的数正好等于分解出的数，结束分解，返回上级函数
		{
			number[s]=j;
			for(k=1;k<=s;k++)
			{
		//		cout<<number[k]<<" ";
				sum=sum*number[k];
			}
		//	cout<<"------"<<sum;
			
		//	cout<<endl;
           if(sum>=sumMAX) sumMAX=sum;//取sum中最大的作为本题结果

            
		
		}
		else if(i>j)//剩的数比分解数大，分解后还有剩余->进行递归，转入下一层循环
		{
			number[s]=j;
			start(i-j,s+1);//注意下一层循环的初始条件已经改变
		}

	}

return 0;

}