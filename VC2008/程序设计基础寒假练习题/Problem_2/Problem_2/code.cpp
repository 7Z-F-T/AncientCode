#include <iostream>
#include <vector>
using namespace std;

vector<int> a;

int main()
{
	int person[30];//0:δ���� 1:���� (��person[1]��ʼ�����)
	int k;
	cin>>k;
	int out=0;//��������
	bool finished=false;
	int m=0;
	int i,j;
	while(finished==false)
	{
		a.clear();
		for(int x=1;x<=2*k;x++)
			person[x]=0;
		i=1;
		j=0;
		m++;
		out=0;
	    while(out<k)
	    {
	    	//i:����������j:��¼��ͷ������m
			if(person[i]==0) j++;
			if(j==m)
			{
				person[i]=1;
				out++;
				j=0;
				a.push_back(i);
			}
			i++;
			if(i>2*k) i=i-2*k;
	    }
		//����outΪk�ˣ�������ֵ����Ƿ��ǻ���(������δ���ֵ����Ƿ��Ǻ���)
		finished=true;
		for(int x=k+1;x<=2*k;x++)
			if(person[x]==0) finished=false;
	}

	cout<<m;

	return 0;
}
