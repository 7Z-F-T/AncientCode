#include "head.h"


int main()
{
	int choice;
	cout<<"��ѡ��������ͣ�\n";
	cout<<endl;
	cout<<"1--�ڽӾ���תΪ��������\n";
	cout<<"2--��������תΪ�ڽӾ���\n";
	cout<<"3--�ڽӾ���תΪ�����\n";
	cout<<"4--�����תΪ�ڽӾ���\n";
	cout<<"5--��������תΪ���б�\n";
	cout<<"6--���б�תΪ��������\n";
    cout<<endl;
	cout<<"��ѡ��(1-6):";
	cin>>choice;

	switch(choice)
	{
	    case 1:linjieTOguanlian();break;
	    case 2:guanlianTOlinjie();break;
		case 3:linjieTOzhengxiang();break;
		case 4:zhengxiangTOlinjie();break;
		case 5:guanlianTObianlie();break;
		case 6:bianlieTOguanlian();break;
	}
	return 0;
}
