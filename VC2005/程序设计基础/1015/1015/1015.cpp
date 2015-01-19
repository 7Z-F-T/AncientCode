#include <iostream>
#include <string>
using namespace std;

int main()
{
    string input;
	string output;
	char col;//列
	char row;//行
	int i;
	int n;//棋盘阶数
	bool used[8][8];//前列后行
	for(int i=0;i<8;i++)
	{
		for(int j=0;j<8;j++)
			used[i][j]=0;
	}

	cin>>n;
	cin>>input;
	col=input[0];
	row=input[1];

	output+=col;
	output+=row;
	used[col-'a'][row-'1']=true;

	for(;;)
	{
		if(col<'a'+n-1&&used[col-'a'+1][row-'1']==false)
		{
			used[col-'a'+1][row-'1']=true;
			col++;
			output+='-';
			output+=col;
			output+=row;
		}
		else if(row<'1'+n-1&&used[col-'a'][row-'1'+1]==false)
		{
			used[col-'a'][row-'1'+1]=true;
			row++;
			output+='-';
			output+=col;
			output+=row;
		}
		else if(row>'1'&&used[col-'a'][row-'1'-1]==false)
		{
			used[col-'a'][row-'1'-1]=true;
			row--;
			output+='-';
			output+=col;
			output+=row;
		}
		else if(col>'a'&&used[col-'a'-1][row-'1']==false)
		{
			used[col-'a'-1][row-'1']=true;
			col--;
			output+='-';
			output+=col;
			output+=row;
		}
		else break;

	}
    if(output.size()<=40)
		cout<<output;
	else
	{
		for(i=0;i<20;i++)
		    cout<<output[i];
		for(;i<output.size()-20;i++){}
		cout<<"...";
		for(;i<output.size();i++)
			cout<<output[i];
	}
	   

}