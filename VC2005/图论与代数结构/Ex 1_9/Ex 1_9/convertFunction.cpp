#include "head.h"

void linjieTOguanlian()
{
	string inputrow;
	vector<node> nodes;
	vector<line> lines;
    //����
	string::iterator it;
	int num=1;//������������node��line���ʱ��
	int length=0;//�洢���󳤶�
	cout<<"�������ڽӾ����Իس����У���ע��ÿ���ַ�֮�䲻Ҫ����ո�"<<endl;
	getchar();
	getline(cin,inputrow,'\n');
	while(1)
	{
		if(length!=0&&inputrow.size()!=length) {cout<<"�������";exit(0);}
		nodes.push_back (node(num));
		int i=1;
		for(it=inputrow.begin();it<inputrow.end();it++)
		{
			if(*it==' ') continue;
			else if((*it)=='1') 
			{
				lines.push_back (line(lines.size()+1,num,i));
			    if(num==i) {cout<<"�벻Ҫ�����Ի���";exit(0);}
			}
			
			i++;
		}
		num++;
		length=inputrow.size();
		if(num>length) break;
		getline(cin,inputrow,'\n');
	}
    //���
	cout<<"��Ӧ��������Ϊ"<<endl;

	string outrow;
	for(vector<node>::iterator it1=nodes.begin ();it1<nodes.end();it1++)
	{
		for(vector<line>::iterator it2=lines.begin ();it2<lines.end();it2++)
		{
            if(it1->num==it2->start) outrow.append(" 1 ");
			else if(it1->num==it2->end) outrow.append("-1 ");
			else outrow.append(" 0 ");
		}
		cout<<outrow<<endl;
		outrow.clear();
	}
}

void guanlianTOlinjie()
{
	string inputrow;
	vector<node> nodes;
	vector<line> lines;
	//����
	cout<<"��������������Իس����в���Ϊ���������������ÿ���ַ�֮���ÿո�ֿ�"<<endl;
    getchar();
	string::iterator it;
	int num=1;//������������node��line���ʱ��
	getline(cin,inputrow,'\n');
	while(inputrow.size()!=0)
	{
		nodes.push_back (node(num));
		int count=0;//line�ĸ���
		for(it=inputrow.begin();it<inputrow.end();it++)
			if(*it=='1'||*it=='0') count++;
		lines.resize(count);
		int i=1;
		for(it=inputrow.begin();it<inputrow.end();it++)
		{

			if((*it)==' ') 
			{
				continue;
			}

			if((*it)=='0') 
			{
				i++;
			}

			if((*it)=='1') 
			{
				lines[i-1].num=i;
				lines[i-1].start=num;
				i++;
			}
			if((*it)=='-') 
			{
				lines[i-1].num=i;
				lines[i-1].end=num;
				i++;
				it++;
			}
		}
		num++;
		getline(cin,inputrow,'\n');
	}
	//���
	cout<<"��Ӧ�ڽӾ���Ϊ"<<endl;

	string outrow;
	bool found=false;//��lines���ҵ��ڵ����ڹ�ϵʱȡ��
	for(vector<node>::iterator it1=nodes.begin ();it1<nodes.end();it1++)
	{
		for(vector<node>::iterator it2=nodes.begin ();it2<nodes.end();it2++)
		{
			for(vector<line>::iterator it3=lines.begin();it3<lines.end();it3++)
			{
				if(it3->start==it1->num&&it3->end==it2->num) found=true;
			}
			if(found==true) cout<<"1 ";
			else cout<<"0 ";
			found=false;
		}
		cout<<endl;
		found=false;
	}
}

