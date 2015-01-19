//#include <iostream>
//using namespace std;
//
//void copy_array(double* P1, double* P2){//copy P2 to P1
//	for(int i = 0; i < 4; i++)
//		P1[i] = P2[i];
//}
//
//void iteration(double* P){
//	double tempP[4];
//	copy_array(tempP, P);
//	P[0] = 0.15/4 + 0.85 * tempP[3];
//	P[1] = 0.15/4 + 0.85 * 0.5 * tempP[0];
//	P[2] = 0.15/4 + 0.85 * 0.5 * tempP[0];
//	P[3] = 0.15/4 + 0.85 * (tempP[1] + tempP[2]);
//}
//
//double difference(double* P1, double* P2){
//	double diff_sum = 0;
//	for(int i = 0; i < 4; i++)
//		diff_sum += (P1[i] - P2[i]) * (P1[i] - P2[i]);
//	return diff_sum;
//}
//
//int main(){
//	double P[4]={1,1,1,1};//value of PageRank 
//	double prevP[4]={1,1,1,1};//value of PageRank at previous iteration
//	for(;;){
//		iteration(P);
//		//cout<<"difference P:"<<difference(P, prevP)<<endl;
//		if(difference(P, prevP) > 0.00000001)
//			copy_array(prevP, P);
//		else 
//			break;
//	}
//
//	for (int i = 0; i < 4; i++)
//		cout<<"P("<<(char)('A'+i)<<") = "<<P[i]<<endl;
//
//	return 0;
//
//}