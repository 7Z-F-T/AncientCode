#include <iostream>
#include "data1.h"
#include "data2.h"
#include "data3.h"
#include "data4.h"
#include "data5.h"

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
	int n=4000;
	bubble(dat1,n);
	bubble(dat2,n);
	bubble(dat3,n);
	bubble(dat4,n);
	bubble(dat5,n);
	return 0;
}