#include <iostream>
using namespace std;

void copy_array(double* P1, double* P2){//copy P2 to P1
	for(int i = 0; i < 4; i++)
		P1[i] = P2[i];
}

void iteration(double* H, double* A){
	A[0] = H[3];
	A[1] = H[0];
	A[2] = H[0];
	A[3] = H[1] + H[2];

	H[0] = A[1]+A[2];
	H[1] = A[3];
	H[2] = A[3];
	H[3] = A[0];

	double A_sum = 0, H_sum = 0; 
	for(int i = 0; i < 4; i++){
		A_sum += A[i];
		H_sum += H[i];
	}
	for(int i = 0; i < 4; i++){
		A[i] /= A_sum;
		H[i] /= H_sum;
	}
}

double difference(double* P1, double* P2){
	double diff_sum = 0;
	for(int i = 0; i < 4; i++)
		diff_sum += (P1[i] - P2[i]) * (P1[i] - P2[i]);
	return diff_sum;
}


int main(){
	double H[4] = {0.25,0.25,0.25,0.25};
	double A[4] = {0.25,0.25,0.25,0.25};
	double prevH[4] = {0.25,0.25,0.25,0.25};
	double prevA[4] = {0.25,0.25,0.25,0.25};
	
	for(;;){
		iteration(H, A);
		//cout<<"difference H "<<difference(H, prevH)<<endl;
		//cout<<"difference A "<<difference(A, prevA)<<endl;
		if(difference(H, prevH) > 0.0000001 || difference(A, prevA) > 0.0000001){
			copy_array(prevH, H);
			copy_array(prevA, A);
		}
		else 
			break;
	}
	for (int i = 0; i < 4; i++)
		cout<<"H("<<(char)('A'+i)<<") = "<<H[i]<<endl;
	cout<<endl;
	for (int i = 0; i < 4; i++)
		cout<<"A("<<(char)('A'+i)<<") = "<<A[i]<<endl;

	return 0;
}