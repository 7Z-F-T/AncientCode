#include <iostream>
#include "data1.h"
#include "data2.h"
#include "data3.h"
#include "data4.h"
#include "data5.h"

void Quick(int dat[] ,int left, int right){
	//���ȸ�����Ԫ�ش�С������������
	if(left>=right) return;
	int pivotPos=left;
	int pivot=dat[left];//ȡ�����Ԫ��Ϊ��׼Ԫ�ؽ��л���,��ķ���ߣ�С�ķ��ұ�
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
	//���ֽ�������ʼ����
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

