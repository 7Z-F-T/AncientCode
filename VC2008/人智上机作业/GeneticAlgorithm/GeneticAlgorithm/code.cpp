#include <iostream>
#include <fstream>
#include <string>
#include <vector>
#include <ctime>
#include <cmath>
#include <stdlib.h>
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

void mutate(vector<city> &a,int n){
	int r1=0,r2=0;
	while(1){
		r1=randomInt(n);
		if(r1!=n)
			break;
	}
	while(1){
		r2=randomInt(n);
		if(r2!=n||r2!=r1)
			break;
	}
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
	a=b;
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

void fuck(vector<city> &a, vector<city> &b, int n){
	int r=randomInt(n);
	vector<city> aa=a;
	vector<city> bb=b;
	for(int i=r;i<n;i++){
		for(int j=0;j<n;j++){
			bool existed=false;
			for(int k=0;k<i;k++){
				if(aa[k].name==b[j].name){
					existed=true;
					break;
				}
			}
			if(existed==false){
				aa[i]=b[j];
				break;
			}
		}
	}
	for(int i=r;i<n;i++){
		for(int j=0;j<n;j++){
			bool existed=false;
			for(int k=0;k<i;k++){
				if(bb[k].name==a[j].name){
					existed=true;
					break;
				}
			}
			if(existed==false)
				bb[i]=a[j];
		}
	}
	a=aa;
	b=bb;
	
}
void printGene(vector<city> a){
	for(int i=0;i<a.size();i++){
		cout<<a[i].name<<" ";
	}
	cout<<value(a);
	cout<<endl;
}

void printPopulation(vector<vector<city> > a){
	for(int i=0;i<a.size();i++){
		printGene(a[i]);
	}
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
	int N=30*n;//��Ⱥ��С
	srand((unsigned int)time(NULL));
	vector<city> ultimateBestGene;
	ultimateBestGene.resize(N);

	///////////////////////////////////////////////////////////////////////////////////////
	//������뷽����ֱ�Ӳ�����;��·�����е�����������Ϊ����
	//���䷽������˳����������
	//���췽�������ѡȡ�������У�����ת��������
	//����Ⱥ�Ĺ��췽��������"���̶�"��ʽѡȡ��������µ���Ⱥ����Ⱥ���������ֲ���
	//�㷨������������ʷ���Ž������(5*��Ⱥ��С)��û�и��£����㷨����
	///////////////////////////////////////////////////////////////////////////////////////

	for(int v=0;v<5;v++){//ִ��5���㷨����������������Ž⣬�����������������Ž�ĸ���
		//�������һ����ʼ��Ⱥ(������ظ�)����СΪN
		vector<vector<city> > population;
		population.resize(N);
		for(int k=0;k<N;k++){
			vector<city> gene;
			gene.resize(n);
			int temp;
			bool existed;
			gene[0]=info[0];
			for(int i=1;i<n;i++){
				while(1){
					temp=randomInt(n);
					existed=false;
					for(int j=0;j<i;j++){
						if(info[temp].name==gene[j].name)
							existed=true;
					}
					if(existed==false){
						gene[i]=info[temp];
						break;
					}
				}
			}
			population[k]=gene;
		}

		if(value(ultimateBestGene)==0)
			ultimateBestGene=population[0];
		
		float historyBestDistanceValue=9999999;
		vector<city> historyBestGene;
		historyBestGene.resize(N);
		int bearTimes=5*n;//�������ܵ���Ⱥ��������ת���������
		int bearTimesCount=0;
		while(1){
			//����������Ⱥ�ڵ����Ż�����Ӧֵ��Ȼ�󿴿������ֵ���Ⱥ�����û�и���
			float bestDistanceValue=9999999;
			vector<city> bestGene;
			bestGene.resize(N);
			for(int k=0;k<N;k++){
				if(bestDistanceValue>value(population[k])){
					bestDistanceValue=value(population[k]);
					bestGene=population[k];
				}
			}
			if(value(historyBestGene)!=0)
				printGene(historyBestGene);
			if(bestDistanceValue<historyBestDistanceValue){
				historyBestDistanceValue=bestDistanceValue;
				historyBestGene=bestGene;
				bearTimesCount=0;
			}
			else{
				bearTimesCount++;
				if(bearTimesCount==bearTimes)
					break;
			}
			//�����СΪN����Ⱥ��ʹ�á����̶ġ�ѡ�񷽷�ѡ��N������(������ظ�)���γ��µĴ�СΪN����Ⱥ
			vector<double> geneValues;
			geneValues.resize(N);
			float bestValue=1/bestDistanceValue;
			for(int k=0;k<N;k++){
				float temp=1/value(population[k]);
				if(temp<bestValue)
					geneValues[k]=1/(bestValue-temp);
				else if(temp==bestValue)
					geneValues[k]=1000;
			}
			double geneValuesSum=0;
			for(int k=0;k<N;k++){
				geneValuesSum+=geneValues[k];
			}
			vector<double> genePossibilityValues;
			genePossibilityValues.resize(N);
			for(int k=0;k<N;k++){
				genePossibilityValues[k]=geneValues[k]/geneValuesSum;
			}
			vector<vector<city> > newPopulation;
			newPopulation.resize(N);
			for(int k=0;k<N;k++){
				int i=-1;
				float s=0;
				float r=0;
				while(1){
					r=randomDecimal();
					if(r!=1) break;
				}
				while(1){
					if(s>r){
						newPopulation[k]=population[i];
						break;
					}
					else{
						i++;
						s=s+genePossibilityValues[i];
					}
				}
			}
			population=newPopulation;

			//���䡣�������ΪpFuck����ȡ˳����������ӽ�
			float pFuck=0.1;
			for(int i=0;i<N;i=i+2){
				float r=randomDecimal();
				if(r<=pFuck)
					fuck(population[i],population[i+1],n);
			}
			//���졣�������ΪpMutate
			float pMutate=0.99;
			for(int i=0;i<N;i++){
				float r=randomDecimal();
				if(r<=pMutate)
					mutate(population[i],n);
			}
		}
		if(value(ultimateBestGene)>historyBestDistanceValue)
			ultimateBestGene=historyBestGene;
	}
	cout<<"�㷨�ظ�ִ��5�κ������������Ž�Ϊ"<<endl;
	printGene(ultimateBestGene);
}