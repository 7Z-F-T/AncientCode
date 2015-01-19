#include <iostream>
#include "data1.h"
#include "data2.h"
#include "data3.h"
#include "data4.h"
#include "data5.h"

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
	int n=4000;
	Select(dat1,n);
	Select(dat2,n);
	Select(dat3,n);
	Select(dat4,n);
	Select(dat5,n);
	return 0;
}