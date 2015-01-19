#include "head.h"


int main()
{
	int choice;
	cout<<"请选择操作类型：\n";
	cout<<endl;
	cout<<"1--邻接矩阵转为关联矩阵\n";
	cout<<"2--关联矩阵转为邻接矩阵\n";
	cout<<"3--邻接矩阵转为正向表\n";
	cout<<"4--正向表转为邻接矩阵\n";
	cout<<"5--关联矩阵转为边列表\n";
	cout<<"6--边列表转为关联矩阵\n";
    cout<<endl;
	cout<<"请选择(1-6):";
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
