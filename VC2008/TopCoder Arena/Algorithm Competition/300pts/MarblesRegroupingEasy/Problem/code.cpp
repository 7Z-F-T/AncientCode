#include <iostream>
#include <vector>
#include <string>
using namespace std;

class MarblesRegroupingEasy
{
public:
	int minMoves(vector <string> boxes)
	{
		vector<vector<int> > matrix(boxes.size(),boxes[0].size());
		int i,j;
		for(i=0;i<boxes.size();i++)
			for(j=0;j<boxes[i].size();j++)
			    if(boxes[i][j]!='0') matrix[i][j]=1;

		//select the row that includes least '0' as the joker box row
		int jokerRow=0;
		int sum,sumMax=0;
		for(i=0;i<matrix.size();i++)
		{
			sum=0;
			for(j=0;j<matrix[i].size();j++)
				sum+=matrix[i][j];
			if(sum>sumMax) 
			{
				jokerRow=i;
				sumMax=sum;
			}
		}
		for(j=0;j<matrix[jokerRow].size();j++)
			matrix[jokerRow][j]=0;//clear the jokerRow


		int Moves=0;
		//search the rest rows, if any row includes more than one '1' ,then clear the row
		//and let Moves++
		for(i=0;i<matrix.size();i++)
	    {
			sum=0;
			for(j=0;j<matrix[0].size();j++)
			    sum+=matrix[i][j];
			if(sum>1)
			{
				for(j=0;j<matrix[i].size();j++)
					matrix[i][j]=0;
				Moves++;
			}
		}

		//search the columns, if any column includes more than one '1' ,then clear the column
		//and let Moves++
		for(j=0;j<matrix[0].size();j++)
	    {
			sum=0;
			for(i=0;i<matrix.size();i++)
			    sum+=matrix[i][j];
			if(sum>1)
			{
				for(i=0;i<matrix.size();i++)
					matrix[i][j]=0;
				Moves++;
			}
		}

		return Moves;
	}
};