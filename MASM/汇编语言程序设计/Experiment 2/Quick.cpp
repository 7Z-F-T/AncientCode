#include <iostream>

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
	int dat[]={16,4,92,35,16,72,34,64,77,1,19,36,26,92,11,62,34,23,76,30,34,72,13,65,33,75,34,23,53,43};
	int left=0,right=29;
	Quick(dat,left,right);
	return 0;
}

