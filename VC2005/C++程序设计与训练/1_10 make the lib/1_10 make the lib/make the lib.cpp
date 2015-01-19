#include <iostream>
#include <vector>
using namespace std;

void ReversePrint(vector<int> *pintVec)
{
	if(pintVec->empty())
		cout<<"empty vector!\n";
	else
	{
	int n;
	n=(int)pintVec->size();
	for(int i=n-1;i>=0;i=i-1)
	{
		cout<<(*pintVec)[i];
		if(i!=0) cout<<" ";
		else cout<<endl;
	}
	}
}