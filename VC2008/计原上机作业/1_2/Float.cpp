#include <stdio.h>
#include <math.h>
#include <stdlib.h>

void getFraction(float temp_fraction,int* fraction){
	for(int i=0;i<23;i++){
		temp_fraction=temp_fraction*2;
		if(temp_fraction>=1){
			temp_fraction--;
			fraction[i]=1;
		}
		else
			fraction[i]=0;
	}
}
void getExponent(int temp_exponent,int* exponent){
	temp_exponent=temp_exponent+127;
	for(int i=7;i>=0;i--){
		int remaining=temp_exponent%2;
		temp_exponent=temp_exponent/2;
		if(remaining==1)
			exponent[i]=1;
		else
			exponent[i]=0;
	}
}
void combine(int sign, int* fraction, int* exponent, char* result_string){
	int i=0;
	result_string[0]=sign+48;
	i++;
	for(int j=0;j<8;j++){
		result_string[i]=exponent[j]+48;
	    i++;
	}
	for(int j=0;j<23;j++){
		result_string[i]=fraction[j]+48;
		i++;
	}
}
unsigned int getResult(char* result_string){
	unsigned int temp=1;
	unsigned int result=0;
	for(int i=31;i>=0;i--){
		unsigned int temp2=(unsigned int)(result_string[i]-48);
		result=result+((unsigned int)(result_string[i]-48))*temp;
		temp*=2;
	}
	return result;
}

int main(){
    float f;
	int sign;
	int exponent[8];
	int fraction[23];
	char result_string[32];
	unsigned int result=0;

	//input
	scanf("%f",&f);
	//get the sign
	if(f<0)
		sign=1;
	else
		sign=0;
	//get the fraction and exponent
	float temp_fraction=f;
	int temp_exponent=0;
    temp_fraction=abs(temp_fraction);
	while(temp_fraction>=2){
		temp_fraction=temp_fraction/2;
		temp_exponent++;
	}
	while(temp_fraction<1){
		temp_fraction=temp_fraction*2;
		temp_exponent--;
	}
    temp_fraction--;
	getFraction(temp_fraction,fraction);
	getExponent(temp_exponent,exponent);
    combine(sign,fraction,exponent,result_string);
	result=getResult(result_string);
	//output
	printf("%x",result);
	
	/*int* pi = (int*)(&f);
	puts("Coding Result by Machine");
	printf("%x",*pi);*/




	return 0;
}