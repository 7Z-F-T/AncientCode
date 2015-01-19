//-------------------------------------
//���������������ע��ͬһ��������ѡ�ö��

#include <iostream>
#include <vector>
#include <math.h>
using namespace std;

vector<int> PrimeNum;
vector<double> GeneratedNum;
vector<int> select;

bool isPrimeNum(int n)
{
	float k=sqrt((float)n);
	for(int i=2;i<=k;i++)
		if(n%i==0) return false;
	return true;

}

void findPrimeNum(int B)//find all prime numbers that <=B
{
	for(int i=2;i<=B;i++)
		if(isPrimeNum(i))
			PrimeNum.push_back(i);
}
double Multiply()
{
	double Mult=1;
	for(int i=0;i<select.size();i++)
		Mult=Mult*select[i];
	return Mult;
}
void recursion(int start)
{
	if(start>PrimeNum.size()-1) return;
	for(int i=start;i<=PrimeNum.size()-1;i++)
	{
		select.push_back(PrimeNum[i]);
		GeneratedNum.push_back(Multiply());
		recursion(i+1);
		select.pop_back();

	}
}

void Generate()//�������п��ܲ����ĳ˻�
{
	recursion(0);
}
int main()
{	
	int B;
	cin>>B;
	findPrimeNum(B);//find all prime numbers that <=B.Stored in PrimeNum[]
	Generate();//�������п��ܲ����ĳ˻�.Stored in GeneratedNum[]
    

	return 0;
}