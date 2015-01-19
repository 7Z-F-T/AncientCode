#include <iostream>
using namespace std;

int main()
{
	char shooter;
	int count;
	bool FoundShooter=false;
	int n;
	cin>>n;

	//start
	for(shooter='A';shooter<='H';shooter++)
	{
	  count=0;

	  if(shooter=='H'||shooter=='F') count++;//A
	  if(shooter=='B') count++;//B
	  if(shooter=='G') count++;//C
	  if(shooter!='B') count++;//D
	  if(shooter!='H'&&shooter!='F') count++;//E
      if(shooter!='F'&&shooter!='H') count++;//F
	  if(shooter!='C') count++;//G
      if(shooter=='H'||shooter=='F') count++;//H

	  if(count==n) {cout<<shooter;FoundShooter=true;}
	}
	if(FoundShooter==false) cout<<"DONTKNOW";

	return 0;
}