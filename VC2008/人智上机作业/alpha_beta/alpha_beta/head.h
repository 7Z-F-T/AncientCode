#include <iostream>
#include <string>
#include <fstream>
#include <vector>
using namespace std;

//classes
class Node{
public:
	string name;
	int parent;
	vector<int> children;
	bool isMaxType;//1:����Max�ڵ㣬0:����Min�ڵ�
	bool isLeaf;//1:������Ҷ���
	int value;//����ֵ
	bool determined;//�˵�ֵ�Ƿ��Ѿ��ɵȺ�ȷ��
	bool accessable;//���Ƿ�ɷ��ʣ�����֦�˾Ͳ��ܷ����ˣ�
	bool visited;//�Ƿ���ʹ�
	Node(string nodeName,int nodeParent,bool nodeType,bool nodeIsLeaf,bool nodeDetermined,bool nodeAccessable,bool nodeVisited){
		name=nodeName;
		parent=nodeParent;
		isMaxType=nodeType;
		isLeaf=nodeIsLeaf;
		determined=nodeDetermined;
		accessable=nodeAccessable;
		visited=nodeVisited;
	}
	Node(){}
	void setValue(int val){
		value=val;
	}
	void addChild(int child){
		children.push_back(child);
	}
};

//variables
Node nodes[10000];

//functions
int myHashFunc(string s){//����һ���ַ���ת����hash���룬����ַ���������<=3
	int i=s.size();
	int hashCode;
	if(i==3)
		hashCode=s[0]*100+(s[1]-48)*10+(s[2]-48);
	else if(i==2)
		hashCode=s[0]*100+(s[1]-48);
	else if(i==1)
		hashCode=s[0]*100;

	return hashCode;
}

void makeUnAccessable(int currentNode){
	nodes[currentNode].accessable=false;
	for(int i=0;i<nodes[currentNode].children.size();i++){
		makeUnAccessable(nodes[currentNode].children[i]);
	}
}

void update(int currentNode,int child,int childValue){//����currentNode�ڵ�
	if(currentNode==-1)
		return;
	if(nodes[currentNode].isMaxType==false){//�����Ƕ���Min�ڵ�Ĳ���
		if(nodes[child].determined==true){//���child�Ƕ���ֵ�Ľڵ㣬����Բ���currentNode�ڵ�ֵ�ĸ��¹���
			if(nodes[currentNode].visited==false){//���ǵ�һ�η���currentNode��ֱ�Ӹ�ֵ����
				nodes[currentNode].visited=true;
				nodes[currentNode].value=childValue;
			}
			else{//�����ٴη���currentNode��ֻ�и�С��ֵ�ܸ���ԭ����ֵ
				if(childValue<nodes[currentNode].value)
					nodes[currentNode].value=childValue;
			}
		}
		else{//��child��û�ж���ֵ�Ľڵ㣬���ܲ���currentNode�ڵ�ֵ�ĸ��¹��������п�����child���ϲ�����֦
			int temp=currentNode;
			while(1){
				if(nodes[temp].visited==true&&nodes[temp].value<=childValue){
					nodes[child].determined=true;//child�ĺ��ӱ���֦��child��ֵҲ�ǹ׶����ˣ�����childҲ����determined
					cout<<"��֦:"<<nodes[child].name<<":";
					for(int i=0;i<nodes[child].children.size();i++){
						if(nodes[nodes[child].children[i]].visited==false){//ע��û�б�visit�ĵ�ſ��Ա���֦
							cout<<nodes[nodes[child].children[i]].name<<" ";//��֦��
						}
						makeUnAccessable(nodes[child].children[i]);
					}
					cout<<endl;
					if(nodes[currentNode].visited==false){
						nodes[currentNode].visited=true;
						nodes[currentNode].value=childValue;
					}
					else{//�����ٴη���currentNode��ֻ�и�С��ֵ�ܸ���ԭ����ֵ
						if(childValue<nodes[currentNode].value)
							nodes[currentNode].value=childValue;
					}
					break;
				}
				else temp=nodes[temp].parent;
				if(temp==-1) break;
				temp=nodes[temp].parent;
				if(temp==-1) break;
			}
		}
		nodes[currentNode].determined=true;//��ô�������������һ��
		for(int i=0;i<nodes[currentNode].children.size();i++){
			if(nodes[nodes[currentNode].children[i]].determined==false)
				nodes[currentNode].determined=false;//��һ������û��determined����ôcurrentNodeҲ������determined
		}
		if(nodes[currentNode].visited==true)
			update(nodes[currentNode].parent,currentNode,nodes[currentNode].value);
	}
	else if(nodes[currentNode].isMaxType==true){//�����Ƕ���Max�ڵ�Ĳ���
		if(nodes[child].determined==true){//���child�Ƕ���ֵ�Ľڵ㣬����Բ���currentNode�ڵ�ֵ�ĸ��¹���
			if(nodes[currentNode].visited==false){//���ǵ�һ�η���currentNode��ֱ�Ӹ�ֵ����
				nodes[currentNode].visited=true;
				nodes[currentNode].value=childValue;
			}
			else{//�����ٴη���currentNode��ֻ�и����ֵ�ܸ���ԭ����ֵ
				if(childValue>nodes[currentNode].value)
					nodes[currentNode].value=childValue;
			}
		}
		else{//��child��û�ж���ֵ�Ľڵ㣬���ܲ���currentNode�ڵ�ֵ�ĸ��¹��������п�����child���ϲ�����֦
			int temp=currentNode;
			while(1){
				if(nodes[temp].visited==true&&nodes[temp].value>=childValue){
					nodes[child].determined=true;//child�ĺ��ӱ���֦��child��ֵҲ�ǹ׶����ˣ�����childҲ����determined
					cout<<"��֦:"<<nodes[child].name<<":";
					for(int i=0;i<nodes[child].children.size();i++){
						if(nodes[nodes[child].children[i]].visited==false){//ע��û�б�visit�ĵ�ſ��Ա���֦
							cout<<nodes[nodes[child].children[i]].name<<" ";//��֦��
						}
						makeUnAccessable(nodes[child].children[i]);
					}
					cout<<endl;
					if(nodes[currentNode].visited==false){
						nodes[currentNode].visited=true;
						nodes[currentNode].value=childValue;
					}
					else{//�����ٴη���currentNode��ֻ�и����ֵ�ܸ���ԭ����ֵ
						if(childValue>nodes[currentNode].value)
							nodes[currentNode].value=childValue;
					}
					break;
				}
				else temp=nodes[temp].parent;
				if(temp==-1) break;
				temp=nodes[temp].parent;
				if(temp==-1) break;
			}
		}
		nodes[currentNode].determined=true;//��ô�������������һ��
		for(int i=0;i<nodes[currentNode].children.size();i++){
			if(nodes[nodes[currentNode].children[i]].determined==false)
				nodes[currentNode].determined=false;//��һ������û��determined����ôcurrentNodeҲ������determined
		}
		if(nodes[currentNode].visited==true)
			update(nodes[currentNode].parent,currentNode,nodes[currentNode].value);
	}
}

