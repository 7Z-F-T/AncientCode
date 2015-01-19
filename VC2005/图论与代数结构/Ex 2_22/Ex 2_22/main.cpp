#include "head.h"
vector<edge> take,takeResult;//存储边
int size;//矩阵阶数
vector<edge> data;
void OKtry(int i,int start);
int output(vector<edge> &take);
int wayResult=9999999999;//结果


int main(int argc,char* argv[])
{
	if(argc!=2)
	{
		cout<<"命令输入错误！";
		return -1;
	}
	char* filename=argv[1];
	ifstream fin(filename);
	if(!fin)
	{
		cout<<"无法打开文件！";
		return -1;
	}
    string temp;


	//文件内容开始入内存
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

	//开始分支与界

    OKtry(1,0);
	for(int i=1;i<=size;i++)
		cout<<takeResult[i].head<<takeResult[i].tail<<" ";
	cout<<endl;
	cout<<wayResult<<endl;





	return 0;
}

void OKtry(int i,int start)//寻找路径
{
	for(int j=start;j<=data.size()-1;j++)
	{
		take[i]=data[j];
		if(i==size)
			output(take);
		else OKtry(i+1,j+1);
	}
}

int output(vector<edge> &take)//判断路径是否有效并输出至结果变量
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

	

	  
		