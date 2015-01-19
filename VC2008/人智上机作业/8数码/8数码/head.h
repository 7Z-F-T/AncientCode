#include <iostream>
#include <vector>
#include <algorithm>
#include <fstream>
#include <string>
#include <math.h>
#include <set>
using namespace std;

int stateCount;//状态计数

class node{
public:
	int mark;//编号
	int num[3][3];//数字排列状况
	int g;//g(n)值
	int f;//f(n)值
	int parent;//父节点的编号
	node(int a1,int a2,int a3, int a4, int a5, int a6, int a7, int a8, int a9, int gg,int pp){
		mark=stateCount;
		stateCount++;
		num[0][0]=a1;
		num[0][1]=a2;
		num[0][2]=a3;
		num[1][0]=a4;
		num[1][1]=a5;
		num[1][2]=a6;
		num[2][0]=a7;
		num[2][1]=a8;
		num[2][2]=a9;
		g=gg;
		parent=pp;
		f=0;
		for(int i=0;i<3;i++){//求h(n)值。其值为每个牌与其目标位置之间的距离的总和。
			for(int j=0;j<3;j++){
				switch(num[i][j]){
					case 1:
						f+=abs(0-i)+abs(0-j);
						break;
					case 2:
						f+=abs(0-i)+abs(1-j);
						break;
					case 3:
						f+=abs(0-i)+abs(2-j);
						break;
					case 4:
						f+=abs(1-i)+abs(2-j);
						break;
					case 5:
						f+=abs(2-i)+abs(2-j);
						break;
					case 6:
						f+=abs(2-i)+abs(1-j);
						break;
					case 7:
						f+=abs(2-i)+abs(0-j);
						break;
					case 8:
						f+=abs(1-i)+abs(0-j);
						break;
				}
			}
		}
		f+=g;//h(n)+g(n)=f(n)
	}
	node(){}
};

vector<node> state;//所有已探查到的状态节点
vector<int> open;//open表
bool explorered[9][9][9][9][9][9][9][9][9];


bool is_goal(node &a){//判断是否达到终点
	if(
		(a.num[0][0]==1)&&(a.num[0][1]==2)&&(a.num[0][2]==3)&&(a.num[1][0]==8)&&(a.num[1][1]==0)
		&&(a.num[1][2]==4)&&(a.num[2][0]==7)&&(a.num[2][1]==6)&&(a.num[2][2]==5)
		)
		return true;
	else return false;
}


bool should_expand(int a1,int a2,int a3, int a4, int a5, int a6, int a7, int a8, int a9){//判断该状态是否应该加入到open表中
	if(explorered[a1][a2][a3][a4][a5][a6][a7][a8][a9]==1)
		return false;
	else return true;
}



void printNode(node& a){
	for(int i=0;i<3;i++){
		cout<<a.num[i][0];
		for(int j=1;j<3;j++){
			cout<<" "<<a.num[i][j];
		}
		cout<<endl;
	}
}

void printResult(node& a){
	if(a.parent==-1){
		return;
	}
	else{
		printResult(state[a.parent]);
		printNode(a);
		cout<<endl;
	}
}

int findOpenMin(){//找到open表里面f值最小的结点的位置
	int temp=99999;
	int pos;
	for(int i=0;i<open.size();i++){
		if(state[open[i]].f<temp){
			temp=state[open[i]].f;
			pos=i;
		}
	}
	return pos;
}
bool solutionExisted(node& a){//判断解是否存在
	int m[9];
	for(int i=0;i<=2;i++)
		m[i]=a.num[0][i];
	for(int i=0;i<=2;i++)
		m[i+3]=a.num[1][i];
	for(int i=0;i<=2;i++)
		m[i+6]=a.num[2][i];
	//求逆序数inverse
	int inverse=0;
	for(int i=0;i<8;i++){
		for(int j=i+1;j<9;j++){
			if(m[i]!=0&&m[j]!=0&&m[i]>m[j])
				inverse++;
		}
	}
	if(inverse%2==1) return true;
	else return false;
}
