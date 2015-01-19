#ifndef HEAD_H
#define HEAD_H

#include <iostream>
#include <string>
#include <vector>
#include <fstream>
#include <string.h>
using namespace std;

static int count=-1;//节点总数


class BaseNode
{
	
	friend class StoreNode;
	friend class AlterNode;
	friend class Node;
	friend class CombineNode;
	friend class OptionNode;
	friend class RepeatNode;
protected:
	int num;//节点编号
	string str;
	virtual void ContentOutput(ostream& out)=0;
	virtual void NetOutput(ostream& out)=0;
};
class Node
{
private:
	BaseNode *p;
	friend class AlterNode;
	friend class CombineNode;
	friend class OptionNode;
	friend class RepeatNode;

public:
	Node(){}
	Node(BaseNode* pNode):p(pNode){/*count++;*/}
	Node(string s);
	~Node(){}
	void ContentOutput(ostream& out);
	void NetOutput(ostream &out);
};
class StoreNode:public BaseNode
{
	
	friend class Node;
	void ContentOutput(ostream& out);
	void NetOutput(ostream &out);
	StoreNode(string s);
};

class AlterNode:public BaseNode
{
	friend class Node;
	vector<Node> NodeVec;
	
public:
	Node start;
	Node end;
	AlterNode(vector<Node> NodeV);
	void ContentOutput(ostream& out);
	void NetOutput(ostream &out);

};
class CombineNode:public BaseNode
{
	friend class Node;
	vector<Node> NodeVec;
	
public:
	CombineNode(vector<Node> NodeV);
	void ContentOutput(ostream& out);
	void NetOutput(ostream &out);

};
class OptionNode:public BaseNode
{
	friend class Node;
	Node node;
	Node start;
	Node end;
public:
	OptionNode(Node no);
	void ContentOutput(ostream& out);
	void NetOutput(ostream &out);

};
class RepeatNode:public BaseNode
{
	friend class Node;
	Node node;
	Node start;
	Node end;
public:
	RepeatNode(Node no);
	void ContentOutput(ostream& out);
	void NetOutput(ostream &out);

};

class definedstr
{
public:
	string name;
	string store;
	Node node;
	Node& createNode();

	definedstr(string str1,string str2):name(str1),store(str2){}
};


#endif