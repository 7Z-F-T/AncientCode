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

int randomInt(int n){//����1~n-1��һ���������
	float temp;
	int randInt;
	while(1){
	temp=(float)rand()/RAND_MAX;
	randInt=(int)(temp*(n));
	if(randInt!=0&&randInt!=n) break;
	}
	return randInt;
}

float randomDecimal(){//����0~1��һ�����С��
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

	//���ļ�
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

	//�����ļ�����
	string str;
	int n=0;//��������
	vector<city> info;//����������Ϣ
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
	for(int loop=0;loop<5;loop++){//��������㷨��һ���ܵõ����Ž⣬�ʲ����ظ�����5�Σ����ѡ���Ž����
								  //���������Խϴ���ʵõ����Ž�
		//�������һ����
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

		//���ó�ʼ����
		//==========================================================================================================
		//����ѡȡ������
		//	��ʼ�¶ȣ�t=280
		//	״̬�����ܵ����������μ���õ����½����һ�μ���õ��Ľ�·��ֵС
		//						�򱾴μ���õ����½����һ�μ���õ��Ľ�·��ֵ�󵫽��ܸ���ֵ����һ���С��
		//	ͬһ�¶��ڼ�����������������ﵽ100n��
		//	�����㷨������ϵ��0.92
		//	�㷨�����������ڱ����¶��¼���õ������·��ֵ���ϴ��¶��¼���õ���·��ֵ��ͬ���������¶Ⱥ��޸��Ž����
		//===========================================================================================================

		float t=280;
		int Lk=100*(n+1);
		double alpha=0.92;
		int nloop=0;//ͬһ�¶����Ѿ������Ĵ���
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
	cout<<"�����ظ�ִ��5�κ����������Ž�Ϊ:"<<endl;
	printSolution(bestSolution);
}