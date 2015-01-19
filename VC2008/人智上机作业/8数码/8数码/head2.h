#include <iostream>
#include <vector>
#include <algorithm>
#include <fstream>
#include <string>
#include <math.h>
#include <set>
using namespace std;

int stateCount;//״̬����

class node{
public:
	int mark;//���
	int num[3][3];//��������״��
	int g;//g(n)ֵ
	int f;//f(n)ֵ
	int parent;//���ڵ�ı��
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
		/*f=(num[0][0]!=1)+(num[0][1]!=2)+(num[0][2]!=3)+(num[1][0]!=8)+(num[1][1]!=0)
			+(num[1][2]!=4)+(num[2][0]!=7)+(num[2][1]!=6)+(num[2][2]!=5)
			+g;*/
		for(int i=0;i<3;i++){
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
		f+=g;
	}
	node(){}
};

vector<node> state;//������̽�鵽��״̬�ڵ�
set<int> open;//open��
//vector<int> closed;//closed��

/*void get_f(node& a){//��f(n)ֵ
	int h;//h(n)ֵ����ʾ����λ�����ָ���
	h=(a.num[0][0]!=1)+(a.num[0][1]!=2)+(a.num[0][2]!=3)+(a.num[1][0]!=8)+(a.num[1][1]!=0)
		+(a.num[1][2]!=4)+(a.num[2][0]!=7)+(a.num[2][1]!=6)+(a.num[2][2]!=5);
	a.f=a.g+h;
}*/

bool is_goal(node &a){//�ж��Ƿ�ﵽ�յ�
	if(
		(a.num[0][0]==1)&&(a.num[0][1]==2)&&(a.num[0][2]==3)&&(a.num[1][0]==8)&&(a.num[1][1]==0)
		&&(a.num[1][2]==4)&&(a.num[2][0]==7)&&(a.num[2][1]==6)&&(a.num[2][2]==5)
		)
		return true;
	else return false;
}

bool should_expand(int a1,int a2,int a3, int a4, int a5, int a6, int a7, int a8, int a9){//�жϸ�״̬�Ƿ�Ӧ�ü��뵽open����
	for(int i=0;i<state.size();i++){
		if(
			(state[i].num[0][0]==a1)&&(state[i].num[0][1]==a2)&&(state[i].num[0][2]==a3)&&
			(state[i].num[1][0]==a4)&&(state[i].num[1][1]==a5)&&(state[i].num[1][2]==a6)&&
			(state[i].num[2][0]==a7)&&(state[i].num[2][1]==a8)&&(state[i].num[2][2]==a9)
			)
			return false;
	}
	return true;	
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
class sortOpen{
public:
	bool operator()(int x1, int x2){
		return state[x1].f<state[x2].f;
	}
};
/*int findOpenMin(){//�ҵ�open������fֵ��С�Ľ���λ��
	int temp=99999;
	int pos;
	for(int i=0;i<open.size();i++){
		if(state[open[i]].f<temp){
			temp=state[open[i]].f;
			pos=i;
		}
	}
	return pos;
}*/