#include <iostream>
#include <fstream>
using namespace std;

struct ints
{
	int x;
	int y;
};

void swap(int* p1,int* p2)
{
	int temp;
	temp=*p2;
	*p2=*p1;
	*p1=temp;
}

int main()
{
	ifstream fin("swap.in");
	if(!fin)
	{
		cout<<"failed to open file!";
		return -1;
	}
    int data[11][101];
	ints move[11][101];
	int t;//组数
    int N[11];//每组中数据的个数
	int M[11];//每组中操作的个数

	fin>>t;
    //read
	for(int i=1;i<=t;i++)
	{
        fin>>N[i]>>M[i];
		for(int j=1;j<=N[i];j++)
			fin>>data[i][j];
		for(int j=1;j<=M[i];j++)
		{
			fin>>move[i][j].x;
			fin>>move[i][j].y;
		}
	}

	//move
	for(int i=1;i<=t;i++)
	{
		for(int j=1;j<=M[i];j++)
		    swap(&data[i][move[i][j].x],&data[i][move[i][j].y]);

	}
    
	//output
	ofstream fout("swap.out");
		if(!fout)
		{
			cout<<"failed to create file!";
			return -1;
		}

	for(int i=1;i<=t;i++)
	{
		for(int j=1;j<=N[i];j++)
			fout<<data[i][j]<<" ";
		fout<<endl;

	}

	fout.close();



	return 0;
	
}