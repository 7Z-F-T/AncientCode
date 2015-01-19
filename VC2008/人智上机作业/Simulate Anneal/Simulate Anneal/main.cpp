#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <ctime>
#include <cmath>
#include <stdlib.h>
#include "function.cpp"
using namespace std;

struct city{
	string name;
	double x;
	double y;
	city(){}
	city(string str,double xx,double yy){
		name=str;
		x=xx;
		y=yy;
	}
};

int randomInt(int n){//生成1~n-1的一个随机整数
	float temp;
	int randInt;
	while(1){
	temp=(float)rand()/RAND_MAX;
	randInt=(int)(temp*(n));
	if(randInt!=0&&randInt!=n) break;
	}
	return randInt;
}

float randomDecimal(){//生成0~1的一个随机小数
	return (float)rand()/RAND_MAX;
}

vector<city> invert(vector<city> a,int r1,int r2){
	if(r1>r2){
		int temp=r2;
		r2=r1;
		r1=temp;
	}
	vector<city> b;
	b.resize(a.size());
	for(int i=0;i<a.size();i++){
		b[i]=a[i];
	}
	for(int i=0;i<=r2-r1;i++){
		b[r1+i]=a[r2-i];
	}
	return b;
}

double value(vector<city> a){
	double val=0;
	int i;
	for(i=0;i<a.size()-1;i++){
		val+=sqrt((a[i].x-a[i+1].x)*(a[i].x-a[i+1].x)+(a[i].y-a[i+1].y)*(a[i].y-a[i+1].y));
	}
	val+=sqrt((a[i].x-a[0].x)*(a[i].x-a[0].x)+(a[i].y-a[0].y)*(a[i].y-a[0].y));
	return val;
}

void printSolution(vector<city> a){
	for(int i=0;i<a.size();i++){
		cout<<a[i].name<<" ";
	}
	cout<<value(a);
	cout<<endl;
}

int main(int argc,char** argv){

	//打开文件
	if(argc!=2){
		cout<<"Command Error!";
		return -1;
	}
	char* filename=argv[1];
	ifstream fin(filename);
	if(!fin){
		cout<<"Cannot Open File!";
		return -1;
	}

	//读入文件内容
	string str;
	int n=0;//城市数量
	vector<city> info;//城市坐标信息
	fin>>str;
	n=atoi(str.c_str());
	info.resize(n+1);
	for(int i=0;i<n;i++){
		fin>>str;
		info[i].name=str;
		fin>>str;
		info[i].x=atof(str.c_str());
		fin>>str;
		info[i].y=atof(str.c_str());
	}
	
	srand((unsigned int)time(NULL));
	vector<city> bestSolution(10);
	for(int loop=0;loop<5;loop++){//由于随机算法不一定能得到最优解，故采用重复计算5次，最后选最优解输出
								  //这样可以以较大概率得到最优解
		//随机产生一个解
		vector<city> solution;
		solution.resize(n);
		int temp;
		bool existed;
		solution[0]=info[0];
		for(int i=1;i<n;i++){
			while(1){
				temp=randomInt(n);
				existed=false;
				for(int j=0;j<i;j++){
					if(info[temp].name==solution[j].name)
						existed=true;
				}
				if(existed==false){
					solution[i]=info[temp];
					break;
				}
			}
		}
		if(value(bestSolution)==0)
			bestSolution=solution;

		//设置初始参数
		//==========================================================================================================
		//参数选取方法：
		//	初始温度：t=280
		//	状态被接受的条件：本次计算得到的新解比上一次计算得到的解路径值小
		//						或本次计算得到的新解比上一次计算得到的解路径值大但接受概率值大于一随机小数
		//	同一温度内计算结束条件：迭代达到100n次
		//	降温算法：乘以系数0.92
		//	算法结束条件：在本次温度下计算得到结果的路径值与上次温度下计算得到的路径值相同，即降低温度后无更优解产生
		//===========================================================================================================

		float t=280;
		int Lk=100*(n+1);
		double alpha=0.92;
		int nloop=0;//同一温度下已经迭代的次数
		int r1=0,r2=0;
		while(1){
			double previousReult=value(solution);
			nloop=0;
			while(1){
				if(nloop==Lk){
					break;
				}
				r1=randomInt(n);
				while(1){
					r2=randomInt(n);
					if(r2!=r1) break;
				}
				vector<city> inverted=invert(solution,r1,r2);
				if(value(inverted)<value(solution)){
					solution=inverted;
				}
				else{
					double possibility=pow(2.718,(-(value(inverted)-value(solution))/t));
					if(possibility>randomDecimal()){
						solution=inverted;
					}
				}
				nloop++;
 				//printSolution(solution);
 				//cout<<t<<endl;
			}
			if(value(solution)==previousReult)
				break;
			else
				t=t*alpha;
			printSolution(solution);
			//cout<<t<<endl;
		}
		printSolution(solution);
		//cout<<t<<endl;
		if(value(solution)<value(bestSolution))
			bestSolution=solution;
	}
	cout<<"程序重复执行5次后，遇到的最优解为:"<<endl;
	printSolution(bestSolution);
}