#include <iostream>
#include <string>
#include <vector>
using namespace std;

struct letter
{
	char ch;
	int valve;
};

int main()
{
	letter a[26];
	for(int i=0;i<26;i++)
	{
        a[i].ch=i+97;
		a[i].valve=0;
	}
	string inputstr;
	cin>>inputstr;
	string::iterator it=inputstr.begin();
	for(;it<inputstr.end();it++)
	{	 
		if(*it>='A'&&*it<='Z') *it=*it+32;
		if(*it>='a'&&*it<='z') 
		{
            a[*it-97].valve++;
		}
	}
	letter temp;
	//sort by valve
	for(int j=1;j<=25;j++)
		for(int i=0;i<=25-j;i++)
		{
             if(a[i+1].valve>a[i].valve)
			 {
				 temp=a[i+1];
				 a[i+1]=a[i];
				 a[i]=temp;
			 }
		}
    //output
	for(int i=0;i<26;i++)
		if(a[i].valve!=0)
			cout<<a[i].ch<<" "<<a[i].valve<<endl;
	

	return 0;
}