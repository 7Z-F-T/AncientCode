#include <iostream>
#include <vector>
using namespace std;

int main()
{
	int N,K;
	cin>>N>>K;
	vector<int> person(N+1);//���0Ϊδ���֣����1Ϊ����
	for(int i=1;i<person.size();i++)//��ʼ��
		person[i]=0;
	int left=N;//ʣ�µ�����
	int i=1;//��������һ��һ���ߣ�
	int j=0;//��������ĳ����ͷ��ʱ����
	while(left>3)
	{
		if(person[i]==0) j++;//����һ��δ���ֵ���
		if(j==K) //������ͷ���ˣ���ʼ����
		{
			person[i]=1;
			j=0;
			K++;
			left--;
		}
		i++;
		if(i>N) i=i-N;
	}
	for(int i=1;i<person.size();i++)
		if(person[i]==0) cout<<i<<endl;

	return 0;
	
}