#include "head.h"

int size;//矩阵阶数，即节点个数
vector<edge> alledge;//存储所有边
vector<edge> selectededge;//存储所选树的边

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


	for(int j=1;j<=size;j++)
	{
		for(int i=1;i<=j;i++)
			fin>>temp;
		for(int i=j+1;i<=size;i++)
		{
			fin>>temp;
			if(atoi(temp.c_str())!=0)
			    alledge.push_back(edge(j,i,atoi(temp.c_str())));
		}
	}

	sort(alledge.begin(),alledge.end(),sortedge());

	selectededge.resize(0);
	vector<edge>::reverse_iterator it=alledge.rbegin();
	while(selectededge.size()<size-1)
	{
		selectededge.push_back(*it);
		alledge.pop_back();
		if(isLoop(selectededge)==true)
			selectededge.pop_back();
		it=alledge.rbegin();
	}

	for(vector<edge>::iterator it=selectededge.begin();it<selectededge.end();it++)
	{
		cout<<it->head<<it->tail<<" ";
	}

return 0;

}

bool isLoop(vector<edge> &selectededge)//判断是否出现了回路
{
	int count=0;
	for(int i=1;i<=size;i++)
	{
		for(vector<edge>::iterator it=selectededge.begin();it<selectededge.end();it++)
		{
			if(it->head==i) count++;
			if(it->tail==i) count++;
		}
		if(count==1) return false;//存在某个点只用了一次<=>不存在回路
		else count=0;
	}
	return true;
}