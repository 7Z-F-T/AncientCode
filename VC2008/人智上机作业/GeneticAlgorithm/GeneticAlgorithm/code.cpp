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
	int N=30*n;//种群大小
	srand((unsigned int)time(NULL));
	vector<city> ultimateBestGene;
	ultimateBestGene.resize(N);

	///////////////////////////////////////////////////////////////////////////////////////
	//个体编码方案：直接采用旅途中路过城市的名称序列作为编码
	//交配方法：按顺序两两交配
	//变异方法：随机选取两个城市，逆序反转其间的序列
	//新种群的构造方法：采用"轮盘赌"方式选取基因组成新的种群，种群个体数保持不变
	//算法结束条件：历史最优结果持续(5*种群大小)次没有更新，则算法结束
	///////////////////////////////////////////////////////////////////////////////////////

	for(int v=0;v<5;v++){//执行5次算法，输出遇到过的最优解，这样可以提高输出最优解的概率
		//随机产生一个初始种群(基因可重复)，大小为N
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
		int bearTimes=5*n;//可以忍受的种群不发生好转的最大轮数
		int bearTimesCount=0;
		while(1){
			//计算现在种群内的最优基因适应值，然后看看与上轮的种群相比有没有改善
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
			//在这大小为N的种群中使用“轮盘赌”选择方法选择N个基因(基因可重复)，形成新的大小为N的种群
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

			//交配。交配概率为pFuck。采取顺序两两配对杂交
			float pFuck=0.1;
			for(int i=0;i<N;i=i+2){
				float r=randomDecimal();
				if(r<=pFuck)
					fuck(population[i],population[i+1],n);
			}
			//变异。变异概率为pMutate
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
	cout<<"算法重复执行5次后，遇到过的最优解为"<<endl;
	printGene(ultimateBestGene);
}