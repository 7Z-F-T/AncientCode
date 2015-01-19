#include <iostream>

void bubble(int dat[],int n){//由大到小排序,n为操作数个数
	if(n==1) return;
	for(int i=0;i<=n-2;i++){
		if(dat[i]<dat[i+1]){
			int temp=dat[i+1];
			dat[i+1]=dat[i];
			dat[i]=temp;
		}
	}
	bubble(dat,n-1);
}

int main(){
	int dat[30]={16,4,92,35,16,72,34,64,77,1,19,36,26,92,11,62,34,23,76,30,34,72,13,65,33,75,34,23,53,43};
	int n=30;
	bubble(dat,n);
	return 0;
}