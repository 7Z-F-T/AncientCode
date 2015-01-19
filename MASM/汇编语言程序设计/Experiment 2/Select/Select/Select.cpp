#include <iostream>

void Select(int dat[],int n){
	if(n==1) return;
	int minIndex=0;//此轮发现的最小的元素的下标
	for(int i=0;i<n;i++){
		if(dat[i]<dat[minIndex])
			minIndex=i;
	}
	if(minIndex!=n-1){
		int temp;
		temp=dat[n-1];
		dat[n-1]=dat[minIndex];
		dat[minIndex]=temp;
	}
	Select(dat,n-1);
}

int main(){
	int dat[]={16,4,92,35,16,72,34,64,77,1,19,36,26,92,11,62,34,23,76,30,34,72,13,65,33,75,34,23,53,43};
	int n=30;
	Select(dat,n);
	return 0;
}