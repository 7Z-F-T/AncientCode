#include <vector>
#include <iostream>
#pragma comment(lib, "ReversePrint.lib")
using namespace std;
const int N=10;
void ReversePrint(vector<int> *pintVec);

int main()
{
    vector<int> intArray(N);
	cout<<"请输入10个整数，以空格相隔"<<endl;
	for(int i=0;i<N;i++)
	    cin>>intArray[i];
	ReversePrint(&intArray);//注意！普通数组中a是a[n]的首地址，
	                        //而vector型动态数组中&intArray是intArray[n]的首地址！
	return 0;
}