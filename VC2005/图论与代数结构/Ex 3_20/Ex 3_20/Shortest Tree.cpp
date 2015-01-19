#include "head.h"

int size;//������������ڵ����
vector<edge> alledge;//�洢���б�
vector<edge> selectededge;//�洢��ѡ���ı�

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

bool isLoop(vector<edge> &selectededge)//�ж��Ƿ�����˻�·
{
	int count=0;
	for(int i=1;i<=size;i++)
	{
		for(vector<edge>::iterator it=selectededge.begin();it<selectededge.end();it++)
		{
			if(it->head==i) count++;
			if(it->tail==i) count++;
		}
		if(count==1) return false;//����ĳ����ֻ����һ��<=>�����ڻ�·
		else count=0;
	}
	return true;
}