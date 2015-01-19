#include <iostream>
#include <vector>
using namespace std;

class MovieRating
{
public:
    double calculate(vector <int> marks, int lowCount, int highCount)
    {
             int size=marks.size();
             int temp=0;
			 double result=0;
             for(int j=0;j<=size-2;j++)
             {
                     for(int i=0;i<=size-2-j;i++)
                     {
                             if(marks[i]<marks[i+1])
                             {
                                     temp=marks[i+1];
                                     marks[i+1]=marks[i];
                                     marks[i]=temp;
                             }
                     }
             }
			 for(int i=highCount;i<marks.size()-lowCount;i++)
				 result=result+marks[i];


             return result/(marks.size()-highCount-lowCount);
     }
};



int main(int argc, char *argv[])
{
    vector<int> marks;
	marks.push_back(31);
	marks.push_back(52);
    marks.push_back(20);
	marks.push_back(86);
	marks.push_back(47);
	marks.push_back(76);
	marks.push_back(82);
	marks.push_back(27);
	marks.push_back(42);
	marks.push_back(29);

    MovieRating x;
    cout<<x.calculate(marks,1,4)<<endl;
    return 0;
}