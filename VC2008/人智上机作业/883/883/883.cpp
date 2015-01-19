#include <vector>
#include <iostream>
using namespace std;

class Status{
public:
	int a1,a2,a3,b1,b2,b3,b4;
	Status(int x1, int x2, int x3, int x4, int x5, int x6, int x7){
		a1=x1;
		a2=x2;
		a3=x3;
		b1=x4;
		b2=x5;
		b3=x6;
		b4=x7;
	}
};
vector<Status> sequence;//存储解的状态序列
vector<Status> tried;//存储已经尝试过的状态
bool found=false;

void outputSequence(){
	for(int i=0;i<sequence.size();i++){
		cout<<"("<<sequence[i].a1<<","<<sequence[i].a2<<","<<sequence[i].a3<<","<<
			sequence[i].b1<<","<<sequence[i].b2<<","<<sequence[i].b3<<","<<
			sequence[i].b4<<")"<<endl;
	}
}
bool isTried(Status s){
	for(int i=0;i<tried.size();i++){
		if(s.a1==tried[i].a1&&s.a2==tried[i].a2&&s.a3==tried[i].a3&&s.b1==tried[i].b1&&
			s.b2==tried[i].b2&&s.b3==tried[i].b3&&s.b4==tried[i].b4)
			return true;
	}
	return false;
}
void find(Status s){
	//先判断是否结束
	if(found==true) return;
	if(s.b1==4&&s.b2==4&&s.b3==4&&s.b4==4){
		sequence.push_back(s);
		outputSequence();
		found=true;
		sequence.pop_back();
		return;
	}
	//再判断当前状态是否被尝试过
	if(isTried(s)==true) return;
	//否则，开始尝试各种操作
	tried.push_back(s);
	sequence.push_back(s);
	//cout<<tried.size()<<endl;
	//cout<<sequence.size()<<endl;

	//a1->b1,b2,b3,b4
	if(s.b1+s.a1<=4)
		find(Status(0,s.a2,s.a3,s.b1+s.a1,s.b2,s.b3,s.b4));
	if(s.b2+s.a1<=4)
		find(Status(0,s.a2,s.a3,s.b1,s.b2+s.a1,s.b3,s.b4));
	if(s.b3+s.a1<=4)
		find(Status(0,s.a2,s.a3,s.b1,s.b2,s.b3+s.a1,s.b4));
	if(s.b4+s.a1<=4)
		find(Status(0,s.a2,s.a3,s.b1,s.b2,s.b3,s.b4+s.a1));
	//a2->b1,b2,b3,b4
	if(s.b1+s.a2<=4)
		find(Status(s.a1,0,s.a3,s.b1+s.a2,s.b2,s.b3,s.b4));
	if(s.b2+s.a2<=4)
		find(Status(s.a1,0,s.a3,s.b1,s.b2+s.a2,s.b3,s.b4));
	if(s.b3+s.a2<=4)
		find(Status(s.a1,0,s.a3,s.b1,s.b2,s.b3+s.a2,s.b4));
	if(s.b4+s.a2<=4)
		find(Status(s.a1,0,s.a3,s.b1,s.b2,s.b3,s.b4+s.a2));
	//a3->b1,b2,b3,b4
	if(s.b1+s.a3<=4)
		find(Status(s.a1,s.a2,0,s.b1+s.a3,s.b2,s.b3,s.b4));
	if(s.b2+s.a3<=4)
		find(Status(s.a1,s.a2,0,s.b1,s.b2+s.a3,s.b3,s.b4));
	if(s.b3+s.a3<=4)
		find(Status(s.a1,s.a2,0,s.b1,s.b2,s.b3+s.a3,s.b4));
	if(s.b4+s.a3<=4)
		find(Status(s.a1,s.a2,0,s.b1,s.b2,s.b3,s.b4+s.a3));
	//a1->a2
	if(s.a1>(8-s.a2)){
		find(Status(s.a1-(8-s.a2),8,s.a3,s.b1,s.b2,s.b3,s.b4));
	}
	else{
		find(Status(0,s.a2+s.a1,s.a3,s.b1,s.b2,s.b3,s.b4));
	}
	//a1->a3
	if(s.a1>(3-s.a3)){
		find(Status(s.a1-(3-s.a3),s.a2,3,s.b1,s.b2,s.b3,s.b4));
	}
	else{
		find(Status(0,s.a2,s.a3+s.a1,s.b1,s.b2,s.b3,s.b4));
	}
	//a2->a1
	if(s.a2>(8-s.a1)){
		find(Status(8,s.a2-(8-s.a1),s.a3,s.b1,s.b2,s.b3,s.b4));
	}
	else{
		find(Status(s.a1+s.a2,0,s.a3,s.b1,s.b2,s.b3,s.b4));
	}
	//a2->a3
	if(s.a2>(3-s.a3)){
		find(Status(s.a1,s.a2-(3-s.a3),3,s.b1,s.b2,s.b3,s.b4));
	}
	else{
		find(Status(s.a1,0,s.a3+s.a2,s.b1,s.b2,s.b3,s.b4));
	}
	//a3->a1
	if(s.a3>(8-s.a1)){
		find(Status(8,s.a2,s.a3-(8-s.a1),s.b1,s.b2,s.b3,s.b4));
	}
	else{
		find(Status(s.a1+s.a3,s.a2,0,s.b1,s.b2,s.b3,s.b4));
	}
	//a3->a2
	if(s.a3>(8-s.a2)){
		find(Status(s.a1,8,s.a3-(8-s.a2),s.b1,s.b2,s.b3,s.b4));
	}
	else{
		find(Status(s.a1,s.a2+s.a3,0,s.b1,s.b2,s.b3,s.b4));
	}
    sequence.pop_back();
}

int main(){
	Status s(8,8,0,0,0,0,0);
	cout<<"This will take several seconds. Please wait..."<<endl;
	find(s);
}