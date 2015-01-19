#include <iostream>
#include <fstream>
using namespace std;
const int MINVAL=-99999999;
const int MAXVAL=99999999;

int main(int argc,char* argv[])
{
	if(argc!=2) {cout<<"Input Error!";return -1;}
	char* file=argv[1];
	ifstream fin(file);
	if(!fin) {cout<<"Cannot Open File!";return -1;}

	//read the matrix from file
	int m,n;
	fin>>m>>n;
	int** A=new int*[m];
	for(int i=0;i<m;i++)
		A[i]=new int[n];
	for(int i=0;i<m;i++)
		for(int j=0;j<n;j++)
			fin>>A[i][j];
    //find the max value of each row and column
	int* rowMin=new int[m];
	int* columnMax=new int[n];
	for(int i=0;i<m;i++)
		rowMin[i]=MAXVAL;
	for(int i=0;i<n;i++)
		columnMax[i]=MINVAL;
	for(int i=0;i<m;i++)
		for(int j=0;j<n;j++)
		{
			if(A[i][j]<rowMin[i])
				rowMin[i]=A[i][j];
		}
	for(int j=0;j<n;j++)
		for(int i=0;i<m;i++)
		{
			if(A[i][j]>columnMax[j])
				columnMax[j]=A[i][j];
		}

    for(int i=0;i<m;i++)
		for(int j=0;j<n;j++)
		{
			if(A[i][j]==rowMin[i]&&A[i][j]==columnMax[j]) 
				cout<<"row:"<<i+1<<" column:"<<j+1<<endl;
		}




  
    return 0;
}