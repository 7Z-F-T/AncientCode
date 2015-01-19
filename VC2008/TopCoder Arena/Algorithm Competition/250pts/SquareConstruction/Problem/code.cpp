#include <iostream>
#include <string>
#include <sstream>
#include <vector>
using namespace std;

class SquareConstruction
{
public:
    vector <string> construct(int N, int a, int b, int c, int d)
	{
		vector<string> strVec;
        int square[20][20];
        memset(square,-1,400*sizeof(int));
		int num=1;
		int end=0;
		square[0][0]=num;
		int xpos=0;
		int ypos=0;
		//start algorithm
		//part 1
		while(end!=1)
		{
		ypos+=a;
		if(ypos>=N) ypos-=N;
		xpos+=b;
		if(xpos>=N) xpos-=N;
		if(square[ypos][xpos]==-1)
             square[ypos][xpos]=++num;
		//part 2
		else{
			ypos+=c;
			if(ypos>=N) ypos-=N;
			xpos+=d;
			if(xpos>=N) xpos-=N;
			if(square[ypos][xpos]==-1)
				 square[ypos][xpos]=++num;
			else end=1;
		    }
		}
		//save data to str
		for(int i=0;i<N;i++)
		{
			stringstream ss;
			for(int j=0;j<N;j++)
			{
				if(j!=0) ss<<' ';
				ss<<square[i][j];
			}
			strVec.push_back(ss.str());
		}




		return strVec;
	}
};