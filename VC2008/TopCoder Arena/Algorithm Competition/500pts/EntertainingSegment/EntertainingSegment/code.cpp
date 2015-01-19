#include <iostream>
#include <vector>
using namespace std;

class EntertainingSegment
{
public:
	int longestEntertainingSegment(vector <int> left, vector <int> right, int k)
	{
		vector<int>::iterator it1,it2;
		it1=right.end();
		it1--;
		int size=*it1;
		vector<int> road(size-1,0);

        it1=left.begin();it2=right.begin();
		while(it1<left.end()&&it2<right.end())
		{
			for(int i=*it1-1;i<=*it2-2;i++)
				road[i]++;
			it1++;
			it2++;
		}
		
		int Length=0;
		int tempLength=0;
		for(it1=road.begin();it1<road.end();it1++)
		{
			if(*it1>=k)
				tempLength++;
			else
			{
				if(tempLength>Length) Length=tempLength;
				tempLength=0;
			}
		}
		if(tempLength>Length) Length=tempLength;
		


		


		return Length;
	}
};

int main()
{
	vector<int> vec1,vec2;
	vec1.push_back(1);
	vec1.push_back(4);
	vec1.push_back(3);
	vec1.push_back(7);

	vec2.push_back(5);
	vec2.push_back(8);
	vec2.push_back(7);
	vec2.push_back(10);
	EntertainingSegment a;
	a.longestEntertainingSegment(vec1,vec2,2);

	return 0;
}