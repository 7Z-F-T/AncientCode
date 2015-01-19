#include "head.h"
vector<edge> take,takeResult;//�洢��
int size;//�������
vector<edge> data;
void OKtry(int i,int start);
int output(vector<edge> &take);
int wayResult=9999999999;//���


int main(int argc,char* argv[])
{
	if(argc!=2)
	{
		cout<<"�����������";
		return -1;
	}
	char* filename=argv[1];
	ifstream fin(filename);
	if(!fin)
	{
		cout<<"�޷����ļ���";
		return -1;
	}
    string temp;


	//�ļ����ݿ�ʼ���ڴ�
	fin>>temp;
	size=atoi(temp.c_str());
	take.resize(size+1);
    
	for(int j=1;j<=size;j++)
	{
		for(int i=1;i<=j;i++)
			fin>>temp;
		for(int i=j+1;i<=size;i++)
		{
			fin>>temp;
            data.push_back(edge(j,i,atoi(temp.c_str())));
		}
	}

	sort(data.begin(),data.end(),sortedge());

	//��ʼ��֧���

    OKtry(1,0);
	for(int i=1;i<=size;i++)
		cout<<takeResult[i].head<<takeResult[i].tail<<" ";
	cout<<endl;
	cout<<wayResult<<endl;





	return 0;
}

void OKtry(int i,int start)//Ѱ��·��
{
	for(int j=start;j<=data.size()-1;j++)
	{
		take[i]=data[j];
		if(i==size)
			output(take);
		else OKtry(i+1,j+1);
	}
}

int output(vector<edge> &take)//�ж�·���Ƿ���Ч��������������
{
	int count=0;
	int sum=0;
	for(int j=1;j<=size;j++)
	{
		for(int i=1;i<=size;i++)
		{
			if(take[i].head==j)
			count++;
			if(take[i].tail==j)
			count++;
		}
		if(count!=2) return 0;
		count=0;
	}
	for(int i=1;i<=size;i++)
		sum=sum+take[i].valve;
	if(sum<wayResult) {wayResult=sum;takeResult=take;}
}

	

	  
		