#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main()
{
	int a[]={1,5,6,2,3,9};
	int Na=6;

	int b[]={5,7,5,3,5,9};
	int Nb=6;

	vector<int> ab;
	for(int i=0;i<Na;i++)
		ab.push_back(a[i]);
	for(int i=0;i<Nb;i++)
		ab.push_back(b[i]);
	sort(ab.begin(),ab.end());

	bool finished=false;

	//while(finished==false)
	//{
		for(int i=0;i<ab.size()-1;)
		{
			if(ab[i]==ab[i+1])
			{
				for(int j=i;j<ab.size()-1;j++)
					ab[j]=ab[j+1];
				ab.pop_back();
			}
			else
				i++;
		}
	//}

	return 0;//set breakpoint to pause and view ab here



}