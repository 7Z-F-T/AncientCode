#include <fstream>
#include <iostream>
using namespace std;

int main()
{
	ifstream fin("a.txt");
	if(!fin) cout<<"failed";
	char a;
	fin>>a;
	cout<<a;
}