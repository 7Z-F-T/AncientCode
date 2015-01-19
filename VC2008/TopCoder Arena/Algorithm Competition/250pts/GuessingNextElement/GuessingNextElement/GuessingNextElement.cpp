#include <iostream>
#include <vector>
using namespace std;

class GuessingNextElement 
{
public:
	int guess(vector <int> A)
	{
		if((A[1]-A[0])==(A[2]-A[1]))
			return(A[A.size()-1]+(A[1]-A[0]));
		else if((A[1]/A[0])==(A[2]/A[1]))
            return(A[A.size()-1]*(A[1]/A[0]));
	}
};
