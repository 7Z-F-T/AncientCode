#include <vector>
#include <iostream>
#pragma comment(lib, "ReversePrint.lib")
using namespace std;
const int N=10;
void ReversePrint(vector<int> *pintVec);

int main()
{
    vector<int> intArray(N);
	cout<<"������10���������Կո����"<<endl;
	for(int i=0;i<N;i++)
	    cin>>intArray[i];
	ReversePrint(&intArray);//ע�⣡��ͨ������a��a[n]���׵�ַ��
	                        //��vector�Ͷ�̬������&intArray��intArray[n]���׵�ַ��
	return 0;
}