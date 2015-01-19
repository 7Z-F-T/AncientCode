#include <iostream>

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
	int dat[]={16,4,92,35,16,72,34,64,77,1,19,36,26,92,11,62,34,23,76,30,34,72,13,65,33,75,34,23,53,43};
	int left=0,right=29;
	Quick(dat,left,right);
	return 0;
}

