#include <iostream>
#include <math.h>
using namespace std;

int main(){
	double M;
	int m;
	int k;
	for(k = 0; k<100; k++){
		M = pow(35 * k + 10, 0.2);
		m = (int)M;
		if(m == M)
			cout<<M<<endl;
	}

	return 0;
}