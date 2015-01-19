#include <iostream>
#include <string>
using namespace std;

//T: target string
//P: Pattern string

void getNext(string& P, int next[])
{
	int j=0,k=-1,lengthP=P.size();
	next[0]=-1;
	while(j<lengthP-1)
		if(k==-1||P[j]==P[k])
		{
			j++;k++;
			next[j]=k;
		}
		else k=next[k];
}

int KMPfind(string& T,string& P, int k, int next[])//search P from position k in T
{
	int posP=0,posT=k;
	int lengthP=P.size();
	int lengthT=T.size();
	while(posP<lengthP&&posT<lengthT)
		if(posP==-1||P[posP]==T[posT])
		{
			posP++;posT++;
		}
		else posP=next[posP];
		if(posP<lengthP) return -1;
		else return posT-lengthP;
}

void replace(string& s,string& t,string& v,int next[])
{
	int k=0;
	while(k<s.size())
	{
		k=KMPfind(s,t,k,next);
		if(k!=-1) 
		{
			s.erase(k,t.size());
			s.insert(k,v);
		}
		else return;
		k=k+v.size();
	}
}
int main()
{
    string s,t,v;
	cout<<"Please input s:"<<endl;
	cin>>s;
	cout<<"Please input t:"<<endl;
	cin>>t;
	cout<<"Please input v:"<<endl;
	cin>>v;
	int* next=new int[t.size()];
	getNext(t,next);
	replace(s,t,v,next);
	cout<<"After replacement,s becomes:"<<endl;
	cout<<s;
	delete []next;
}