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
int main()
{
	string P;
	cin>>P;
	int* next=new int[P.size()];
	getNext(P,next);
	string T;
	cin>>T;
	cout<<KMPfind(T,P,0,next);
    delete []next;
}