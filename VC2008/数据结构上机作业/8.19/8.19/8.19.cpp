#include"Tree.h"
#include"Graphlnk.h"

template<class T,class E>
void DFS(Graphlnk<T,E>& G,int v,bool visited[],TreeNode<T,E>*& t)
{																//Assumption:array visited[] has been initialized
	TreeNode<T,E>* tmp = new TreeNode<T,E>;						//To store the information of tree node t
	tmp->data = G.getValue(v);
	visited[v] = true;
	int w = G.getFirstNeighbor(v);
	t = tmp;													//Evaluate the node t
	while(w != -1)												//Circulate until the end port
	{
		if(!visited[w])		DFS(G,w,visited,tmp->firstChild);	//Reservation Visit
		w = G.getNextNeighbor(v,w);
		if(w != -1 && !visited[w])								//If we find an unvisited sibling
		{
			TreeNode<T,E>* tmp0 = new TreeNode<T,E>;tmp0->data = G.getValue(w);
			tmp = tmp->nextSibling = tmp0;						//Construct the nextSibling node
			visited[w] = true;
			w = G.getNextNeighbor(v,w);
		}
	}
}

template<class T,class E>	void visit(TreeNode<T,E>* p)		{cout<<p->data<<'\t';}
																//Output Function

int main()
{
	Graphlnk<int,int> Gl;
	cin>>Gl;
	int cv = Gl.NumberOfVertices();
	bool* visited = new bool [cv];
	memset(visited,0,cv*sizeof(bool));
	TreeNode<int,int>* t = NULL;
	cout<<"Now we start constructing the DFS tree from the vertices 0"<<endl;
	DFS(Gl,0,visited,t);
	Tree<int,int> tr(t);										//Construct the Visiting Tree
	tr.preOrder(visit);
	delete [] visited;
	cout<<"\nEnd of Algorithm"<<endl;
	char tmp;cin>>tmp;
	return 0;
}