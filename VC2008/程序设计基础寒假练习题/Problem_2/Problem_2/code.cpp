#include <iostream>
#include <vector>
using namespace std;

vector<int> a;

int main()
{
	int person[30];//0:未出局 1:出局 (从person[1]开始向后数)
	int k;
	cin>>k;
	int out=0;//出局人数
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
	    	//i:迭代变量，j:记录人头够不够m
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
		//现在out为k了，检验出局的人是否都是坏人(即检验未出局的人是否都是好人)
		finished=true;
		for(int x=k+1;x<=2*k;x++)
			if(person[x]==0) finished=false;
	}

	cout<<m;

	return 0;
}
