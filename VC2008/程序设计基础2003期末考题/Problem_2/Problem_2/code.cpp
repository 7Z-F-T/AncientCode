#include <iostream>
#include <vector>
using namespace std;

int main()
{
	int N,K;
	cin>>N>>K;
	vector<int> person(N+1);//标号0为未出局，标号1为出局
	for(int i=1;i<person.size();i++)//初始化
		person[i]=0;
	int left=N;//剩下的人数
	int i=1;//迭代器（一步一步走）
	int j=0;//控制数到某个人头数时踢人
	while(left>3)
	{
		if(person[i]==0) j++;//数到一个未出局的人
		if(j==K) //数够人头数了，开始踢人
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