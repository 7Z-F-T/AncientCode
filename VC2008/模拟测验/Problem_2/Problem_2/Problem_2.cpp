#include <iostream>
using namespace std;

int main()
{
    int A,B,C,D;//0:未作案 1:作案
	int count=0;

	for(A=0;A<=1;A++)
		for(B=0;B<=1;B++)
			for(C=0;C<=1;C++)
				for(D=0;D<=1;D++)
				{
					count=0;
					if(A==1||B==1) count++;
					if(B==1||C==1) count++;
					if(C==1||D==1) count++;
					if(A==0||C==0) count++;
					if(B==0||D==0) count++;
					
					if(count==5)
					{
						cout<<A<<endl;
						cout<<B<<endl;
						cout<<C<<endl;
						cout<<D<<endl;
					}
				}

	return 0;
}