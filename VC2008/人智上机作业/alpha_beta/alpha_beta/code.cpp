#include "head.h"

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

	//�����ļ�
	string readStr;
	fin>>readStr;//����"ROOT"
	fin>>readStr;//������ڵ�����
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

	
//��ʼ̽��ڵ㲢���м�֦
	fin>>readStr;
	while(readStr!="END"){
		int currentNode=myHashFunc(readStr);
		fin>>readStr;
		if(nodes[currentNode].accessable==true){
			nodes[currentNode].visited=true;
			nodes[currentNode].isLeaf=true;
			nodes[currentNode].determined=true;
			nodes[currentNode].value=atoi(readStr.c_str());
			update(nodes[currentNode].parent,currentNode,nodes[currentNode].value);//���ϸ���currentNode�����Ƚڵ�
		}
		fin>>readStr;
	}
	cout<<"���ڵ�:"<<nodes[root].name<<endl;
	cout<<"����ֵ:"<<nodes[root].value<<endl;
	for(int i=0;i<nodes[root].children.size();i++){
		if(nodes[nodes[root].children[i]].value==nodes[root].value){
			cout<<"Ӧ�ߵĲ���:"<<nodes[nodes[root].children[i]].name<<endl;
			break;
		}
	}


	
	

	return 0;
}