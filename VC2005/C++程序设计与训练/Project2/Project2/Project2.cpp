#include "head.h"
vector<definedstr> strV;
//Node
void Node::ContentOutput(ostream& out)
{
	p->ContentOutput(out);
}
void Node::NetOutput(ostream &out)
{
	p->NetOutput(out);
}

Node::Node(string s):p(new StoreNode(s)){}
//StoreNode
StoreNode::StoreNode(string s)
{
	str.clear();
    str=s;
	count++;
	num=count;
}
void StoreNode::ContentOutput(ostream& out)
{
	out<<"I="<<num;
	out<<"  W="<<str<<endl;
}
void StoreNode::NetOutput(ostream& out)
{
    out<<"  E="<<num<<endl;
	out<<"S="<<num;
};
//AlterNode
AlterNode::AlterNode(vector<Node> NodeV):NodeVec(NodeV),start(Node("!null")),end(Node("!null")){}
void AlterNode::ContentOutput(ostream& out)
{
	start.ContentOutput(out);
	end.ContentOutput (out);
	vector<Node>::iterator iter;
	for(iter=NodeVec.begin();iter<NodeVec.end();iter++)
		iter->ContentOutput (out);
}
void AlterNode::NetOutput(ostream& out)
{
	out<<"  E="<<start.p->num<<endl;
	vector<Node>::iterator iter;
	for(iter=NodeVec.begin();iter<NodeVec.end();iter++)
	{
		out<<"S="<<start.p->num;
		iter->NetOutput(out);
		out<<"  E="<<end.p->num<<endl;
	}
	out<<"S="<<end.p->num;
}

//CombineNode
CombineNode::CombineNode(vector<Node> NodeV):NodeVec(NodeV){}
void CombineNode::ContentOutput(ostream& out)
{
	vector<Node>::iterator iter;
	for(iter=NodeVec.begin();iter<NodeVec.end();iter++)
		iter->ContentOutput (out);
}
void CombineNode::NetOutput(ostream& out)
{
	vector<Node>::iterator iter;
	for(iter=NodeVec.begin();iter<NodeVec.end();iter++)
		iter->NetOutput (out);
}

//OptionNode
OptionNode::OptionNode(Node no):node(no),start(Node("!null")),end(Node("!null")){}
void OptionNode::ContentOutput(ostream& out)
{
	start.ContentOutput(out);
    node.ContentOutput (out);
	end.ContentOutput (out);
}
void OptionNode::NetOutput(ostream &out) 
{
	out<<" E="<<start.p->num<<endl;
	out<<"S="<<start.p->num;
	out<<"  E="<<end.p->num<<endl;
	out<<"S="<<start.p->num;
	node.NetOutput (out);
    out<<" E="<<end.p->num<<endl;
	out<<"S="<<end.p->num;
}
//RepeatNode
RepeatNode::RepeatNode(Node no):node(no),start(Node("!null")),end(Node("!null")){}
void RepeatNode::ContentOutput(ostream& out)
{
	start.ContentOutput(out);
    node.ContentOutput (out);
	end.ContentOutput (out);
}
void RepeatNode::NetOutput(ostream &out) 
{
	out<<" E="<<start.p->num<<endl;
	out<<"S="<<end.p->num;
	out<<"  E="<<start.p->num<<endl;
	out<<"S="<<start.p->num;
	node.NetOutput (out);
    out<<" E="<<end.p->num<<endl;
	out<<"S="<<end.p->num;
}
    
Node& definedstr::createNode()
{
	string::iterator  it=store.begin();
	string::iterator  it2;
	vector<definedstr>::iterator iter;
	string temp;

	if(*it=='<')//处理<>
	{
		it2=it;
		for(;it2<store.end ();it2++)
		{
			if(*it2=='>')
			{
				for(it++;it<it2;it++)
					temp.push_back (*it);
				break;
			}
		}
	    iter=strV.begin();
		for(;iter<strV.end();iter++)
		{if((*iter).store==temp) node=iter->createNode();}
		node=Node(&RepeatNode(node));
		return node;
	}
	else if(*it=='[')//处理[]
	{
		it2=it;
		for(;it2<store.end ();it2++)
		{
			if(*it2==']')
			{
				for(it++;it<it2;it++)
					temp.push_back (*it);
				break;
			}
		}
	    iter=strV.begin();
		for(;iter<strV.end();iter++)
			if((*iter).store==temp) node=iter->createNode();
		node=Node(&OptionNode(node));
		return node;
	}
	else//处理|
	{
		vector<string> vec;
		it2=it;
		for(;it2<store.end();it2++)
		{
			if(*it2=='|') 
			{
				
				for(;it<it2;it++)
				{temp.push_back (*it);vec.push_back (temp);}
				it=it2;it++;
			}
		}
		if(vec.size()==0) goto other;
		vector<Node> vecN;
	    iter=strV.begin();
		for(int i=0;i<vec.size();i++)
		{
		    for(iter=strV.begin();iter<strV.end();iter++)
				if((*iter).store==vec[i]) vecN.push_back (iter->createNode());
		}
		node=Node(&AlterNode(vecN));
		return node;
	}
	//其他情况
	
other:
	node=Node(store);
	return node;
	


}







