#include <iostream>
#include <vector>
#include <string>
using namespace std;

int min(int a,int b);

int main()
{
	int num1,num2;//人员的数目
	int numMIN;
	vector<string> row1,row2,row;
	string str;
	cin>>num1;
	getline(cin,str);
	for(int i=0;i<num1;i++)
	{
        getline(cin,str);
		row1.push_back(str);
	}
	cin>>num2;
	getline(cin,str);
	for(int i=0;i<num2;i++)
	{
		getline(cin,str);
		row2.push_back(str);
	}

	numMIN=min(num1,num2);
	vector<string>::iterator it1=row1.begin();
	vector<string>::iterator it2=row2.begin();
	for(int i=0;i<numMIN;i++)
	{
        row.push_back(*it1);
		row.push_back(*it2);
		it1++;
		it2++;
	}
	if(numMIN==num2)
	{
		for(;it1<row1.end();it1++)
			row.push_back(*it1);
	}
	else 
	{
		for(;it2<row2.end();it2++)
			row.push_back(*it2);
	}

	for(vector<string>::iterator it=row.begin();it<row.end();it++)
	{
		cout<<*it<<endl;
	}

return 0;

}

int min(int a,int b)
{
	if(a<=b) return a;
	else return b;
}