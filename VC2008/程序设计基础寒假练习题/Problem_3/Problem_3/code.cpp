#include <iostream>
using namespace std;

void invert(int* p)
{
	if(*p==0) *p=1;
	else *p=0;
}

int main()
{
	int coin[1000];//1:�������� 0:��������
	int m;
	cin>>m;//Ӳ���ܸ���
	for(int i=0;i<m;i++)
		coin[i]=1;
   	int temp;
	int j=0;
	int count=0;
	bool finished=false;
	
	while(finished==false)
	{
		count++;
		j++;//�˷�Ҫ����Ӳ�Ҹ���
		if(j>m) j=j-m;
	    //����ż�����ҷ�ת
		if(j%2==0)
		{
	    for(int i=0;i<=j/2-1;i++)
	    {
		    invert(&coin[i]);
		    invert(&coin[j-1-i]);
		    temp=coin[j-1-i];
		    coin[j-1-i]=coin[i];
            coin[i]=temp;

	    }
		}
	    //�����������ҷ�ת
		else
		{
	    for(int i=0;i<=(j-1)/2;i++)
	    {
	      	if(i==j-1-i) invert(&coin[i]);
	    	else 
	    	{
	    		invert(&coin[i]);
	    		invert(&coin[j-1-i]);
	    		temp=coin[j-1-i];
	    		coin[j-1-i]=coin[i];
	    		coin[i]=temp;

		    }
	    }
		}
		//���
		finished=true;
		for(int i=0;i<m;i++)
			if(coin[i]==0) finished=false;
	}

	cout<<count;

	return 0;


}