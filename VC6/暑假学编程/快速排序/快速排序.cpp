#include <iostream.h>
int count=0;
void sort(int array[],int zz,int yy)
{
	int z,y,i,k;
	count++;
	cout<<"现执行sort(a,"<<zz<<","<<yy<<")  ";
	if(zz<yy)
	{
		z=zz,y=yy,k=array[z];
		do{
			while((z<y&&array[y]>=k)) y--;
			if(z<y)
				array[z]=array[y];
			while((z<y)&&array[z]<=k) z++;
			if(z<y)
				array[y]=array[z];

		}while(z!=y);
		array[z]=k;
		
		cout<<"此次sort执行结果为";
		for(i=zz;i<=yy;i++)
		{
			cout<<array[i]<<" ";
		
		}
		cout<<"   同时k已到位，"<<"建立左右分界点为"<<"a["<<z<<"]"<<"="<<array[z]<<endl;
       

		cout<<"开始排左侧，";sort(array,zz,z-1);
		cout<<"开始排右侧，";sort(array,z+1,yy);

	}
	else cout<<"因下标下界不大于上界，不进行操作"<<endl;

}

int main()
{
	int a[9],i;
	cout<<"请输入9个整数"<<endl;
	for(i=0;i<=8;i++)
		cin>>a[i];
	sort(a,0,8);
	cout<<"sort函数执行次数为"<<count<<"次"<<endl;
	cout<<"排序结果为";
	for(i=0;i<=8;i++)
		cout<<a[i]<<" ";
	cout<<endl;
	return 0;
}