void guanlianTObianlie()
{
	string inputrow;
	vector<node> nodes;
	vector<line> lines;
	//����
	cout<<"��������������Իس����в���Ϊ���������������ÿ���ַ�֮���ÿո�ֿ�"<<endl;
	getchar();

	string::iterator it;
	int num=1;//������������node��line���ʱ��
	getline(cin,inputrow,'\n');
	while(inputrow.size()!=0)
	{
		nodes.push_back (node(num));
		int count=0;//line�ĸ���
		for(it=inputrow.begin();it<inputrow.end();it++)
			if(*it=='1'||*it=='0') count++;
		lines.resize(count);
		int i=1;
		for(it=inputrow.begin();it<inputrow.end();it++)
		{

			if((*it)==' ') 
			{
				continue;
			}

			if((*it)=='0') 
			{
				i++;
			}

			if((*it)=='1') 
			{
				lines[i-1].num=i;
				lines[i-1].start=num;
				i++;
			}
			if((*it)=='-') 
			{
				lines[i-1].num=i;
				lines[i-1].end=num;
				i++;
				it++;
			}
		}
		num++;
		getline(cin,inputrow,'\n');
	}
	//���
	cout<<"��Ӧ���б�Ϊ"<<endl;

	vector<line>::iterator iter;
	for(iter=lines.begin();iter<lines.end();iter++)
	{
		cout<<iter->start<<' ';
	}
	cout<<endl;
	for(iter=lines.begin();iter<lines.end();iter++)
	{
		cout<<iter->end<<' ';
	}
	cout<<endl;



}
void bianlieTOguanlian()
{
	int repeat=0;//����ѭ������
	int nodenum=0;//�ڵ����
	string inputrow;
	vector<line> lines;
	//����
	cout<<"��������б��Իس����У�ÿ���ַ�֮���ÿո�ֿ�"<<endl;
	getchar();

	string::iterator it;
	int num=1;//������������node��line���ʱ��
	getline(cin,inputrow,'\n');
	while(inputrow.size()!=0)
	{
		int count=0;//line�ĸ���
		for(it=inputrow.begin();it<inputrow.end();it++)
			if(*it=='0'||*it=='1'||*it=='2'||*it=='3'||*it=='4'
				||*it=='5'||*it=='6'||*it=='7'||*it=='8'||*it=='9') count++;
		lines.resize(count);
		int i=1;
		for(it=inputrow.begin();it<inputrow.end();it++)
		{
			int pos=it-inputrow.begin();

			if((*it)==' ') 
			{
				continue;
			}

			else if(repeat==0)
			{
				lines[i-1].num=i;
				lines[i-1].start=atoi(inputrow.data()+pos);
				if(lines[i-1].start>nodenum) nodenum=lines[i-1].start;
				i++;
			}
			else if(repeat==1)
			{
				lines[i-1].num=i;
				lines[i-1].end=atoi(inputrow.data()+pos);
				if(lines[i-1].end>nodenum) nodenum=lines[i-1].end;
				i++;
			}
		}
		num++;
		repeat++;
		if(repeat==2) break;
		getline(cin,inputrow,'\n');

	}
	//���
	cout<<"��Ӧ��������Ϊ"<<endl;

	string outrow;
	for(int i=1;i<=nodenum;i++)
	{
		for(vector<line>::iterator it2=lines.begin ();it2<lines.end();it2++)
		{
			if(i==it2->start) outrow.append(" 1 ");
			else if(i==it2->end) outrow.append("-1 ");
			else outrow.append(" 0 ");
		}
		cout<<outrow<<endl;
		outrow.clear();
	}

}
void linjieTOzhengxiang()
{
	string inputrow;
	vector<node> nodes;
	vector<line> lines;
	//����
	cout<<"�������ڽӾ����Իس����У���ע��ÿ���ַ�֮�䲻Ҫ����ո�"<<endl;
	getchar();

	string::iterator it;
	int num=1;//������������node��line���ʱ��
	int length=0;//�洢���󳤶�
	getline(cin,inputrow,'\n');
	while(1)
	{
		if(length!=0&&inputrow.size()!=length) {cout<<"�������";exit(0);}
		nodes.push_back (node(num));
		int i=1;
		for(it=inputrow.begin();it<inputrow.end();it++)
		{
			if((*it)=='1') 
			{
				lines.push_back (line(lines.size()+1,num,i));
				if(num==i) {cout<<"�벻Ҫ�����Ի���";exit(0);}
			}

			i++;
		}
		num++;
		length=inputrow.size();
		if(num>length) break;
		getline(cin,inputrow,'\n');
	}
	//���
	cout<<"��Ӧ�����Ϊ"<<endl;

	int pos;//�洢λ��
	pos=1;
	char p[50];
	string outrow1,outrow2;
	for(int i=1;i<=nodes.size ();i++)
	{
		itoa(pos,p,10);
		outrow1.append (p);
		outrow1.append (" ");
		for(vector<line>::iterator iter=lines.begin ();iter<lines.end();iter++)
		{
			if(iter->start==i)
			{
				itoa(iter->end,p,10);
				outrow2.append(p);
				outrow2.append (" ");
				pos++;
			}
		}

	}
	itoa(pos,p,10);
	outrow1.append(p);
	cout<<outrow1<<endl;
	cout<<outrow2<<endl;


}
void zhengxiangTOlinjie()
{
	int repeat=0;//����ѭ������
	int nodenum=0;//�ڵ����
	string inputrow1,inputrow2;
	vector<int> posline;//�洢������һ������
	vector<int> nodeline;//�洢�����ڶ�������
	vector<line> lines;
	bool found=false;
	//����
	cout<<"������������Իس����У�ÿ���ַ�֮���ÿո�ֿ�"<<endl;
	getchar();

	string::iterator it1,it2;
	vector<int>::iterator it3,it4;
	getline(cin,inputrow1,'\n');
	getline(cin,inputrow2,'\n');
	char* Word;
	char seps[]=" ";
	string temp=inputrow1;
	Word=strtok(&(temp[0]),seps);
	while(Word!=NULL)
	{
		posline.push_back (atoi(Word));
		Word=strtok(NULL,seps);
	}
	temp=inputrow2;
	Word=strtok(&(temp[0]),seps);
	while(Word!=NULL)
	{
		nodeline.push_back (atoi(Word));
		Word=strtok(NULL,seps);
	}
	//posline[posline.size()-1]--;
    for(int i=0;i<(posline.size()-1);i++)
	{
		for(int j=(posline[i]-1);j<(posline[i+1]-1);j++)
		{
			lines.push_back (line(lines.size()+1,i+1,nodeline[j]));
		}
	}
	nodenum=posline.size()-1;
	//���
	cout<<"��Ӧ�ڽӾ���Ϊ"<<endl;

	for(int i=1;i<=nodenum;i++)
	{
		for(int j=1;j<=nodenum;j++)
		{
			for(vector<line>::iterator iter=lines.begin();iter<lines.end();iter++)
			{
				if(iter->start==i&&iter->end==j) found=true;
			}
			if(found==true) cout<<" 1 ";
			else cout<<" 0 ";
			found=false;
		}
		cout<<endl;
	}



		


}



