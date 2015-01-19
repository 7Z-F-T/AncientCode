#include <iostream>
#include <string>
using namespace std;

string number[30]={"","one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty", "twenty-one", "twenty-two", "twenty-three", "twenty-four", "twenty-five", "twenty-six", "twenty-seven", "twenty-eight", "twenty-nine"};
int main()
{
    int A,B;
loop:cin>>A>>B;

	void output1(int a,int b);
	void output2(int a,int b);
	void output3(int a,int b);
	void output4(int a,int b);
	void output5(int a,int b);
	void output6(int a,int b);
	
	if(B==0) output6(A,B);
	else if(B==15) output4(A,B);
    else if(B==30) output3(A,B);
	else if(B==45) output5(A,B);
	else if(B>0&&B<30) output1(A,B);
	else if(B>30&&B<60) output2(A,B);
	
    goto loop;
	return 0;
}

void output1(int a,int b)
{
	cout<<"It is "<<number[b]<<" past "<<number[a]<<"."<<endl;
}
void output2(int a,int b)
{
    cout<<"It is "<<number[60-b]<<" to "<<number[a+1]<<"."<<endl;
}
void output3(int a,int b)
{
    cout<<"It is half past "<<number[a]<<"."<<endl;
}
void output4(int a,int b)
{
    cout<<"It is a quarter past "<<number[a]<<"."<<endl;
}
void output5(int a,int b)
{
    cout<<"It is a quarter to "<<number[a+1]<<"."<<endl;
}
void output6(int a,int b)
{
    cout<<"It is "<<number[a]<<" o'clock."<<endl;
}