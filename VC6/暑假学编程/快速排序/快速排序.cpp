#include <iostream.h>
int count=0;
void sort(int array[],int zz,int yy)
{
	int z,y,i,k;
	count++;
	cout<<"��ִ��sort(a,"<<zz<<","<<yy<<")  ";
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
		
		cout<<"�˴�sortִ�н��Ϊ";
		for(i=zz;i<=yy;i++)
		{
			cout<<array[i]<<" ";
		
		}
		cout<<"   ͬʱk�ѵ�λ��"<<"�������ҷֽ��Ϊ"<<"a["<<z<<"]"<<"="<<array[z]<<endl;
       

		cout<<"��ʼ����࣬";sort(array,zz,z-1);
		cout<<"��ʼ���Ҳ࣬";sort(array,z+1,yy);

	}
	else cout<<"���±��½粻�����Ͻ磬�����в���"<<endl;

}

int main()
{
	int a[9],i;
	cout<<"������9������"<<endl;
	for(i=0;i<=8;i++)
		cin>>a[i];
	sort(a,0,8);
	cout<<"sort����ִ�д���Ϊ"<<count<<"��"<<endl;
	cout<<"������Ϊ";
	for(i=0;i<=8;i++)
		cout<<a[i]<<" ";
	cout<<endl;
	return 0;
}

