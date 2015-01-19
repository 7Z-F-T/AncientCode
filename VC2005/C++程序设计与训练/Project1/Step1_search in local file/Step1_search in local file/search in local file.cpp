
#include "head.h"



int main(int argc,char* argv[])
{
	if(argc!=2) {cout<<"--Wrong command format£¡--";return -1;}
	char* filename=argv[1];
		
    if(GetReady(filename))
    cout<<"Index ready!"<<endl;

    string input;
	cout<<"--Please input words or strings to search(type exit to quit)--:"<<endl;
	getline(cin,input,'\n');
	while(input!="exit")
		Search(input);




	












	return 0;
}

