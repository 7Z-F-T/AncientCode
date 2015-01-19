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
	bool isMaxType;//1:代表Max节点，0:代表Min节点
	bool isLeaf;//1:代表是叶结点
	int value;//估计值
	bool determined;//此点值是否已经由等号确定
	bool accessable;//可是否可访问（被剪枝了就不能访问了）
	bool visited;//是否访问过
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
int myHashFunc(string s){//负责将一个字符串转化成hash编码，这个字符串长度限<=3
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

void update(int currentNode,int child,int childValue){//更新currentNode节点
	if(currentNode==-1)
		return;
	if(nodes[currentNode].isMaxType==false){//以下是对于Min节点的操作
		if(nodes[child].determined==true){//如果child是定死值的节点，则可以参与currentNode节点值的更新工作
			if(nodes[currentNode].visited==false){//若是第一次访问currentNode，直接赋值即可
				nodes[currentNode].visited=true;
				nodes[currentNode].value=childValue;
			}
			else{//若是再次访问currentNode，只有更小的值能更新原来的值
				if(childValue<nodes[currentNode].value)
					nodes[currentNode].value=childValue;
			}
		}
		else{//若child是没有定死值的节点，则不能参与currentNode节点值的更新工作，但有可能在child身上产生剪枝
			int temp=currentNode;
			while(1){
				if(nodes[temp].visited==true&&nodes[temp].value<=childValue){
					nodes[child].determined=true;//child的孩子被剪枝，child的值也盖棺定论了，所以child也成了determined
					cout<<"剪枝:"<<nodes[child].name<<":";
					for(int i=0;i<nodes[child].children.size();i++){
						if(nodes[nodes[child].children[i]].visited==false){//注意没有被visit的点才可以被剪枝
							cout<<nodes[nodes[child].children[i]].name<<" ";//剪枝！
						}
						makeUnAccessable(nodes[child].children[i]);
					}
					cout<<endl;
					if(nodes[currentNode].visited==false){
						nodes[currentNode].visited=true;
						nodes[currentNode].value=childValue;
					}
					else{//若是再次访问currentNode，只有更小的值能更新原来的值
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
		nodes[currentNode].determined=true;//是么？看看下面检验一下
		for(int i=0;i<nodes[currentNode].children.size();i++){
			if(nodes[nodes[currentNode].children[i]].determined==false)
				nodes[currentNode].determined=false;//有一个孩子没有determined，那么currentNode也不能算determined
		}
		if(nodes[currentNode].visited==true)
			update(nodes[currentNode].parent,currentNode,nodes[currentNode].value);
	}
	else if(nodes[currentNode].isMaxType==true){//以下是对于Max节点的操作
		if(nodes[child].determined==true){//如果child是定死值的节点，则可以参与currentNode节点值的更新工作
			if(nodes[currentNode].visited==false){//若是第一次访问currentNode，直接赋值即可
				nodes[currentNode].visited=true;
				nodes[currentNode].value=childValue;
			}
			else{//若是再次访问currentNode，只有更大的值能更新原来的值
				if(childValue>nodes[currentNode].value)
					nodes[currentNode].value=childValue;
			}
		}
		else{//若child是没有定死值的节点，则不能参与currentNode节点值的更新工作，但有可能在child身上产生剪枝
			int temp=currentNode;
			while(1){
				if(nodes[temp].visited==true&&nodes[temp].value>=childValue){
					nodes[child].determined=true;//child的孩子被剪枝，child的值也盖棺定论了，所以child也成了determined
					cout<<"剪枝:"<<nodes[child].name<<":";
					for(int i=0;i<nodes[child].children.size();i++){
						if(nodes[nodes[child].children[i]].visited==false){//注意没有被visit的点才可以被剪枝
							cout<<nodes[nodes[child].children[i]].name<<" ";//剪枝！
						}
						makeUnAccessable(nodes[child].children[i]);
					}
					cout<<endl;
					if(nodes[currentNode].visited==false){
						nodes[currentNode].visited=true;
						nodes[currentNode].value=childValue;
					}
					else{//若是再次访问currentNode，只有更大的值能更新原来的值
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
		nodes[currentNode].determined=true;//是么？看看下面检验一下
		for(int i=0;i<nodes[currentNode].children.size();i++){
			if(nodes[nodes[currentNode].children[i]].determined==false)
				nodes[currentNode].determined=false;//有一个孩子没有determined，那么currentNode也不能算determined
		}
		if(nodes[currentNode].visited==true)
			update(nodes[currentNode].parent,currentNode,nodes[currentNode].value);
	}
}

