#ifndef GRAPHPATH_H
#define GRAPHPATH_H

#include"Graphlnk.h"

template<typename T,typename E>
void Dijkstra(Graph<T,E>& G,int v,E dist[],int path[])
{
	int n = G.NumberOfVertices();
	bool* S = new bool [n];
	int i,j;
	for(i=0;i<n;i++)
	{
		dist[i] = G.getWeight(v,i);
		S[i] = false;
		path[i] = (i != v && dist[i] < maxWeight) ? v : -1;
	}
	S[v] = true;dist[v] = maxWeight;
	for(i=0;i<n-1;i++)
	{
		int u=v;
		for(j=0;j<n;j++)
			if(!S[j] && dist[j] < dist[u])
				u = j;
		S[u] = true;
		for(j=0;j<n;j++)
		{
			E w = G.getWeight(u,j);
			if(!S[j] && w<maxWeight && dist[u]+w<dist[j])
			{
				dist[j] = dist[u] + w;
				path[j] = u;
			}
		}
	}
}

template <class T, class E>
void BellmanFord (Graph<T, E>& G, int v,E dist[], int path[])
{
	E w;  int i, k, u, n = G.NumberOfVertices();
    for (i = 0; i < n; i++)
	{
        dist[i] = G.getWeight(v, i);
        if (i != v && dist[i] < maxValue) path[i] = v;
        else path[i] = -1;
    } 
	for (k = 2; k < n; k++)
        for (u = 0; u < n; u++)
            if (u != v)
			{
                for (i = 0; i < n; i++)
				{
                    w = G.getWeight(i, u);
 					if (w > 0 && w < maxValue && dist[u] > dist[i]+w)
					{
                        dist[u] = dist[i]+w;
                        path[u] = i;
                    }
                }
			}
}

template <typename T, typename E>
void TopologicalSort(Graph<T,E>& G)	{
	int i,j,w,v;
	int top = -1;					//Initiate the stack
	int n = G.NumberOfVertices();
	int* count = new int [n];		//The stack of in-degree
	for(i=0;i<n;i++)	count[i] = 0;
	cin>>i>>j;
	while(i > -1 && i < n && j > -1 && j < n)	{
		G.inserEdge(i,j);count[j]++;
		cin>>i>>j;
	}
	for(i=0;i<n;i++)	{
		if(count[i] == 0)	{
			count[i] = top;top = i;		//Push the vertex
		}
	}
	for(i=0;i<n;i++)	{
		if(top == -1)	{
			cout<<"There exists loop(s)!"<<endl;return;}
		else	{
			v = top;top = count[top];
			cout<<G.getValue(v)<<'\t';
			w.G.getFirstNeighbor(v);
			while(w != -1)
			{
				if(--count[w] == 0)	{
					count[w] = top;top=w;
				}
				w = G.getNextNeightbor(v,w);
			}
		}
	}
}

#endif