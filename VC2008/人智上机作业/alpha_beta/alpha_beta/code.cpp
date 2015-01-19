#include "head.h"

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

	//读入文件
	string readStr;
	fin>>readStr;//读入"ROOT"
	fin>>readStr;//读入根节点名字
	nodes[myHashFunc(readStr)]=Node(readStr,-1,1,false,false,true,false);
	int root=myHashFunc(readStr);


	fin>>readStr;
	while(readStr!="VALUE"){
		int parentNode=myHashFunc(readStr);
		fin>>readStr;
		while(readStr!="END"){
			int childNode=myHashFunc(readStr);
			nodes[childNode]=Node(readStr,parentNode,!nodes[parentNode].isMaxType,false,false,true,false);
			nodes[parentNode].addChild(childNode);
			fin>>readStr;
		}
		fin>>readStr;
	}

	
//开始探查节点并进行剪枝
	fin>>readStr;
	while(readStr!="END"){
		int currentNode=myHashFunc(readStr);
		fin>>readStr;
		if(nodes[currentNode].accessable==true){
			nodes[currentNode].visited=true;
			nodes[currentNode].isLeaf=true;
			nodes[currentNode].determined=true;
			nodes[currentNode].value=atoi(readStr.c_str());
			update(nodes[currentNode].parent,currentNode,nodes[currentNode].value);//向上更新currentNode的祖先节点
		}
		fin>>readStr;
	}
	cout<<"根节点:"<<nodes[root].name<<endl;
	cout<<"倒推值:"<<nodes[root].value<<endl;
	for(int i=0;i<nodes[root].children.size();i++){
		if(nodes[nodes[root].children[i]].value==nodes[root].value){
			cout<<"应走的步骤:"<<nodes[nodes[root].children[i]].name<<endl;
			break;
		}
	}


	
	

	return 0;
}