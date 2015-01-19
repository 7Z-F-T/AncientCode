#include <iostream>
using namespace std;

void printout(char* str, int n, int* M){
	cout<<str<<"\t";
	for(int i=0; i<n; i++)
		cout<<M[i]<<" ";
	cout<<endl;
}


void P10(int* KEY){
	int key[10];
	memcpy(key,KEY,sizeof(int)*10);
	KEY[1-1] = key[3-1];
	KEY[2-1] = key[5-1];
	KEY[3-1] = key[2-1];
	KEY[4-1] = key[7-1];
	KEY[5-1] = key[4-1];
	KEY[6-1] = key[10-1];
	KEY[7-1] = key[1-1];
	KEY[8-1] = key[9-1];
	KEY[9-1] = key[8-1];
	KEY[10-1] = key[6-1];
}

void LS1(int* KEY){
	int key[5];
	memcpy(key,KEY,sizeof(int)*5);
	KEY[1-1] = key[2-1];
	KEY[2-1] = key[3-1];
	KEY[3-1] = key[4-1];
	KEY[4-1] = key[5-1];
	KEY[5-1] = key[1-1];
}

void LS2(int* KEY){
	int key[5];
	memcpy(key,KEY,sizeof(int)*5);
	KEY[1-1] = key[3-1];
	KEY[2-1] = key[4-1];
	KEY[3-1] = key[5-1];
	KEY[4-1] = key[1-1];
	KEY[5-1] = key[2-1];
}

void P8(int* KEY, int* K){
	K[1-1]=KEY[6-1];
	K[2-1]=KEY[3-1];
	K[3-1]=KEY[7-1];
	K[4-1]=KEY[4-1];
	K[5-1]=KEY[8-1];
	K[6-1]=KEY[5-1];
	K[7-1]=KEY[10-1];
	K[8-1]=KEY[9-1];
}

void IP(int* KEY){
	int key[8];
	memcpy(key,KEY,sizeof(int)*8);
	KEY[1-1] = key[2-1];
	KEY[2-1] = key[6-1];
	KEY[3-1] = key[3-1];
	KEY[4-1] = key[1-1];
	KEY[5-1] = key[4-1];
	KEY[6-1] = key[8-1];
	KEY[7-1] = key[5-1];
	KEY[8-1] = key[7-1];
	printout("After IP : ", 8, KEY);
}

void IPR(int* KEY){
	int key[8];
	memcpy(key,KEY,sizeof(int)*8);
	KEY[1-1] = key[4-1];
	KEY[2-1] = key[1-1];
	KEY[3-1] = key[3-1];
	KEY[4-1] = key[5-1];
	KEY[5-1] = key[7-1];
	KEY[6-1] = key[2-1];
	KEY[7-1] = key[8-1];
	KEY[8-1] = key[6-1];
	printout("After IPR : ", 8, KEY);
}

void FK(int* IN, int* K){
	int L[4];
	int R[4];
	memcpy(L,IN,sizeof(int)*4);
	memcpy(R,IN+4,sizeof(int)*4);
	//EP
	int T[8];
	T[1-1] = R[4-1];
	T[2-1] = R[1-1];
	T[3-1] = R[2-1];
	T[4-1] = R[3-1];
	T[5-1] = R[2-1];
	T[6-1] = R[3-1];
	T[7-1] = R[4-1];
	T[8-1] = R[1-1];
	//XOR
	for(int i = 0; i < 8; i++)
		T[i] = T[i] ^ K[i];
	//S0,S1
	int T1[4];
	int T2[4];
	int T11[2];
	int T22[2];
	memcpy(T1,T,sizeof(int)*4);
	memcpy(T2,T+4,sizeof(int)*4);

	int S0[4][4] = {{1,0,3,2}, {3,2,1,0}, {0,2,1,3}, {3,1,3,2}};
	int S1[4][4] = {{0,1,2,3}, {2,0,1,3}, {3,0,1,0}, {2,1,0,3}};

	int m,n,r;

	m = T1[0]*2 + T1[3];
	n = T1[1]*2 + T1[2];
	r = S0[m][n];
	T11[0] = r/2;
	T11[1] = r%2;

	m = T2[0]*2 + T2[3];
	n = T2[1]*2 + T2[2];
	r = S1[m][n];
	T22[0] = r/2;
	T22[1] = r%2;

	int T3[4];
	memcpy(T3,T11,sizeof(int)*2);
	memcpy(T3+2,T22,sizeof(int)*2);

	//P4
	int T4[4];
	T4[1-1] = T3[2-1];
	T4[2-1] = T3[4-1];
	T4[3-1] = T3[3-1];
	T4[4-1] = T3[1-1];

	//XOR
	for(int i = 0; i < 4; i++)
		T4[i] = T4[i] ^ L[i];

	memcpy(IN,T4,sizeof(int)*4);
	memcpy(IN+4,R,sizeof(int)*4);
	printout("After FK : ", 8, IN);
}

void SW(int* IN){
	int T[8];
	memcpy(T,IN,sizeof(int)*8);
	memcpy(IN,T+4,sizeof(int)*4);
	memcpy(IN+4,T,sizeof(int)*4);
	printout("After SW : ", 8, IN);
}

int main(){

	int KEY[10] = {0,1,1,1,1,1,1,1,0,1};//KEY

	//Prepare K1 and K2
	int KEYbackup[10];
	memcpy(KEYbackup, KEY, sizeof(int)*10);
	P10(KEY);
	LS1(KEY);
	LS1(KEY+5);
	int K1[8];
	P8(KEY,K1);//get K1
	LS2(KEY);
	LS2(KEY+5);
	int K2[8];
	P8(KEY,K2);//get K2

	//Encrypt MSG
	
	/*int MSG[8] = {1,1,1,0,1,0,1,0};
	cout<<"Begin Crypting"<<endl;
	printout("Message : ", 8, MSG);
	printout("Key : \t", 10, KEYbackup);
	printout("K1 : \t", 8, K1);
	printout("K2 : \t", 8, K2);
	IP(MSG);
	FK(MSG, K1);
	SW(MSG);
	FK(MSG, K2);
	IPR(MSG);
	printout("Cipher : ", 8, MSG);*/
	

	//Decrypt MSG
	int MSG[8] = {1,0,1,0,0,0,1,0};
	cout<<"Begin Decrypting"<<endl;
	printout("Cipher : ", 8, MSG);
	printout("Key : \t", 10, KEYbackup);
	printout("K2 : \t", 8, K2);
	printout("K1 : \t", 8, K1);
	IP(MSG);
	FK(MSG, K2);
	SW(MSG);
	FK(MSG, K1);
	IPR(MSG);
	printout("Message : ", 8, MSG);

	return 0;
}