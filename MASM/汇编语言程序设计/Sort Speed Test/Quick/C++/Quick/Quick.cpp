#include <iostream>
#include "data1.h"
#include "data2.h"
#include "data3.h"
#include "data4.h"
#include "data5.h"

void Quick(int dat[] ,int left, int right){
	//首先根据首元素大小划分左右序列
	if(left>=right) return;
	int pivotPos=left;
	int pivot=dat[left];//取最左端元素为基准元素进行划分,大的放左边，小的放右边
	for(int i=left+1;i<=right;i++){
		if(dat[i]>pivot){
			pivotPos++;
			if(pivotPos!=i){
				int temp=dat[i];
				dat[i]=dat[pivotPos];
				dat[pivotPos]=temp;
			}
		}
	}
	dat[left]=dat[pivotPos];
	dat[pivotPos]=pivot;
	//划分结束，开始排序
	Quick(dat,left,pivotPos-1);
	Quick(dat,pivotPos+1,right);
}

int main(){
	int left=0,right=3999;
	Quick(dat1,left,right);
	Quick(dat2,left,right);
	Quick(dat3,left,right);
	Quick(dat4,left,right);
	Quick(dat5,left,right);
	return 0;
}

