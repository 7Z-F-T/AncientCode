#include <iostream>
#include <string>
#include <stack>
using namespace std;




class BlockCounter
{
public:
	stack<char> s;
    void Change(stack<char> &s)//intended to compile contents between ( and )
	{
		string str;
		int repetition;
		s.pop();//delete )
		do{
			str.push_back(s.top());
			s.pop();
		}while(s.top()!=',');
		s.pop();//delete ,
		repetition=s.top()-48;//char to int
		s.pop();//delete the number
		s.pop();//delete (

        string::reverse_iterator rit; 
		for(int i=1;i<=repetition;i++)
		{
		    for(rit=str.rbegin();rit<str.rend();rit++)
		    {
				s.push(*rit);
			}
		}
	}

	long long countBlocks(string word)
	{
		for(string::iterator it=word.begin();it<word.end();it++)
		{
			s.push(*it);
			if(s.top()==')') Change(s);//start to change the stack when ) comes
		}
        
		//now begin to count blocks
		int count=1;
        char temp=s.top();
		s.pop();
		while(s.empty()==false)
		{
			if(s.top()!=temp) 
			{
				count++;
				temp=s.top();
				s.pop();
			}
			else 
			{
				s.pop();
			}
		}
		return count;
	}
};


int main()
{ 
	BlockCounter x;
	x.countBlocks("ABA(2,ABA)(3,ABA)");

	return 0;
}