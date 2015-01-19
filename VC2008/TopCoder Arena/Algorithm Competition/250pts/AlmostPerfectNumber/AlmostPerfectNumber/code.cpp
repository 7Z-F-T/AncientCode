#include <iostream>
using namespace std;

class AlmostPerfectNumber
{
public:
    static int count(int left, int right, int k)
	{
		int sum=0;
		int count=0;
		for(int i=left;i<=right;i++)
		{
			for(int j=1;j<i;j++)
			{
				if(i%j==0)
					sum+=j;
			}
			if(i-sum<=k) count++;
			sum=0;
		}
		return count;
	}
};

int main()
{
	cout<<AlmostPerfectNumber::count(5,5,5);
	return 0;
}
	

